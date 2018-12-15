package Problem;

import java.util.ArrayList;

import Searchable.MatrixChar;

public class PipeProblem implements Problem<MatrixChar>{
	private char[][] problem;
	
	
	public PipeProblem() {
		problem=null;
	}
	
	public PipeProblem (ArrayList<String> level){
		
		int column = level.get(0).length();
		int row= level.size()-1;
		
		char[][] p = new char[row][column];
		for(int i=0;i<row;i++) {
			String str = level.get(i);
			for(int j=0;j<column;j++)
				p[i][j]=str.charAt(j);
		}
		
		this.problem=p;
	} 

	public PipeProblem(char[][] newChar){
		problem = newChar;
		
	} 
	
	@Override
	public MatrixChar  getProblem() {
		
		return new MatrixChar(problem);
	}
}
