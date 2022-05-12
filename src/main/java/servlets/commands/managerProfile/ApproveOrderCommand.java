package servlets.commands.managerProfile;

import DB.Cache;
import DB.DBManager;
import servlets.commands.Command;
import servlets.commands.managerProfile.ManagerProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = Long.valueOf(request.getParameter("orderId"));
        DBManager.getInstance().modifyOrderStatus(orderId,"pending payment");

        ManagerProfileCommand managerProfileCommand = new ManagerProfileCommand();


        return managerProfileCommand.execute(request,response);
    }
}
