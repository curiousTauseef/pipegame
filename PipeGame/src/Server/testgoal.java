package Server;

import java.io.IOException;
import java.util.ArrayList;

public class testgoal {

	public static String ChangeDirection(String direction,String pipe) {
		
		
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

 	public static boolean isGoal(char[][] initial) {
		
		
		
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
				flag=reachGoal(list,initial);
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
				flag=reachGoal(list,initial);
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
				flag=reachGoal(list,initial);
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
				flag=reachGoal(list,initial);
				if(flag==true)
					return flag;
				else {
					list.clear();
				}
			}
			
			return flag;
		}
		
		
	
	//recevied an array in the format of [cameFromDirection,toLocation(x,y),pipe type] and the current satate
	public static boolean reachGoal (ArrayList<String> list,char[][] initial) {
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
				 if(y+1>columns) //out of boundary
					 return false;
				 else {
					 y++;//Updating y parameter0
					 cameFromDirection=ChangeDirection("up",pipe);//Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
					 	 }
				 }
			}
				
			
			else if (pipe.equals("|")) {
				 if((x+1)>rows) //out of boundary
					 return false;
				 else {
					 x++;//Updating y parameter
					 cameFromDirection=ChangeDirection("up",pipe); //Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
				if(y+1>columns) //out of boundary
					return false;
				else
				{
					 y++;//Updating y parameter
					 cameFromDirection=ChangeDirection("down",pipe); //Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
					 	 }
				 }
				
			}
				
			else if (pipe.equals("7")) {
				 if(x+1>rows) //out of boundary
					 return false;
				 else {
		
					 x++;//Updating y parameter
					 cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
					 	 }
				 }
			}
			else if (pipe.equals("-"))

				 if(y+1>columns) //out of boundary
					 return false;
				 else {
					 y++;//Updating y parameter
					 cameFromDirection=ChangeDirection("left",pipe); //Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
					 }
				 }
			}
			else if (pipe.equals("F")) {
				 if(x+1>rows) //out of boundary
					 return false;
				 else {
		
					 x++;//Updating y parameter
					 cameFromDirection=ChangeDirection("right",pipe); //Updating the direction according to the pipe
					 pipe=new String(new Character(initial[x][y]).toString()); //Updating the pipe type according to the new place
					 
					 if(pipe.equals("g"))
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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
						 return true;
					 else {
						 list.clear();//clear the old list
						 list.add(0, cameFromDirection);
						 list.add(1, x+","+y);
						 list.add(2,pipe);
						 return reachGoal(list,initial);
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

	
	public static void main(String[] args) throws IOException
	{
		char[][] level= new char[4][4];
		
		level[0][0] = 's';
		level[0][1] = '-';
		level[0][2] = '-';
		level[0][3] = 'J';
		
		level[1][0] = 'F';
		level[1][1] ='-';
		level[1][2] ='-';
		level[1][3] ='J';
				
		level[2][0] ='L';
		level[2][1] ='-';
		level[2][2] ='-';
		level[2][3]='7';
				
		level[3][0]='g';
		level[3][1]='-';
		level[3][2]='-';
		level[3][3]='J';
		
		for(int i=0;i<4;i++) {}
		
		System.out.println(isGoal(level));
				
		
		
	}
}
