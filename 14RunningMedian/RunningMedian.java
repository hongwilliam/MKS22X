import java.util.*;

public class RunningMedian{
	
	//you need two heaps - the min and max - for this problem
	public MyHeap max, min;
	private int minSize, maxSize;

	//constructor
	public RunningMedian(){
		max = new MyHeap(false);
		min = new MyHeap();
		minSize = 0;
		maxSize = 0;}

	//insert value into heap in proper place... O(log n)
	//case by case analysis
	public void add(int n){
		
		//case 1: when both heaps are empty, just add to the min heap
		if (minSize == 0 && maxSize == 0){
			min.add(n);
			minSize += 1; }
			
		else{
			//case 2: when minHeap has 1 element but maxHeap is empty
			if (minSize == 1 && maxSize == 0){
				//case 2a: when root of minHeap less than n, n can't be added to minHeap
				//so add n to the maxHeap
				if (min.peek() < n){
					max.add(n); }
				
				//case 2b: if n is less than root of minHeap, move the original root of minHeap	
				//to the maxHeap, remove the original root of minHeap and make n the new root
				else{
					max.add(min.remove());
					min.add(n); }
				maxSize += 1; }
			
			else{
				//case 3: when both heaps are not empty and have same size
				if (minSize == maxSize){
					if (min.peek() >= n){
						min.add(n);
						minSize += 1; }
						
					else{
						//case 3b: when root of minHeap is smaller than n, but n is not
						//large enough to be considered for the maxHeap
						if (n >= min.peek() && n < max.peek() ){
							min.add(n);
							minSize += 1; }
							
				
						else{
							//case 3c: when n is very large, add it to maxHeap
							if (max.peek() <= n){
								max.add(n);
								maxSize += 1; }
						}
					}
				}
				
				else{
					//case 4: if the minHeap is larger than the maxHeap
					if (minSize > maxSize){
						
						//case 4a: even if n is viable for minHeap, add it to the maxHeap
						if (min.peek() <= n){
							max.add(n);
							maxSize += 1; }
							
						//case 4b: add the root of the minHeap to the maxHeap if n is smaller
						//than root of minHeap, and we add n to minHeap
						else{
							max.add(min.remove() );
							min.add(n);
							maxSize += 1; }
					}
					
					//case 5: if maxHeap is larger than the minHeap
					else{
						//case 5a: even if n is viable for maxHeap, add it to the minHeap
						if (max.peek() >= n){
							min.add(n);
							minSize += 1; }
							
						//case 5b: otherwise, remove the root of maxHeap and add that to minHeap
						//add n to the maxHeap 
						else{
							min.add(max.remove() );
							minSize += 1;
							max.add(n); }
					}
				}
			}
		}
	} 
	

	//for a max heap: look at largest of small heap
	//for a min heap: look at smallest of large heap
	//O(1) time - make sure to typecast to double otherwise errors occur
	public double getMedian(){
	
		if ( (minSize == 1 && maxSize == 0) || (minSize == 0 && maxSize == 1) ){
			if (minSize == 1){
				return (double) min.peek(); }
			else{
				return (double) max.peek(); }
		}
		
		else{
			double minRoot = (double) min.peek();
			double maxRoot = (double) max.peek();
		
			if (minSize == maxSize){
				return (minRoot + maxRoot) / 2; }
			else{
				if (minSize > maxSize){
					return minRoot; }
				else{
					return maxRoot;	}
			}
		}
	}

	//new and improved
	public class MyHeap{
	
		private Integer[] array = new Integer[100];
		private int size = 0, direction = 1; 
		
		public MyHeap(){}
	
		public MyHeap(boolean whichDirection){
			if (whichDirection == false){
				direction = -1; }
		}
	
		public void add(Integer s){
			if (size + 1 == array.length){
				resize(); }
			
			array[size+1] = s;
			size += 1;
			pushUp(); }
		
		public Integer remove(){
			if (size == 0){
				throw new IllegalArgumentException(); }
			
			Integer original = array[1]; //the root
			array[1] = array[size];
			size -= 1;
			pushDown(); 
		
			return original; }
		
		public Integer peek(){
			if (size == 0){
				throw new IllegalArgumentException(); }
			
			return array[1]; //the root 
		}
	
		//note: i/2 is the parent
		private void pushUp(){
			int i = size;
			while(i != 1 && (array[i].compareTo(array[i/2]) * direction) > 0){
				int p = i/2;
				Integer swap = array[p];
				array[p] = array[i];
				array[i] = swap;
				i = p; }
		}
	
		//note: 2i and 2i + 1 are the children
		private void pushDown(){
			int i = 1;
		
			Integer swap;
			boolean traversing = true;
		
			while (traversing && 2*i <= size){
				if (2*i+1 <= size && array[2*i+1].compareTo(array[2*i]) * direction > 0){
					if (array[i].compareTo(array[2*i+1]) * direction < 0){
						int c2 = 2*i + 1;
						swap = array[c2];
						array[c2] = array[i];
						array[i] = swap;
						i = c2; }
					else{
						traversing = false; }
				}
			
			else{
				if (array[i].compareTo(array[2*i]) * direction < 0){
					int c1 = 2*i;
					swap = array[c1];
					array[c1] = array[i];
					array[i] = swap;
					i = c1; }
				else{
					traversing = false; }
				}
			}
		}
		
		public String toString(){
			return java.util.Arrays.toString(array); }
		
		private void resize(){
			Integer[] resized = new Integer[size*2];
			int i=1;
			while (i <= size){
				resized[i] = array[i];
				i += 1; }
			
			array = resized;
		}
	
	}
	
	//testing...
	public static void main(String[] args){
		RunningMedian r = new RunningMedian();
		r.add(-5);
		//System.out.println(r.getMedian()); //-5.0
		
		RunningMedian r1 = new RunningMedian();
		r1.add(1);
		r1.add(2);
		r1.add(3);
		//System.out.println(r1.getMedian()); //2.0
		
		RunningMedian r2 = new RunningMedian();
		r2.add(-1);
		r2.add(1);
		//System.out.println(r2.getMedian()); //0.0
	}

}