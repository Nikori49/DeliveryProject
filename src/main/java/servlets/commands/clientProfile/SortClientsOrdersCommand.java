package servlets.commands.clientProfile;

import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortClientsOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String sortedBy = request.getParameter("clientOrderListSortedBy");
        request.getSession().setAttribute("clientOrderListSortedBy",sortedBy);


        return new ClientProfileCommand().execute(request,response);
    }
}
