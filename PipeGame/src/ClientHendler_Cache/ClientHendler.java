package ClientHendler_Cache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHendler {
	
	public void  handleClient(InputStream input,OutputStream output) throws IOException;
}
