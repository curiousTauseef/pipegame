package ClientHendler_Cache;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface Cache {
	public boolean find(Integer key);
	public void save(Integer key,ArrayList<String> sol);
	public ArrayList<String> load(Integer key) throws FileNotFoundException;


}
