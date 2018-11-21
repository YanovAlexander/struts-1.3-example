package ua.com.struts.utils;

import ua.com.struts.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

    private static final String USER_ID = "user-id";
    private static final String USER_AUTHY_ID = "user-authy-id";

    private static final int MAX_INACTIVE_INTERVAL = 30 * 60;

    public void saveUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_AUTHY_ID, user.getAuthyId());
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    public int getUserAuthyId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (int) session.getAttribute(USER_AUTHY_ID);
        }

        return 0;
    }

    public long getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (long) session.getAttribute(USER_ID);
        }

        return 0;
    }
}
