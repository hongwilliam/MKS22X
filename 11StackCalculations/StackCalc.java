import java.util.*;
import java.lang.*;

public class StackCalc{
	
	public static boolean isOp(String s){
		return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%") ); }
		
	//for subtraction and division, this was not commutative
	public static String apply(String op, String a, String b){
		
		double A = Double.parseDouble(a);
		double B = Double.parseDouble(b); 
		String answer = "";
		
		if (op.equals("+")){
			answer += A + B; }
		else{
			if (op.equals("-")){
				answer += B - A; }
			else{
				if (op.equals("*")){
					answer += A * B; }
				else{
					if (op.equals("/")){
						answer += B / A; }
					else{
						answer += A % B; }
				}
			}
		}
		
		return answer;
	}
		
	//mr k gave us pseudo code for this
	public static double eval(String s){
		String[] tokens = s.split(" ");
		Stack<String> nums = new Stack<String>();
		int i=0;
		while (i < tokens.length){
			if (isOp(tokens[i])){
				nums.push(apply(tokens[i], "" + nums.pop(), "" + nums.pop() )); }
			else{
				nums.push(tokens[i]); }
			i += 1; }
		return Double.parseDouble("" + nums.pop() );
	}
	
	public static void main(String[] args){
		System.out.println(StackCalc.eval("10 2 +"));//12.0
		System.out.println(StackCalc.eval("10 2 -"));//8.0
		System.out.println(StackCalc.eval("10 2.0 +"));//12.0
		System.out.println(StackCalc.eval("11 3 - 4 + 2.5 *"));//30.0
		System.out.println(StackCalc.eval("8 2 + 99 9 - * 2 + 9 -"));//839.0
		System.out.println(StackCalc.eval("10 2 + 10 * 1 + 1 1 1 + + + 10 10 + -"));//104.0
	}
	
}