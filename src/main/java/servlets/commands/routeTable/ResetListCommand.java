package servlets.commands.routeTable;

import DB.Cache;
import DB.Utils;
import DB.entity.Route;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ResetListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "index.jsp";

        Integer itemsPerPage = 5;

        List<Route> routeList = new ArrayList<>(Cache.getInstance().getRouteList());


        int routePageCount = Utils.pageCount(routeList,itemsPerPage);

        routeList= Utils.getPage(routeList,itemsPerPage,0);


        request.getSession().setAttribute("startFilter",null);
        request.getSession().setAttribute("destinationFilter",null);
        request.getSession().setAttribute("routeListPage",routeList);
        request.getSession().setAttribute("listSortedBy",null);
        request.getSession().setAttribute("currentRouteTablePageNumber",0);
        request.getSession().setAttribute("routeTablePageQuantity",routePageCount);
        request.getSession().setAttribute("itemsPerRoutePage",itemsPerPage);




        return address;
    }
}
