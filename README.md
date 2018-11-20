**1.** Install IBM WebSphere from official site.(Create profile, delete default enterprise applications in WebSphere administrative console)<br>
**2.** Install Sybase ASE.<br>
**3.** Configure db.properties file in **resources/db.properties**. Set HOST and PORT for Sybase. <br>
**4.** Run 01-db.sql to create database table. <br>
**5.** If certificate not trusted by Websphere: <br>
Configured the properties as below in the WebSphere<br>
Select Servers > Application Servers > server_name > Process Definition > Java Virtual Machine > Custom Properties > New.<br>
a) javax.net.ssl.trustStore = jre_install_dir\lib\security\cacerts<br>
Example: C:\Program Files\WebSphere\AppServer\java\jre\lib\security\cacerts<br>
b) javax.net.ssl.trustStorePassword = changeit (default)<br>
c) javax.net.ssl.trustStoreType = jks<br>