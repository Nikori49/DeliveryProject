package servlets.commands.routeTable;

import DB.Cache;
import DB.Utils;
import DB.entity.Route;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SetItemsPerRoutePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String startFilter = (String) request.getSession().getAttribute("startFilter");
        String destinationFilter = (String) request.getSession().getAttribute("destinationFilter");
        String sortedBy = (String) request.getSession().getAttribute("listSortedBy");

        Integer itemsPerPage = Integer.valueOf(request.getParameter("itemsPerPage"));

        List<Route> routeList = new ArrayList<>(Cache.getInstance().getRouteList());

        routeList= Utils.filterRouteList(routeList,startFilter,destinationFilter);


        int routePageCount = Utils.pageCount(routeList,itemsPerPage);


        routeList = Utils.sortRouteList(routeList,sortedBy);



        routeList= Utils.getPage(routeList,itemsPerPage,0);


        request.getSession().setAttribute("currentRouteTablePageNumber",0);
        request.getSession().setAttribute("routeTablePageQuantity",routePageCount);
        request.getSession().setAttribute("itemsPerRoutePage",itemsPerPage);

        request.getSession().setAttribute("routeListPage",routeList);
        return "index.jsp";
    }
}
