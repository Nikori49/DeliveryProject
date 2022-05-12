package servlets.commands.routeTable;

import DB.Cache;
import DB.DBManager;
import DB.Utils;
import DB.entity.Route;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class FilterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "home.jsp";


        String startFilter = request.getParameter("startFilter");
        String destinationFilter = request.getParameter("destinationFilter");
        String sortedBy = (String) request.getSession().getAttribute("listSortedBy");




        Integer itemsPerPage = (Integer) request.getSession().getAttribute("itemsPerRoutePage");
        if(itemsPerPage==null){
            itemsPerPage = (Integer) request.getServletContext().getAttribute("itemsPerRoutePage");
        }
        //Integer currentRoutePageNumber = (Integer) request.getSession().getAttribute("currentRouteTablePageNumber");


        List<Route> routeList = new ArrayList<>(Cache.getInstance().getRouteList());

        routeList=Utils.filterRouteList(routeList,startFilter,destinationFilter);


        int routePageCount = Utils.pageCount(routeList,itemsPerPage);


        routeList = Utils.sortRouteList(routeList,sortedBy);



        routeList= Utils.getPage(routeList,itemsPerPage,0);


        request.getSession().setAttribute("startFilter",startFilter);
        request.getSession().setAttribute("destinationFilter",destinationFilter);


        request.getSession().setAttribute("currentRouteTablePageNumber",0);
        request.getSession().setAttribute("routeTablePageQuantity",routePageCount);
        request.getSession().setAttribute("itemsPerRoutePage",itemsPerPage);

        request.getSession().setAttribute("routeListPage",routeList);
        return address;
    }
}
