package Searchable;

import java.util.ArrayList;

import Server.State;

public interface Searchable<T> {
	
	public State<T> GetIntialState();//return the initial state
	public boolean isGoal(State<T> init);//check if the current state is the goal
	public ArrayList<State<T>>GetAllPossible(State<T> s);//return the state evolution
	
	
}
 	