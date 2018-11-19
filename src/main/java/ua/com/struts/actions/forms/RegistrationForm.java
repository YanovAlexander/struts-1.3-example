package ua.com.struts.actions.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.utils.Validations;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm extends ActionForm {
    private String username;
    private String password;
    private String email;

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
        this.email = null;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return Validations.registrationValidation(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
