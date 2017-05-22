public class Location implements Comparable<Location>{

	private int row, col, distanceToStart, distanceToGoal;
	private Location previous;
	private boolean aStar;
	
	public Location(int r, int c, Location prev, int distToStart, int distToGoal, boolean astar){
		row = r;
		col = c;
		previous = prev;
		distanceToStart = distToStart;
		distanceToGoal = distToGoal;
		aStar = astar; }
		
	public int getR(){
		return row; }
		
	public int getC(){
		return col; }
		
	public int getStart(){
		return distanceToStart; }
		
	public int getGoal(){
		return distanceToGoal; }
		
	public Location getPrev(){
		return previous; }

	public int getDistance(Location L){
		int rowDiff = (L.getR() - getR());
		int colDiff = (L.getC() - getC());
		return Math.abs(rowDiff + colDiff); }
		
	public int compareTo(Location L){
		if (aStar){
			return (distanceToStart + distanceToGoal) - (L.getStart() - L.getGoal()); }
		else{
			return distanceToGoal - L.getGoal(); }
	}
	
}