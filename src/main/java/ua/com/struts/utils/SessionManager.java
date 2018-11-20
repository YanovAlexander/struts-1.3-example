package ua.com.struts.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

    public static final String USER_AUTHY_ID = "user-authy-id";

    private static final int MAX_INACTIVE_INTERVAL = 30 * 60;

    public void saveUserAuthyId(HttpServletRequest request, int authyId) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_AUTHY_ID, authyId);
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    public int getUserAuthyId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (int) session.getAttribute(USER_AUTHY_ID);
        }

        return 0;
    }
}
