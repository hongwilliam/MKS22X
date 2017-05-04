import java.util.*;

public class MyHeap{
	
	//direction value of 1 denotes max heap, -1 denotes min heap
	private String[] array = new String[100];
	private int size = 0, direction = 1; 
	
	public MyHeap(){}
	
	public MyHeap(boolean whichDirection){
		if (whichDirection == false){
			direction = -1; }
	}
	
	public void add(String s){
		if (size + 1 == array.length){
			resize(); }
			
		array[size+1] = s;
		size += 1;
		pushUp(); }
		
	public String remove(){
		if (size == 0){
			throw new IllegalArgumentException(); }
			
		String original = array[1]; //the root
		array[1] = array[size];
		size -= 1;
		pushDown(); 
		
		return original; }
		
	public String peek(){
		if (size == 0){
			throw new IllegalArgumentException(); }
			
		return array[1]; //the root 
	}
	
	//note: i/2 is the parent
	private void pushUp(){
		int i = size;
		while(i != 1 && (array[i].compareTo(array[i/2]) * direction) > 0){
			String swap = array[i/2];
			array[i/2] = array[i];
			array[i] = swap;
			i /= 2; }
	}
	
	//note: 2i and 2i + 1 are the children
	private void pushDown(){
		int i = 1;
		
		String swap = "";
		while (i != size){
			
			//should child1 get swapped?
			if(array[i].compareTo(array[2*i]) * direction < 0){
				swap = array[2*i];
				array[2*i] = array[i];
				array[i] = swap;
				i *= 2; }
			
			//should child2 get swapped?
			else{
				if (array[i].compareTo(array[2*i +1]) * direction < 0)
					swap = array[2*i +1];
					array[2*i +1] = array[i];
					array[i] = swap;
					i = 2*i +1; }
		}
	}
	
	private void resize(){
		String[] resized = new String[size*2];
		int i=1;
		while (i <= size){
			resized[i] = array[i];
			i += 1; }
			
		array = resized;
	}
	
}
	

	
