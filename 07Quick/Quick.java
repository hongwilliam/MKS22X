import java.util.*;

public class Quick{

	
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }
	
	public static int part(int[] data, int start, int end){
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
		
		//the last N elements must be occupied by the larger numbers
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

		/*for testing purposes only
		int x=0;
		while (x < data.length){
			System.out.print(data[x] + " ");
			x += 1; }
		System.out.print("\n"); */
		
		return end-largerOrEqual; 
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
		
		/* int[] test3 = new int[]{};
		System.out.println(quickselect(test3,0));//Invalid k
		
		int[] test1 = new int[]{2, 10, 15, 23, 0, 5};
		System.out.println(quickselect(test1,0)); //0
		System.out.println(quickselect(test1,1)); //2
		System.out.println(quickselect(test1,2)); //5
		System.out.println(quickselect(test1,3)); //10
		System.out.println(quickselect(test1,4)); //15
		System.out.println(quickselect(test1,5)); //23
		
		int[] test2 = new int[]{36, 8, 11, 55, 2, 13, 93, 20, 9, 76, 25, 90, 56, 10, 77,
			66, 27, 64, 1, 33, 54, 70, 68, 43, 53, 98, 63, 91, 82, 83, 28, 74, 81, 32, 29, 
			46, 85, 78, 99, 62, 30, 95, 24, 100, 52, 31, 17, 44, 87, 47, 41, 5, 89, 97, 40, 
			38, 94, 15, 39, 4, 67, 49, 75, 72, 92, 22, 59, 57, 19, 65, 48, 37, 86, 26, 42, 
			51, 73, 88, 7, 21, 34, 69, 79, 61, 45, 23, 18, 80, 50, 35, 16, 60, 84, 14, 58, 6, 3, 71, 96, 12};
		System.out.println(quickselect(test2,0)); //1
		System.out.println(quickselect(test2,49)); //50
		System.out.println(quickselect(test2,99)); //100
		System.out.println(quickselect(test2,100)); //Invalid k */
		
	}
	
}