<%@ taglib uri="http://struts.apache.org/tags-html" prefix="s" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<hr/>
<s:form action="/loginVerification">
    Please enter a verification code from SMS: <s:text property="verificationCode"/><br/>
    <s:messages id="verification_error" property="verification.empty.error">
        <div style="color:red">
            <bean:write name="verification_error"/>
        </div>
    </s:messages>
    <s:submit value="verify"/>
</s:form>