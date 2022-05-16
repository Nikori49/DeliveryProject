package servlets;

import DB.entity.User;
import servlets.commands.clientProfile.ClientProfileCommand;
import servlets.commands.managerProfile.ManagerProfileCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestHttp = (HttpServletRequest) request;
        User user= (User) requestHttp.getSession().getAttribute("loggedUser");

        if (user==null){
            HttpServletResponse responseHttp = (HttpServletResponse) response;
            responseHttp.sendRedirect("index.jsp");
        }else {
            if (Objects.equals(user.getRole(), "manager")){
                ManagerProfileCommand managerProfileCommand=new ManagerProfileCommand();
                managerProfileCommand.execute((HttpServletRequest) request, (HttpServletResponse) response);
            }
            if (Objects.equals(user.getRole(), "client")){
                ClientProfileCommand clientProfileCommand = new ClientProfileCommand();
                clientProfileCommand.execute((HttpServletRequest) request, (HttpServletResponse) response);
            }
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
