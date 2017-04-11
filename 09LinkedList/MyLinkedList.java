import java.lang.IllegalArgumentException;
import java.util.*;

public class MyLinkedList implements Iterable<Integer>{
	
	public LNode head, tail;
	public int size;
	
	//iterator class
	private class MyLinkedListIterator implements Iterator<Integer>{
		LNode start = head;
		
		public boolean hasNext(){
			return start != null; }
			
		public Integer next(){
			if (hasNext()){
				LNode original = start;
				start = start.getNext();
				return new Integer(original.getValue()); }
			else{
				throw new NoSuchElementException(); }
		}
		
		public void remove(){
			throw new UnsupportedOperationException(); }
	}
	
	public Iterator<Integer> iterator(){
		return new MyLinkedListIterator(); }
	
	//default construtor
	public MyLinkedList(){
		head = null;
		tail = null;
		size = 0; }
		
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
			answer = "[]"; }
		else{
			LNode start = head;
			answer = "[" + start.getValue();
			start = start.getNext(); 
			
			while (i < size){
				answer += ", " + start.getValue();
				start  = start.getNext(); 
				i += 1; }
		}
				
		answer += "]";
		return answer; }
			
	private LNode getNthNode(int n){
		if(n < 0 || n > size){
	    throw new IllegalArgumentException(); }
		
		LNode start;
		
		//in what half is the n in?
		if (n >= size/2){
			start = head;
			int i = 0;
			
			while (i < n){
				start = start.getNext();
				i += 1; }
		}
		
		else{
			start = tail;
			int i = size-1;
			
			while (i > n){
				start = start.getPrevious();
				i -= 1; }
		}
		
		return start; }
	
	//return value of element specified at the index
	public int get(int index){
		return getNthNode(index).getValue(); }
	
	//change value of element specified at index to newValue, return original
	public int set (int index, int newValue){
		if (index == size){
			throw new IllegalArgumentException(); }
			
		LNode start = getNthNode(index);
		int original = start.getValue();
		start.setValue(newValue);
		
		return original; }
	
	//Phase II methods
	
	//return index of 1st occurence of value in list, -1 if not found
	public int indexOf(int value){
		LNode current = head; 
		int i=0;
		
		while (i < size){
			if (current.getValue() == value){
				return i; }
			current = current.getNext(); 
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
				head.setPrevious(node);
				node.setNext(head);
				head = node; }
				
			else{
				//making the tail the new node
				if (index == size){
					tail.setNext(node);
					node.setPrevious(tail);
					tail = node; }
					
				else{
					LNode move = getNthNode(index-1);
					node.setPrevious(move);
					node.setNext(move.getNext());
					move.getNext().setPrevious(node);
					move.setNext(node); }
			}	
		}
		
		size += 1;
		return true; }
	
	//remove element at index, return value removed
	public int remove (int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(); }
			
		int original = 0;
		
		if (index == 0){
			original = head.getValue();
			head = head.getNext();
			head.setPrevious(null); }
		else{
			if (index == size-1){
				original = tail.getValue();
				tail = tail.getPrevious();
				tail.setNext(null); }
				
			else{
				LNode node = getNthNode(index);
				original = node.getValue();
				node.getPrevious().setNext(node.getNext());
				node.getNext().setPrevious(node.getPrevious());
			}
		}
			
		size -= 1;
		return original; }
	
		/** 
		//testing
		public static void main(String[] args){
			MyLinkedList L = new MyLinkedList();
			
			L.add(0,1);
			L.add(1,2);
			L.add(2,4);
			L.add(3,8);
			L.add(4,16);
			L.add(5,32);
			
			System.out.println(L.size()); //6
			System.out.println(L.toString()); //[1, 2, 4, 8, 16, 32];
			System.out.println(L.get(3)); //8
			System.out.println(L.indexOf(8)); //3
			System.out.println(L.indexOf(128)); //-1
			L.add(64);
			System.out.println(L.toString()); //[1, 2, 4, 8, 16, 32, 64];
			L.set(0,0);
			System.out.println(L.toString()); //[0, 2, 4, 8, 16, 32, 64];
			L.remove(6);
			System.out.println(L.toString()); //[0, 2, 4, 8, 16, 32];
		} */
		
}