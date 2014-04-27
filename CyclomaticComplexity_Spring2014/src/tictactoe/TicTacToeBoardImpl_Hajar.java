package tictactoe;

public class TicTacToeBoardImpl_Hajar implements TicTacToeBoard{

        public static final int NO_MOVE = -1;
        public static final int NO_MATCH = -1;

        protected int [] movesArray;


        //constructor Here
        public TicTacToeBoardImpl_Hajar ()
        {
                final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
                movesArray = new int [CELL_COUNT];
                for (int i = 0; i< CELL_COUNT; i++)
                {
                        movesArray [i] = NO_MOVE;
                }
        }
        //Reports back what mark is at specified row and column
        //row must be less that ROW_COUNT and similarly for column
        //null is returned when the spot is empty

        public Mark getMark(int row, int column)
        {
                assert 0 <= row :"0>" + row+"=row!";
                assert 0<= column :"0>" +column+"=column!";
                assert row<ROW_COUNT :"row=" +row+">="+ROW_COUNT+"=ROW_COUNT";
                assert column<COLUMN_COUNT :"column=" +column+">="+COLUMN_COUNT+"=COLUMN_COUNT";
                return isEven(SearchForIndexInMovesArray(getIndexForGrid(row,column)));
        }
        //Places a mark in specified row and column
        //part of pre: row has to be less than ROW_COUNT and similarly for column
        //there cannot already be a mark int the specified spot
        //the game cannot be over
        public void setMark(int row, int column)
        {
                assert 0 <= row: "0>"+row+"=row";
                assert 0<= column:"0>"+column+"=column";
                assert row<=ROW_COUNT : "row=" +row+">="+ROW_COUNT+"=ROW_COUNT";
                assert column <=COLUMN_COUNT:"column=" +column+">="+COLUMN_COUNT+"=COLUMN_COUNT";
                assert !isGameOver() : "Game is over";
                assert getMark(row, column) == null:"getMark("+row+","+column+")="+getMark(row, column)+"is not null";
                
                if(!isGameOver())
                {
                        int newMove = getIndexForGrid(row,column);
                        int placeHolder = SearchForIndexInMovesArray(-1);
                        this.movesArray[placeHolder] = newMove;
                        return;
                }
        }
        //null is returned if the game is over
        //X is sent if number of Marks on board is even
        //O is sent if number of Marks on board is odd
        public Mark getTurn()
        {
                if(isGameOver())
                {
                        return null;
                }
                int nextTurn = SearchForIndexInMovesArray(-1);
                return isEven(nextTurn);
        }

        //Finds out if the game is over
        public boolean isGameOver()
        {
                if(movesArray[8] != -1)
                        return true;
                else if(toFindTheWinner())
                        return true;
                else
                        return false;
        }

        //The game must be over in order to check
        //null is returned if there is a tie
        public Mark getWinner()
        {
                assert isGameOver(): "The game is not over";
                if (toFindTheWinner() && movesArray[8] == -1)
                        return isEven(SearchForIndexInMovesArray(-1)-1);
                else if (toFindTheWinner() && movesArray[8] != -1)
                        return Mark.X;
                return null;
        }


        //Helper Methods

        //Returns given coordinates will tell caller what grid index that sent in stuff is at
        protected int getIndexForGrid(int row, int column)
        {
                assert row >=0:"row=" +row+"<0";
                assert row<ROW_COUNT :"row=" +row+">=" +ROW_COUNT+"=NUMBER_ROWS";
                assert column >=0 :"column = " +column+ "<0";
                assert column <ROW_COUNT :"column ="+column+">="+COLUMN_COUNT+"NUMBER_COLUMNS";
                
                return (3*row) + column;
        }

        //Returns index where given value is at
        protected int SearchForIndexInMovesArray(int search)
        {
                assert search< movesArray.length:"i>movesArray.length: " +search;
                int arrayIndex = 0;
                while (this.movesArray[arrayIndex] != search)
                {
                        arrayIndex++;
                        if(arrayIndex == 9)
                        {
                                arrayIndex = NO_MATCH;
                                return arrayIndex;
                        }
                }
                return arrayIndex;
        }

        //Finds if index represents an O or an X
        protected Mark isEven(int arrayIndex)
        {
                if(arrayIndex%2 == 0)
                        return Mark.X;
                else if(arrayIndex%2== 1)
                {
                        return Mark.O;
                }
                return null;
        }

        protected boolean toFindTheWinner()
        {
                //top Horizontal
                if(getMark(0,0) == getMark(0,1)&& getMark(0,0) == getMark (0,2))
                {
                        if(getMark(0,0)!=null)
                                return true;
                }
                //middle Horizontal
                if (getMark (1,0)== getMark(1,1)&& getMark (1,0)== getMark (1,2))
                {
                        if(getMark(1,0)!=null)
                                return true;
                }
                //bottom Horizontal
                if(getMark(2,0) == getMark (2,1) && getMark (2,0) == getMark (2,2))
                {
                        if(getMark(2,0)!=null)
                                return true;
                }
                //middle vertical
                if (getMark(0,1)== getMark(1,1)&& getMark(0,1)==getMark(2,1))
                {
                        if(getMark(0,1)!=null)
                                return true;
                }
                //right vertical
                if(getMark (0,2) ==getMark(1,2)&& getMark(0,2)== getMark (2,2))
                {
                        if(getMark(0,2)!=null)
                                return true;
                }
                //left vertical
                if (getMark(0,0)==getMark (1,0)&& getMark(0,0)== getMark(2,0))
                {
                        if(getMark(0,0)!=null)
                                return true;
                }
                //left to right diagonal -from top
                if (getMark (0,0) == getMark (1,1) && getMark (0,0) == getMark (2,2))
                {
                        if(getMark(0,0)!=null)
                                return true;
                }
                //left to right diagonal -from bottom
                if (getMark (2,0)== getMark (1,1)&& getMark (2,0)==getMark (0,2))
                {
                        if(getMark(2,0)!=null)
                                return true;
                }
                //otherwise return null
                return false;
        }


        //makes board and is the toString
        public String toString()
        {
                // initialize the marker to store the "X" or the "O" symbol and the board based on array elements
                String board = "0|1|2\n\r-----\n\r3|4|5\n\r-----\n\r6|7|8";
                String marker = "";

                // Loop 1: replace all the X's and O's based on the current set of moves in the movesArray
                for(int i=0; i < 9 ;i++)
                {
                        marker = isEven(i).toString();
                        int value = movesArray[i];

                        if (value >= 0)
                                board = board.replace(Integer.toString(value), marker);
                }

                // Loop 2: clear out the leftover board elements with empty spaces since no move exists for those cells
                for (int i = 0; i < 9; i++)
                {
                        board = board.replace(Integer.toString(i), " ");
                }

                return board;
        }
}