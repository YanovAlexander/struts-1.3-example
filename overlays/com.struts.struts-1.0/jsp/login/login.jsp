<jsp:include page="../index.jsp"/>
<hr/>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="s" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<s:form action="loginProcess" >
    Username : <s:text property="email" /> <br>
    <s:messages id="username_error" property="email.error">
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
    <s:submit value="login"/>
    <s:messages id="login_error" property="login.error">
        <div style="color:red">
            <bean:write name="login_error" />
        </div>
    </s:messages>
</s:form>

