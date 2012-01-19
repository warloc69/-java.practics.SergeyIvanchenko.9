import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
public class TestServer {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestServer.class);
	public static void main(String args[]) {
	org.apache.log4j.PropertyConfigurator.configure("log\\log4j.properties");
		try { 
			FileServerImpl fs = new FileServerImpl();
			Naming.rebind("rmi://localhost/FileServer",fs);
		} catch (Exception e) {
			log.info(e);
		}
		log.info("exit");
	}
}