package ClientHendler_Cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Algorithem.BestFirstSearch;
import Algorithem.hillClimbing;
import Gradehill.costStateGrader;
import Problem.PipeProblem;
import Searchable.MatrixChar;
import Server.State;
import Solution.sulotionParser;
import Solver.PipeSolver;
import Solver.Solver;

public class simpleClientHendler implements ClientHendler {

	Solver<MatrixChar,ArrayList<State<MatrixChar>>> pipeSulotion;
	Cache cahceManger = SimpleCacheManager.getInstance();


	@Override
	public void handleClient(InputStream input,OutputStream output) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(output,true);
		ArrayList<String> level = new ArrayList<String>();
		
		ArrayList<State<MatrixChar>> solution = new ArrayList<State<MatrixChar>>();

		Integer key;
		String line;
			line = " ";

			while(!(line.equals("done"))) {
				line = in.readLine();
				//System.out.println(line);
				level.add(line);
			}
			
			
			key=level.toString().hashCode();

		
			
			ArrayList<String> sol=new ArrayList<>();
			sol=cahceManger.load(key);
			
		
				
			{
				PipeProblem problem = new PipeProblem(level);//pipe problem
				pipeSulotion = new PipeSolver(new BestFirstSearch<MatrixChar>());//pipe sulotion with cest firs search
				solution= pipeSulotion.solve(problem.getProblem());
				sol= new sulotionParser().BackTrace(solution);
				cahceManger.save(key,sol);
				for(int i=0;i<sol.size();i++)
					out.println(sol.get(i));
				
			}
			
	}
	
	

}
