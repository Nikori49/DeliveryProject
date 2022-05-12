package servlets.commands.loginBlock;

import servlets.commands.Command;
import servlets.commands.clientProfile.ClientProfileCommand;
import servlets.commands.managerProfile.ManagerProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String selectedLanguage=request.getParameter("changeLanguageTo");
        request.getSession().setAttribute("language",selectedLanguage);
        String returnTo = request.getParameter("returnTo");
        if (Objects.equals(returnTo, "managerProfile.jsp")){
            ManagerProfileCommand managerProfileCommand = new ManagerProfileCommand();
            managerProfileCommand.execute(request,response);
        }
        if (Objects.equals(returnTo, "clientProfile.jsp")){
            ClientProfileCommand clientProfileCommand = new ClientProfileCommand();
            clientProfileCommand.execute(request,response);
        }

        return returnTo;


    }
}
