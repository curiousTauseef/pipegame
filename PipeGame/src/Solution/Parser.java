package Solution;

import Server.State;

public interface Parser<T,E> {
	
	public E BackTrace(State<T> state, State<T> goal);
}
