import java.util.*;

public class FrontierQueue implements Frontier{
	
	private LinkedList<Location> FQ;
	
	public FrontierQueue(){
		FQ = new LinkedList<Location>(); }
		
	public void add (Location L){
		FQ.add(L); }
		
	public Location next(){
		return FQ.remove(); }
	
}