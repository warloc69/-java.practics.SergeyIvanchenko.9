import java.io.*;
public class FileLoader {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestServer.class);
	public void loadFile(String name, FileServer fs) {
		try {
			fs.open(name);
			File f = new File(name);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
			while(fs.available() != -1) {
				int i = fs.read();
				if(i==-1) 
					break;
				out.write(i);
				out.flush();
			}
			out.close();
			fs.close();
		} catch (BadFileException e) {
		  log.error("loadFile "+e.getMessage());
		} catch (IOException e) {
		    log.error(e);
		}
	}
	public void loadBuffFile(String name, FileServer fs) {
		try {
			fs.open(name);
			File f = new File(name);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
			while(fs.available() != -1) {
				byte[] i = fs.read(10);
				if(i==null) 
					break;
				out.write(i,0,i.length);
				out.flush();
			}
			out.close();
			fs.close();
		} catch (BadFileException e) {
		  log.error("loadFile "+e.getMessage());
		} catch (IOException e) {
		    log.error(e);
		}
	}
}