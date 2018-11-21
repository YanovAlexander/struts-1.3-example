package ua.com.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import ua.com.struts.actions.forms.LoginForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;
import ua.com.struts.model.User;
import ua.com.struts.services.AuthyService;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;
import ua.com.struts.utils.Passwords;
import ua.com.struts.utils.SessionManager;

public class LoginAction extends Action {

    private static final Logger LOG = Logger.getLogger(LoginAction.class);

    private final AuthyService authyService;
    private final SessionManager sessionManager;

    public LoginAction() {
        this.authyService = new AuthyService();
        this.sessionManager = new SessionManager();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        LoginForm loginForm = (LoginForm) form;
        ActionErrors errors = new ActionErrors();
        //TODO handle error messages.

        try {
            AuthenticationDao authentication = new AuthenticationDaoImpl();
            User user = authentication.findUserByEmailAndPassword(loginForm.getEmail(),
                    Passwords.encryptPassword(loginForm.getPassword()));
            loginForm.setUsername(user.getUsername());
            authyService.sendVerificationRequest(user.getAuthyId());
            sessionManager.saveUser(request, user);
            return mapping.findForward(AuthenticationConstants.LOGIN_VERIFICATION);
        } catch (DatabaseException e) {
            LOG.error("execute: connection refused");
            return mapping.findForward(AuthenticationConstants.CONNECTION_REFUSED);
        } catch (Exception e) {
            LOG.error("execute: Error", e);
            return mapping.findForward(AuthenticationConstants.ERROR);
        }
    }
}
