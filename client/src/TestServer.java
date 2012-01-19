import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.*;
public class TestServer {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestServer.class);
	public static void main(String args[]) {
	org.apache.log4j.PropertyConfigurator.configure("log\\log4j.properties");
		try {
			if (System.getSecurityManager() == null) {
			System.setProperty("java.security.policy","client.policy");
            System.setSecurityManager(new RMISecurityManager());
        }
			String lookupString = "rmi://" + args[0] + ":" + args[1] + "/" + "FileServer";
            FileServer hello = (FileServer) Naming.lookup(lookupString);
            log.info("RMI object found");
			FileLoader fl = new FileLoader();
			fl.loadFile(args[2],hello);
		} catch (Exception e) {
			log.info(e);
		}
		log.info("exit");
	}
	
}