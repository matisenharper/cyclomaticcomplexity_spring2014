package tictactoe;

/**
 * @author kart
 *
 */
public class TicTacToeBoardUtils {
	public static final int NO_MOVE = -1;
	public static final int NO_MATCH = -1;
	
	private TicTacToeBoardUtils()
	{
		//DO NOT INSTANTIATE!
	}
	
	public static int getRow(int gridIndex)
	{
		assert gridIndex >= 0 : "gridIndex = " + gridIndex + " < 0!";
		assert gridIndex < TicTacToeBoard.ROW_COUNT*TicTacToeBoard.COLUMN_COUNT : "gridIndex = " + gridIndex + " >= " + (TicTacToeBoard.ROW_COUNT*TicTacToeBoard.COLUMN_COUNT) + " = SLOT_COUNT!";
		return gridIndex / TicTacToeBoard.COLUMN_COUNT;
	}
	
	public static int getColumn(int gridIndex)
	{
		assert gridIndex >= 0 : "gridIndex = " + gridIndex + " < 0!";
		assert gridIndex < TicTacToeBoard.ROW_COUNT*TicTacToeBoard.COLUMN_COUNT : "gridIndex = " + gridIndex + " >= " + (TicTacToeBoard.ROW_COUNT*TicTacToeBoard.COLUMN_COUNT) + " = SLOT_COUNT!";
		return gridIndex % TicTacToeBoard.COLUMN_COUNT;
	}
	
	public static int getGridIndex(int row, int column)
	{
		assert row >= 0 : "row = " + row + " < 0!";
		assert row < TicTacToeBoard.ROW_COUNT : "row = " + row + " >= " + TicTacToeBoard.ROW_COUNT + " = NUM_ROWS!";
		assert column >= 0 : "column = " + column + " < 0!";
		assert column < TicTacToeBoard.ROW_COUNT : "column = " + column + " >= " + TicTacToeBoard.COLUMN_COUNT + " = NUM_COLUMNS!";
		return row*TicTacToeBoard.COLUMN_COUNT + column;
	}

	public static int getArrayIndex(int[] intArray, int key)
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
	
	public static String toString(int[] movesArray)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("[");
		int indexOfNoMove = (getArrayIndex(movesArray, NO_MOVE) == NO_MATCH ? movesArray.length : getArrayIndex(movesArray, NO_MOVE));
		for(int i = 0; i < indexOfNoMove; i++)
		{
			sb.append(movesArray[i]);
			if(i < indexOfNoMove - 1) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
