package Algorithem;

import java.util.ArrayList;
import java.util.LinkedList;

import Searchable.Searchable;
import Searcher.CommonSearcher;
import Server.State;


public class BFS<T> extends CommonSearcher<T> {


	@Override
	public ArrayList<State<T>> search(Searchable<T> searchable) {

		this.openList=new LinkedList<State<T>>();
		openList.add(searchable.GetIntialState());
		while(!openList.isEmpty()) {
			State<T> n =popOpenList();
			closedSet.add(n);

			if(searchable.isGoal(n))
				return BackTrace(searchable.GetIntialState(), n);

			ArrayList<State<T>> successors=searchable.GetAllPossible(n);

			for(State<T> state : successors){
				if((!(closedSet.contains(state))) && (!(openList.contains(state))))
				{
					state.setCameFrom(n);
					addToOpenList(state);
				}
			}

		}
		return null;
	}
}

