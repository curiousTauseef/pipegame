package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import ClientHendler_Cache.ClientHendler;


public class PipeServer implements Server{
	private int port;
	private volatile boolean stop;
	

	public PipeServer(int port){
		this.port=port;
		
		stop=false; }

	private void runServer(ClientHendler ch) throws Exception{

		
		ServerSocket server=new ServerSocket(port);
		server.setSoTimeout(1000);

		while(!stop){ 

			try{ 

				Socket aClient=server.accept(); // blocking call try 

				try {
					ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					aClient.getInputStream().close();
					aClient.getOutputStream().close();
					aClient.close();
				} catch (IOException e) {

				//	e.printStackTrace();
				}
				
			} 
			catch (SocketTimeoutException ste) {
				//ste.printStackTrace();
			}

		}

		server.close();
	}

	@Override
	public Socket start(ClientHendler ch) {

		new Thread(()->{
			try {
				runServer(ch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		return null;
	}

	@Override
	public void stop() {
		stop=true;
	}

}
