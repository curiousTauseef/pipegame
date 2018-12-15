package Solution;

import java.util.ArrayList;

import Searchable.MatrixChar;
import Server.State;

public class sulotionParser   {
	

public ArrayList<String> BackTrace(ArrayList<State<MatrixChar>> backTrace) {
	

	State<MatrixChar> goalState=backTrace.get(0);
	State<MatrixChar> initialState=backTrace.get(backTrace.size()-1);
	
	char[][] initial=initialState.getState().getMatrix();
	char[][] goal=goalState.getState().getMatrix();
	
	int rows = initial.length;		//rows of the initial state
	int columns = initial[0].length; //columns of the initial state
	
	ArrayList<String> list=new ArrayList<>();
	int counter=0;
	
	
	for(int i=0;i<rows;i++)
	{	for(int j=0;j<columns;j++)
		{
		if(initial[i][j]!=goal[i][j]) {
			counter= NumberOfRottateNeeded(initial[i][j], goal[i][j]);
			list.add(new String(i+","+j+","+counter));
		}
	}
}
	list.add("done");
	ArrayList<String> sulotion= new ArrayList<>(list);
	return sulotion;

}



public int NumberOfRottateNeeded(char current,char needed) {
	int counter=0;
	while(current != needed )
	{
		if(current=='|')
			current ='-';
		else if(current=='-')
			current = '|';
		else if(current== 'F')
			current = '7';
		else if(current =='7')
			current = 'J';
		else if(current =='J')
			current = 'L';
		else if(current =='L')
			current = 'F';
		counter++;
	}
	return counter;
}

}
