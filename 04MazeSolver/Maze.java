import java.util.*;
import java.io.*;


public class Maze{

	//instance variables
	private char[][] maze;
	private boolean animate;

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
		Maze f = new Maze("data1.dat");
		System.out.println(f.toString()); }
		
}
			
			