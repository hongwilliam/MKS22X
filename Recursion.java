public class Recursion {
	public static String name(){
		return "Hong,William"; }
	
	public static double helper(double n, double guess){

		if (n < 0){
			throw new IllegalArgumentException("n cannot be negative"); }
		
		else{
		
			if (n == 0.0){
				return 0.0; }
			double square = guess * guess, margin = Math.abs(1.0 - square/n);
			if (margin <= 10e-12){
				return guess; }
			
			else{
				return helper(n, (n / guess + guess) / 2.0); }
				
		}
	}

	public static double sqrt(double n){
		if (n < 0){
			throw new IllegalArgumentException("n cannot be negative"); }
			
		else{
			return helper(n, 1);}
	}
	
	//testing...
	public static void main(String[] args){
		System.out.println(sqrt(0.0)); //0.0
		System.out.println(sqrt(0.04253)); //0.20622802913376717
		System.out.println(sqrt(1.0)); //1.0
		System.out.println(sqrt(2.0)); //1.4142135623746899
		System.out.println(sqrt(3.0)); //1.7320508075688772
		System.out.println(sqrt(4.0)); //2.000000000000002
		System.out.println(sqrt(15.5)); //3.9370039370059344
		System.out.println(sqrt(16.1)); //4.012480529547833
		System.out.println(sqrt(64.0)); //8.00000000000017
		System.out.println(sqrt(75.0)); //8.66025403784659
		System.out.println(sqrt(81.0)); //9.000000000007091
		System.out.println(sqrt(101.01)); //10.050373127401787
		System.out.println(sqrt(-5.0)); //IllegalArgumentException: n cannot be negative
	}

}