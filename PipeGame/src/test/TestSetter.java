package test;

// edit these imports according to your project
import ClientHendler_Cache.Cache;
import ClientHendler_Cache.ClientHendler;
import ClientHendler_Cache.SimpleCacheManager;
import ClientHendler_Cache.simpleClientHendler;
import Server.Server;
import Solver.PipeSolver;
import Server.PipeServer;
import Solver.Solver;


public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		dt.setServerClass(PipeServer.class);
		dt.setClientHandlerInterface(ClientHendler.class);
		dt.setClientHandlerClass(simpleClientHendler.class);
		dt.setCacheManagerInterface(Cache.class);
		dt.setCacheManagerClass(SimpleCacheManager.class);
		dt.setSolverInterface(Solver.class);
		dt.setSolverClass(PipeSolver.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new PipeServer(port);
		s.start(new simpleClientHendler());
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}

}
