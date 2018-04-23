package hdriel.phm;

public class Soldier {

	private int number;  // the number soldier
	private char rank;   // the rank of the soldier
	
	public Soldier(){
		this.number = 0;
		this.rank = 'O';
		
	}
	public Soldier(char rank , int number ){
		this.number = number;
		this.rank = rank;	
	}
	
	public boolean isEqual(Soldier s){ return rank == s.rank || number == s.number;}
	public boolean isntEqual(Soldier s){return !isEqual(s);}
	 
	public char getChar(){ return rank;}              // Get the rank of the soldier
	public int getNumber(){ return number;}           // Get the number of the soldier
	public String getName(){ return ""+rank+number;}  // Get the name as char number : "a1"
	
	public void print(){
		 System.out.print(""+rank+number);
	}

}
