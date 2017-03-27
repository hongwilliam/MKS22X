public class Merge{

	public static void merge (int[] a, int[] b, int[] destination){
		int iA = 0, iB = 0, x = 0;
		
		while (x < destination.length){
			if (iA != a.length && (iB == b.length || a[iA] <= b[iB] ) ){
				destination[x] = a[iA];
				iA += 1; }
			
			else{
				destination[x] = b[iB];
				iB += 1; }
			x += 1;
		}
	}
	
	//to make things easier
	public static int[] copy(int[] ary, int start, int end){
		int[] answer = new int[end - start];
		int check = start, i = 0;
		
		while (check < end){
			answer[i] = ary[check];
			i += 1;
			check += 1; }
			
		return answer;
	}
	
	//pseudo code from course website
	public static void mergesort(int[] ary){
		
		//if base case... then stop
		if (ary.length <= 1){
			return; }
			
		else{
			int midpt = ary.length / 2;
			
			//copy the left and right sides
			int[] left = copy(ary, 0, midpt);
			int[] right = copy(ary, midpt, ary.length);
			
			//then mergesort both sides
			mergesort(left);
			mergesort(right);
			
			//finally. merge the two halves into the original array
			merge(left, right, ary);
		}
	}
	
	//for testing purposes
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }
		
	public static String toString(int[] ary){
		String answer = "";
		int i = 0;
		while (i < ary.length){
			answer += ary[i] + " ";
			i += 1; }
		return answer; }
		
	public static void main(String[] args){
		
		int[] test = new int[1000];
		
		int i = 0;
		while (i < 1000){
			test[i] = randomWithRange(0, 1000);
			i += 1; }
			
		mergesort(test);
		System.out.println(toString(test));
	}

}