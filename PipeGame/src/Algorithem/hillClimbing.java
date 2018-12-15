package Algorithem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import Gradehill.stateGrader;
import Searchable.Searchable;
import Searcher.CommonSearcher;
import Server.State;





public class hillClimbing<T> extends CommonSearcher<T> {

    private long timeToRun;	
    private stateGrader<T> grader;


    public hillClimbing(long timeToRun, stateGrader<T> grade) {
    	this.openList=new LinkedList<State<T>>();
        this.timeToRun = timeToRun;
        this.grader=grade;
    }


    @Override
    public ArrayList<State<T>> search(Searchable<T> searchable) {
    	//Define the current state as an initial state
    	openList.add(searchable.GetIntialState());

    	long time0 = System.currentTimeMillis();
    	
    	//Loop until the goal state is achieved or no more operators can be applied on the current state:
    	while ((System.currentTimeMillis() - time0 < timeToRun) && openList.size()>0) {

    		State<T> next = popOpenList();
    		closedSet.add(next);
    		
    		if(searchable.isGoal(next)) {

    			return BackTrace(searchable.GetIntialState(), next);
    		}
    		
    		ArrayList<State<T>> neighbors = searchable.GetAllPossible(next);

    		if (Math.random() < 0.7) { // with a high probability
    			// find the best one

    			int grade = 0;
    			for (State<T> state : neighbors) {
                   // double g = state.getCost();
    				int g = grader.grade(state);
                    state.setCameFrom(next);//you forgot this line you idiot!!!!!!!
    				if (g > grade) {
    					grade = g;
    					next = state;
    					//add this step to the solution
    					//result.add
    					if(!closedSet.contains(state) && !openList.contains(state))
    					{
    						openList.add(state);
    					}
    					
    				}
    				else if(!closedSet.contains(state) && !openList.contains(state))
					{
						openList.add(state);
					}
    			}
    		}
    		
    		else 
    			next = neighbors.get(new Random().nextInt(neighbors.size()));
 
    	}

    	return null;
    }
}






//
//
//Discrete Space Hill Climbing Algorithm
//   currentNode = startNode;
//   loop do
//      L = NEIGHBORS(currentNode);
//      nextEval = -INF;
//      nextNode = NULL;
//      for all x in L 
//         if (EVAL(x) > nextEval)
//              nextNode = x;
//              nextEval = EVAL(x);
//      if nextEval <= EVAL(currentNode)
//         //Return current node since no better neighbors exist
//         return currentNode;
//      currentNode = nextNode;