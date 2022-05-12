package servlets.commands.clientProfile;

import DB.Cache;
import DB.DBManager;
import servlets.commands.Command;
import servlets.commands.clientProfile.ClientProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayForOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        DBManager.getInstance().modifyOrderStatus(orderId,"in progress");
        ClientProfileCommand clientProfileCommand = new ClientProfileCommand();

        return clientProfileCommand.execute(request,response);
    }
}
