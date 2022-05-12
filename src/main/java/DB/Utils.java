package DB;

import DB.entity.Order;
import DB.entity.Route;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public  class Utils {
    public static List getPage(List list,Integer itemsPerPage,Integer currentPage){

        int pageCount = (int) Math.ceil(list.size()/new BigDecimal(itemsPerPage).doubleValue());

        int listSize= list.size();

        int adjustedItemsPerPage = itemsPerPage;
        if(currentPage+1==pageCount){
            while (listSize>itemsPerPage){
                listSize=listSize-itemsPerPage;

            }
            if(listSize<itemsPerPage){
                adjustedItemsPerPage=listSize;
                System.out.println(adjustedItemsPerPage);
            }
        }
        List listPage = new ArrayList<>();
        if (listSize>0){

            for (int i=0;i<adjustedItemsPerPage;i++){
                listPage.add(list.get(currentPage*itemsPerPage+i));
            }
        }



        return listPage;
    }


    public static List<Route> filterRouteList (List<Route> routeList,String startFilter,String destinationFilter){


        if (startFilter!=null || destinationFilter!=null){

            List<Route> markedForRemoval=new ArrayList<>();
            for (Route r:routeList) {
                if ((startFilter != null && !r.getStart().contains(startFilter)) ||
                        (destinationFilter != null && !r.getDestination().contains(destinationFilter)))markedForRemoval.add(r);
            }
            routeList.removeAll(markedForRemoval);
        }
        return routeList;
    }

    public static List<Route> sortRouteList (List<Route> routeList,String sortedBy){

        if (Objects.equals(sortedBy, "destinationAscending")){
            routeList.sort(Comparator.comparing(Route::getDestination));
        }
        if (Objects.equals(sortedBy, "destinationDescending")){
            routeList.sort((o1, o2) -> o2.getDestination().compareTo(o1.getDestination()));
        }
        if (Objects.equals(sortedBy, "startAscending")){
            routeList.sort(Comparator.comparing(Route::getStart));
        }
        if (Objects.equals(sortedBy, "startDescending")){
            routeList.sort((o1, o2) -> o2.getStart().compareTo(o1.getStart()));
        }
         return  routeList;
    }
    public static List<Order> sortOrderList (List<Order> orderList,String sortedBy){
        if (Objects.equals(sortedBy, "statusAscending")){
            orderList.sort(Comparator.comparing(Order::getOrderStatus));
        }
        if (Objects.equals(sortedBy, "statusDescending")){
            orderList.sort((o1, o2) -> o2.getOrderStatus().compareTo(o1.getOrderStatus()));
        }
        if (Objects.equals(sortedBy, "orderPlacementTimeAscending")){
            orderList.sort(Comparator.comparing(Order::getOrderPlacementTime));
        }
        if (Objects.equals(sortedBy, "orderPlacementTimeAscending")){
            orderList.sort((o1, o2) -> o2.getOrderPlacementTime().compareTo(o1.getOrderPlacementTime()));
        }
        return orderList;
    }

    public static Integer pageCount (List list,Integer itemsPerPage){
        return (int) Math.ceil(list.size()/new BigDecimal(itemsPerPage).doubleValue());
    }

}
