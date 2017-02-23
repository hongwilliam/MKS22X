import java.util.*;

public class KnightBoard{

	private int[][] board;

	//system: -1 is an unoccupied square, [1,n^2] are occupied squares
	public KnightBoard(int row, int col){
		board = new int[row][col]; 
		int i=0;
		for (int r=0; r < board.length; r++){
			for (int c=0; c < board[r].length; c++){
				board[r][c] = -1; 
			}
		}
	}

	private boolean canPlaceKnight(int r, int c){
		//check if r and c are valid inputs (inside the board)
		if (r >= 0 && r < board.length && c >= 0 && c < board[r].length){
			if (board[r][c] == -1){
				return true; }
		}
		return false;
	}
	
	/** this may be used later when the base assignment is complete (optimization)
	tip: priortize the squares with the fewest outgoing moves
	this function will count the number of possible moves a knight can go from a given square */
	
	private int possibleMoves (int r, int c){
		int answer = 0;
		
		//2 up, 1 left
		if (canPlaceKnight(r-2, c-1)){
			answer += 1; }

		//2 up, 1 right
		if (canPlaceKnight(r-2, c+1)){
			answer += 1; }

		//2 down, 1 left
		if (canPlaceKnight(r+2, c-1)){
			answer += 1; }

		//2 down, 1 right
		if (canPlaceKnight(r+2, c+1)){
			answer += 1; }

		//2 left, 1 up
		if (canPlaceKnight(r-1, c-2)){
			answer += 1; }

		//2 left, 1 down	
		if (canPlaceKnight(r+1, c-2)){
			answer += 1; }

		//2 right, 1 up
		if (canPlaceKnight(r-1, c+2)){
			answer += 1; }

		//2 right, 1 down
		if (canPlaceKnight(r+1, c+2)){
			answer += 1; }

		return answer;

	}	
	
	//helper function
	private boolean solveH(int r, int c, int level){
		
		//for the recursive function, these following conditions must be met
		if (r < 0 || c < 0 || r >= board.length || c >= board[0].length){
			//this means that the r and c are out of the board
			return false; }
			
		//next, if the r and c was valid, then the function must check if the square was already taken
		if (board[r][c] != -1){
			return false; }
			
		//if the false conditions are avoided, then the knight can be placed
		board[r][c] = level;
		
		//the tree branches off here
		if (solveH(r+2,c+1,level+1) || solveH(r+1,c+2,level+1) || solveH(r-1,c+2,level+1) || solveH(r-2,c+1,level+1) ||
			solveH(r-2,c-1,level+1) || solveH(r-1,c-2,level+1) || solveH(r+1,c-2,level+1) || solveH(r+2,c-1,level+1) ){
				return true; }
		
		//the final case since there will be x * y knights if solution exists
		int x = board.length, y = board[0].length;
		if (level + 1 == x * y){
			return true; }
			
		//backtracking
		board[r][c] = -1;
		return false;
		
	}
	
	public void solve(){
		solveH(0,0,0);
	}
	
	public String toString(){
		String answer = "";
		for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[r].length; c++) {
					if (board[r][c] + 1 < 10){
						answer += " 0" + (board[r][c] + 1); }
					else{
						answer += " " + (board[r][c] + 1); } 
				}
				answer += "\n"; 
		}
			
		return answer;
	}
	
	public static void main(String[] args){
		KnightBoard k1 = new KnightBoard(5,5);
		k1.solve();
		System.out.println(k1.toString());
		
		KnightBoard k2 = new KnightBoard(6,6);
		k2.solve();
		System.out.println(k2.toString());
		
		KnightBoard k3 = new KnightBoard(7,7);
		k3.solve();
		System.out.println(k3.toString());
		
		KnightBoard k4 = new KnightBoard(8,8);
		k4.solve();
		System.out.println(k4.toString()); 
		
	}
	
}
	
	