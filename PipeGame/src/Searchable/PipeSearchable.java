package Searchable;


import java.util.ArrayList;
import java.util.HashSet;

import Problem.PipeProblem;
import Server.State;

public class PipeSearchable implements Searchable<MatrixChar>{


	private State<MatrixChar> initial;
	private ArrayList<State<MatrixChar>> possible;
	private HashSet<State<MatrixChar>> states;
	private int counter;



	public PipeSearchable (PipeProblem p) { //need to insert all possible states in the state array
		initial=new State<MatrixChar>(p.getProblem());
		this.states = new HashSet<>();
		this.states.add(initial);
		counter =initial.getState().matrix.length*initial.getState().matrix[0].length+5;
	}

	public void setInitial(State<MatrixChar> init) { //need to insert all possible states in the state array
		this.initial = init;

	}

	public char rottete(char ch) {
		if(ch=='|')
			return '-';
		else if(ch=='-')
			return '|';
		else if(ch== 'F')
			return '7';
		else if(ch=='7')
			return 'J';
		else if(ch=='J')
			return 'L';
		else if(ch=='L')
			return 'F';

		return ' ';

	}

	//change the state to new state with one diffrent char(ch) in place (row,col)
	public MatrixChar changeOneState (MatrixChar state,int row,int col,char ch) {


		char[][] newState=new char[state.getMatrix().length][state.getMatrix()[0].length];

		for(int i=0;i<state.getMatrix().length;i++) {
			for(int j=0;j<state.getMatrix()[0].length;j++) {
				newState[i][j]=state.getMatrix()[i][j];
			}
		}
		newState[row][col]=ch;
		MatrixChar matrix= new MatrixChar(newState);
		return matrix;

	}

	//check every cell in the recived state do the rottete if needed and save in new
	//arrayList and return the arrayList
	@Override
	public ArrayList<State<MatrixChar>> GetAllPossible(State<MatrixChar> s){
		MatrixChar state=s.getState();
		ArrayList<State<MatrixChar>> list=new ArrayList<State<MatrixChar>>();
		int rotate=0;
		for(int i=0;i<state.getMatrix().length;i++) {
			for(int j=0;j<state.getMatrix()[0].length;j++) {
				if(state.getMatrix()[i][j]==' ' || state.getMatrix()[i][j]=='g'||state.getMatrix()[i][j]=='s')
					rotate=0;
				else if(state.getMatrix()[i][j]=='|'||state.getMatrix()[i][j]=='-')
					rotate=2;
				else
					rotate=4;
				for(int r=0;r<rotate;r++) {
					char ch =rottete(state.getMatrix()[i][j]);
					if(ch != ' ')
					{
						MatrixChar newMatrix = changeOneState(state,i,j,ch);
						State<MatrixChar> sta= new State<MatrixChar> (newMatrix);
						state=state.cloneMatrix(newMatrix);
						
						for(int i2=0;i2<state.getMatrix().length;i2++) {
							for(int j2=0;j2<state.getMatrix()[0].length;j2++) {
								
								System.out.print(state.getMatrix()[i2][j2]);
							}
							System.out.println();
						}
						System.out.println();
						
						if(!states.contains(sta)) {
							if(tryToReach(sta, i, j)) {
								list.add(sta);
								states.add(sta);
							}
						}
					}
				}
			}
		}

		return list;
	}

