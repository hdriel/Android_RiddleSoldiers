package hdriel.phm;


public class ShowSoldiers {

	private Soldier board[][];            // board of soldiers
	private int size;                 // the size of the squar board 


	void setSoldierInArr(int i, int j, char c, int n){ 
		if(i >= 0 && i < size && j >= 0 && j < size)
			board[i][j] = new Soldier(c,n);
	}
	
	private boolean checkEachRow(){
		for (int k = 0; k < size; k++)
		{
			for (int m = 0; m < size; m++)
			{
				for (int i = k; i < size; i++)
				{
					for (int j = 0; j < size; j++)
					{
						if (m != j && board[k][m].isEqual(board[i][j])){
							return false;
						}
					}
					break;
				}
			}
		}
		return true;
		
	}      // Check if there are same soldier in the same row
	private boolean checkEachCol(){
		for (int k = 0; k < size; k++)
		{
			for (int m = 0; m < size; m++)
			{
				for (int i = k; i < size; i++)
				{
					for (int j = 0; j < size; j++)
					{
						if (m != j && board[m][k].isEqual(board[j][i])){
							return false;
						}
					}
					break;
				}
			}
		}
		
		return true;		
	}      // Check if there are same soldier in the same col
	private boolean checkEachMainDiag(){
		for (int r = 0; r < size; r++)
		{	
			for (int m = 0; m < size; m++)
			{
				for (int k = 0; k < size; k++)
				{
					for (int i = 0; i < size; i++)
					{
						for (int j = 0; j < size; j++)
						{
							if ((i > j) && (i - j == r) && (m - k == r) && k != j && board[m][k].isEqual(board[i][j])){
								return false;
							}
							else if ((j > i) && (j - i == r) && (k - m == r) && k != j && board[m][k].isEqual(board[i][j])){
								return false;
							}
							else if ((i == j) && (m == k) && k != j && board[m][k].isEqual(board[i][j])) {
								return false;
							}
						}
					
					}
				}
			}
		}
		return true;
	} // Check if there are same soldier in the same main diagnoal
	private boolean checkEachSecDiag(){
		for (int cmp = 0; cmp < size * 2; cmp++)
		{
			for (int m = 0; m < size; m++)
			{
				for (int k = 0; k < size; k++)
				{
					for (int i = 0; i < size; i++)
					{
						for (int j = 0; j < size; j++)
						{
							if ((j + i == cmp) && (m + k == cmp) && k != j && board[m][k].isEqual(board[i][j])){
								return false;
							}
						}
						
					}
				}
			}
		}
		
		return true;
	}  // Check if there are same soldier in the same sec diagnoal

	public ShowSoldiers(int size){
		this.size = size;
		if (this.size <= 0) this.size = 5;
		board = new Soldier[this.size][];
		for (int i = 0; i < this.size; i++){
			board[i] = new Soldier[this.size];
		}
		
		board[0][0] = new Soldier('c', 1);
		board[0][1] = new Soldier('c', 2);
		board[0][2] = new Soldier('c', 3);
		board[0][3] = new Soldier('c', 4);
		board[0][4] = new Soldier('c', 5);
		
		board[1][0] = new Soldier('s', 1);
		board[1][1] = new Soldier('s', 2); 
		board[1][2] = new Soldier('s', 3); 
		board[1][3] = new Soldier('s', 4); 
		board[1][4] = new Soldier('s', 5); 
		
		board[2][0] = new Soldier('t', 1);
		board[2][1] = new Soldier('t', 2);
		board[2][2] = new Soldier('t', 3); 
		board[2][3] = new Soldier('t', 4); 
		board[2][4] = new Soldier('t', 5); 
		
		board[3][0] = new Soldier('e', 1);
		board[3][1] = new Soldier('e', 2);
		board[3][2] = new Soldier('e', 3); 
		board[3][3] = new Soldier('e', 4);  
		board[3][4] = new Soldier('e', 5); 
		
		board[4][0] = new Soldier('h', 1); 
		board[4][1] = new Soldier('h', 2);
		board[4][2] = new Soldier('h', 3); 
		board[4][3] = new Soldier('h', 4); 
		board[4][4] = new Soldier('h', 5); 
	}           // Ctor
	
