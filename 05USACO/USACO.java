import java.util.*;
import java.io.*;

//William Hong, MKS22X Pd 6
//Partner: Jenny Gao

public class USACO{

	//bronze problem
	//scan.nextInt() was the most useful tool for this section of the problem
	public static int bronze(String filename){
		int answer = 0;
		try{
			Scanner scan = new Scanner(new File(filename));
				
			//The first 4 items are R, C, E, and N
			int row = scan.nextInt(), col = scan.nextInt(), 
				elevationFinal = scan.nextInt(), numDig = scan.nextInt();
			int[][] lake = new int[row][col]; 
			
			//Lines 2 to R+1 - initial lake
			for (int r=0; r < row; r++){
				for (int c=0; c < col; c++){
					lake[r][c] = scan.nextInt(); 
				}
			}
			
			//Retreiving digging instructions
			//"digging" is the array that stores the instructions
			int[][] digging = new int[numDig][3];
			for (int r=0; r < numDig; r++){
				for (int c=0; c < 3; c++){
					digging[r][c] = scan.nextInt();
				}
			}
			
			//loop thru number of digging instructions
			int execute=0;
			while (execute < numDig){
				int R_s = digging[execute][0] - 1, C_s = digging[execute][1] - 1, D_s = digging[execute][2];

				//make a new temproary array that represents the 3x3 cow herd
				int[][] cows = new int[3][3];
				int i=0;
				cows[0][0] = lake[R_s][C_s];
				cows[0][1] = lake[R_s][C_s+1];
				cows[0][2] = lake[R_s][C_s+2];
				
				cows[1][0] = lake[R_s+1][C_s];			
				cows[1][1] = lake[R_s+1][C_s+1];			
				cows[1][2] = lake[R_s+1][C_s+2];
				
				cows[2][0] = lake[R_s+2][C_s];
				cows[2][1] = lake[R_s+2][C_s+1];
				cows[2][2] = lake[R_s+2][C_s+2];
				
				//find initial max elevation first to stomp down 
				int maxElev = cows[0][0];
				for (int r=0; r < 3; r++){
					for (int c=0; c < 3; c++){
						if (cows[r][c] > maxElev){
							maxElev = cows[r][c]; }
					}
				}
			
				//commence the stomping for highest squares first
				for (int r=0; r < 3; r++){
					for (int c=0; c < 3; c++){
						if (cows[r][c] == maxElev){
							cows[r][c] -= D_s; }
					}
				}
				
				//match the other ones to stomp down
				int matchThis = maxElev - D_s;
				for (int r=0; r < 3; r++){ 
					for (int c=0; c < 3; c++){
						if (cows[r][c] > matchThis){
							cows[r][c] = matchThis; }
					}
				}

				//going back to the original lake
				lake[R_s][C_s] = cows[0][0];
				lake[R_s][C_s+1] = cows[0][1];
				lake[R_s][C_s+2] = cows[0][2];
				
				lake[R_s+1][C_s] = cows[1][0];
				lake[R_s+1][C_s+1] = cows[1][1];
				lake[R_s+1][C_s+2] = cows[1][2];
				
				lake[R_s+2][C_s] = cows[2][0];
				lake[R_s+2][C_s+1] = cows[2][1];
				lake[R_s+2][C_s+2] = cows[2][2];
					
				execute += 1;
			}

			//accounting for the elevationFinal (areas which are above ground are 0)
			for (int r=0; r < lake.length; r++){
				for (int c=0; c < lake[0].length; c++){
					if (lake[r][c] - elevationFinal > 0){
						lake[r][c] = 0; }
					else{
						lake[r][c] = Math.abs(lake[r][c] - elevationFinal); }
				}	
			}
		
			//final step: finding the sum
			int totalDepth = 0;
			for (int r=0; r < lake.length; r++){
				for (int c=0; c < lake[0].length; c++){
					totalDepth += lake[r][c];
				}
			}

			answer = 5184 * totalDepth;
			
		}	
		catch(FileNotFoundException e){
						
			System.out.println("Error! File not found");
			System.exit(1); }
		
		return answer;
			
	}
	
	//testing
	public static void main(String[]args){
		USACO x = new USACO();
		System.out.println(x.bronze("testfile1.txt")); //342144
		System.out.println(x.bronze("testfile2.txt")); //102762432
		System.out.println(x.bronze("testfile3.txt")); //1058992704
	}
	
	
	

}