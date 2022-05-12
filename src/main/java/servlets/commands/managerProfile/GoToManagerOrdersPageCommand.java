package servlets.commands.managerProfile;

import servlets.commands.Command;
import servlets.commands.clientProfile.ClientProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToManagerOrdersPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer currentPage= Integer.valueOf(request.getParameter("goToPage"));

        request.getSession().setAttribute("currentManagerOrdersPage",currentPage);
        return new ManagerProfileCommand().execute(request,response);
    }
}
