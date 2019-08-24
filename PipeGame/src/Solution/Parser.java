package Solution;

import Server.State;
import java.util.ArrayList;

public interface Parser<T> {
	
	public ArrayList<String> BackTrace(ArrayList<State<T>> state);
}
