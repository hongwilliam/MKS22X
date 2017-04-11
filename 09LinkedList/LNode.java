public class LNode{

	public int value;
	public LNode previous, next;

	//2 constructors required
	public LNode(int myValue){
		value = myValue; }

	public LNode(int myValue, LNode myPrevious, LNode myNext){
		value = myValue;
		next = myNext; }
		
	//setters
	public void setValue(int myValue){
		value = myValue; }
	
	public void setPrevious(LNode myNode){
		previous = myNode;  }
		
	public void setNext(LNode myNode){
		next = myNode; }
	
	//getters
	public int getValue(){
		return value; }
		
	public LNode getPrevious(){
		return previous; }
		
	public LNode getNext(){
		return next; }

}