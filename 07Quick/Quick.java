import java.util.*;

public class Quick{

	
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }
	
	/** public static int part(int[] data, int start, int end){
		int randomIndex = randomWithRange(start,end);
		int partition = data[randomIndex];
		//System.out.println(partition); //for testing purposes only
		
		//finding # of larger elements than partition
		int largerOrEqual=0, i=start;
		while(i < end){
			if (data[i]  >= partition){
				largerOrEqual += 1; }
			i += 1;
		}

		//put the partition where it belongs
		int swap1 = data[end-largerOrEqual];
		data[randomIndex] = swap1;
		data[end-largerOrEqual] = partition;
		
		//the last N elements must be occupied by the larger or equal numbers
		int goLeft = end, goRight = start;
		while (goRight < end-largerOrEqual && goLeft > end-largerOrEqual){
			//this is when the element at the end belongs there
			if (data[goLeft] >= partition){
				goLeft -= 1; }
			else{
				//otherwise, make the swap with the elements in the front
				//if applicable of course
				if(data[goRight] >= partition){
					int greater = data[goRight];
					data[goRight] = data[goLeft];
					data[goLeft] = greater;
					goRight += 1;
					goLeft -= 1; }	
				else{
					goRight += 1; }
			}
		}

		for testing purposes only
		int x=0;
		while (x < data.length){
			System.out.print(data[x] + " ");
			x += 1; }
		System.out.print("\n"); 
		
		return end-largerOrEqual; 
	} */
	
	//i realized my preivous function was a bit slow (2 loops)
	//after some trial and error...
	public static int part(int[] data, int start, int end){
		int randomIndex = randomWithRange(start,end);
		int partition = data[randomIndex];
		//System.out.println(partition); //for testing purposes only
		
		//variables that will change dynamically
		int right = start, left = end, swap1 = 0, swap2 = 0;
		
		//this is our only terminating condition, which accounts for duplicates
		//failing to account for this was the primary problem with my last attempt
		while (right < left){
			
			//when the element at the index going right is equal to partition
			//we move it to the left as the index keeps moving on to the right
			if (data[right] == partition){
				swap1 = data[right];
				swap2 = data[right+1];
				data[right] = swap2;
				data[right+1] = swap1; }
				
			//same idea as above but applying to the index that is going left and checking
			//we move the element to the right 
			if (data[left] == partition){
				swap1 = data[left];
				swap2 = data[left-1];
				data[left] = swap2;
				data[left-1] = swap1; }
				
			//remember: all items less than partition are to the left
			if (data[right] > data[left]){
				swap1 = data[right];
				swap2 = data[left];
				data[left] = swap1;
				data[right] = swap2; }
				
			//update the index checking variables
			if (data[right] < partition){
				right += 1; }
			
			if (data[left] > partition){
				left -= 1; }
			
		}
		
		//this is the final index
		return right;
		
	}
	
	public static int quickselect(int[] data, int k){
		//first things first...
		if (k < 0 || k >= data.length){
			throw new IllegalArgumentException("Invalid k"); }
		
		//these are the initial variables
		int start = 0, end = data.length-1,
			answer = part(data,start,end);
	
		//the variables will get updated in the while loop if 1st try doesn't work
		while (answer != k){
			//shrink the section of array we want to look at
				
			//choice 1: eliminate smaller choices
			if (answer > k){
				answer = part(data,k,end); }
				
			//choice 2: eliminate larger choices
			else{
				answer = part(data,start,k); }
					
		}
		
		return data[answer];
	}
	
	//testing...
	public static void main (String[] args){
		Quick x = new Quick();
		
		// int[] test3 = new int[]{};
		//System.out.println(quickselect(test3,0));//Invalid k
		
		int[] test1 = new int[]{2, 10, 15, 23, 0, 5};
		System.out.println(x.quickselect(test1,0)); //0
		System.out.println(x.quickselect(test1,1)); //2
		System.out.println(x.quickselect(test1,2)); //5
		System.out.println(x.quickselect(test1,3)); //10
		System.out.println(x.quickselect(test1,4)); //15
		System.out.println(x.quickselect(test1,5)); //23
		
		int[] test2 = new int[]{36, 8, 11, 55, 2, 13, 93, 20, 9, 76, 25, 90, 56, 10, 77,
			66, 27, 64, 1, 33, 54, 70, 68, 43, 53, 98, 63, 91, 82, 83, 28, 74, 81, 32, 29, 
			46, 85, 78, 99, 62, 30, 95, 24, 100, 52, 31, 17, 44, 87, 47, 41, 5, 89, 97, 40, 
			38, 94, 15, 39, 4, 67, 49, 75, 72, 92, 22, 59, 57, 19, 65, 48, 37, 86, 26, 42, 
			51, 73, 88, 7, 21, 34, 69, 79, 61, 45, 23, 18, 80, 50, 35, 16, 60, 84, 14, 58, 6, 3, 71, 96, 12};
		System.out.println(x.quickselect(test2,0)); //1
		System.out.println(x.quickselect(test2,49)); //50
		System.out.println(x.quickselect(test2,99)); //100
		//System.out.println(quickselect(test2,100)); //Invalid k 

		/* unfortunately, these cases don't work 
		int[] test4 = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		System.out.println(x.quickselect(test4,4)); //3
		System.out.println(x.quickselect(test4,3)); //3
		System.out.println(x.quickselect(test4,3)); //3

		int[] test5 = new int[]{4, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2};
		System.out.println(x.quickselect(test5,0)); //0
		System.out.println(x.quickselect(test5,3));
		System.out.println(x.quickselect(test5,6));
		System.out.println(x.quickselect(test5,9));
		System.out.println(x.quickselect(test5,13)); */
		
		
	}

}