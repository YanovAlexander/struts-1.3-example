package ua.com.struts.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.dao.SybaseConnector;
import ua.com.struts.utils.AuthenticationConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        SybaseConnector.disconnect();
        return mapping.findForward(AuthenticationConstants.SUCCESS);
    }
}
