package servlets.commands.loginBlock;

import DB.DBManager;
import DB.entity.User;
import servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String address = "index.jsp";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        byte[] digest;
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            digest = md.digest();

            for( int i=0; i<digest.length; i++ ) {
                byte b = digest[ i ];
                String hex = Integer.toHexString((int) 0x00FF & b);
                if (hex.length() == 1)
                {
                    sb.append("0");
                }
                sb.append(hex);
            }
        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }


        String passHash=sb.toString();
        System.out.println("login ==> " + login);
        StringBuilder errorMessage = new StringBuilder();
        User user = DBManager.getInstance().findUserByLogin(login);

        System.out.println("user ==> " + user);
        if (user != null && user.getPassword().equals(passHash)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);


        }
        if (user==null || !user.getPassword().equals(passHash)){
            System.out.println("login error");
            errorMessage.append("No such login password pair.");
            request.setAttribute("loginError",errorMessage.toString());
        }
        return address;

    }
}
