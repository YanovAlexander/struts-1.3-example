<jsp:include page="loggedIn.jsp"/>
<hr/>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<br/>Welcome, <bean:write name="loginProcess" property="email"/>