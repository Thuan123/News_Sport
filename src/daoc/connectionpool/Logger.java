package daoc.connectionpool;

public class Logger {
	private String classname;
	 
	 public Logger(String classname) {
	  this.classname = classname;
	 }
	 
	 public void log(String msg) {
	  System.out.println("[" + this.classname + "] " + msg);
	 }
	 
	 public void error(String msg) {
	  System.out.println("[ERROR] [" + this.classname + "] " + msg);
	 }
}
