package ua.com.struts.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;


public class Validations {
    private static final Logger LOG = Logger.getLogger(Validations.class);

    public static ActionErrors loginValidation(String username, String password) {
        ActionErrors errors = new ActionErrors();
        checkIfEmpty(username, password, errors);

        AuthenticationDao authentication = new AuthenticationDaoImpl();
        try {
            if (errors.isEmpty() && !authentication.isUserExist(username, Passwords.encryptPassword(password))) {
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

    public static ActionErrors registrationValidation(String username, String password) {
        ActionErrors errors = new ActionErrors();
        checkIfEmpty(username, password, errors);

        if (errors.isEmpty() && !password.matches(AuthenticationConstants.PASSWORD_PATTERN)) {
            errors.add(AuthenticationConstants.PASSWORD_ERROR_KEY, new ActionMessage(AuthenticationConstants.PASSWORD_SECURITY_ERROR));
        }

        AuthenticationDao authentication = new AuthenticationDaoImpl();
        try {
            if (errors.isEmpty() && authentication.isUserExist(username)) {
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
