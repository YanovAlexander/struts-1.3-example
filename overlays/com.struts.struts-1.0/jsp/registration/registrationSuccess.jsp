<jsp:include page="../index.jsp"/>
<hr/>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<br/>Welcome, <bean:write name="registrationProcess" property="email"/>, you are register successfully!