package crawler.thread;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnect {
	private static int lport = 3307;
	private static String rhost = "128.199.148.119";
	private static String host = "128.199.148.119";
	private static int rport = 3306;
	private static String user ="thuandh";
	private static String password ="asdfer";
	public static Session session = null;
	
	public static void connect(){
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
			int assinged_port = session.setPortForwardingL(lport, rhost,
					rport);
			System.out.println("localhost:" + assinged_port + " -> "
					+ rhost + ":" + rport);
			System.out.println("Port Forwarded");
		} catch (Exception e) {
			System.out.println("Can not forward port");
		}
	}
	
	
public static void main(String[] args){
	try {
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		session = jsch.getSession(user, host, 22);
		session.setPassword(password);
		session.setConfig(config);
		session.connect();
		System.out.println("Connected");
		int assinged_port = session.setPortForwardingL(lport, rhost,
				rport);
		System.out.println("localhost:" + assinged_port + " -> "
				+ rhost + ":" + rport);
		System.out.println("Port Forwarded");
	} catch (Exception e) {
		System.out.println("Can not forward port");
	}
}
}
