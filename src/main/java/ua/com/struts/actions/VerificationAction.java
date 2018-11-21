package ua.com.struts.actions;

import com.authy.api.Token;
import org.apache.struts.action.*;
import ua.com.struts.actions.forms.VerificationForm;
import ua.com.struts.services.AuthyService;
import ua.com.struts.utils.AuthenticationConstants;
import ua.com.struts.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerificationAction extends Action {

    private final AuthyService authyService;
    private final SessionManager sessionManager;

    public VerificationAction() {
        this.authyService = new AuthyService();
        this.sessionManager = new SessionManager();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        VerificationForm verificationForm = (VerificationForm) form;
        ActionErrors errors = new ActionErrors();

        int authyId = sessionManager.getUserAuthyId(request);
        Token token = authyService.verifyToken(authyId, verificationForm.getVerificationCode());
        if (token.isOk()) {
            return mapping.findForward(AuthenticationConstants.SUCCESS);
        } else {
            errors.add(AuthenticationConstants.VERIFICATION_ERROR_KEY, new ActionMessage(AuthenticationConstants.VERIFICATION_ERROR_KEY));
            addErrors(request, new ActionMessages(errors));
            return mapping.findForward(AuthenticationConstants.ERROR);
        }
    }
}
