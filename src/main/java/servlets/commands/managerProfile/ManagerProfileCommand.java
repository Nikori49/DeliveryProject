package servlets.commands.managerProfile;

import DB.Cache;
import DB.DBManager;
import DB.Utils;
import DB.entity.Order;
import DB.entity.Route;
import DB.entity.User;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ManagerProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orderList = DBManager.getInstance().getAllOrders();
        List<Order> ordersPendingApproval = new ArrayList<>();
        //List<Order> ordersPendingPayment = new ArrayList<>();
        List<Order> ordersInProgress = new ArrayList<>();
        List<User> userList = DBManager.getInstance().getAllClients();
        Integer currentPage = (Integer) request.getSession().getAttribute("currentManagerOrdersPage");
        if(currentPage==null){
            currentPage = 0;
        }
        String sortedBy = (String) request.getSession().getAttribute("managerOrderListSortedBy");



        for (Order o:orderList) {
            if(Objects.equals(o.getOrderStatus(), "pending approval")){
                ordersPendingApproval.add(o);
            }
           /* if(Objects.equals(o.getOrderStatus(), "pending payment")){
               ordersPendingPayment.add(o);
            }*/
            if(Objects.equals(o.getOrderStatus(), "in progress")){
                ordersInProgress.add(o);
            }

        }

        if (sortedBy!=null){

            Utils.sortOrderList(orderList, sortedBy);
        }
        System.out.println("sorted 2 "+ sortedBy);



        orderList.removeIf(order -> Objects.equals(order.getOrderStatus(), "pending approval") || Objects.equals(order.getOrderStatus(), "in progress"));
        request.getSession().setAttribute("userList",userList);


        Integer pageCount = Utils.pageCount(orderList,10);
        orderList= Utils.getPage(orderList,10,currentPage);

        request.getSession().setAttribute("allOrders",orderList);

        request.getSession().setAttribute("managerOrdersPageCount",pageCount);

        request.getSession().setAttribute("currentManagerOrdersPage",currentPage);

        request.getSession().setAttribute("managerOrderListSortedBy",sortedBy);




        if (ordersPendingApproval.isEmpty()){
            request.getSession().setAttribute("ordersPendingApproval",null);
        }else {
            request.getSession().setAttribute("ordersPendingApproval",ordersPendingApproval);
        }



        //request.getSession().setAttribute("ordersPendingPayment",ordersPendingPayment);
        if (ordersInProgress.isEmpty()){
            request.getSession().setAttribute("ordersInProgress",null);
        }else {
            request.getSession().setAttribute("ordersInProgress",ordersInProgress);
        }




        return "managerProfile.jsp";
    }
}
