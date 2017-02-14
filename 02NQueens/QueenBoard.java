public class QueenBoard{
	private int[][] board;
	private int solutionCount = -1;
	
	//constructor
	public QueenBoard(int n) {
		board = new int[n][n]; }
	
	//system: 0 = safe blank square, 1 = queen
	//can a queen be placed in a specific blank square?
	public boolean canPlaceQueen(int row, int col) {
		
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
	
	private void addQueen(int row, int col){
		board[row][col] = 1; }
		
	private void removeQueen(int row, int col){
		board[row][col] = 0; }
	
	//this will be useful when doing countSolutions
	private void resetBoard(){
		int size = board.length;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				board[r][c] = 0;
			}
		}
	}

	//helper function
	//find the first solution
	private boolean solveH(int col) {
		
		int size = board.length; 
		//base case: if number of queens is equal to "n" (number of cols)
		if(col == size){
			solutionCount = 1;
			return true; }
			
		else{
			//looping thru each row
			for (int row = 0; row < size; row++) {
				if (canPlaceQueen(row, col)) {
					addQueen(row, col);
					
					//placing next queen in the next col
					if(solveH(col + 1)){
						return true; }
					else{
						//backtracking if this leads to dead end
						removeQueen(row,col); }
						
				}
			}
		}
	
		return false; 
		
	}

	public void solve(){
		solveH(0); }
	
	//helper function to find all possible solutions
	private void countSolutionsH(int col){
		int size = board.length;
		
		if(col == board.length){
			solutionCount += 1; }
		
		else{
			//looping thru each row...
			for(int row = 0; row < size; row++){
				if(canPlaceQueen(row, col)){
					addQueen(row, col);
					countSolutionsH(col + 1);
					removeQueen(row, col);
				}
			}
		}
		
    }

	public void countSolutions(){
		//clear the board just in case you modified it before
		resetBoard();
		
		//if this was run before, then reset the number of solutions
		//also, start incrementing this from 0 because default value was -1
		solutionCount = 0;
		countSolutionsH(0);
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
		QueenBoard q = new QueenBoard(8);
		
		//if no solving methods return, solutionCount should be -1
		//System.out.println(q.getSolutionCount()); //-1

		q.solve();
		System.out.println(q.toString());
		//System.out.println(q.getSolutionCount()); //1
		System.out.println("\nHow many solutions for 8x8 board?");
		
		q.countSolutions();
		System.out.println(q.getSolutionCount()); //92
		
		//some other n*n boards...
		QueenBoard a = new QueenBoard(1);
		System.out.println("\nHow many solutions for 1x1 board?");
		a.countSolutions();
		System.out.println(a.getSolutionCount()); //1
		
		QueenBoard b = new QueenBoard(2);
		System.out.println("\nHow many solutions for 2x2 board?");
		b.countSolutions();
		System.out.println(b.getSolutionCount()); //0
		
		QueenBoard c = new QueenBoard(3);
		System.out.println("\nHow many solutions for 3x3 board?");
		c.countSolutions();
		System.out.println(c.getSolutionCount()); //0
		
		QueenBoard d = new QueenBoard(4);
		System.out.println("\nHow many solutions for 4x4 board?");
		d.countSolutions();
		System.out.println(d.getSolutionCount()); //2
		
		QueenBoard e = new QueenBoard(5);
		System.out.println("\nHow many solutions for 5x5 board?");
		e.countSolutions();
		System.out.println(e.getSolutionCount()); //10
		
		QueenBoard f = new QueenBoard(6);
		System.out.println("\nHow many solutions for 6x6 board?");
		f.countSolutions();
		System.out.println(f.getSolutionCount()); //4
		
		QueenBoard g = new QueenBoard(7);
		System.out.println("\nHow many solutions for 7x7 board?");
		g.countSolutions();
		System.out.println(g.getSolutionCount()); //40
		
		QueenBoard h = new QueenBoard(9);
		System.out.println("\nHow many solutions for 9x9 board?");
		h.countSolutions();
		System.out.println(h.getSolutionCount()); //352
		
		QueenBoard i = new QueenBoard(10);
		System.out.println("\nHow many solutions for 10x10 board?");
		i.countSolutions();
		System.out.println(i.getSolutionCount()); //724
		
	}

}