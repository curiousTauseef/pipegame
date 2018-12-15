package Server;


import java.io.*;
import java.net.*;

public class testMain {



	private static Socket s;

	public static void main(String[] args) throws IOException {


		String hostName = "127.0.0.1";
		int portNumber = 6403;

		int counter=1;

		PrintWriter writer = null ;
		
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\sean assis\\Desktop\\patam\\results.txt",true)),true);

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		for(;counter<2;counter++) {
			s = new Socket(hostName,portNumber);
			System.out.println("conected to server");
			PrintWriter pw =new PrintWriter(s.getOutputStream(),true);
			BufferedReader fromserver = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String filename ="level"+String.valueOf(counter)+".txt";
			BufferedReader fromfile = new BufferedReader(new FileReader("C:\\Users\\sean assis\\Desktop\\patam\\"+filename));//change this to the location of your files
			String line =new String();
			System.out.println("sending : " +filename);
			while(!line.equals("done")) {
				line = fromfile.readLine();
				pw.println(line);
				System.out.println(line);
			}
			line ="SOMETHING";
			fromfile.close();
			writer.println(">=============================<");
			writer.println(filename);
			writer.println();
			System.out.println("reciving : " +filename);
			while(!(line.equals("done"))) {
				line =fromserver.readLine();
				writer.println(line);
			}
			writer.println(">=============================<");
		}


	}


}