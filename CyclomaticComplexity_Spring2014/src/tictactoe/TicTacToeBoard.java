package tictactoe;

//Note: By convention, the player with symbol Mark.X always goes first
public interface TicTacToeBoard 
{
	public final static int ROW_COUNT = 3;
	public final static int COLUMN_COUNT = 3;
	
	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of post: rv == null <==> the (row, column) spot on the board is empty
	public Mark getMark(int row, int column);

	//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//part of pre: getMark(row, column) == null
	//part of pre: !isGameOver()
	public void setMark(int row, int column);

	//part of post: rv == null <==> it is neither player's turn (i.e. game is over)
	public Mark getTurn();
	
	//part of post: See Tic-tac-toe rules in order to fill this out
	public boolean isGameOver();
	
	//part of pre: isGameOver()
	//part of post: rv == null <==> neither player won (i.e. the game ended in a tie)
	public Mark getWinner();
}
//
//Mention the constructor in the assignment
//
//In English, what do each of the preconditions of setMark() say?
//		
//For each of the following, answer:
//	
//a) Is the array filling possible?
//b) What Tic-tac-toe board does it correspond to?  (Draw the 3x3 tic-tac-toe board with X's and O's)
//c) What would getTurn() return?
//d) What would isGameOver() return?
//e) What would getWinner() return?
//		
//* [1, 8, 6, 4, 2, 3, -1, -1, -1]
//* [-1, -1, -1, -1, -1, -1, -1, -1, -1]
//* [2, 5, 7, 9, 8, 3, 2, 6, -1]
//* [0, 4, 1, 2, 8, 6, -1, -1, -1]
//* [0, 3, 1, 4, 2, 5, -1, -1, -1]
//* [1, -1, -1, -1, -1, -1, -1, -1, -1]
//* [-1, -1, 2, 0, 6, 7, 9, 3, 4]
//* [1, -2, -2, -2, -2, -2, -2, -2, -2]
//
//For each of the following, write down one filling of the array that corresponds to the board.  If the board is not possible, 
//write "NOT POSSIBLE"
//	
//a)
//X| | 
//_____
// | | 
//_____
// | | 
//b)
// | | 
//_____
//O| |O
//-----
// |X| 
//c)
//X|O|X
//-----
// | | 
//-----
// | | 