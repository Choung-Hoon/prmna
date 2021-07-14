package lee.manager;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lee.common.db.JResultSet;

public class QueryMgr {
	
	public QueryMgr(){}
	
	public boolean setSQL(String sql){
        Connection conn = null;
        Statement  stmt = null;        
                
        try {
            
            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch(Exception e){
                    
            System.out.println(e.toString());
             if (conn != null){
             
                 try{ 
                 
                     conn.rollback(); 
                     
                 }catch (Exception e1){ 
                 
                 }
             }
                
             return false;
                
        } finally {
        
              if ( stmt != null ) try {stmt.close(); }catch(Exception e){} 
              if ( conn != null ) 
                    try{
                        conn.setAutoCommit(true);
                        conn.commit();
                        conn.close();
                    }catch(Exception e){} 

        }
        
        return true ;        
        
    }
    
    public boolean setSQL(Vector vt){
    
        Connection conn = null;
        Statement  stmt = null;        
        
       try {
        

            conn = getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            for(int i = 0 ;  i < vt.size() ;i++){
                
                String sql = (String)vt.elementAt(i);
                System.out.println(sql);
                stmt.executeUpdate(sql);
            }
            
        } catch(Exception e){
                
                System.out.println(e.toString());
                if (conn != null){
                    try{ 
                    
                        conn.rollback(); 
                        
                    }catch (Exception e1){ 
                    
                    }
                }
                
                return false;
        } finally {
        
              if ( stmt != null ) try {stmt.close(); }catch(Exception e){} 
              if ( conn != null ) 
                    try{
                        conn.setAutoCommit(true);
                        conn.commit();
                        conn.close();
                    }catch(Exception e){} 

        }
        
        return true ;        
        
    }    
    
    public JResultSet getSQL(String sql){
        
        Connection conn = null;
        Statement  stmt = null;
        JResultSet jrs = null;
        
        try {
        
            conn = getConnection();

            stmt = conn.createStatement();

            System.out.println(sql);
           jrs =  new JResultSet(stmt.executeQuery(sql)) ;

        } catch(Exception e){
            
                System.out.println(e.toString());

        } finally {
        
              if ( stmt != null ) try {stmt.close(); }catch(Exception e){} 
              if ( conn != null ) 
                    try{
                        conn.close();
                    }catch(Exception e){} 

        }        
        
        return jrs;
    }

    public Vector getSQL( Vector vt ){

        Vector vtRes = new Vector();
        Connection conn = null;
        Statement  stmt = null;
        JResultSet jrs = null;
       try {
        
            conn = getConnection();

            stmt = conn.createStatement();

            for(int i = 0 ;  i < vt.size() ;i++){
                
                String sql = (String)vt.elementAt(i);
                System.out.println(sql);
                jrs =  new JResultSet(stmt.executeQuery(sql)) ;
                vtRes.addElement(jrs);
            }
            
        } catch(Exception e){
            
                System.out.println(e.toString());

        } finally {
        
              if ( stmt != null ) try {stmt.close(); }catch(Exception e){} 
              if ( conn != null ) 
                    try{
                        conn.close();
                    }catch(Exception e){} 

        }        
        
        return vtRes;
    }

    private Connection getConnection(){
    
	   Connection conn = null;
    
	   try{
     	
		   	Context initCnt = new InitialContext();
		   	Context env = (Context) initCnt.lookup("java:comp/env/");
		   	DataSource ds = (DataSource) env.lookup("jdbc/jndi_lee");
		   	
	        /*
	        String DB_DRIVER = "org.gjt.mm.mysql.Driver" ;
	        String DB_URL = "jdbc:mysql://localhost:3306/hissfdb";              
	        String DB_USER = "hissf";                                      
	        String DB_PASSWORD= "wkdgkrwoeks20!!";                                   
	    	*/
	        //Class.forName(DB_DRIVER);  
	        //conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        conn = ds.getConnection();
	        
        
	   }catch(Exception e){
                
         System.out.println(e.toString());

	   }
      
        return conn ;      
   	}
}
