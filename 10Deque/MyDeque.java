import java.util.*;

public class MyDeque{

	String[] array = new String[5];
	int front = 0, back = 4; //arbitrary values
	
	//useful but not required methods
	public boolean emptyCheck(){
		return array[front] == null; }
		
	//when you reach the end, wrap around
	public boolean fullCheck(){
		return (!emptyCheck() && (front == (back+1) % array.length)); }
		
	public void resize(){
		int n = front;
		//double in capacity if no space left
		String[] temp = new String[array.length * 2];
		
		int i = 0;
		while (i < array.length){
			if (n == array.length){
				n = 0; }
				
			temp[i] = array[n];
			n += 1;
			i += 1;
		}
		
		data = temp;
		front = 0;
		back = (array.length / 2) - 1;
	} 
	
	public addFirst(String s){
		if (s == null){
			throw new NullPointerException(); }
		
		else{
			if (front == 0){
				front = array.length; }
				
			else{
				resize(); }
		}
		
		front -= 1;
		array[front] = s;
	}
	
	public void addLast(String s){
		if (s == null){
			throw new NullPointerException(); }
			
		else{
			if (back == array.length -1){
				back = -1; }
				
			else{
			 resize(); } 
		}
		
		back += 1;
		array[back] = s;
	}
	
	public String removeLast(){
		if (emptyCheck()){
			throw new NoSuchElementException(); }
			
		String original = array[back];
		array[back] = null;
		back -= 1;
		
		if (back == -1){
			back = array.length = 1; }
			
		return original;
	}
	
	public String removeFirst(){
		if (emptyCheck()){
			throw new NoSuchElementException(); }
			
		String original = array[front];
		array[front] = null;
		front += 1;
		
		if (front == array.length){
			front = 0; }
		
		return original;
	}
	
	public static void main(String [] args){}

}