/*package test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.Assert.assertEquals;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.Before;
import org.junit.Test;

import test.test1.Points;
import tictactoe.Mark;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Student;

public class TicTacToeTests_STUDENT {
	private TicTacToeBoard ticTacToeBoard_STUDENT;
	
	@Retention(value=RUNTIME)
	@Target(value=METHOD)
	public @interface Points {
		int value();
	
	@Before public void setUp() {
		ticTacToeBoard_STUDENT = new TicTacToeBoardImpl_Student();
	}
	@Points(value=0)
	@Test public void emptyBoardTest()
	{
		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				assertEquals(null, ticTacToeBoard_STUDENT.getMark(i, j));
			}
		}
		assertEquals(Mark.X, ticTacToeBoard_STUDENT.getTurn());
		assertEquals(false, ticTacToeBoard_STUDENT.isGameOver());
	}
	@Points(value=0)
	@Test public void oneSymbolTest()
	{		
		final int ROW = 0; 
		final int COLUMN = 2;
		final Mark MARK = Mark.X;

		ticTacToeBoard_STUDENT.setMark(ROW, COLUMN);

		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				if(i == ROW && j == COLUMN)
				{
					assertEquals(MARK, ticTacToeBoard_STUDENT.getMark(i, j));
				}
				else
				{
					assertEquals(null, ticTacToeBoard_STUDENT.getMark(i, j));
				}
			}
		}
		assertEquals(Mark.O, ticTacToeBoard_STUDENT.getTurn());
		assertEquals(false, ticTacToeBoard_STUDENT.isGameOver());
	}
	@Points(value=0)
	@Test
	public void twoSymbolTest()
	{
		final int ROW1 = 1;
		final int COLUMN1 = 2;
		Mark MARK1 = Mark.X;
		ticTacToeBoard_STUDENT.setMark(ROW1, COLUMN1);

		final int ROW2 = 1;
		final int COLUMN2 = 1;
		Mark MARK2 = Mark.O;
		ticTacToeBoard_STUDENT.setMark(ROW2, COLUMN2);

		for(int i = 0; i < TicTacToeBoard.ROW_COUNT; i++)
		{
			for(int j = 0; j < TicTacToeBoard.COLUMN_COUNT; j++)
			{
				if(i == ROW1 && j == COLUMN1)
				{
					assertEquals(MARK1, ticTacToeBoard_STUDENT.getMark(i, j));
				}
				else if(i == ROW2 && j == COLUMN2)
				{
					assertEquals(MARK2, ticTacToeBoard_STUDENT.getMark(i, j));
				}
				else
				{
					assertEquals(null, ticTacToeBoard_STUDENT.getMark(i, j));
				}
			}
		}
		assertEquals(Mark.X, ticTacToeBoard_STUDENT.getTurn());
		assertEquals(false, ticTacToeBoard_STUDENT.isGameOver());
	}
	@Points(value=0)
	@Test(expected=AssertionError.class)
	public void setMark_OutOfRange()
	{
		final int ROW1 = 4;
		final int COLUMN1 = 4;
		ticTacToeBoard_STUDENT.setMark(ROW1, COLUMN1);
	}
	@Points(value=0)
	@Test public void earlyWinnerTest()
	{
		int row, column;

		row = 1; column = 0;
		ticTacToeBoard_STUDENT.setMark(row, column);

		row = 2; column = 2;
		ticTacToeBoard_STUDENT.setMark(row, column);

		row = 1; column = 1;
		ticTacToeBoard_STUDENT.setMark(row, column);

		row = 2; column = 1;
		ticTacToeBoard_STUDENT.setMark(row, column);

		row = 1; column = 2;
		ticTacToeBoard_STUDENT.setMark(row, column);
		
		assertEquals(null, ticTacToeBoard_STUDENT.getTurn());
		assertEquals(true, ticTacToeBoard_STUDENT.isGameOver());
		assertEquals(Mark.X, ticTacToeBoard_STUDENT.getWinner());
	}
}*/