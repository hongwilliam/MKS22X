import java.util.Random;

public class Quick{

	
	public static int randomWithRange(int min, int max){
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min; }
	
	public static int part(int[] data, int start, int end){
		int randomIndex = randomWithRange(start,end);
		int partition = data[randomIndex];
		System.out.println(partition); //for testing purposes only
		
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

		//for testing purposes only
		int x=0;
		while (x < data.length){
			System.out.print(data[x] + " ");
			x += 1; }
		System.out.print("\n");
		
		return end-largerOrEqual;
	}
	
	public static void main (String[] args){
		int[] test1 = new int[]{2, 4, 7, 9, 1, 6, 8, 5, 3, 0};
		Quick x = new Quick();
		//System.out.println(x.part(test1,0,9));
		
		int[] test2 = new int[]{999,999,999,4,1,0,3,2,999,999,999};
		System.out.println(x.part(test2,0,10));
		
	}
	
}