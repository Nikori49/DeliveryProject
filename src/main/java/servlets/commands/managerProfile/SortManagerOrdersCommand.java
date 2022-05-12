package servlets.commands.managerProfile;

import servlets.commands.Command;
import servlets.commands.clientProfile.ClientProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortManagerOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sortedBy = request.getParameter("managerOrderListSortedBy");
        System.out.println("sorted 1 "+ sortedBy);
        request.getSession().setAttribute("managerOrderListSortedBy",sortedBy);


        return new ManagerProfileCommand().execute(request,response);
    }
}
