public class Merge{

	public static void merge (int[] a, int[] b, int[] destination){
		
		int iA = 0, iB = 0, x = 0;
		
		while (x < destination.length){
			if (iA != a.length && a[iA] <= b[iB]){
				destination[x] = a[iA];
				iA += 1; }
			
			else{
				if (iB == b.length){
					destination[x] = a[iA];
					iA += 1; }
				else{
					destination[x] = b[iB];
					iB += 1; }
			}
			x += 1;
			
		}
		
	}
	
	//for testing purposes
	public static String toString(int[] data){
		String answer = "";
		int i = 0;
		while (i < data.length){
			answer += data[i] + " " ;
			i += 1; }
			
		return answer;
	}
		
	public static void main(String[] args){
		int[] a = new int[]{0, 2, 4, 6, 8};
		int[] b = new int[]{1, 3, 5, 7, 9};
		int[] c = new int[10];
			
		merge(a, b, c);
		System.out.println(toString(c));
			
	}


}