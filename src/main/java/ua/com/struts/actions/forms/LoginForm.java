package ua.com.struts.actions.forms;

import org.apache.struts.action.*;
import ua.com.struts.utils.Validations;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ActionForm {
    private String username = null;
    private String password = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.username = null;
        this.password = null;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return Validations.loginValidation(this);
    }
}
