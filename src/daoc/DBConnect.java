package daoc;
//cd ~/Desktop && rsync -avh -e ssh News_Sport.war thuandh@128.199.148.119:/var/lib/jetty/jetty-distribution-9.2.2.v20140723/webapps/
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

public class DBConnect {
	
	private static int lport = 3307;
	private static String rhost = "128.199.148.119";
	private static String host = "128.199.148.119";
	private static int rport = 3306;
	private static String user = /*"root";*/"thuandh";
	private static String password = /*"a@2a@2";//*/"asdfer";
	private static String dbuserName = "sig";
	private static String dbpassword = "abd29310a58869782b993544e8580ff2";
	private static String url = "jdbc:mysql://localhost:" + lport + "/sig_news";
	private static String driverName = "com.mysql.jdbc.Driver";
	public static Session session = null;

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Properties properties = new Properties();
			try {
				properties.load(DBConnect.class
						.getResourceAsStream("/database.properties"));
			} catch (Exception e) {
			}
			
			Class.forName(properties.getProperty("driver", driverName))
					.newInstance();
			conn = DriverManager.getConnection(
					properties.getProperty("url",url),
					properties.getProperty("username", dbuserName),
					properties.getProperty("password", dbpassword));
			System.out.println("Database connection established");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null && !conn.isClosed()) {
			}
		}
		return conn;
	}


	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}