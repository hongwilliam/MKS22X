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
		int larger=0, i=start;
		while(i < end){
			if (data[i]  > partition){
				larger += 1; }
			i += 1;
		}

		//put the partition where it belongs
		int swap1 = data[end-larger];
		data[randomIndex] = swap1;
		data[end-larger] = partition;
		
		//the last N elements must be occupied by the larger numbers
		int goLeft = end, goRight = start;
		while (goLeft > end-larger){
			//this is when the element at the end belongs there
			if (data[goLeft] > partition){
				goLeft -= 1; }
			else{
				//otherwise, make the swap with the elements in the front
				//if applicable of course
				if(data[goRight] > partition){
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
		
		return end-larger;
	}
	
	public static void main (String[] args){
		int[] test = new int[]{2, 4, 7, 9, 1, 6, 8, 5, 3, 0};
		Quick x = new Quick();
		//System.out.println(x.part(test, 0, 9));
		System.out.println(x.part(test,0,9));
		
	}
	
}