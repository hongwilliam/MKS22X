import java.util.*;

public class FrontierPriorityQueue implements Frontier{
	
	private MyHeap FPQ;
		
	public class MyHeap{
	
		//direction value of 1 denotes max heap, -1 denotes min heap
		private Location[] array = new Location[100];
		private int size = 0, direction = 1; 
	
		public MyHeap(){}
	
		public MyHeap(boolean whichDirection){
			if (whichDirection == false){
				direction = -1; }
		}
	
		public void add(Location L){
			if (size + 1 == array.length){
				resize(); }
			
			array[size+1] = L;
			size += 1;
			pushUp(); }
		
		public Location remove(){
			if (size == 0){
				throw new NoSuchElementException(); }
			
			Location original = array[1]; //the root
			array[1] = array[size];
			size -= 1;
			pushDown(); 
		
			return original; }
		
		public Location peek(){
			if (size == 0){
				throw new NoSuchElementException(); }
			
			return array[1]; //the root 
		}
	
		//note: i/2 is the parent
		private void pushUp(){
			int i = size;
			while(i != 1 && (array[i].compareTo(array[i/2]) * direction) > 0){
				Location swap = array[i/2];
				array[i/2] = array[i];
				array[i] = swap;
				i /= 2; }
		}
	
		//note: 2i and 2i + 1 are the children
		private void pushDown(){
			int i = 1;
		
			Location swap;
			boolean traversing = true;
		
			while (traversing && 2*i <= size){
				if (2*i+1 <= size && array[2*i+1].compareTo(array[2*i]) * direction > 0){
					if (array[i].compareTo(array[2*i+1]) * direction < 0){
						swap = array[2*i+1];
						array[2*i+1] = array[i];
						array[i] = swap;
						i = 2*i+1; }
					else{
						traversing = false; }
				
				}
			
				else{
					if (array[i].compareTo(array[2*i]) * direction < 0){
						swap = array[2*i];
						array[2*i] = array[i];
						array[i] = swap;
						i = 2*i; }
					else{
						traversing = false; }
				}
			
			}
		}
	
	
		private void resize(){
			Location[] resized = new Location[size*2];
			int i=1;
			while (i <= size){
				resized[i] = array[i];
				i += 1; }
			
			array = resized;
		}

	}

	public FrontierPriorityQueue(){
		FPQ = new MyHeap(false); }
		
	public void add (Location L){
		FPQ.add(L); }
		
	public Location next(){
		return FPQ.remove(); }
		
}