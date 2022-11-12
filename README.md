# JavaWebProject-SmartPortables
<em> Deploy on your local: </em>
1. download tomcat server, mysql, mongaDB

2. save the entire file to your path :~/tomcat/webapp

3.run Tomcat server , MySql database and mongoDB in your terminal
(commands maybe different depends on your OS and environment, I use MacOS and brew)

    Tomcat server start:
        /Users/yourusername/apache-tomcat-*/bin/startup.sh
    Mysql database:
        mysql.server start
    MangoDB:
        brew services start mongodb-community@5.0 

4.Open 'src/MySqlDataStoreUtilities.java' in the folder and change database root and root password with your local mysqlDB's username and password.

5.open and run 'create_table.sql' in the folder to create tables in your loacal Mysql DB.

6.open your browser and enter "localhost:8080/AS3"

6.Now you can use the website.
