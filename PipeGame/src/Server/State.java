package Server;



public class State<T> implements Comparable<State<T>> {//implements to comprable and implemnt compare to
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	public State(){
		this.state=null;
	}
	public State(T p){
		this.state=p;
		cost=0;
	}
	public T getState() {
		return state;
	}
	public void setState(T state) {
		this.state = state;
	}
	public double getCost() {
		return cost ;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
		//this.setCost(cameFrom.getCost()+1);
	}
	@Override
	public int hashCode() {
		
		return this.state.hashCode();
	}
	
	@Override
	public String toString() {
		
		return this.state.toString();
	}

	@Override
	public boolean equals(Object obj) {
		
		return this.state.equals(obj);
	}


	
	@Override
	public int compareTo(State<T> state) {
		
		return (int)(this.cost-state.cost);
	}
	}
	
