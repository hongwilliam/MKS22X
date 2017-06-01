import java.lang.IllegalArgumentException;
import java.util.*;

public class MyLinkedList implements Iterable<Integer>{
	
	public class LNode{

		public int value;
		public LNode previous = null, next = null;

		//2 constructors required
		public LNode(int myValue){
			value = myValue; }
		
		public String toString(){
			return value + ""; }
		
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
	
	LNode head = null, tail = null;
	int size = 0;
	
	//iterator class
	private class MyLinkedListIterator implements Iterator<Integer>{
		LNode start = head;
		
		public boolean hasNext(){
			return start != null; }
			
		public Integer next(){
			if (hasNext()){
				LNode original = start;
				start = start.next;
				return new Integer(original.value); }
			else{
				throw new NoSuchElementException(); }
		}
		
		public void remove(){
			throw new UnsupportedOperationException(); }
	}
	
	public Iterator<Integer> iterator(){
		return new MyLinkedListIterator(); }
	
	//default construtor
	public MyLinkedList(){}
		
	//Phase I methods
	
	//add value to the end
	public boolean add (int value){
		return add(size, value); }
		
	public int size(){
		return size; }
		
	public String toString(){
		String answer = "";
		//this is because the first node was already checked before the while
		int i = 1; 
		
		if (size == 0){
			answer = "["; }
		else{
			LNode start = head;
			answer = "[" + start.value;
			start = start.next; 
			
			while (i < size){
				answer += ", " + start.value;
				start  = start.next; 
				i += 1; }
		}
				
		answer += "]";
		return answer; }
			
	private LNode getNthNode(int n){
		if(n < 0 || n > size){
			throw new IllegalArgumentException(); }
		
		LNode start;
		
		//in what half is the n in?
		
		//come in from the left
		if (n <= size/2){
			start = head;
			
			int i = 0;
			while (i < n){
				start = start.next;
				i += 1; }
		}
		
		//come in from the right
		else{
			start = tail;
			
			int i = size-1;
			while (i > n){
				start = start.previous;
				i -= 1; }
		}
		
		return start; }
	
	//added extra case to throw exception
	//return value of element specified at the index
	public int get(int index){
		if (index < 0 || index > size){
			throw new IllegalArgumentException(); }
			
		return getNthNode(index).value; }
	
	//change value of element specified at index to newValue, return original
	public int set (int index, int newValue){
		if (index == size){
			throw new IllegalArgumentException(); }
			
		LNode start = getNthNode(index);
		int original = start.value;
		start.value = newValue;
		
		return original; }
	
	//Phase II methods
	
	//return index of 1st occurence of value in list, -1 if not found
	public int indexOf(int value){
		LNode start = head; 
		int i=0;
		
		while (i < size){
			if (start.value == value){
				return i; }
			start = start.next; 
			i += 1; }
			
		return -1;
	}
	
	//insert new element at specified index, at front, and end
	//there are multiple possible scenarios
	public boolean add(int index, int value){
		if (index < 0 || index > size){
			throw new IllegalArgumentException(); }
			
		LNode node = new LNode(value);
		
		//making a size 1 node
		if (size == 0){
			head = node;
			tail = node; }
			
		else{
			//making the head the new node
			if (index == 0){
				head.previous = node;
				node.next = head;
				head = node; }
				
			else{
				//making the tail the new node
				if (index == size){
					tail.next = node;
					node.previous = tail;
					tail = node; }
					
				else{
					LNode move = getNthNode(index-1);
					node.previous = move;
					node.next = move.next;
					move.next.previous = node;
					move.next = node; }
			}	
		}
		
		size += 1;
		return true; }
	
	//remove element at index, return value removed
	public int remove (int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(); }
			
		int original = 0;
		
		if (size == 1){
			original = head.value;
			head = null;
			tail = null; }
		else{
			if (index == 0){
				original = head.value;
				head = head.next;
				head.previous = null; }
			else{
				if (index == size-1){
					original = tail.value;
					tail = tail.previous;
					tail.next = null; }
				else{
					LNode node = getNthNode(index);
					original = node.value;
					node.previous.next = node.next;
					node.next.previous = node.previous; }
			}
		}
			
		size -= 1;
		return original; }
		
		
	
	//used a classmate's test cases
	
    public static void main(String [] args){
		/**
		//Testing: constructor, toString, size, add, get, remove
		
		MyLinkedList a= new MyLinkedList();
		System.out.println(a.toString()+"\nSize: "+a.size());//[], size=0
		for(int i=0; i<20; i++){
			a.add(i);
			if(a.get(a.size()-1)%2==0)
			a.remove(a.size()-1);
			//System.out.println(a);//Check if all nums arent odd
		}
	
		System.out.println(a+"\nSize: "+a.size());//odds 0-20 in order, size=10
	
		//Testing: set, indexOf, add(i,v); reinforce others
		for(int i=0; i<10; i++){
			a.set(i,i*100);
		}
	
		System.out.println(a+"\nSize: "+a.size());//mulitples of 100, size=10
		for(int i=0; i<10; i++){
			a.set(i,a.indexOf(i*100));
		}
	
        System.out.println(a+"\nSize: "+a.size());//0-9 in order, size=10
		a.remove(1);
		a.add(1,1);
		for(int i=0; i<10; i++){
			a.add(0,i*-1-1);
		}
		a.add(a.size(),10);
	
		System.out.println(a+"\nSize: "+a.size());//-10-10 in order, size=21
	
		//Testing: Iterator
		MyLinkedList data = new MyLinkedList();
		int i = 0;
		while(i < 15){
			data.add(i);
			i++;
		}
		System.out.println("\nContents: "+data);
		System.out.println("Standard loop:");
		for(int n = 0; n < data.size(); n++){
			System.out.print(data.get(n)+" ");
		}
		System.out.println();
		System.out.println("for-each loop:");
		for(Integer s : data){
			System.out.print(s+" ");
		}
		//should be same
		*/
		}
	

}