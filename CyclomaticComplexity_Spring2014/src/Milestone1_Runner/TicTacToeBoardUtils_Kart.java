package Milestone1_Runner;

public class TicTacToeBoardUtils_Kart {
	public static String toString(TicTacToeBoard board)
	{
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				Mark mark = board.getMark(i, j);
				String string = (mark == null ? " " : mark.toString());
				sb.append(string);
				if(j < (TicTacToeBoard.COLUMN_COUNT - 1)) sb.append("|");
			}
			sb.append("\n");
			if(i < (TicTacToeBoard.ROW_COUNT - 1)) sb.append("-----\n");
		}
		return sb.toString();
	}
}
