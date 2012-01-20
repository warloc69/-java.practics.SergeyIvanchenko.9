import java.rmi.*;
import java.io.*;
public interface FileServer extends Remote {
	public void open(String name) throws RemoteException,BadFileException;
	public void close() throws RemoteException,BadFileException;
	public int available() throws RemoteException,BadFileException;
	public int read() throws RemoteException,BadFileException;
	public byte[] read(int bufsize) throws RemoteException,BadFileException;
}
	