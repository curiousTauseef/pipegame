package Searcher;

import java.util.ArrayList;

import Searchable.Searchable;
import Server.State;

public interface Searcher<T> {
	
	
	public ArrayList<State<T>> search(Searchable<T> searchable);
	public int getNumberOfNodesEvaluated();

	
}
