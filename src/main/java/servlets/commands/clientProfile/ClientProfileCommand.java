package servlets.commands.clientProfile;

import DB.Cache;
import DB.DBManager;
import DB.Utils;
import DB.entity.Order;
import DB.entity.User;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User loggedUser =(User) request.getSession().getAttribute("loggedUser");

        Long userId = loggedUser.getId();
        List<Order> clientOrderList = DBManager.getInstance().getUsersOrders(userId);
        List<Order> clientAwaitingPaymentOrderList=new ArrayList<>();
        Integer currentPage = (Integer) request.getSession().getAttribute("currentClientsOrdersPage");
        if(currentPage==null){
            currentPage = 0;
        }
        String sortedBy = (String) request.getSession().getAttribute("clientOrderListSortedBy");



        if (clientOrderList.isEmpty()){
            request.setAttribute("clientsOrders",null);
        }else {

            for (Order o:clientOrderList) {
                if (Objects.equals(o.getOrderStatus(), "pending payment")){
                    clientAwaitingPaymentOrderList.add(o);
                }
            }
            if(clientAwaitingPaymentOrderList.isEmpty()){
                request.setAttribute("clientsOrdersAwaitingPayment",null);
            }else {
                request.setAttribute("clientsOrdersAwaitingPayment",clientAwaitingPaymentOrderList);
            }
            if (sortedBy!=null){

                Utils.sortOrderList(clientOrderList, sortedBy);
            }

            Integer pageCount = Utils.pageCount(clientOrderList,10);
            clientOrderList = Utils.getPage(clientOrderList,10,currentPage);

            request.setAttribute("clientsOrders",clientOrderList);
            request.getSession().setAttribute("clientsOrdersPageCount",pageCount);

            request.getSession().setAttribute("currentClientsOrdersPage",currentPage);

            request.getSession().setAttribute("clientOrderListSortedBy",sortedBy);

        }


        return "clientProfile.jsp";
    }
}
