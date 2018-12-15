package Solution;

import java.util.ArrayList;

import Server.State;

public class PipeSolution implements Solution<ArrayList<State<char[][]>>> {
	private ArrayList<State<char[][]>> sulotion;
	
	public PipeSolution(ArrayList<State<char[][]>> s){ this.sulotion=s;}

	@Override
	public ArrayList<State<char[][]>> getSulotion() {
		return this.sulotion;
	}
	

//
//	char[][] goal= (char[][]) goalS.getState();
//	char[][] initial=(char[][]) intial.getState();
//	int rows = initial.length;		//rows of the initial state
//	int columns = initial[0].length; //columns of the initial state
//	
//	ArrayList<String> list=new ArrayList<>();
//	int counter=0;
//	
//	
//	for(int i=0;i<rows;i++)
//	{	for(int j=0;j<columns;j++)
//		{
//		if(initial[i][j]!=goal[i][j]) {
//			counter=commonSearcher.NumberOfRottateNeeded(initial[i][j], goal[i][j]);
//			list.add(new String(i+","+j+","+counter));
//		}
//	}
//}
//	return (Sulotion) list;
//	
	
}
