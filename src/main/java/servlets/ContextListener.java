package servlets;

import DB.Cache;
import DB.Utils;
import DB.entity.Route;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebListener
public class ContextListener implements ServletContextListener {
    final Logger log = LogManager.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        String path = servletContext.getRealPath("/WEB-INF/log4j2.log");
        System.setProperty("logFile", path);


        log.debug("path" + path);

        List<Route> routeList = new ArrayList<>(Cache.getInstance().getRouteList());


        List<Route> routeListForForm = new ArrayList<>(routeList);
        List<String> cities = new ArrayList<>();
        for (Route r : routeListForForm) {
            cities.add(r.getStart());
        }
        cities = cities.stream().distinct().collect(Collectors.toList());


        int routePageCount = (int) Math.ceil(routeList.size() / 5.0);

        routeList = Utils.getPage(routeList, 5, 0);


        List<Integer> routePageNumberList = new ArrayList<>();
        for (int i = 1; i <= routePageCount; i++) {
            routePageNumberList.add(i);
        }

        servletContext.setAttribute("cityList", cities);


        servletContext.setAttribute("routeTablePageNumberList", routePageNumberList);
        servletContext.setAttribute("currentRouteTablePageNumber", 0);
        servletContext.setAttribute("routeTablePageQuantity", routePageCount);
        servletContext.setAttribute("itemsPerRoutePage", 5);
        servletContext.setAttribute("routeListPage", routeList);
        servletContext.setAttribute("todayDate", LocalDate.now());
        servletContext.setAttribute("language", "en");

        servletContext.setAttribute("routeList", Cache.getInstance().getRouteList());
        servletContext.setAttribute("tariffList", Cache.getInstance().getTariffList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
