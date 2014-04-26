//Alexandra Hoffpauir
//September 16, 2013

package tictactoe;

public class TicTacToeBoardImpl_Hoffpauir implements TicTacToeBoard
{	
	
	public static void main(String[] args)
	{
		TicTacToeBoardImpl_Hoffpauir game= new TicTacToeBoardImpl_Hoffpauir();
		game.setMark(1,0);
		game.setMark(2,2);
		game.setMark(1, 1);
		game.setMark(2, 1);
		game.setMark(1, 2);
		System.out.println(game.getWinner());
		System.out.println();
		System.out.println(game);
		
	}
	
	
	public final static int ROW_COUNT=3;
	public final static int COLUMN_COUNT=3;
	
	protected static final int NO_MOVE= -1;
	protected static final int NO_MATCH=-1;
	protected int[] movesArray;
	private Mark winner;
	
	//Constructor- makes a tic tac toe board array that is completely empty
	public TicTacToeBoardImpl_Hoffpauir()
	{
		final int CELL_COUNT= ROW_COUNT*COLUMN_COUNT;
		movesArray= new int[CELL_COUNT];
		for(int i=0; i<CELL_COUNT; i++)
		{
			movesArray[i]=NO_MOVE;
		}
		
	}
	
	//Pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
	//Post: rv == null <==> the (row,column) spot on the board is empty
	public Mark getMark(int row, int column)
	{
		assert 0 <= row : "0 > " + row + " = row!";
		assert 0 <= column : "0 > " + column + " = column!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = ROW_COUNT";
		assert column < COLUMN_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = COLUMN_COUNT";
		assert 0 <= column : "0 > " + column + " = column!";
		
			
		int cell=-1;
		Mark result=null;
		if(row==0)
		{
			if(column==0)
				cell=0;
			else if(column==1)
				cell=1;
			else if(column==2)
				cell=2;
		}
		else if(row==1)
		{
			if(column==0)
				cell=3;
			else if(column==1)
				cell=4;
			else if(column==2)
				cell=5;
			
		}
		else if(row==2)
		{
			if(column==0)
				cell=6;
			else if(column==1)
				cell=7;
			else if(column==2)
				cell=8;
		}
		
		for(int i=0; i < movesArray.length; i++)
		{
			if(movesArray[i]==cell)
			{
				if(i==-1)
					result=null;
				else if(i==0)
					result=Mark.X;
				else if (i%2==0)
					result=Mark.X;
				else 
					result=Mark.O;
			}
		}
		return result;
	}
	
	//Pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT 
	//Pre: getMark(row, column) == null
	//Pre: !isGameOver()
	//Post: Steps through the array until it finds a -1 and places the cell number in the array at that location.
	public void setMark(int row, int column)
	{
		assert 0 <= row : "0 > " + row + " = row!";
		assert 0 <= column : "0 > " + column + " = column!";
		assert row < ROW_COUNT : "row = " + row + " >= " + ROW_COUNT + " = ROW_COUNT";
		assert column < COLUMN_COUNT : "column = " + column + " >= " + COLUMN_COUNT + " = COLUMN_COUNT";
		assert 0 <= column : "0 > " + column + " = column!";
		assert !isGameOver() : "Game is over!";
		assert getMark(row, column) == null : "getMark(" + row + ", " + column + ") = " + getMark(row, column) + " is not null!";
		
		
		int cell=-1;
		if(row==0)
		{
			if(column==0)
				cell=0;
			else if(column==1)
				cell=1;
			else if(column==2)
				cell=2;
		}
		else if(row==1)
		{
			if(column==0)
				cell=3;
			else if(column==1)
				cell=4;
			else if(column==2)
				cell=5;
			
		}
		else if(row==2)
		{
			if(column==0)
				cell=6;
			else if(column==1)
				cell=7;
			else if(column==2)
				cell=8;
		}
		
		for(int i=0; i<movesArray.length; i++)
		{
			if(movesArray[i]==-1)
			{
				movesArray[i]=cell;
				break;
			}
		}
		
	}
	
	//Post: rv == null <==>  it is neither player's turn (game over)
	//Post: "number of marks on board is even " -> rv == Mark.X
	//Post: "number of marks on board is odd " -> rv == Mark.O
	public Mark getTurn()
	{
		
		Mark value=null;
		for(int i=0; i < movesArray.length; i++)
		{
			if(movesArray[i]==-1)
			{
				if(i==0 || i%2==0)
				{
					value = Mark.X;	
				}
				else
				{
					value = Mark.O;
				}
				break;
			}
		}
		if(this.isGameOver())
		{
			value=null;
		}
		
		return value;
	}
	
	//Post: Calls on the toString and checks to see if there are any matches in all the possibilities of a winning game
	//Post: Returns false if not, if so sets the winner and returns true.
	public boolean isGameOver()
	{
		boolean result=false;
		String board=this.toString();
		String space=" ";
		if(board.charAt(0)==board.charAt(2) && board.charAt(2)==board.charAt(4) && board.charAt(0)!=' ')
		{
			if(board.charAt(0)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(12)==board.charAt(14) && board.charAt(14)==board.charAt(16) && board.charAt(12)!=' ')
		{
			if(board.charAt(12)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(24)==board.charAt(26) && board.charAt(26)==board.charAt(28) && board.charAt(24)!=' ')
		{
			if(board.charAt(24)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(0)==board.charAt(12) && board.charAt(12)==board.charAt(24) && board.charAt(0)!=' ')
		{
			if(board.charAt(0)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(2)==board.charAt(14) && board.charAt(14)==board.charAt(26) && board.charAt(2)!=' ')
		{
			if(board.charAt(2)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(4)==board.charAt(16) && board.charAt(16)==board.charAt(28) && board.charAt(4)!=' ')
		{
			if(board.charAt(4)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(0)==board.charAt(14) && board.charAt(14)==board.charAt(28) && board.charAt(0)!=' ')
		{
			if(board.charAt(0)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(board.charAt(4)==board.charAt(14) && board.charAt(14)==board.charAt(24) && board.charAt(4)!=' ')
		{
			if(board.charAt(4)=='X')
				this.winner=Mark.X;
			else
				this.winner=Mark.O;
			result=true;
		}
		if(!board.contains(space))
		{
			result=true;
		}
		return result;
	} 
	
	//Pre: isGameOver()
	//Post: rv == null <==> neither player won (tie game)
	public Mark getWinner()
	{
		assert isGameOver() : "Game is not over!";
		this.isGameOver();
		return this.winner;
	} 
	
	public String toString()
	{
		String theBoard="";
		String board="";
		for(int r=0; r<3; r++)
		{
			for(int c=0; c<3; c++)
			{
				if(this.getMark(r, c)==Mark.X)
				{
					board+='X';
				}
				else if(this.getMark(r, c)==Mark.O)
				{
					board+='O';
				}
				else
				{
					board+=" ";
				}
			}
		}
		
		theBoard=board.charAt(0)+"|"+board.charAt(1)+"|"+board.charAt(2)+"\n-----\n"+board.charAt(3)+"|"+board.charAt(4)+"|"+board.charAt(5)+"\n-----\n"+board.charAt(6)+"|"+board.charAt(7)+"|"+board.charAt(8);
		return theBoard;
	}
	

}
