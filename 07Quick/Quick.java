import java.util.*;

public class Quick{

	
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }

	public static int part(int[] data, int start, int end){
		int randomIndex = randomWithRange(start,end);
		int partition = data[randomIndex];
		//System.out.println(partition); //for testing purposes only
		
		
		//swap the partition and beginning element first
		data[randomIndex] = data[start];
		data[start] = partition;

		int i1 = start+1, i2 = start+1, goal = start;
		//objective is to get variable "goal" to the end
		while (i1 <= end){
			if (data[i1] < partition){
				
				int larger = data[i2];
				//data[i] is small so keep it to left of partition
				data[i2] = data[i1];
				
				//as the i is farther down to the right, it should assume "larger" values
				data[i1] = larger;
				
				//our "goal" is being updated as well
				goal = i2;
				i2 += 1; }
				
			i1 += 1;
		}
	
		data[start] = data[goal];
		data[goal] = partition;

		return goal;
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
				
			//choice 1: eliminate smaller choices (left)
			if (answer < k){
				start = answer+1;
				answer = part(data,start,end); }
				
			//choice 2: eliminate larger choices (right)
			else{
				end = answer-1;
				answer = part(data,start,end); }
					
		}
		
		return data[answer];
	}
	
	private static void helper(int[] data, int start, int end){
		if (start >= end){
			return; }
			
		int partition = part(data,start,end);
		helper(data, start, partition-1);
		helper(data, partition+1, end);
		
	}
	
	public static void quicksort(int[] data){
		helper(data, 0, data.length-1); }
		
	public static String toString(int[] data){
		String answer = "";
		int i=0;
		while (i < data.length){
			answer += data[i] + " ";
			i += 1; }
			
		return answer;
	}
	
	
	//testing...
	public static void main (String[] args){
		/** Quick x = new Quick();
		
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

		int[] test4 = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
		System.out.println(x.quickselect(test4,4)); //3
		System.out.println(x.quickselect(test4,3)); //3
		System.out.println(x.quickselect(test4,3)); //3
		
		int[] test5 = new int[]{4, 0, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2};
		System.out.println(x.quickselect(test5,0)); //0
		System.out.println(x.quickselect(test5,3)); //3
		System.out.println(x.quickselect(test5,6)); //3
		System.out.println(x.quickselect(test5,9)); //3
		System.out.println(x.quickselect(test5,13)); //4
		
		x.quicksort(test5);
		System.out.println(x.toString(test5));
		
		int[] test6 = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,};
		x.quicksort(test6);
		System.out.println(x.toString(test6));
		
		int[] test7 = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 1, 2,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,};
		x.quicksort(test7);
		System.out.println(x.toString(test7)); */
		
	}

}