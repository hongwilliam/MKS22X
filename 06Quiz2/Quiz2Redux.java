import java.util.*;

public class Quiz2Redux{
	
	
	//the substring changes for each recursive call
	//the int i keeps track of the string's index
	
	//note: I had trouble due to stack overflow of a recursive void funvtion
	public static void help(ArrayList<String> words, String s, String substring, int i){
		int len = s.length();
		
		//terminating case
		if (i == len){
			words.add(substring); 
			return; }
			
		//similar to groupSum coding bat problem
		//either include the larger substring or not
		help (words, s, substring, i+1);
		help (words, s, substring + s.charAt(i), i+1); 
		return;
		
	}
		
	public static ArrayList<String> combinations(String s){
		ArrayList<String> words = new ArrayList<String>();
		help(words, s, "", 0);
		Collections.sort(words);
		return words;
	}
	
	//testing...
	public static void main(String[] args){
		System.out.println(combinations("abcd"));
		//[, a, ab, abc, abcd, abd, ac, acd, ad, b, bc, bcd, bd, c, cd, d]
	}
	
}
	
	