	public ShowSoldiers(){
		this.size = 5;
		board = new Soldier[this.size][];
		for (int i = 0; i < this.size; i++){
			board[i] = new Soldier[this.size];
		}
		// solution with out diagonals
		/*
		board[0][0] = new Soldier('s', 1);
		board[0][1] = new Soldier('t', 2);
		board[0][2] = new Soldier('c', 3);
		board[0][3] = new Soldier('h', 4);
		board[0][4] = new Soldier('e', 5);
		
		board[1][0] = new Soldier('e', 2);
		board[1][1] = new Soldier('s', 3); 
		board[1][2] = new Soldier('t', 4); 
		board[1][3] = new Soldier('c', 5); 
		board[1][4] = new Soldier('h', 1); 
		
		board[2][0] = new Soldier('h', 3);
		board[2][1] = new Soldier('e', 4);
		board[2][2] = new Soldier('s', 5); 
		board[2][3] = new Soldier('t', 1); 
		board[2][4] = new Soldier('c', 2); 
		
		board[3][0] = new Soldier('c', 4);
		board[3][1] = new Soldier('h', 5);
		board[3][2] = new Soldier('e', 1); 
		board[3][3] = new Soldier('s', 2);  
		board[3][4] = new Soldier('t', 3); 
		
		board[4][0] = new Soldier('t', 5); 
		board[4][1] = new Soldier('c', 1);
		board[4][2] = new Soldier('h', 2); 
		board[4][3] = new Soldier('e', 3); 
		board[4][4] = new Soldier('s', 4);
		*/
		
		// 1 = red
		// 2 = green
		// 3 = blue
		// 4 = pink
		// 5 = brown
		
		// solution with diagonals
		board[0][0] = new Soldier('s', 2);
		board[0][1] = new Soldier('t', 4);
		board[0][2] = new Soldier('e', 1);
		board[0][3] = new Soldier('h', 3);
		board[0][4] = new Soldier('c', 5);
		
		board[1][0] = new Soldier('e', 3);
		board[1][1] = new Soldier('h', 5); 
		board[1][2] = new Soldier('c', 2); 
		board[1][3] = new Soldier('s', 4); 
		board[1][4] = new Soldier('t', 1); 
		
		board[2][0] = new Soldier('c', 4);
		board[2][1] = new Soldier('s', 1);
		board[2][2] = new Soldier('t', 3); 
		board[2][3] = new Soldier('e', 5); 
		board[2][4] = new Soldier('h', 2); 
		
		board[3][0] = new Soldier('t', 5);
		board[3][1] = new Soldier('e', 2);
		board[3][2] = new Soldier('h', 4); 
		board[3][3] = new Soldier('c', 1);  
		board[3][4] = new Soldier('s', 3); 
		
		board[4][0] = new Soldier('h', 1); 
		board[4][1] = new Soldier('c', 3);
		board[4][2] = new Soldier('s', 5); 
		board[4][3] = new Soldier('t', 2); 
		board[4][4] = new Soldier('e', 4); 
	}

	
	public boolean replaceSoldiers(int x1, int y1, int x2, int y2){
		if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0){
			return false;
		}
		else{
			Soldier s = board[x1][y1];
			board[x1][y1] = board[x2][y2];
			board[x2][y2] = s;
			return true;
		}
	}
	
	public void print(){
		System.out.println();
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();	
	}                         // Print the board
	
	public boolean finish(boolean includeDiags){
		if(includeDiags){
			return checkEachRow() && checkEachCol() && checkEachMainDiag() && checkEachSecDiag();
		}
		else{
			return checkEachRow() && checkEachCol();
		}
	
	}                    // Check if the game over
	
	public Soldier[][] getSoldiersArray(){return board;}
}
