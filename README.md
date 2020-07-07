# outreach-fms 

<h2>Spring Boot</h2>

<h3>First step is to configure the application</h3>

<b>Configuring application for local setup</b>

As this application is communicating with database, the first step is to setup the database.

The dump file for the dabase is kept inside the resource folder within the application. Just executing the "outreach_fms.sql" file will create the database with some master data information.
The link of the dabase dumb is given below.
<pre><a href="https://github.com/rvprasath/outreach-fms/tree/master/outreach-fms/src/main/resources">https://github.com/rvprasath/outreach-fms/tree/master/outreach-fms/src/main/resources</a></pre>

<b>Note:</b> Admin credentials are already preconfigured for easy login to the application.

<pre>
employeeId: 787958
username: admin
email: admin@test.com
password: 123
</pre>

PMO, POC and participants data will be stored by processing the excels.

<b>Setting up the Local environment in spring tool suit</b>
  
Download and install the STS. Then open the sts. From the window menu, under show view open git prespective. Then from there clone the git repository of this outreach-fms application. The git clone url is given below
  
  <pre>https://github.com/rvprasath/outreach-fms.git</pre>
  
Once done with the cloning of repository right click the git application icon and import the project to the workspace.
Then the application is available in the workspace package explorer.

<b>Configuring the application for launching in the local server</b>

some values need to be setup in the below application.properties
<pre><a href="https://github.com/rvprasath/outreach-fms/tree/master/outreach-fms/src/main/resources">application.properties</a></pre>

MySQL database configuration

<pre>spring.datasource.url=jdbc:mysql://localhost:3306/outreach-fms</pre>
The line in the above line in properties file describes the environment, port and database name. Set the database name as <b>outreach-fms</b>

<pre>
spring.datasource.username=root
spring.datasource.password=admin
</pre>

This above line has the database username and password that needs to be set accordingly, of how the database is initially configured.

<pre>
shared.file.path=D:/outreachfmsExcel
shared.file.new-path-name=excelArchive
shared.file.new-path=D:/outreachfmsExcel/excelArchive
</pre>

The above line has the local file system path where the excel files need to be uploaded and where the archive need to be backed up. Set the path accordingly.
In this case uploaded files will be intially kept inside "outreachfmsExcel" and the archive file will be save inside "excelArchive" folder which is inside "outreachfmsExcel" folder.


As the application is configured for gmail smtp, Email and password needs to be provided for sending email from the given address.

<pre>
email.properties.email-address=email@email.com
email.properties.email-password=password
</pre>

This above values must be configured with email address and password of gmail.
<b>Note:</b> Turn on enable less secure apps in gmail account setting to transport mail from within the outreachfms application.
If enable less secure apps is turned off email will not be send and produce error.

<pre>
email.properties.node-url=http://localhost:4200
</pre>
This is the most important setup just to provide the angular connectivity for accessing feedback form clicking the email link.
The above property must be the url of in which angular application will be served on calling from browser.










