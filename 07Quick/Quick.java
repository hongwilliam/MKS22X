import java.util.*;

public class Quick{

	
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }

	//UPDATE: used dutch flag partion psuedo code from class
	public static int part(int[] data, int start, int end){
		int randomIndex = randomWithRange(start, end);
		int partition = data[randomIndex];
		//System.out.println(partition); //for testing purposes only
		
		//swap the partition and beginning element first
		int swap1 = data[randomIndex], swap2 = data[start];
		data[randomIndex] = swap2;
		data[start] = swap1;

		int i=start, low = start, high = end; 
		while (i <= high){
			//"ignore duplicates"
			if (data[i] == partition){
				i += 1; }
				
			else{
				if (data[i] < partition){
					//swap the current element and the low
					swap1 = data[i];
					swap2 = data[low];
					data[i] = swap2;
					data[low] = swap1; 
				
					low += 1;
					i += 1; }
					
				else{
					//swap the current and the high
					swap1 = data[i];
					swap2 = data[high];
					data[i] = swap2;
					data[high] = swap1;
					
					high -= 1; }
			}
		}	
		
		return (low + high) / 2;
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
		//base case
		if (start >= end){
			return; }
			
		int randomIndex = randomWithRange(start, end);
		int partition = data[randomIndex];
		//System.out.println(partition); //for testing purposes only
		
		//swap the partition and beginning element first
		int swap1 = data[randomIndex], swap2 = data[start];
		data[randomIndex] = swap2;
		data[start] = swap1;
		
		//the idea is nearly the same for the partition function - dutch flag
		int i = start, low = start, high = end;
		while (i <= high){
		
			if (data[i] == partition){
				i += 1; }
			else{
				if (data[i] < partition){
					swap1 = data[i];
					swap2 = data[low];
					data[i] = swap2;
					data[low] = swap1;
					
					i += 1;
					low += 1; }
					
				else{
					swap1 = data[i];
					swap2 = data[high];
					data[i] = swap2;
					data[high] = swap1;
					
					high -= 1; }
					
			}
		}
		
		helper(data, start, low-1);
		helper(data, high+1, end);
		
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
		/* Quick x = new Quick();
		
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