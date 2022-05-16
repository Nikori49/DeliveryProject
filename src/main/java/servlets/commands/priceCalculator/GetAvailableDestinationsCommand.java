package servlets.commands.priceCalculator;

import DB.DBManager;
import DB.entity.Route;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetAvailableDestinationsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "index.jsp";
        String start = request.getParameter("start");
        System.out.println(start);
        List <String> availableDestinations = new ArrayList<>();
        List<Route> routeList = DBManager.getInstance().getRouteList();
        routeList.removeIf( route -> !route.getStart().equals(start));
        for (Route r:routeList) {
            availableDestinations.add(r.getDestination());
        }
        System.out.println(availableDestinations);
        request.getSession().setAttribute("selectedStart",start);
        request.getSession().setAttribute("availableDestinations",availableDestinations);
        request.getSession().setAttribute("orderPlaced",null);

        request.getSession().setAttribute("calculatorError",null);


        return address;
    }
}
