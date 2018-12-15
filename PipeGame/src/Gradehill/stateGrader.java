package Gradehill;

import Server.State;

public interface stateGrader<T> {

	int grade(State<T> s);
}
