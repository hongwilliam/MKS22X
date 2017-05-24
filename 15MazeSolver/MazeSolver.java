public class MazeSolver{
	
	private Maze board;
	private boolean animator;
	private Frontier front;
		
	public MazeSolver(String filename, boolean animate){
		board = new Maze(filename);
		animator = animate; }
		
	public MazeSolver(String filename){
		board = new Maze(filename);
		animator = false; }
	
	//self explannatory
	public boolean isSafe(int row, int col){
		return (board.get(row,col) == ' '); }
		
	public int getDist(Location L, int r, int c){
		int rowDiff = (L.getR() - r);
		int colDiff = (L.getC() - c);
		return Math.abs(rowDiff + colDiff); }	
		
	//this will analyze the 4 adjacent spots of the current path used in solver
	public void analyzeAdjacent(Location L){
		int r = L.getR();
		int c = L.getC();
		board.set(r, c, ',');
		
		int dist1 = getDist(board.getStart(), r, c);
		int dist2 = getDist(board.getEnd(), r, c);
		boolean isAStar = L.getAStar();
			
		if( isSafe(r, c + 1) || board.get(r, c + 1) == 'E'){
			front.add(new Location(r, c, L, dist1, dist2, isAStar)); }
			
		if( isSafe(r, c - 1)|| board.get(r, c - 1) == 'E'){
			front.add(new Location(r, c, L, dist1, dist2, isAStar)); }
			
		if( isSafe(r + 1, c) || board.get(r + 1, c) == 'E'){
			front.add(new Location(r, c, L, dist1, dist2, isAStar)); }
			
		if( isSafe(r - 1, c) || board.get(r - 1, c) == 'E'){
			front.add(new Location(r, c, L, dist1, dist2, isAStar)); }
		
	}
	
	public void showAnimation(Location L){
		while(L != null){
			board.set(L.getR(), L.getC(), '@');
			if(animator){
				System.out.println(board.toString(50)); }
			L = L.getPrev(); }
	}
	//default
	public void solve(){
		solve(1); }
		
	//0 - DFS -> stack
	//1 - BFS -> queue
	//2 - BestFirst -> priority queue
	//3 - a* -> priority queue
	public void solve(int style){
		boolean isSolved = false;
		
		if (style == 0){
			front = new FrontierStack(); }
		
		if (style == 1){
			front = new FrontierQueue(); }
			
		if (style == 2){
			front = new FrontierPriorityQueue(); }
		
		if (style == 3){
			front = new FrontierPriorityQueue();
			board.getStart().setAStar(true); }
			
		front.add(board.getStart());
		
		//from pseudo code learned in class
		while (front.hasNext() && !isSolved){
			
			if (animator){
				System.out.println(board.toString(100)); //delay 
			}
			Location next = front.next();
			
			if (next.getGoal() == 0){
				isSolved = true;
				showAnimation(next); }
			else{
				analyzeAdjacent(next); }
			
		}
	}
	
	public String toString(){
		return board.toString(); }	
		
}

