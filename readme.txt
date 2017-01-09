JAX-RS client programming examples
==================================
This archive contains the following examples:
4. a client for the negotiate service

IMPORTAT: these clients are built with Jersey 2.24, that implements JAX-RS 2.0 API.
As Jersey and JAX-RS are not included in Java SE, in order to run this example
with Java SE it is necessary to include their jars (and dependencies).
The necessary jars are included in the lib directory.
The ant script (build.xml) includes these jars in the classpath.
Alternatively, you can include the necessary jars into the JDK jre/lib/ext directory
(in this case, you can uncomment the part of the build.xml that loads libraries from
lib into the classpath)

Note: you must be connected to the internet in order to run the ISBN and GeoIP examples
successfully. Note that sometimes the services may be unavailable.
The other examples can be run locally but you need a Tomcat 8 installation.

Tomcat 8 setup
--------------
You can use the Tomcat installation available in XAMPP
Add the following code to the Tomcat configuration file "tomcat-user.xml"

  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="root" password="root" roles="manager-gui,manager-script"/>

In this way you have the possibility to access the Tomcat 8 manager via web application.
After having started Tomcat, you can browse localhost:8080 to display the main Tomcat page.
From this page you can access the management page using the credentials set into tomcat-user.xml.

Deploying the counter and negotiate services
--------------------------------------------
Before launching the counter and negotiate clients it is necessary to deploy the corresponding
services into your Tomcat 8 installation. Deployment can be done simply by copying the .war
files from the war directory in this archive to the webapps Tomcat directory.
If Tomcat has been started, it will deploy the services automatically.
You can check that the services are running by looking at the Tomcat management page or by
browsing the WADL of the services:
localhost:8080/negotiate/webapi/application.wadl for the negotiate service

Running the examples
--------------------
The examples can be compiled and run by calling the run targets in the build.xml ant script:
ant run-negotiate