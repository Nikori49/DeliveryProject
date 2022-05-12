package servlets.commands.priceCalculator;

import DB.Cache;
import DB.DBManager;
import DB.entity.Order;
import DB.entity.Route;
import DB.entity.Tariff;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculateExpectedPriceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "index.jsp";
        List<String> errorList=new ArrayList<>();
        String start = (String) request.getSession().getAttribute("selectedStart");

        String destination = request.getParameter("destination");

        String language = (String) request.getSession().getAttribute("language");
        if (language == null) {
            language = (String) request.getSession().getServletContext().getAttribute("language");
        }


        List<Route> routeList =DBManager.getInstance().getRouteList();
        Route route = new Route();
        for (Route r:routeList) {
            if(Objects.equals(r.getStart(), start) && Objects.equals(r.getDestination(), destination)){
                route=r;
            }
        }
        int cargoMass = 0;
        int routeLength = route.getLength();
        double cargoHeight = 0.0;
        double cargoLength = 0.0;
        double cargoWidth = 0.0;


        String cargoMassString = request.getParameter("expectedMass");
        if (!cargoMassString.matches("[0-9]+")){
            switch (language) {
                case ("en"): {
                    errorList.add("Not valid mass");
                    break;
                }
                case ("ru"): {
                    errorList.add("Недопустимое значение веса груза.");
                }
            }
        }else {
            cargoMass = Integer.parseInt(cargoMassString);
        }

        String cargoHeightString = request.getParameter("expectedHeight");
        if(!cargoHeightString.matches("[0-9]+[.,]?[0-9]*")){
            switch (language) {
                case ("en"): {
                    errorList.add("Not valid height.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Недопустимое значение веса груза.");
                }
            }
        }else {
            cargoHeight = Double.parseDouble(cargoHeightString);
        }

        String cargoLengthString = request.getParameter("expectedLength");
        if(!cargoLengthString.matches("[0-9]+[.,]?[0-9]*")){
            switch (language) {
                case ("en"): {
                    errorList.add("Not valid length.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Недопустимое значение длины груза.");
                }
            }
        }else {
            cargoLength = Double.parseDouble(cargoLengthString);
        }

        String cargoWidthString = request.getParameter("expectedWidth");
        if(!cargoWidthString.matches("[0-9]+[.,]?[0-9]*")){
            switch (language) {
                case ("en"): {
                    errorList.add("Not valid width.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Недопустимое значение ширины груза.");
                }
            }
        }else {
            cargoWidth = Double.parseDouble(cargoWidthString);
        }

        double cargoVolume = cargoHeight*cargoLength*cargoWidth;

        double highestPossibleHeight=0.0;
        double highestPossibleLength=0.0;
        double highestPossibleWidth=0.0;
        int highestPossibleMass=0;
        List<Tariff> tariffList = Cache.getInstance().getTariffList();
        for (Tariff t:tariffList) {
            if(t.getCargoHoldHeight()>highestPossibleHeight)highestPossibleHeight=t.getCargoHoldHeight();
            if(t.getCargoHoldHeight()>highestPossibleLength)highestPossibleLength=t.getCargoHoldLength();
            if(t.getCargoHoldHeight()>highestPossibleWidth)highestPossibleWidth=t.getCargoHoldWidth();
            if(t.getCargoMassCap()>highestPossibleMass)highestPossibleMass=t.getCargoMassCap();
        }



        if(cargoMass>highestPossibleMass){
            switch (language) {
                case ("en"): {
                    errorList.add("Sorry, we can't provide vehicle capable of transporting cargo that heavy.");
                    break;
                }
                case ("ru"): {
                    errorList.add("К сожалению мы не можем предоставить транспорт для настолько тяжелого груза.");
                }
            }
        }
        System.out.println(highestPossibleHeight+" "+cargoHeight);
        System.out.println(highestPossibleLength+" "+cargoLength);
        System.out.println(highestPossibleWidth+" "+cargoWidth);

        if(highestPossibleHeight<cargoHeight || highestPossibleLength<cargoLength ||highestPossibleWidth<cargoWidth){
            switch (language) {
                case ("en"): {
                    errorList.add("Sorry, we can't provide vehicle capable of transporting cargo that large");
                    break;
                }
                case ("ru"): {
                    errorList.add("К сожалению мы не можем предоставить транспорт для настолько объемного груза.");
                }
            }
        }
        if(errorList.isEmpty()){
            List<Tariff> suitableTariffs =new ArrayList<>();
            for (Tariff t:tariffList) {
                if(t.getCargoHoldHeight()*t.getCargoHoldLength()*t.getCargoHoldWidth()>cargoVolume && t.getCargoMassCap()>cargoMass){
                    suitableTariffs.add(t);
                }
            }
            System.out.println(suitableTariffs);
            Tariff bestValueTariff= new Tariff();
            int lowestPrice = 100;
            int range=0;
            if(routeLength>=0 && routeLength<150)range=1;
            if(routeLength>=150 && routeLength<500)range=2;
            if(routeLength>=500)range=3;

            for (Tariff t:suitableTariffs) {
                if(t.getPricePerKm()<lowestPrice && ((t.getName().contains("Ближний") && range==1)
                        ||(t.getName().contains("Средний") && range==2)||
                        (t.getName().contains("Дальний") && range==3)) )
                {
                    lowestPrice = t.getPricePerKm();
                    bestValueTariff=t;
                }
            }

            Integer expectedPrice = bestValueTariff.getPricePerKm()*routeLength;


            request.getSession().setAttribute("expectedPrice",expectedPrice);
            request.getSession().setAttribute("bestValueTariff",bestValueTariff);
            request.getSession().setAttribute("selectedStart",null);
            request.getSession().setAttribute("availableDestinations",null);

                Order possibleOrder = new Order();
                possibleOrder.setTariffId(bestValueTariff.getId());
                possibleOrder.setRouteId(route.getId());
                possibleOrder.setCargoMass(cargoMass);
                request.getSession().setAttribute("possibleOrder",possibleOrder);


        }
        request.getSession().setAttribute("calculatorError",errorList);

        return address;
    }
}
