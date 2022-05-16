package servlets.commands.loginBlock;

import DB.DBManager;
import DB.entity.User;
import servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "register.jsp";
        List<String> errorMessage = new ArrayList<>();

        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String phoneNumber = request.getParameter("phone_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String language = (String) request.getSession().getAttribute("language");
        if (language == null) {
            language = (String) request.getSession().getServletContext().getAttribute("language");
        }


        if (!email.matches("[a-z0-9._]+@[a-z0-9]+\\.[a-z]+")) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Not valid E-mail");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Недействительный адрес электронной почты");
                }
            }

        }
        if (!Objects.equals(DBManager.getInstance().findUserByEMail(email), null)) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("E-mail is already in use");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Адрес электронной почты уже занят");
                }
            }
        }
        if (!login.matches("[a-zA-Z0-9_]{5,16}")) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Not valid login\n(it's supposed to be 5-16 symbols long, and contain latin characters)");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Недействительное имя пользователя\n(оно должно быть 5-16 символов в длину, и состоять из латиницы)");
                }
            }
        }

        if (!Objects.equals(DBManager.getInstance().findUserByLogin(login), null)) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Login is already taken");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Имя пользователя уже занято");
                }
            }
        }
        if (!phoneNumber.matches("\\+[0-9\\-]{8,17}")) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Not valid phone number");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Недействительный номер телефона");
                }
            }
        }
        if (!Objects.equals(DBManager.getInstance().findUserByPhoneNumber(phoneNumber), null)) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Phone is already in use");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Номер телефона уже занят");
                }
            }
        }
        if (password.length() < 5) {
            switch (language) {
                case ("en"): {
                    errorMessage.add("Password must be at least 5 symbols long");
                    break;
                }
                case ("ru"): {
                    errorMessage.add("Пароль должен быть как минимум 5 символов в длину");
                }
            }
        }
        if (errorMessage.isEmpty()) {
            User user = new User(0l, email, phoneNumber, name, surname, login, password, "client");
            user = DBManager.getInstance().insertUser(user);
            address = "index.jsp";
            request.getSession().setAttribute("loggedUser", user);
        } else {
            request.setAttribute("error", errorMessage);

            try {
                request.getRequestDispatcher(address).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }


        return address;
    }
}
