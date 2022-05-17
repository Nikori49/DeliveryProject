package filters;

import DB.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestHttp = (HttpServletRequest) request;
        User user= (User) requestHttp.getSession().getAttribute("loggedUser");

        if (user==null){
            chain.doFilter(request,response);
        }else {
            HttpServletResponse responseHttp = (HttpServletResponse) response;
            responseHttp.sendRedirect("profile.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
