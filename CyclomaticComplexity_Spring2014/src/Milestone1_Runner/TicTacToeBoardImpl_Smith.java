package Milestone1_Runner;
/*package tictactoe;


public class TicTacToeBoardImpl_Smith implements TicTacToeBoard
{
	//Symbolics:
	protected static final int NO_MOVE = -1;
	protected static final int NO_MATCH = -1;

	protected int[] movesArray;
	
	public TicTacToeBoardImpl_Smith()
	{
		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
		movesArray = new int[CELL_COUNT];
		for(int i = 0; i < CELL_COUNT; i++)
		{
			movesArray[i] = NO_MOVE;
		}
	}
	
	public Mark getMark(int row, int column) {
		return null;
	}

	//rv == null <==> it is neither player's turn (i.e. game is over)
	public Mark getTurn() {
		return Mark.O;
	}

	//part of post: rv == null <==> game ended in a tie
	public Mark getWinner() {
		assert isGameOver() : "Game is not over!";
		return null;
	}

	public boolean isGameOver() {
		return false;
	}

	public void setMark(int row, int column) {
		assert 0 <= row : "0 > " + row + " = row!";
		assert 0 <= column : "0 > " + column + " = column!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = ROW_COUNT";
		assert column < COLUMN_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = COLUMN_COUNT";
		assert 0 <= column : "0 > " + column + " = column!";
		assert !isGameOver() : "Game is over!";
		assert getMark(row, column) == null : "getMark(" + row + ", " + column + ") = " + getMark(row, column) + " is not null!";

		int gridIndex = getGridIndex(row, column);
		int arrayIndex = getArrayIndex(movesArray, gridIndex);
		assert arrayIndex == NO_MATCH : "arrayIndex = " + arrayIndex + "! : row = " + row + " column = " + column + " already occupied!";
		int firstNoMovesIndex = getArrayIndex(movesArray, NO_MOVE);
		
		//part of pre:
		assert firstNoMovesIndex != NO_MATCH : "gridIndex = " + gridIndex + " already appears in movesArray at array index = " + firstNoMovesIndex + "!";
		movesArray[firstNoMovesIndex] = gridIndex;
	}
	
	protected static int getGridIndex(int row, int column)
	{
		assert row >= 0 : "row = " + row + " < 0!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = NUM_ROWS!";
		assert column >= 0 : "column = " + column + " < 0!";
		assert column < ROW_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = NUM_COLUMNS!";
		return row*COLUMN_COUNT + column;
	}

	protected static int getArrayIndex(int[] intArray, int key)
	{
		int i = 0;
		boolean foundMatch = false;
		while(!foundMatch && i < intArray.length)
		{
			foundMatch = (intArray[i] == key);
			if(!foundMatch) i++;
		}
		
		if(!foundMatch)
		{
			return NO_MATCH;
		}
		else
		{
			return i;
		}
	}
}
*/