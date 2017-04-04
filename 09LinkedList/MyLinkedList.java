import java.lang.IllegalArgumentException;

public class MyLinkedList{
	
	public LNode start;
	public int size;
	
	//default construtor
	public MyLinkedList(){
		start = null;
		size = 0; }
		
	//Phase I methods
	
	//add value to the end
	public boolean add (int value){
		if (size == 0){
			start = new LNode(value); }
		else{
			LNode current = start;
			//while you aren't at the end
			while (current.next != null){
				current = current.getNext();  }
				
			current.next = new LNode(value); }
			
		size += 1;
		return true; }
		
	public int size(){
		return size; }
		
	public String toString(){
		String answer = "";
		//this is because the first node was already checked before the while
		int i = 1; 
		
		if (size == 0){
			answer = "[]"; }
		else{
			LNode current = start;
			answer = "[" + current.getValue();
			current = current.getNext(); 
			
			while (i < size){
				answer += ", " + current.getValue();
				current  = current.getNext(); 
				i += 1; }
				
		}
				
		answer += "]";
		return answer; }
		
	//return value of element specified at the index
	public int get(int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(); }
			
		LNode current = start;
		int i=0;
		
		//finding the right node is O(N)
		//this is basic strategy to trasverse thru a loop
		while (i < index){
			current = current.getNext(); 
			i += 1; }
			
		return current.getValue();
	}
	
	//change value of element specified at index to newValue, return original
	public int set (int index, int newValue){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(); }
			
		LNode current = start;
		int i=0;
		
		//same idea as the get method for finding the index
		while (i < index){
			current = current.getNext();
			i += 1; }
		
		int original = current.getValue(); //retrieve original here
		current.setValue(newValue); //make the change here
		
		return original;
	}
	
	//Phase II methods
	
	//return index of 1st occurence of value in list, -1 if not found
	public int indexOf(int value){
		LNode current = start; 
		int i=0;
		
		while (i < size){
			if (current.getValue() == value){
				return i; }
			current = current.getNext(); 
			i += 1; }
			
		return -1;
	}
	
	//insert new element at specified index, at front, and end
	public void add(int index, int value){
		if (index < 0 || index > size){
			throw new IllegalArgumentException(); }
			
		if (index == size){
			add(value); }
			
		else{
			if (index == 0){
				LNode L = new LNode(value);
				L.setNext(start);
				start = L;
				size += 1; }
			else{
				LNode current = start;
				LNode L = new LNode(value);
				LNode original = new LNode(0);
				
				int i=0;
				while (i < size){
					if (i == index-1){
						original = current; }
					
					current = current.getNext();
					i += 1;
				}
				
				L.setNext(current);
				original.setNext(L);
				size += 1; 
			}
		}
	}
	
	//remove element at index, return value removed
	public int remove (int index){
		if (index < 0 || index >= size){
			throw new IllegalArgumentException(); }
			
		LNode current = start;
		int original = start.getValue();
		
		if (index == 0){
			start = start.getNext(); }
			
		int i=0;
		while (i < index){
			if (i == index-1){
				LNode succeeding = current.getNext();
				original = succeeding.getValue(); }
			
			current = current.getNext();
			i += 1;
		}
		
		size -= 1;
		return original; }
	
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
		}
		
}