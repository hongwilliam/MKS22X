import java.util.*;

public class FrontierStack implements Frontier{
	
	private Stack<Location> FS;
	
	public FrontierStack(){
		FS = new Stack<Location>(); }
		
	public void add (Location L){
		FS.push(L); }
		
	public Location next(){
		return FS.pop(); }
		
	public boolean hasNext(){
		return (FS.size() != 0); }
	
}