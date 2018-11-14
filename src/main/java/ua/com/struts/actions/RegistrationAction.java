package ua.com.struts.actions;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.actions.forms.RegistrationForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;
import ua.com.struts.utils.Passwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationAction extends Action {
    private static final Logger LOG = Logger.getLogger(RegistrationAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        RegistrationForm registration = (RegistrationForm) form;
        AuthenticationDao authenticationDao = new AuthenticationDaoImpl();
        try {
            authenticationDao.saveUser(registration.getUsername(), Passwords.encryptPassword(registration.getPassword()));
            return mapping.findForward(AuthenticationConstants.SUCCESS);
        } catch (DatabaseException e) {
            LOG.error("registration: connection refused");
            return mapping.findForward(AuthenticationConstants.CONNECTION_REFUSED);
        } catch (Exception e) {
            LOG.error("registration: Error", e);
        }

        return mapping.findForward(AuthenticationConstants.ERROR);
    }
}
