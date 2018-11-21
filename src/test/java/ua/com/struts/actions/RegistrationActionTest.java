package ua.com.struts.actions;

import servletunit.struts.MockStrutsTestCase;

public class RegistrationActionTest extends MockStrutsTestCase {

    public void testEmptyUsernameTest() {
        setRequestPathInfo("/registrationProcess");
        addRequestParameter("username", "");
        addRequestParameter("password", "Password1#");
        addRequestParameter("email", "someemail@some.com");
        actionPerform();
        verifyActionErrors(new String[]{"username.empty.error"});
    }
}
