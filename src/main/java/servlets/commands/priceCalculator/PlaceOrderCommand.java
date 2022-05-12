package servlets.commands.priceCalculator;

import DB.DBManager;
import DB.entity.Order;
import DB.entity.User;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Order currentOrder =(Order) request.getSession().getAttribute("possibleOrder");
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        List<String> errorList= new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        currentOrder.setOrderPlacementTime(Timestamp.valueOf(now));

        String language = (String) request.getSession().getAttribute("language");
        if (language == null) {
            language = (String) request.getSession().getServletContext().getAttribute("language");
        }



        String deliveryAddress=request.getParameter("deliveryAddress");
        if (deliveryAddress.isEmpty()){
            switch (language) {
                case ("en"): {
                    errorList.add("Delivery address cannot be empty.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Адрес доставки не может быть пустым");
                }
            }
        }else {
            currentOrder.setDeliveryAddress(deliveryAddress);
        }

        String cargoName=request.getParameter("cargoName");
        if (cargoName.isEmpty()){
            switch (language) {
                case ("en"): {
                    errorList.add("Cargo name cannot be empty.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Наименование груза не может быть пустым");
                }
            }
        }else {
            currentOrder.setCargoName(cargoName);
        }


        String pickUpDateString = request.getParameter("pickUpDate");
        System.out.println("string"+pickUpDateString);
        Date pickUpDate = null;
        if (pickUpDateString.isEmpty()){
            switch (language) {
                case ("en"): {
                    errorList.add("Pick up date cannot be empty.");
                    break;
                }
                case ("ru"): {
                    errorList.add("Дата подбора не может быть пустой");
                }
            }
        }else {
            pickUpDate = Date.valueOf(pickUpDateString);
            currentOrder.setPickUpDate(pickUpDate);
        }

        if (errorList.isEmpty()){
            currentOrder.setOrderStatus("pending approval");

            int daysToTravel = DBManager.getInstance().getRouteById(currentOrder.getRouteId()).getLength();
            daysToTravel=Math.floorDiv(daysToTravel,(75*24));
            currentOrder.setDateOfArrival(Date.valueOf(pickUpDate.toLocalDate().plusDays(daysToTravel)));

            User currentUser = (User) request.getSession().getAttribute("loggedUser");
            currentOrder.setUserId(currentUser.getId());

            currentOrder = DBManager.getInstance().insertOrder(currentOrder);

            request.getSession().setAttribute("possibleOrder",currentOrder);

            request.getSession().setAttribute("orderPlaced",currentOrder);

        }

        request.getSession().setAttribute("calculatorError",errorList);





        return "home.jsp";
    }
}
