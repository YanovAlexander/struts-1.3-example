package ua.com.struts.actions.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.utils.Validations;

import javax.servlet.http.HttpServletRequest;

public class VerificationForm extends ActionForm {

    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return Validations.validateVerificationCode(this);
    }
}
