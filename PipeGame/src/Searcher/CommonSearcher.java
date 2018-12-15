package Searcher;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

import Searchable.Searchable;
import Server.State;

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	
	protected Queue<State<T>> openList;
	protected HashSet<State<T>> closedSet=new HashSet<State<T>>();
	protected int evaluatedNodes=0;
	
	public CommonSearcher() {
		//openList=new PriorityQueue<State<T>>();
		evaluatedNodes=0;
	}
	
	public abstract ArrayList<State<T>> search(Searchable<T> searchable);
	
	protected State<T> popOpenList()
	{ 	
		evaluatedNodes++;
		return openList.poll();
	}

	
	
	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}
	
	
	public void addToOpenList(State<T> initial) {
		openList.add(initial);
		
	}
	
 protected ArrayList<State<T>> BackTrace(State<T> intial, State<T> goalS) {
		
		ArrayList<State<T>> list= new ArrayList<>();
		list.add(goalS);
		
		while(goalS.getCameFrom()!=null)
		{
			goalS=goalS.getCameFrom();
			list.add(goalS);
		}
		return list;
	}
	
	
	


		
	}
	
	

