public class MazeSolver{
	
	private Maze board;
	private boolean animator, isSolved;
	private Frontier front;
		
	public MazeSolver(String filename, boolean animate){
		board = new Maze(filename);
		animator = animate;
		isSolved = false; }
		
	public MazeSolver(String filename){
		board = new Maze(filename);
		animator = false; }
	
	//this will keep track of the path in the recursive solver
	public void markSpot(Location L){
		int r = L.getR();
		int c = L.getC();
		board.set(r, c,'@');
		if (L.getPrev() != null){
			markSpot(L.getPrev()); }
	}
	
	//this will analyze the 4 adjacent spots of the current path used in solver
	public void analyzeAdjacent(Location L, boolean b){
		int r = L.getR();
		int c = L.getC();
			
		if(board.get(r, c + 1) == ' ' || board.get(r, c + 1) == 'E'){
			front.add(board.getLocation(r, c + 1, b, L)); }
			
		if(board.get(r, c - 1) == ' ' || board.get(r, c - 1) == 'E'){
			front.add(board.getLocation(r, c - 1, b, L)); }
			
		if(board.get(r + 1, c) == ' ' || board.get(r + 1, c) == 'E'){
			front.add(board.getLocation(r + 1, c, b, L)); }
			
		if(board.get(r - 1, c) == ' ' || board.get(r - 1, c) == 'E'){
			front.add(board.getLocation(r - 1, c, b, L)); }
		
	}
	
	public void solve(){
		solve(1); }
		
	//0 - DFS -> stack
	//1 - BFS -> queue
	//2 - BestFirst -> priority queue
	//3 - a* -> priority queue
	public void solve(int style){
		if (style == 0){
			front = new FrontierStack();
			solve(false); }
		
		if (style == 1){
			front = new FrontierQueue();
			solve(false); }
			
		if (style == 2){
			front = new FrontierPriorityQueue();
			solve(false); }
		
		else{
			front = new FrontierPriorityQueue();
			solve(true); }
	}
	
	//based on style inputted
	public void solve(boolean b){
		int rowStart = board.getStart().getR();
		int colStart = board.getStart().getC();
		int rowEnd = board.getEnd().getR();
		int colEnd = board.getEnd().getC();
		
		board.set(rowStart, colStart, 'S');
		board.set(rowStart, colEnd, 'E');
		solve(board.getStart(), b); }
		
	//the recursive solver
	public void solve(Location L, boolean b){
		int r = L.getR();
		int c = L.getC();
		if (isSolved){
			return; //finished 
		}
		
		else{
			if (board.get(r,c) == 'E'){
				isSolved = true;
				markSpot(L);
				return; //finished
			}
			
			else{
				board.set(r, c, '.');
				analyzeAdjacent(L, b);
				solve(front.next(), b); }
		}
		
	}
	
	public String toString(){
		return board.toString(); }

}