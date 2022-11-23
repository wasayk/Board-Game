/*
 * This class represents the Evaluation of our game
 * @author Abdul-Wasay Khan.
 */
public class Evaluate{
	   /*
	    * This is the initial class so we will be
	    * initializing the member variables here
	    */	
	private int size;
	private int tilesToWin;
	private int maxLevels; //not used
	//instance variable that stores the board
	private char[][] gameBoard;;
	
	
	   /*
	    * Constructor Evaluate
	    * @param size size of the board
	    * @param tilesToWin adjacent tiles to Win
	    */
	public Evaluate(int size, int tilesToWin, int maxLevels) {
		this.tilesToWin = tilesToWin;
		this.size = size;
		// creating new 2d character array with size 
		gameBoard = new char[size][size];
		
		// looping through each position and making each position empty
		for(int row=0; row < size; row++){
		    for(int col= 0; col <  size; col++){
		    	gameBoard[row][col] = 'e';
		    }
		}
	}
	
	   /*
	    * create a new Dictionary of type Dictionary with desired size
	    * @return the new Dictionary
	    */
	 public Dictionary createDictionary() {
		 Dictionary newDict = new Dictionary(9887);
		 return newDict;
	 }
	 
	   /*
	    * represents the content of the 2d array as a string
	    * then checks whether there is a record in dict with this string as the key
	    * @return the Dictionary object that contains it or returns null
	    */
	 public Record repeatedState (Dictionary dict) {
		 
		 // creating empty string
		 String s = "";
		 //looping through our gameboard
		 for(int i=0;i <  size;i++){
			    for(int j=0; j <  size;j++){
			    	// adding each position to our string
			        s = s + gameBoard[i][j];
			    }
		 }
			
		 //dict.get does our checking for us with our string as the attribute and returns either null or our record
		return dict.get(s);
		 
	 }
	 
	   /*
	    * represents the content of the 2d array as a string
	    * then creates  an object of class Record that stores this string as the key
	    * then puts this record into dict if it is not a duplicate
	    */
	 public void insertState(Dictionary dict, int score, int level) {
		 
		 //creating empty string
		 String s = "";
		 
		 //looping through our gameboard
		 for(int i=0;i <  size;i++){
			    for(int j=0; j <  size;j++){
			    	// adding each position to our string
			        s = s + gameBoard[i][j];
			    }
		 }
		 
		 //creating object of class record that stores this string as key
		 Record rec = new Record(s, score, level);
		 // putting this record into dict if it is not a duplicate (put checks for this)
		 dict.put(rec);
			
		 
	 }
	 
