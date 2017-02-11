public class NQueens{
	private int[][] board;
	private int solutionCount = -1;
	//constructor
	public NQueens(int size) {
		board = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				board[r][c] = 0;
			}
		}
	}
	
	// can a queen be placed in a specific square
	public boolean isSafe(int[][] board, int row, int col) {
		
		//check for an entire row
		for (int colCheck = 0; colCheck < col; colCheck++) {
			if (board[row][colCheck] == 1) {
				return false; }
		}
		
		//check northwest and northeast
		for (int rowCheck = row, colCheck = col; rowCheck >= 0 && colCheck >= 0; rowCheck--,colCheck--) {
			if (board[rowCheck][colCheck] == 1) {
				return false;
			}
		}

		//check southwest and southeast
		for (int rowCheck = row, colCheck = col; rowCheck < board.length && colCheck >= 0; rowCheck++, colCheck--) {
			if (board[rowCheck][colCheck] == 1) {
				return false;
			}
		}
	
		return true;
	}

	//find the first solution
	public boolean solve(int col, int queens) {
		
		//base case: if number of queens is equal to "n" (number of cols)
		if(col == queens){
			if (solutionCount == -1){
				solutionCount = 1; }
			else{
				solutionCount += 1; }
			return true; }
			
		else{
			//looping thru each row
			for (int row = 0; row < queens; row++) {
				if (isSafe(board, row, col)) {
					board[row][col] = 1;
					
					//placing next queen in the next col
					if(solve(col + 1, queens)){
						return true; }
					else{
						//backtracking if this leads to dead end
						board[row][col] = 0; }
						
				}
			}
		}
		
		//if the code reaches this point, then there is no solution for that column
		return false; 
		
	}
	
	public int getSolutionCount(){
		return solutionCount; }
	
	public String toString(){
		int size = board.length;
		String answer = "";
		for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (board[r][c] == 0){
						answer += " _"; }
					else{
						answer += " Q"; }
				}
				answer += "\n";
			}
			
		return answer;
	}

	//testing
	public static void main(String[] args) {
		NQueens q = new NQueens(8);
		q.solve(0,8);
		//System.out.println(q.getSolutionCount());
		System.out.println(q.toString());

	}

}