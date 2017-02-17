public class KnightBoard{

	private int[][] board;

	public KnightBoard(int row, int col){
		board = new int[row][col]; }

	/** this will be used later when the base assignment is complete (optimization)
	tip: priortize the squares with the fewest outgoing moves
	this function will count the number of possible moves a knight can go from a given square 
	
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

	}	*/
	
	//helper function
	//note: 0 is a safe spot
	private boolean solveH(int r, int c, int level){
		
		//for the recursive function, these following conditions must be met
		if (r < 0 || c < 0 || r >= board.length || c >= board[0].length){
			//this means that the r and c are out of the board
			return false; }
			
		//next, if the r and c was valid, then the function must check if the square was already taken
		if (board[r][c] > 0){
			return false; }
			
		//if the false conditions are avoided, then the knight can be placed
		board[r][c] = level;
		
		//the tree branches off here
		if (solveH(r-2,c-1,level+1) || solveH(r-2,c+1,level+1) || solveH(r+2,c-1,level+1) || solveH(r+2,c+1,level+1) ||
			solveH(r-1,c-2,level+1) || solveH(r+1,c-2,level+1) || solveH(r-1,c+2,level+1) || solveH(r+1,c+2,level+1) ){
			return true; }
	
		//the final case since there will be x * y knights if solution exists
		int x = board.length, y = board[0].length;
		if (level == x * y){
			return true; }
			
		board[r][c] = 0;
		return false;
		
	}
	
	public void solve(){
		solveH(0,0,1);
	}
	
	public String toString(){
		String answer = "";
		for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[r].length; c++) {
					answer += " " + board[r][c]; }
				answer += "\n";
		}
			
		return answer;
	}
	
	public static void main(String[] args){
		KnightBoard k = new KnightBoard(7,7);
		System.out.println(k.solveH(0,0,1));
		//k.solve();
		System.out.println(k.toString());
	}
	
}
	
	