package Solver;

import java.util.ArrayList;

import Problem.PipeProblem;
import Searchable.MatrixChar;
import Searchable.PipeSearchable;
import Searcher.Searcher;
import Server.State;

public class PipeSolver implements Solver<MatrixChar,ArrayList<State<MatrixChar>>>{

	Searcher <MatrixChar> searcher;
	
	
	public PipeSolver(Searcher <MatrixChar> s) {
		this.searcher=s;
	}

	
	

	@Override
	public ArrayList<State<MatrixChar>> solve(MatrixChar p) {
	
	return searcher.search(new PipeSearchable(new PipeProblem(p.getMatrix())));
		
		
		
	}
	
}