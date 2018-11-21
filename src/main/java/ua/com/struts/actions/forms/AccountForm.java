package ua.com.struts.actions.forms;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.model.User;

import javax.servlet.http.HttpServletRequest;

public class AccountForm extends ActionForm {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        user = null;
    }
}
