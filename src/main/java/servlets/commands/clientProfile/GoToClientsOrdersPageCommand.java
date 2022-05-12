package servlets.commands.clientProfile;

import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToClientsOrdersPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer currentPage= Integer.valueOf(request.getParameter("goToPage"));

        request.getSession().setAttribute("currentClientsOrdersPage",currentPage);
        return new ClientProfileCommand().execute(request,response);
    }
}
