<jsp:include page="../index.jsp"/>
<hr/>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="s" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<s:form action="registrationProcess">
    Username : <s:text property="username" /> <br>
    <s:messages id="username_error" property="username.error">
        <div style="color:red">
            <bean:write name="username_error" />
        </div>
    </s:messages>
    Password : <s:password property="password"/> <br>
    <s:messages id="password_error" property="password.error">
        <div style="color:red">
            <bean:write name="password_error" />
        </div>
    </s:messages>
    Email : <s:text property="email"/> <br>
    <s:messages id="email_error" property="email.error">
        <div style="color:red">
            <bean:write name="email_error" />
        </div>
    </s:messages>
    <s:submit value="register"/>
    <s:messages id="registration_error" property="registration.error">
        <div style="color:red">
            <bean:write name="registration_error" />
        </div>
    </s:messages>
</s:form>
