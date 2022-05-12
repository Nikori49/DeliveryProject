package tags;

import DB.DBManager;
import DB.entity.User;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ContactSupportTag extends TagSupport {
    public int doStartTag(){
        JspWriter out = pageContext.getOut();
        try{
            List<User> userList =DBManager.getInstance().getAllUsers();
            userList.removeIf(user -> !Objects.equals(user.getRole(), "manager"));
            List<String> managerPhoneList = new ArrayList<>();
            for (User user:userList) {
                managerPhoneList.add(user.getPhoneNumber());
            }
            for (String string:managerPhoneList) {
                out.print(string);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
