package ua.com.struts.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.actions.forms.LoginForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;
import ua.com.struts.utils.Passwords;

public class LoginAction extends Action {
    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        LoginForm loginForm = (LoginForm) form;

        try {
            AuthenticationDao authentication = new AuthenticationDaoImpl();
            authentication.isUserExist(loginForm.getUsername(), Passwords.encryptPassword(loginForm.getPassword()));
        } catch (DatabaseException e) {
            LOG.error("execute: connection refused");
            return mapping.findForward(AuthenticationConstants.CONNECTION_REFUSED);
        } catch (Exception e) {
            LOG.error("execute: Error", e);
            return mapping.findForward(AuthenticationConstants.ERROR);
        }

        return mapping.findForward(AuthenticationConstants.SUCCESS);
    }
}
