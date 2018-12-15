package Algorithem;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Searchable.Searchable;
import Searcher.CommonSearcher;
import Server.State;

public class BestFirstSearch<T> extends CommonSearcher<T> {

	
	
	@Override
	public ArrayList<State<T>> search(Searchable<T> searchable) {
		  this.openList= new PriorityQueue<State<T>>();
		  addToOpenList(searchable.GetIntialState());
		  
		  
		while(openList.size()>0){
			
			State<T> n=popOpenList();// dequeue 
			closedSet.add(n);// remove the best node from
			
			this.closedSet.add(n);
		if((searchable.isGoal(n)))//NEED TO check if isGoal return true
			return this.BackTrace(searchable.GetIntialState(), n); // private method, back traces through the parents
		ArrayList<State<T>> successors=searchable.GetAllPossible(n); //however it is implemented 
				for(State<T> state : successors){
					if((!(closedSet.contains(state))) && (!(openList.contains(state))))
					{
						state.setCameFrom(n);
						addToOpenList(state);
					}
					else
					{
						 if(state.getCost()<n.getCost()) {
							if(!openList.contains(state))
							{
								state.setCameFrom(n);
								addToOpenList(state);
							}
								else {
									openList.remove(state);
									openList.add(state);
								}
						 }
					}
				}
			}
		return null;
	}

	
	






}
