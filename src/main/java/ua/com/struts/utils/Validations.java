package ua.com.struts.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import ua.com.struts.actions.forms.LoginForm;
import ua.com.struts.actions.forms.RegistrationForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;


public class Validations {
    private static final Logger LOG = Logger.getLogger(Validations.class);

    public static ActionErrors loginValidation(LoginForm loginForm) {
        ActionErrors errors = new ActionErrors();
        checkIfEmpty(loginForm.getUsername(), loginForm.getPassword(), errors);

        AuthenticationDao authentication = new AuthenticationDaoImpl();
        try {
            if (errors.isEmpty() && !authentication.isUserExist(loginForm.getUsername(), Passwords.encryptPassword(loginForm.getPassword()))) {
                errors.add(AuthenticationConstants.LOGIN_ERROR, new ActionMessage(AuthenticationConstants.USER_NOT_EXISTS));
            }
        } catch (DatabaseException e) {
            LOG.error("loginValidation: connection refused");
            errors.add(AuthenticationConstants.LOGIN_ERROR, new ActionMessage(AuthenticationConstants.CONNECTION_REFUSED));
        } catch (Exception e) {
            LOG.error("loginValidation: Error", e);
            errors.add(AuthenticationConstants.LOGIN_ERROR, new ActionMessage(AuthenticationConstants.LOGIN_ERROR));
        }

        return errors;
    }

    public static ActionErrors registrationValidation(RegistrationForm registrationForm) {
        ActionErrors errors = new ActionErrors();
        checkIfEmpty(registrationForm.getUsername(), registrationForm.getPassword(), errors);

        if (StringUtils.isEmpty(registrationForm.getEmail())) {
            errors.add(AuthenticationConstants.EMAIL_ERROR_KEY, new ActionMessage(AuthenticationConstants.EMAIL_EMPTY_ERROR));
        }

        if (errors.isEmpty() && !registrationForm.getPassword().matches(AuthenticationConstants.PASSWORD_PATTERN)) {
            errors.add(AuthenticationConstants.PASSWORD_ERROR_KEY, new ActionMessage(AuthenticationConstants.PASSWORD_SECURITY_ERROR));
        }

        if (errors.isEmpty() && !registrationForm.getEmail().matches(AuthenticationConstants.EMAIL_PATTERN)) {
            errors.add(AuthenticationConstants.EMAIL_ERROR_KEY, new ActionMessage(AuthenticationConstants.EMAIL_NOT_CORRECT));
        }

        AuthenticationDao authentication = new AuthenticationDaoImpl();
        try {
            if (errors.isEmpty() && authentication.isUserExist(registrationForm.getUsername())) {
                errors.add(AuthenticationConstants.USERNAME_ERROR_KEY, new ActionMessage(AuthenticationConstants.USER_EXISTS));
            }
        } catch (DatabaseException e) {
            LOG.error("registrationValidation: connection refused");
            errors.add(AuthenticationConstants.REGISTRATION_ERROR, new ActionMessage(AuthenticationConstants.CONNECTION_REFUSED));
        } catch (Exception e) {
            LOG.error("registrationValidation: Error", e);
            errors.add(AuthenticationConstants.REGISTRATION_ERROR, new ActionMessage(AuthenticationConstants.REGISTRATION_ERROR));
        }
        return errors;
    }

    private static void checkIfEmpty(String username, String password, ActionErrors errors) {
        if (StringUtils.isEmpty(username)) {
            errors.add(AuthenticationConstants.USERNAME_ERROR_KEY, new ActionMessage(AuthenticationConstants.USERNAME_EMPTY_ERROR));
        }
        if (StringUtils.isEmpty(password)) {
            errors.add(AuthenticationConstants.PASSWORD_ERROR_KEY, new ActionMessage(AuthenticationConstants.PASSWORD_EMPTY_ERROR));
        }
    }
}
