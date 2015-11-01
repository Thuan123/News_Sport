package daoc.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import com.mysql.jdbc.CallableStatement;

import daoc.DBConnect;

public class DBPool {
	private static Logger logger = new Logger("DBPool"); // biến logger của lớp Logger trên
    public static LinkedList pool = new LinkedList(); // pool để chứa các connections
    public final static int MAX_CONNECTIONS = 100;  // số connection tối đa trong pool là 10, tuỳ ý!!
    public final static int INI_CONNECTIONS = 2; // số connection khi bắt đầu khởi tạo là 2
    
    
    public static String url = "jdbc:mysql://node28425-env-4706287.jelastic.skali.net/sig_news";
	public static String dbName = "sig_news";
	public static String driver = "com.mysql.jdbc.Driver";
	private static String dbuserName = "root";
	private static String dbpassword = "AFFcxy11791";
    
	/*
	 * Thiet lap mot connection toi database
	 * 
	 */
	
    public static Connection makeDBConnection() throws SQLException {
        Connection conn = null;
        try {
        	Properties properties = new Properties();
			try {
				properties.load(DBPool.class
						.getResourceAsStream("/database.properties"));
			} catch (Exception e) {
			}
            Class.forName(properties.getProperty("driver", driver)).newInstance();
            conn = DriverManager.getConnection(
					properties.getProperty("url",url),
					properties.getProperty("username", dbuserName),
					properties.getProperty("password", dbpassword));
             
            if(conn!=null){
            	System.out.println("Thanh cong");
            }else{
            	System.out.println("That bai");
            }
            
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }catch (Exception e) {
      }
        return conn;
    }
    
    /*
     * Khoi tao connection pool
     */
    
    public static void build(int number) {
        logger.log("Establishing " + number + " connections...");
        Connection conn = null;
        release();
        for (int i = 0; i < number; i++) {
            try {
                conn = makeDBConnection();
            } catch (SQLException ex) {
                logger.log("Error: " + ex.getMessage());                
            }
            if (conn != null) {
                pool.addLast(conn);
            }
        }
        
        logger.log("Number of connection: " + pool.size());
    }
    /*
     * Release connection pool
     */
    public static void release() {
        logger.log("Closing connections in pool...");
        synchronized (pool) {
            for (Iterator it = pool.iterator(); it.hasNext(); ) {
                Connection conn = (Connection) it.next();
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    logger.error(
                        "release: Cannot close connection! (maybe closed?)");
                }
            }
            pool.clear();
        }
        logger.log("Release connection OK");
    }
    
    
    /*
     * Lay ra mot connection
     * 
     */
 public static Connection getConnection() {
        Connection conn = null;
        try{
         synchronized (pool) {
             conn = (java.sql.Connection) pool.removeFirst();
         }
         if (conn == null) {
          conn = makeDBConnection();
         }
         try {
             conn.setAutoCommit(true);
          } catch (Exception ex) {}
          
         } catch(Exception e){            
            logger.error("Method getConnection(): Error executing >>>" + e.toString());
            try {
             logger.log("Make connection again.");
             conn = makeDBConnection();
             conn.setAutoCommit(true);
       } catch (SQLException e1) {
       }
       logger.log(""+conn);
       }
        return conn;
  }
  /*
   * Dua mot connection tro lai pool
   * 
   */
    
 public static void putConnection(java.sql.Connection conn) {
     try { // Ignore closed connection
         if (conn == null || conn.isClosed()) {
             logger.log("putConnection: conn is null or closed: " + conn);
             return;
         }
         if (pool.size() >= MAX_CONNECTIONS) {
             conn.close();
             return;
         }
     } catch (SQLException ex) {}

     synchronized (pool) {
         pool.addLast(conn);
         pool.notify();
     }
 }
 
 
 
//phương thức close một connection và preparedStatement
public static void releaseConnection(Connection conn, PreparedStatement preStmt) {       
     putConnection(conn);
     try {
         if (preStmt != null) {
             preStmt.close();
         }
     } catch (SQLException e) {}
 }

 public static void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
     releaseConnection(conn, preStmt);
     try {
         if (rs != null) {
             rs.close();
         }
     } catch (SQLException e) {}
 }

 public static void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
     releaseConnection(conn, preStmt, rs);
     try {
         if (stmt != null) {
             stmt.close();
         }
     } catch (SQLException e) {}
 }
 public static void releaseConnection(Connection conn, CallableStatement cs, ResultSet rs) {
  putConnection(conn);
     try {
         if (cs != null) {
          cs.close();
         }
         if(rs!=null){
          rs.close();
         }
     } catch (SQLException e) {}
 }
  
}
