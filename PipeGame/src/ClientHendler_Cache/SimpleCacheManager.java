package ClientHendler_Cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleCacheManager implements Cache {
	
	private  static HashMap<Integer ,ArrayList<String>> cacheSet;
	private static SimpleCacheManager c;
	private SimpleCacheManager() {}//cacheSet=new HashMap<Integer,ArrayList<String>>(); }
	public static SimpleCacheManager getInstance() {
		if(c==null)
			{ c=new SimpleCacheManager();
			  cacheSet =new HashMap<Integer ,ArrayList<String>>();
			  
			}
		return c;
	}
	
	public boolean find(Integer key) {
		
		if(cacheSet.containsKey(key))
				return true;
		else
			return false;
		
	}
	
	public void save(Integer key,ArrayList<String> sol) {
		cacheSet.put(key, sol);
		File file= new File(key+".txt");//להעיף את הנתיב
		try {
			@SuppressWarnings("resource")
			PrintWriter write = new PrintWriter(file);
			write.println(key);
			write.flush();
			for(int i=0;i<sol.size();i++) {
				write.println(sol.get(i));
				write.flush();
			}
			write.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	
	public ArrayList<String> load(Integer key) throws FileNotFoundException {
		
		if(find(key)) {
			return cacheSet.get(key);
		}
		
			
			File file= new File(key+".txt");
			
			if(file.exists())
			{			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			ArrayList<String> sol=new ArrayList<>();
			try {
				str = br.readLine();
				
				while(!(str.equals("done"))) {
					sol.add(str);
					str= br.readLine();
				}
				sol.add("done");
				return sol;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		else
		{
			return null;
			// need to go to the solver and ask for soultion and save
		}
		
		
		
	}
}
