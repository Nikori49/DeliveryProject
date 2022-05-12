package servlets.commands.routeTable;

import DB.Cache;
import DB.Utils;
import DB.entity.Route;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SortRouteTableCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String address = "home.jsp";

        String startFilter = (String) request.getSession().getAttribute("startFilter");
        String destinationFilter = (String) request.getSession().getAttribute("destinationFilter");
        String sortedBy =  request.getParameter("listSortedBy");


        Integer itemsPerPage = (Integer) request.getSession().getAttribute("itemsPerRoutePage");
        if (itemsPerPage==null){
            itemsPerPage= (Integer) request.getServletContext().getAttribute("itemsPerRoutePage");
        }

        Integer currentRoutePageNumber = (Integer) request.getSession().getAttribute("currentRouteTablePageNumber");
        if (currentRoutePageNumber==null){
            currentRoutePageNumber= (Integer) request.getServletContext().getAttribute("currentRouteTablePageNumber");
        }


        List<Route> routeList = new ArrayList<>(Cache.getInstance().getRouteList());

        routeList= Utils.filterRouteList(routeList,startFilter,destinationFilter);



       // int routePageCount = Utils.pageCount(routeList,itemsPerPage);

       Utils.sortRouteList(routeList,sortedBy);

        routeList= Utils.getPage(routeList,itemsPerPage,currentRoutePageNumber);

        request.getSession().setAttribute("routeListPage",routeList);
        request.getSession().setAttribute("listSortedBy",sortedBy);

        request.getSession().setAttribute("currentRouteTablePageNumber",currentRoutePageNumber);

        //request.getSession().setAttribute("routeTablePageQuantity",routePageCount);
        //request.getSession().setAttribute("itemsPerRoutePage",itemsPerPage);




        return address;





    }
}
