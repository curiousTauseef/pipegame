package Algorithem;


import java.util.ArrayList;

import java.util.HashSet;

import java.util.Stack;

import Searchable.Searchable;
import Searcher.CommonSearcher;
import Server.State;

public class DFS<T> extends CommonSearcher<T> {
	HashSet<State<T>> closedSet=new HashSet<State<T>>();

	@Override
	public ArrayList<State<T>> search(Searchable<T> searchable) {

		Stack<State<T>> openList=new Stack<State<T>>();
		openList.push(searchable.GetIntialState());
		while(!openList.isEmpty()) {
			State<T> n =openList.pop();
			this.evaluatedNodes++;
			closedSet.add(n);

			if(searchable.isGoal(n)) {
				System.out.println("number of evaluted: "+this.evaluatedNodes);
				return BackTrace(searchable.GetIntialState(), n);
			}
			ArrayList<State<T>> successors=searchable.GetAllPossible(n);

			for(State<T> state : successors){
				if((!(closedSet.contains(state))) && (!(openList.contains(state))))
				{
					state.setCameFrom(n);
					openList.push(state);
				}
			}

		}
		return null;
	}
}




//function DFS(Start, Goal)
//push(Stack,Start)
//while Stack is not empty
//    var Node := Pop(Stack)
//    Color(Node, Grey)
//    if Node = Goal
//        return True
//    for Child in Expand(Node)
//        if not Colored(Child)
//           push(Stack, Child)
//    Color(Node, Black)