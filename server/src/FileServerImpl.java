import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
public class FileServerImpl extends UnicastRemoteObject implements FileServer, Serializable {
	private BufferedInputStream file = null;
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestServer.class);
	public static final  long serialVersionUID = 1l;
	boolean opened = false;
	public FileServerImpl() throws RemoteException
    {
		super();
    }
	public void open(String name) throws RemoteException,BadFileException {
		try {
			File f = new File(name);
			if ( f.exists()) {
				file = new BufferedInputStream(new FileInputStream(f));
				opened = true;
			} else {
				throw new BadFileException("file not exists");
			}
		} catch (IOException e) {
			log.error(e);
			throw new BadFileException("io error", e);
		} 
	}
	public void close() throws RemoteException,BadFileException {
		try {
			if (opened) {
				file.close();
				opened = false;
			} else {
				throw new BadFileException("file not opened");
			}
		} catch (IOException e) {
			log.error(e);
			throw new BadFileException("io error", e);
		}
	}
	public int available() throws RemoteException,BadFileException {
		int i = -1;
		try {
			if (opened) {
				i = file.available();
			} else {
				throw new BadFileException("file not opened");
			}
		} catch (IOException e) {
			log.error(e);
			throw new BadFileException("io error", e);
		}
		return i;
	}
	public int read() throws RemoteException,BadFileException {
		int bt = -1;
		try {
			if (opened) {
				bt = file.read();
			} else {
				throw new BadFileException("file not opened");
			}
		} catch (IOException e) {
			log.error(e);
			throw new BadFileException("io error", e);
		}
		return bt;
	}
	public byte[] read(int bufsize) throws RemoteException,BadFileException {
		byte [] bts = new byte[bufsize];
		try {
			if (opened) {
				for (int i = 0; i < bufsize; i++) { 
					int bt = file.read();
					if (bt != -1) {
						bts[i] = (byte) bt;
					} else {
						if (i==0) {
							return null;
						}
						if ((i < (bufsize-1))) {
							byte[] temp = new byte[i];
							System.arraycopy(bts,0,temp,0,i);
							return temp;
						}
					}
				}
			} else {
				throw new BadFileException("file not opened");
			}
			return bts;
		} catch (IOException e) {
			log.error(e);
			throw new BadFileException("io error", e);
		}
	}
}