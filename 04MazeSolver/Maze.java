import java.util.*;
import java.io.*;


public class Maze{

	//instance variables
	private char[][] maze;
	private boolean animate;
	private int startR, startC;
	
	//constructor: setting up the maze using scanner
	public Maze(String filename){
		animate = false;
		try{
			Scanner scan = new Scanner(new File(filename));
			int length = 1;
			String line = scan.nextLine();
			int width = line.length();
			while(scan.hasNextLine()){
				line += scan.nextLine();
				length += 1; }
				
			maze = new char[length][width];
			
			int i = 0;
			while (i < line.length()){
				maze[i / width][i % width] = line.charAt(i);
				i += 1;
			}
			
		} catch(FileNotFoundException e){
			System.out.println("Error! File not found");
			System.exit(1); }
	}
	
	//copy pasted from course site to help with solving
	 private void wait(int millis){ 
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }
	 
    public boolean solveH(int r, int c){
	
		//from course site
		if(animate){
            System.out.println("\033[2J\033[1;1H"+this);
            wait(20); } 
		
		//base case: did the "solver" reach the end?
		if (maze[r][c] == 'E'){
			return true; }
			
		//is the next square safe to go?
		if (maze[r][c] == ' '){
			
			//by default, the safe square will be marked as @
			//if it not part of solution, then we change it later
			//this step took me some time to figure out 
			maze[r][c] = '@';
			
			//tree branches off here
			if ( solveH(r+1,c) || solveH(r-1,c) || solveH(r,c+1) || solveH(r,c-1) ){
				return true; }
			
			//otherwise, if we need to backtrack, then mark the spot as .
			maze[r][c] = '.'; }

		//if function reaches this point, then no solutione exists
		return false;
    }
	
	public boolean solve(){
		int found = 0;
		for (int r=0; r < maze.length && found == 0; r++){
			for (int c=0; c < maze[0].length && found == 0; c++){
				//find the s!
				if (maze[r][c] == 'S'){
					startR = r;
					startC = c;
					found = 1; }
			}
		}
		//Initialize starting row and startint col with the location of the S. 
        maze[startR][startC] = ' ';//erase the S, and start solving!
        return solveH(startR,startC);	
    }
	
	public String toString(){
		String answer = "";
		for (int r=0; r < maze.length; r++){
			for (int c=0; c < maze[r].length; c++){
				answer += maze[r][c]; }
			answer += "\n";
		}
		return answer;
	}

	public void setAnimate(boolean b){
		animate = b; }
	
	public void clearTerminal(){
        	//erase terminal, go to top left of screen.
       		System.out.println("\033[2J\033[1;1H"); }
			
	//testing...
	public static void main (String[] args){
		/** 
		Maze f1 = new Maze("data1.dat");
		System.out.println(f1.toString());	//check out what the maze looks like
		f1.solve();
		System.out.println(f1.toString()); //check out the solution (and other attempts that didn't work) 
		
		Maze f2 = new Maze("data2.dat");
		System.out.println(f2.toString());	
		f2.solve();
		System.out.println(f2.toString()); 
		
		Maze f3 = new Maze("data3.dat");
		System.out.println(f3.toString());	
		f3.solve();
		System.out.println(f3.toString()); */
		
		
		}
		
}
			
			