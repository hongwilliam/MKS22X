import java.util.*;

public class MyDeque{

	String[] array = new String[5];
	int front, back;
	
	//useful but not required methods
	public boolean emptyCheck(){
		return data[front] == null; }
		
	//when you reach the end, wrap around
	public boolean fullCheck(){
		return (!emptyCheck() && (front == (back+1) % data.length)); }
		
	/* fill this in later
	public void resize(){
			
	} */
	
	public addFirst(String s){
		if (s == null){
			throw new NullPointerException(); }
		
		else{
			if (front == 0){
				front = data.length; }
				
			/* else{
				resize(); } */
		}
		
		front -= 1;
		data[front] = s;
	}
	
	public void addLast(String s){
		if (s == null){
			throw new NullPointerException(); }
			
		else{
			if (back == data.length -1){
				back = -1; }
				
			/* else{
			 resize(); } */
		}
		
		back += 1;
		data[back] = s;
	}
	
	public String removeLast(){
		if (emptyCheck()){
			throw new NoSuchElementException(); }
			
		String original = data[back];
		data[back] = null;
		back -= 1;
		
		if (back == -1){
			back = data.length = 1; }
			
		return original;
	}
	
	public String removeFirst(){
		if (emptyCheck()){
			throw new NoSuchElementException(); }
			
		String original = data[front];
		data[front] = null;
		front += 1;
		
		if (front == data.length){
			front = 0; }
		
		return original;
	}
	
	
}