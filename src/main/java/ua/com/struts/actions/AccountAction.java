package ua.com.struts.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ua.com.struts.actions.forms.AccountForm;
import ua.com.struts.dao.AuthenticationDao;
import ua.com.struts.dao.impl.AuthenticationDaoImpl;
import ua.com.struts.model.User;
import ua.com.struts.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountAction extends Action {

    private final AuthenticationDao dao;
    private final SessionManager sessionManager;

    public AccountAction() {
        this.dao = new AuthenticationDaoImpl();
        this.sessionManager = new SessionManager();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        long userId = sessionManager.getUserId(request);
        User user = dao.findUserById(userId);

        AccountForm accountForm = (AccountForm) form;
        accountForm.setUser(user);

        return mapping.getInputForward();
    }
}