	   /*
	    * stores the symbol into the gameboard at a specific position
	    */
	 public void storePlay(int row, int col, char symbol) {
		 gameBoard[row][col] = symbol;
		 
	 }
		 
		
	   /*
	    * checks to see if gameboard at a specific position is empty
	    * @return true if the position is empty
	    * @return false if it is not empty
	    */
	 public boolean squareIsEmpty (int row, int col) {
		 if (gameBoard[row][col] == ('e')) {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	   /*
	    * checks to see if gameboard at a specific position is the computer's tile
	    * @return true if the position is the computer's tile
	    * @return false if it is not the computer's tile
	    */
	 public boolean tileOfComputer (int row, int col) {
		 if (gameBoard[row][col] == 'c') {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	   /*
	    * checks to see if gameboard at a specific position is the human's tile
	    * @return true if the position is the human's tile
	    * @return false if it is not the human's tile
	    */
	 public boolean tileOfHuman (int row, int col) {
		 if (gameBoard[row][col] == 'h') {
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	   /*
	    * checks to see if gameboard has a number of adjacent tiles of type symbol
	    * in a column, row or diagonal
	    * @return true if there are 
	    * @return false if not
	    */
	 public boolean wins (char symbol) {
		 //checking for adjacent tiles in a row
		 //looping through each row
		 for (int row = 0; row < size; row++) {
			 //setting a counter to 0 that will count how many adjacent tiles
			 // if we loop through all the columns in a row and move on to the next row
			 // counter is automatically reset to 0
		 	int counter = 0;
		 	//looping through the columns
			for(int col = 0; col < size ; col++) {
				 // if we find the desired symbol at a position
				 if (gameBoard[row][col] == symbol) {
					 // we increment the counter
					 counter++;
					 
					 //if the counter reaches the required tiles to win
				 	if (counter == tilesToWin) {
				 		// we return true
				 		return true;
					 }
				 	
				 	// else if we don't find a symbol at the position, we reset our counter to 0
				 } else {
					 counter = 0;
					}
				 } 
		       }
		    


		 //checking for adjacent tiles in a column
		 //looping through each column
		 for (int col = 0; col < size; col++) {
			 //setting a counter to 0 that will count how many adjacent tiles
			 // if we loop through all the rows in a column and move on to the next column
			 // counter is automatically reset to 0
			int counter = 0;
			//looping through the row
			for(int row = 0; row < size; row++) {
				 // if we find the desired symbol at a position
				 if (gameBoard[row][col] == symbol) {
					// we increment the counter
					 counter++;
					 
					//if the counter reaches the required tiles to win
				 	if (counter == tilesToWin) {
				 	// we return true
				 		return true;
				 	}
				 	
				 // else if we don't find a symbol at the position, we reset our counter to 0
				} else {
					counter = 0;
			 	}
			 }					 
		 }
		    
		 	
		 //checking for adjacent tiles in a right diagonal ( \ )
		//looping through the row
		 for (int row = 0; row < size; row++) {
			//looping through each column
		 	for (int col = 0; col < size; col++) {
				 //setting a counter to 0 that will count how many adjacent tiles
				 // if we loop through all the rows and columns in a diagonal and move on to the next diagonal
				 // counter is automatically reset to 0
		 		int counter = 0;
		 		//looping through potential diagonals
		 		for (int diagonal = 0; diagonal < size-row-col; diagonal++) {
		 			// if we find the desired symbol at a position adjacent in a right diagonal position
		 			if (gameBoard [row+diagonal][col+diagonal] == symbol) {
		 				// we increment the counter
		 				counter++;
		 			
		 				//if the counter reaches the required tiles to win
				 	if (counter == tilesToWin) {
				 		// we return true
				 		return true;
				 	} 
				
				 // else if we don't find a symbol at the position, we reset our counter to 0
		 		} else {
					counter = 0;
		 			}
		 		}
		 	}
		 }
		 	
		 //checking for adjacent tiles in a left diagonal ( / )
		//looping through potential diagonals in a ( / ) direction
		 for (int diagonal = 0; diagonal <= 2 * (size-1); diagonal++) {
			//setting a counter to 0 that will count how many adjacent tiles
			 // if we loop through all the rows and columns in a diagonal and move on to the next diagonal
			 // counter is automatically reset to 0
		 	int counter = 0;
		 	
		 	// creating an integer max that is the max position that a diagonal could reach
		 	int max = Math.min(size-1, diagonal);
		 	// creating an integer min that is the minimum position that a diagonal could reach
		 	int min = Math.max(0, diagonal-size+1);
		 		
		 	//looping through the columns starting at the minimum positions that the diagonal can reach and ending with the max
		 	for (int col = min; col <= max; col++) { 
		 		// setting row equal to the diagonal minus each column we loop through
		 		int row = diagonal - col;
		 		
		 			// if we find the desired symbol at a position adjacent in a left diagonal position
		 			if (gameBoard [row][col] == symbol) {
		 				// we increment the counter
		 				counter++;
		 			
		 			//if the counter reaches the required tiles to win
				 	if (counter == tilesToWin) {
				 		//we return true
				 		return true;
				 	} 
				 	
				 // else if we don't find a symbol at the position, we reset our counter to 0
		 		} else {
					counter = 0;
				}	
		 	}
		 }
		 
		 // if we haven't found any adjacent tiles that match the number of our tiles to win
		 // we return false
		 return false;

	 }
	 
	   /*
	    * checks to see if gameboard has reached a draw
	    * @return true if there are no empty tiles left
	    * @return false if there is empty tiles
	    */
	 public boolean isDraw() {
		 // we create a boolean variable that is assigned true
		 boolean draw = true;
		 //looping through our gameboard
		 for (int row = 0; row < size; row++) {
			    for (int col = 0; col < size; col++) {
			    	//if there are any positions that are empty
			        if (gameBoard[row][col] == 'e'){
			        	//there is no draw and we assign false to draw
			        	draw = false;
			        }
			    }
		 }
		 // we return draw (false if empty positions, true otherwise)
		 return draw;
	 }
	 
	   /*
	    * evaluates the gameboard based of conditions
	    * @return 0 if the human has won (adjacent tiles to win 'h')
	    * @return 3 if the computer has won (adjacent tiles to win 'c')
	    * @return 2 if there is a draw (no empty positions left)
	    * @return 1 if the game is ongoing
	    */
	 public int evalBoard() {
		 // local integer that stores the condition of the game
		 int val;
		 // if human has won
		 if (wins('h') == true) {
			 // assign 0 to val
			 val = 0;
		 }
		 // if computer has won
		 else if (wins('c') == true) {
			 //assign 3 to val
			 val = 3;
		 }
		 
		 // if there is a draw
		 else if (isDraw() == true) {
			 //assign 2 to val
			 val = 2;
		 }
		 
		 // if game is ongoing
		 else {
			 //assign 1 to val
			 val = 1;
		 }
		 //return the val
		 return val;
 
	 }
		 
}
	 
	 
		 
		 
	 
	 
	 
	 


