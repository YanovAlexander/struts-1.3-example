package ua.com.struts.actions;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import ua.com.struts.actions.forms.RegistrationForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;
import ua.com.struts.services.MailProvider;
import ua.com.struts.services.impl.MailProviderImpl;
import ua.com.struts.service.AuthyService;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.DatabaseException;
import ua.com.struts.utils.Passwords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationAction extends Action {
    private static final Logger LOG = Logger.getLogger(RegistrationAction.class);
    private final AuthyService authyService;

    public RegistrationAction() {
        this.authyService = new AuthyService();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        ActionErrors errors = new ActionErrors();
        RegistrationForm registration = (RegistrationForm) form;
        MailProvider mailProvider = new MailProviderImpl();
        mailProvider.sendAuthorizationMail(registration.getUsername(), "yanov.alexander@gmail.com");
        AuthenticationDao authenticationDao = new AuthenticationDaoImpl();

        try {
            int authyId = authyService.sendRegistrationRequest(registration.getEmail(), registration.getPhoneNumber(),
                    registration.getCountryCode());
            mailProvider.sendAuthorizationMail(registration.getUsername(), registration.getPassword());
            authenticationDao.saveUser(registration.getUsername(), Passwords.encryptPassword(registration.getPassword()),
                    registration.getEmail(), registration.getCountryCode(), registration.getPhoneNumber(), authyId);
            return mapping.findForward(AuthenticationConstants.LOGIN);
        } catch (DatabaseException e) {
            LOG.error("registration: connection refused");
            errors.add(AuthenticationConstants.REGISTRATION_ERROR, new ActionMessage(AuthenticationConstants.CONNECTION_REFUSED));
            addErrors(request, new ActionMessages(errors));
            return mapping.findForward(AuthenticationConstants.ERROR);
        } catch (Exception e) {
            LOG.error("registration: Error", e);
            errors.add(AuthenticationConstants.REGISTRATION_ERROR, new ActionMessage(AuthenticationConstants.REGISTRATION_ERROR));
            addErrors(request, new ActionMessages(errors));
        }

        return mapping.findForward(AuthenticationConstants.ERROR);
    }
}
