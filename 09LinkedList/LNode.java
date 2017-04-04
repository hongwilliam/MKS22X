public class LNode{

	public int value;
	public LNode next;

	//2 constructors required
	public LNode(int myValue){
		value = myValue; }

	public LNode(int myValue, LNode myNext){
		value = myValue;
		next = myNext; }
		
	//setters
	public void setValue(int myValue){
		value = myValue; }
	
	public void setNext(LNode myNode){
		next = myNode; }
	
	//getters
	public int getValue(){
		return value; }
		
	public LNode getNext(){
		return next; }

	public static void main(String[] args){}
}