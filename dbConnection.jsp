<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.sql.*"%>
<%@ page import="lee.manager.queryMgr"%>
<%@ page import="lee.common.db.JResultSet"%>

<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<body>
<%
	// Notice, do not import org.gjt.mm.mysql.*
	// or you will have problems!
    String DB_DRIVER = "com.mysql.jdbc.Driver" ; //type2 driver
    String DB_URL = "jdbc:mysql://localhost:3306/leehouse";	//type2 driver 
    String DB_USER = "root";                                      
    String DB_PASSWORD= "dragon";  
	
	try {
		// The newInstance() call is a work around for some
		// broken Java implementations
    
		Class.forName(DB_DRIVER).newInstance(); 
		Logger.info("=========== successful to load driver. ============ ");
	}
	catch (Exception E) {
		Logger.info("=========== Unable to load driver. ==================");
		E.printStackTrace();
	}
    
    //JResultSet jrs = null ;   
    Connection conn = null ;
    Statement stmt = null ;
    ResultSet jrs = null ;
    
	try {
		
        Logger.info("=====> DB 커넥션 연결시도");
        for (Enumeration e = DriverManager.getDrivers() ; e.hasMoreElements() ;) {
                             Logger.info("=====> Driver : " + e.nextElement() + " <=========== " );
             //DriverManager.println((String)e.nextElement());
        }
        Logger.info("=====> DB 내용 확인 끝");
		      	
      	//JSqlManager jManager = new JSqlManager();
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        
        // Create Oracle DatabaseMetaData object         
        DatabaseMetaData  meta = conn.getMetaData ();
                                                         
        // get driver info:
        Logger.info("JDBC driver version is " + meta.getDriverVersion()); 	  
        Logger.info("JDBC getDriverMajorVersion version is " + meta.getJDBCMajorVersion()); 	
        Logger.info("JDBC getDriverMinorVersion version is " + meta.getDriverMinorVersion());   
       	
       	//Do something with the Connection
       	jrs = new queryMgr().getSQL( "SELECT * from lee_admin" );
  		
        String strId = null;
		String strName = null;
		String strAge = null;
		String ADT_SERNO = "";
           	
           	out.println( "<table border='1'>" );
           	out.println( "<tr><td>행번</td><td>이름</td></tr>" );
           	
           	while( jrs.next() ){
           		ADT_SERNO = jrs.getString("ADT_SERNO");
           		strName = jrs.getString("CLERK_NM").trim();
           		 
         		out.println( "<tr>" );  	
         		out.println( "<td>" + strId + "</td>" );
         		out.println( "<td>" + strName + "</td>" );
         		out.println( "</tr>" );
           	}
			
		out.println( "</table> ");
           	
	}
	catch (Exception E) {
		Logger.info("SQLException: " + E.getMessage());
	}finally
	{
		try{
			stmt.close();
			conn.close();
		}catch(SQLException e){}	
	}
	
	
%>	
</body>	
</html>
	
	
	
	
	
	
	
	
	

