package servlets;

import DB.Cache;
import DB.DBManager;
import DB.Utils;
import DB.entity.Route;
import DB.entity.Tariff;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Arrival", value = "/index.jsp")
public class Arrival extends HttpServlet {
    String address = "home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(address).forward(request, response);
    }


}
