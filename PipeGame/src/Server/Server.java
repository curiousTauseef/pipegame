package Server;

import java.net.Socket;

import ClientHendler_Cache.ClientHendler;

public interface Server {
	public Socket start(ClientHendler ch);
	public void stop();

}
