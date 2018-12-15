package Server;

import ClientHendler_Cache.simpleClientHendler;

public class Main {

	public static void main(String[] args) throws Exception {
		
				simpleClientHendler ch = new simpleClientHendler();
				//int portNumber = Integer.parseInt(args[0]);
				PipeServer s = new PipeServer(6450);
				try {
					s.start(ch);
				}catch (Exception e) {
		            System.exit(1);
				}
				
			}

		

	}


