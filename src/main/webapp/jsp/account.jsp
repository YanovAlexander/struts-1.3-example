<%@taglib uri="http://struts.apache.org/tags-html" prefix="s" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<hr/>
<s:link action="logout">logout</s:link>
<hr/>
<h3>Account info</h3>
<b>Username: </b><bean:write name="account" property="user.username"/><br/>
<b>Email: </b><bean:write name="account" property="user.email"/><br/>
<b>Phone: </b><bean:write name="account" property="user.countryCode"/><bean:write name="account" property="user.phoneNumber"/>