	public String findS (MatrixChar m) {
		char[][] matrix=m.getMatrix();

		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				if(matrix[i][j]=='s')
					return new String(i+","+j);
			}
		}
		return null;
	}

	public ArrayList<String> SpossibleDirection (MatrixChar m, int row,int col){
		char[][] matrix=m.getMatrix();
		int matrixRow=matrix.length;
		int matrixCol=matrix[0].length;
		ArrayList<String> dir=new ArrayList<>();

		if(row+1<=matrixRow)
			dir.add("down");
		if(row-1>=0)
			dir.add("up");
		if(col+1<=matrixCol)
			dir.add("right");
		if(col-1>=0)
			dir.add("left");
		return dir;
	}
	/*
	public int tryToReach(ArrayList<String> list,MatrixChar matrix,int current ) {

		char[][] initial=matrix.getMatrix();


		String cameFromDirection = list.get(0);
		String location = list.get(1);
		String pipe = list.get(2);
		int x= new Integer(location.substring(0, location.indexOf(',')));
		int y= new Integer(location.substring(location.indexOf(',')+1,location.length()));
		int rows = initial.length;
		int columns = initial[0].length;


		///////////////////UP//////////////	
		///////////////////UP//////////////
		if(cameFromDirection.equals("up"))
		{	
			if (pipe.equals("L")) {
				if(y+1>=columns) //out of boundary
					return 0;
				else {
					y++;//Updating y parameter0
					current++;
					cameFromDirection=ChangeDirection("up",pipe);//Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return (1000000);
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					} 
				}
			}
			else if (pipe.equals("J")) {
				if(y-1<0) //out of boundary
					return 0;
				else {

					y--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("up",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}


			else if (pipe.equals("|")) {
				if((x+1)>=rows) //out of boundary
					return 0;
				else {
					x++;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("up",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}
			else if(pipe.equals("F")) 
				return current;

			else if (pipe.equals("7"))
				return current;

			else if (pipe.equals("-"))
				return current;
			else
				return current;
		}


		///////////////////DOWN//////////////////
		///////////////////DOWN/////////////////
		else if(cameFromDirection.equals("down"))
		{
			if(pipe.equals("F"))
			{
				if(y+1>=columns) //out of boundary
					return 0;
				else
				{
					y++;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}
			else if (pipe.equals("7")) {
				if(y-1<0) //out of boundary
					return 0;
				else {

					y--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}

			else if(pipe.equals("|"))
			{
				if(x-1<0) //out of boundary
					return 0;
				else {
					x--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}
			else if (pipe.equals("J")) 
				return current;
			else if (pipe.equals("L")) 
				return current;
			else if (pipe.equals("-"))
				return current;
			else
				return current;
		}

		////////////////////LEFT/////////////////
		////////////////////LEFT/////////////////
		else if(cameFromDirection.equals("left"))
		{
			if (pipe.equals("J")) {
				if(x-1<0) //out of boundary
					return 0;
				else {

					x--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}

			}

			else if (pipe.equals("7")) {
				if(x+1>=rows) //out of boundary
					return 0;
				else {

					x++;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}
			else if (pipe.equals("-"))

				if(y+1>=columns) //out of boundary
					return 0;
				else {
					y++;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}

			else if (pipe.equals("L"))
				return current;

			else if (pipe.equals("|"))
				return current;

			else
				return current;
		}


		////////////////RIGHT///////////////////////
		////////////////RIGHT///////////////////////
		else if (cameFromDirection.equals("right"))//the cameFromDirection is right
		{

			if (pipe.equals("-")){

				if(y-1<0) //out of boundary
					return 0;
				else {
					y--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}
			else if (pipe.equals("F")) {
				if(x+1>=rows) //out of boundary
					return 0;
				else {

					x++;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}

			}

			else if (pipe.equals("L")) {

				if(x-1<0) //out of boundary
					return 0;
				else {

					x--;//Updating y parameter
					current++;
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
						return 1000000;
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return tryToReach(list,new MatrixChar(initial),current);
					}
				}
			}



			else if (pipe.equals("7"))
				return current;

			else if (pipe.equals("J"))
				return current;

			else if (pipe.equals("|"))
				return current;

			else
				return current;
		}

		else
			return current;
	}
	 */


	@Override
	public State<MatrixChar> GetIntialState() {
		return initial;
	}

	public ArrayList<State<MatrixChar>> GetPossible(){
		return possible;
	}

	public  String ChangeDirection(String direction,String pipe) {


		if(direction.equals("right")) {
			if(pipe.equals("L")) {
				return "down";
			}
			else if(pipe.equals("F")) {
				return "up";
			}
			else if(pipe.equals("-")) {
				return "right";
			}
			else
				return " ";

		}
		else if(direction.equals("left")) {
			if(pipe.equals("J")) {
				return "down";
			}
			else if(pipe.equals("7")) {
				return "up";
			}
			else if(pipe.equals("-")) {
				return "left";
			}
			else
				return " ";
		}
		else if(direction.equals("up")) {
			if(pipe.equals("J")) {
				return "right";
			}
			else if(pipe.equals("L")) {
				return "left";
			}
			else if(pipe.equals("|")) {
				return "up";
			}
			else
				return " ";
		}
		else //(direction.equals("down")) {}
		{if(pipe.equals("F")) {
			return "left";
		}
		else if(pipe.equals("7")) {
			return "right";
		}
		else if(pipe.equals("|")) {
			return "down";
		}
		else
			return " ";
		}


	}


	public boolean tryToReach (State<MatrixChar> init,int x,int y) {

		ArrayList<String> list= new ArrayList<>();


		if(init.getState().matrix[x][y] =='L') {
			if(x-1>=0) {
				list.add(0, "down");
				list.add(1, (x-1)+","+y);
				list.add(2, new Character(init.getState().matrix[x-1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(y+1<init.getState().getMatrix()[0].length) {
				list.clear();
				list.add(0, "left");
				list.add(1, x+","+(y+1));
				list.add(2, new Character(init.getState().matrix[x][y+1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		if(init.getState().matrix[x][y] =='7') {
			if(x+1<init.getState().getMatrix().length) {
				list.add(0, "up");
				list.add(1, (x+1)+","+y);
				list.add(2, new Character(init.getState().matrix[x+1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(y-1>=0) {
				list.clear();
				list.add(0, "right");
				list.add(1, x+","+(y-1));
				list.add(2, new Character(init.getState().matrix[x][y-1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		if(init.getState().matrix[x][y] =='J') {
			if(x-1>=0) {
				list.add(0, "down");
				list.add(1, (x-1)+","+y);
				list.add(2, new Character(init.getState().matrix[x-1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(y-1>=0) {
				list.clear();
				list.add(0, "right");
				list.add(1, x+","+(y-1));
				list.add(2, new Character(init.getState().matrix[x][y-1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		if(init.getState().matrix[x][y] =='F') {
			if(x+1<init.getState().getMatrix().length) {
				list.add(0, "up");
				list.add(1, (x+1)+","+y);
				list.add(2, new Character(init.getState().matrix[x+1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(y+1>init.getState().getMatrix()[0].length) {
				list.clear();
				list.add(0, "left");
				list.add(1, x+","+(y+1));
				list.add(2, new Character(init.getState().matrix[x][y+1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		if(init.getState().matrix[x][y] =='|') {
			if(x+1<init.getState().getMatrix().length) {
				list.add(0, "up");
				list.add(1, (x+1)+","+y);
				list.add(2, new Character(init.getState().matrix[x+1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(x-1>=0) {
				list.clear();
				list.add(0, "down");
				list.add(1, (x-1)+","+(y));
				list.add(2, new Character(init.getState().matrix[x-1][y]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		if(init.getState().matrix[x][y] =='-') {
			if(y+1<init.getState().getMatrix()[0].length) {
				list.add(0, "left");
				list.add(1, x+","+(y+1));
				list.add(2, new Character(init.getState().matrix[x][y+1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
			if(y-1>=0) {
				list.clear();
				list.add(0, "right");
				list.add(1, x+","+(y-1));
				list.add(2, new Character(init.getState().matrix[x][y-1]).toString());

				if(reachGoal(list, init, counter))
					return true;
			}
		}

		return false;
	}

	public boolean isGoal(State<MatrixChar> init) {

		char[][] initial=init.getState().getMatrix();
		int rows = initial.length;		//rows of the initial state
		int columns = initial[0].length; //columns of the initial state
		int xS=-1;
		int yS=-1;
		boolean flag=false;
		ArrayList<String> list=new ArrayList<>();

		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {

				if(initial[i][j]=='s') {
					xS=i;
					yS=j;
					break;
				}
			}
		}
		if(xS-1>-1)
		{

			list.add(0,"down");
			list.add(1,  (xS-1)+","+yS);
			list.add(2, new String(new Character(initial[xS-1][yS]).toString()));
			flag=reachGoal(list,init,counter);
			if(flag==true)
				return flag;
			else {

				list.clear();
			}
		}

		if(xS+1<rows && flag==false)
		{

			list.add(0,"up");
			list.add(1,  (xS+1)+","+yS);
			list.add(2, new String(new Character(initial[xS+1][yS]).toString()));
			flag=reachGoal(list,init,counter);
			if(flag==true)
				return flag;
			else {

				list.clear();
			}
		}
		if(yS-1>-1 && flag==false)
		{

			list.add(0,"right");
			list.add(1,  xS+","+(yS-1));
			list.add(2, new String(new Character(initial[xS][yS-1]).toString()));
			flag=reachGoal(list,init,counter);
			if(flag==true)
				return flag;
			else {
				list.clear();
			}
		}
		if(yS+1<columns && flag==false)
		{
			list.add(0,"left");
			list.add(1,  xS+","+(yS+1));
			list.add(2, new String(new Character(initial[xS][yS+1]).toString()));
			flag=reachGoal(list,init,counter);
			if(flag==true)
				return flag;
			else {
				list.clear();
			}
		}

		return flag;
	}



	//recevied an array in the format of [cameFromDirection,toLocation(x,y),pipe type] and the current satate
	public boolean reachGoal (ArrayList<String> list,State<MatrixChar> matrix,int count) {

		char[][] initial=matrix.getState().getMatrix();


		String cameFromDirection = list.get(0);
		String location = list.get(1);
		String pipe = list.get(2);
		int x= new Integer(location.substring(0, location.indexOf(',')));
		int y= new Integer(location.substring(location.indexOf(',')+1,location.length()));
		int rows = initial.length;
		int columns = initial[0].length;

		if(pipe.equals("g")) {
			matrix.setCost(count);
			return true;
		}
		if(count ==0) {
			return false;
		}
		///////////////////UP//////////////	
		///////////////////UP//////////////
		if(cameFromDirection.equals("up"))
		{	
			if (pipe.equals("L")) {
				if(y+1>=columns) //out of boundary
					return false;
				else {
					y++;//Updating y parameter0
					cameFromDirection=ChangeDirection("up",pipe);//Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g")) {
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					} 
				}
			}
			else if (pipe.equals("J")) {
				if(y-1<0) //out of boundary
					return false;
				else {

					y--;//Updating y parameter
					cameFromDirection=ChangeDirection("up",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}


			else if (pipe.equals("|")) {
				if((x+1)>=rows) //out of boundary
					return false;
				else {
					x++;//Updating y parameter
					cameFromDirection=ChangeDirection("up",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}
			else if(pipe.equals("F")) 
				return false;

			else if (pipe.equals("7"))
				return false;

			else if (pipe.equals("-"))
				return false;
			else
				return false;
		}


		///////////////////DOWN//////////////////
		///////////////////DOWN/////////////////
		else if(cameFromDirection.equals("down"))
		{
			if(pipe.equals("F"))
			{
				if(y+1>=columns) //out of boundary
					return false;
				else
				{
					y++;//Updating y parameter
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}
			else if (pipe.equals("7")) {
				if(y-1<0) //out of boundary
					return false;
				else {

					y--;//Updating y parameter
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}

			else if(pipe.equals("|"))
			{
				if(x-1<0) //out of boundary
					return false;
				else {
					x--;//Updating y parameter
					cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}
			else if (pipe.equals("J")) 
				return false;
			else if (pipe.equals("L")) 
				return false;
			else if (pipe.equals("-"))
				return false;
			else
				return false;
		}

		////////////////////LEFT/////////////////
		////////////////////LEFT/////////////////
		else if(cameFromDirection.equals("left"))
		{
			if (pipe.equals("J")) {
				if(x-1<0) //out of boundary
					return false;
				else {

					x--;//Updating y parameter
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}

			}

			else if (pipe.equals("7")) {
				if(x+1>=rows) //out of boundary
					return false;
				else {

					x++;//Updating y parameter
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}
			else if (pipe.equals("-"))

				if(y+1>=columns) //out of boundary
					return false;
				else {
					y++;//Updating y parameter
					cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}

			else if (pipe.equals("L"))
				return false;

			else if (pipe.equals("|"))
				return false;

			else
				return false;
		}


		////////////////RIGHT///////////////////////
		////////////////RIGHT///////////////////////
		else if (cameFromDirection.equals("right"))//the cameFromDirection is right
		{

			if (pipe.equals("-")){

				if(y-1<0) //out of boundary
					return false;
				else {
					y--;//Updating y parameter
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}
			else if (pipe.equals("F")) {
				if(x+1>=rows) //out of boundary
					return false;
				else {

					x++;//Updating y parameter
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}

			}

			else if (pipe.equals("L")) {

				if(x-1<0) //out of boundary
					return false;
				else {

					x--;//Updating y parameter
					cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place

					if(pipe.equals("g"))
					{
						matrix.setCost(count);
						return true;
					}
					else {
						list.clear();//clear the old list
						list.add(0, cameFromDirection);
						list.add(1, x+","+y);
						list.add(2,pipe);
						return reachGoal(list, matrix ,count-1);
					}
				}
			}



			else if (pipe.equals("7"))
				return false;

			else if (pipe.equals("J"))
				return false;

			else if (pipe.equals("|"))
				return false;

			else
				return false;
		}

		else
			return false;
	}



}
