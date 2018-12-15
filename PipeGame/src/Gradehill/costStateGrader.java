package Gradehill;

import Searchable.MatrixChar;
import Server.State;

public class costStateGrader implements stateGrader<MatrixChar> {
	
	
	
	@Override
	public int grade(State<MatrixChar> state) {
		int temp =	state.getState().getMatrix().length*state.getState().getMatrix()[0].length+5;
		

		return (int) (temp-state.getCost());
	}
}
