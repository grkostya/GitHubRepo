Env:
java 1.7, IDEA Ultimate

Precondition:

Connect to db and execute ddl/dml from ddl_dml folder.

Deploy and using:

1. Open project in IDEA;
2. Check Tomcat configuration,if it has some issues - fix it.;
4. Update database  propety connection in file jdbc.properties:

Example:

//jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
//jdbc.url=jdbc:oracle:thin:@localhost:1521:orcl
//jdbc.username=scott
//jdbc.password=scott
//jdbc.dialect = org.hibernate.dialect.Oracle10gDialect


jdbc.driverClassName=org.postgresql.Driver
jdbc.url=jdbc:postgresql://localhost:5444/edb
jdbc.username=scott
jdbc.password=scott
jdbc.dialect = org.hibernate.dialect.PostgreSQLDialect

and hibernate.cfg.xml (strange, but I coudn't find alternative way and I believe - it exists ;-) )

<property name="connection.driver_class">org.postgresql.Driver</property>
<property name="connection.url">jdbc:postgresql://localhost:5444/edb</property>
<property name="connection.username">scott</property>
<property name="connection.password">scott</property>
<property name="connection.pool_size">100</property>
<property name="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</property>
<property name="cache.provider_class">org.hibernate.cache.NoCacheProvider</property>
<property name="show_sql">true</property>



3. Deploy;
4. Go to localhost:8081 (see StartScreen.png). "Update from File" button is disabled;
5. Choose Excel File(testData/pricelist.xls) for loading data (see ChooseFileScreen.png). 
6. Press "Upload" button (see UploadScreen.png);
7. "Update from File" button is enabled,"Upload" button is disabled;
8. Press "Update from File" button (see UpdateFromFileScreen.png);
9. Data grid updated, get start screen again (see StartScreen.png);
10. Done.


