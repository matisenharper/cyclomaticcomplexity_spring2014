package tictactoe;

public class TicTacToeBoardImpl_Rocha implements TicTacToeBoard
{
		//Symbolics:
		protected static final int NO_MOVE = -1;
		protected static final int NO_MATCH = -1;
		
		protected int[] movesArray;
		
		public TicTacToeBoardImpl_Rocha()
		{
			final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;
			movesArray = new int[CELL_COUNT];
			for(int i = 0; i < CELL_COUNT; i++)
			{
				movesArray[i] = NO_MOVE;
			}
		}
		
		//private method to keep track of where the count in the array is.
		private int trackMovArr()
		{
			int count = 0;
			for(int i = 0; i < (ROW_COUNT*COLUMN_COUNT); i++)
			{
				if(this.movesArray[i] == -1)
				{
					break;
				}
				else
				{
					count++;
				}
			}
			return count;
		}
		
		//private method to create the numerical spot on the board with row and column given
		private int boardPlace(int row, int column)
		{
			int bPlace = 0;
			
			if(row == 0 && column == 0)
			{
				bPlace = 0;
			}
			if(row == 0 && column == 1)
			{
				bPlace = 1;
			}
			if(row == 0 && column == 2)
			{
				bPlace = 2;
			}
			if(row == 1 && column == 0)
			{
				bPlace = 3;
			}
			if(row == 1 && column == 1)
			{
				bPlace = 4;
			}
			if(row == 1 && column == 2)
			{
				bPlace = 5;
			}
			if(row == 2 && column == 0)
			{
				bPlace = 6;
			}
			if(row == 2 && column == 1)
			{
				bPlace = 7;
			}
			if(row == 2 && column == 2)
			{
				bPlace = 8;
			}
			return bPlace;
		}
		
		//part of pre: 0 <=row < ROW_COUNT && 0 <= column < COLUMN_COUNT
		//part of post: rv == null <==> the (row, column) spot on the board is empty
		public Mark getMark(int row, int column)
		{
			int bPlace = boardPlace(row, column);
			int position = NO_MOVE;
			Mark mark;
			
			for(int i = 0; i < (ROW_COUNT*COLUMN_COUNT); i++)
			{
				if(bPlace == this.movesArray[i])
				{
					position = i;
					break;
				}
			}
			if(position == NO_MOVE)
			{
				mark = null;
			}
			else if(position == 0)
			{
				mark = Mark.X;
			}
			else if(position%2 == 0)
			{
				mark = Mark.X;
			}
			else
			{
				mark = Mark.O;
			}
			return mark;
		}
			
		//part of pre: 0 <= row < ROW_COUNT && 0 <= column < COLUMN_COUNT
		//part of pre: getMark(row, column) == null
		//part of pre !isGameOver()
		//part of post: 
		public void setMark(int row, int column)
		{
			
			if((!isGameOver()) && (getMark(row, column) == null))
			{
				this.movesArray[this.trackMovArr()] = this.boardPlace(row, column);
			}
			
		}
		
		//part of post: rv == null <==> it is neither player's turn (i.e. game is over)
		//part of post: "number of Marks on board is even"=> rv == Mark.X
		//part of post: "number of Marks on board is odd" => rv == Mark.O
		public Mark getTurn()
		{
			Mark mark;
			if(isGameOver())
			{
				mark = null;
			}
			else if(this.trackMovArr() == 0)
			{
				mark = Mark.X;
			}
			else if((this.trackMovArr())%2 == 0)
			{
				mark = Mark.X;
			}
			else
			{
				mark = Mark.O;
			}
			return mark;
		}
		
		//part of post: rv == true <==> three Marks of the same type run through 
		//				three consecutive diagonal/horizontal/vertical positions on the board.
		//part of post: rv == true <==> if all elements in movesArray are used up before theres a winner.
		public boolean isGameOver()
		{
			//arrays with 3 elements for the sequence of winnings
			int[] horWin1 = new int[3];
			int[] horWin2 = new int[3];
			int[] horWin3 = new int[3];
			int[] verWin1 = new int[3];
			int[] verWin2 = new int[3];
			int[] verWin3 = new int[3];
			int[] diogWin1 = new int[3];
			int[] diogWin2 = new int[3];
			
			//first number in sequence of win for horizontals
			int horFirNum1 = 0;
			int horFirNum2 = 3;
			int horFirNum3 = 6;
			//first number in sequence of win for verticals
			int verFirNum1 = 0;
			int verFirNum2 = 1;
			int verFirNum3 = 2;
			//first number in sequence of win for diagonals
			int diogFirNum1 = 0;
			int diogFirNum2 = 2;
			
			//set up all arrays in proper order to win horizontally, vertically, and diagonally
			for(int i = 0; i < 3; i++)
			{
				horWin1[i] = horFirNum1;
				horWin2[i] = horFirNum2;
				horWin3[i] = horFirNum3;
				verWin1[i] = verFirNum1;
				verWin2[i] = verFirNum2;
				verWin3[i] = verFirNum3;
				diogWin1[i] = diogFirNum1;
				diogWin2[i] = diogFirNum2;
				
				horFirNum1 = horFirNum1 + 1;
				horFirNum2 = horFirNum2 + 1;
				horFirNum3 = horFirNum3 + 1;
				verFirNum1 = verFirNum1 + 3;
				verFirNum2 = verFirNum2 + 3;
				verFirNum3 = verFirNum3 + 3;
				diogFirNum1 = diogFirNum1 + 4;
				diogFirNum2 = diogFirNum2 + 2;		
			}
			
			//number check to know if player X has won 
			int horXAns1 = 0;
			int horXAns2 = 0;
			int horXAns3 = 0;
			int verXAns1 = 0;
			int verXAns2 = 0;
			int verXAns3 = 0;
			int diogXAns1 = 0;
			int diogXAns2 = 0;
			
			//loop to look and find X wins possible
			for(int i = 0; i < this.trackMovArr(); i = i + 2)
			{
				for(int a = 0; a < horWin1.length; a++)
				{
					if(movesArray[i] == horWin1[a])
					{
						horXAns1 += 1;
						if(horXAns1 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < horWin2.length; a++)
				{
					if(movesArray[i] == horWin2[a])
					{
						horXAns2 += 1;
						if(horXAns2 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < horWin3.length; a++)
				{
					if(movesArray[i] == horWin3[a])
					{
						horXAns3 += 1;
						if(horXAns3 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < verWin1.length; a++)
				{
					if(movesArray[i] == verWin1[a])
					{
						verXAns1 += 1;
						if(verXAns1 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < verWin2.length; a++)
				{
					if(movesArray[i] == verWin2[a])
					{
						verXAns2 += 1;
						if(verXAns2 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < verWin3.length; a++)
				{
					if(movesArray[i] == verWin3[a])
					{
						verXAns3 += 1;
						if(verXAns3 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < diogWin1.length; a++)
				{
					if(movesArray[i] == diogWin1[a])
					{
						diogXAns1 += 1;
						if(diogXAns1 == 3)
						{
							return true;
						}
					}
				}
				for(int a = 0; a < diogWin2.length; a++)
				{
					if(movesArray[i] == diogWin2[a])
					{
						diogXAns2 += 1;
						if(diogXAns2 == 3)
						{
							return true;
						}
					}
				}
			}//end of first for loop that keeps track of X
			

			
			//number check to know if player O has won 
					int horOAns1 = 0;
					int horOAns2 = 0;
					int horOAns3 = 0;
					int verOAns1 = 0;
					int verOAns2 = 0;
					int verOAns3 = 0;
					int diogOAns1 = 0;
					int diogOAns2 = 0;
					
					//loop to look and find O wins possible
					for(int i = 1; i < this.trackMovArr(); i = i + 2)
					{
						for(int a = 0; a < horWin1.length; a++)
						{
							if(movesArray[i] == horWin1[a])
							{
								horOAns1 += 1;
								if(horOAns1 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < horWin2.length; a++)
						{
							if(movesArray[i] == horWin2[a])
							{
								horOAns2 += 1;
								if(horOAns2 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < horWin3.length; a++)
						{
							if(movesArray[i] == horWin3[a])
							{
								horOAns3 += 1;
								if(horOAns3 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < verWin1.length; a++)
						{
							if(movesArray[i] == verWin1[a])
							{
								verOAns1 += 1;
								if(verOAns1 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < verWin2.length; a++)
						{
							if(movesArray[i] == verWin2[a])
							{
								verOAns2 += 1;
								if(verOAns2 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < verWin3.length; a++)
						{
							if(movesArray[i] == verWin3[a])
							{
								verOAns3 += 1;
								if(verOAns3 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < diogWin1.length; a++)
						{
							if(movesArray[i] == diogWin1[a])
							{
								diogOAns1 += 1;
								if(diogOAns1 == 3)
								{
									return true;
								}
							}
						}
						for(int a = 0; a < diogWin2.length; a++)
						{
							if(movesArray[i] == diogWin2[a])
							{
								diogOAns2 += 1;
								if(diogOAns2 == 3)
								{
									return true;
								}
							}
						}
					}//end of first for loop that keeps track of O
					
				if(this.trackMovArr() == 9)
				{
					return true;
				}
				
				return false;
			
		}
			
		//part of pre: isGameOver()
		//part of post:rv == null <==> neither player won (i.e. the game ended in a tie)
		public Mark getWinner()
		{
			
			//arrays with 3 elements for the sequence of winnings
					int[] horWin1 = new int[3];
					int[] horWin2 = new int[3];
					int[] horWin3 = new int[3];
					int[] verWin1 = new int[3];
					int[] verWin2 = new int[3];
					int[] verWin3 = new int[3];
					int[] diogWin1 = new int[3];
					int[] diogWin2 = new int[3];
					
					//first number in sequence of win for horizontals
					int horFirNum1 = 0;
					int horFirNum2 = 3;
					int horFirNum3 = 6;
					//first number in sequence of win for verticals
					int verFirNum1 = 0;
					int verFirNum2 = 1;
					int verFirNum3 = 2;
					//first number in sequence of win for diagonals
					int diogFirNum1 = 0;
					int diogFirNum2 = 2;
					
					//set up all arrays in proper order to win horizontally, vertically, and diagonally
					for(int i = 0; i < 3; i++)
					{
						horWin1[i] = horFirNum1;
						horWin2[i] = horFirNum2;
						horWin3[i] = horFirNum3;
						verWin1[i] = verFirNum1;
						verWin2[i] = verFirNum2;
						verWin3[i] = verFirNum3;
						diogWin1[i] = diogFirNum1;
						diogWin2[i] = diogFirNum2;
						
						horFirNum1 = horFirNum1 + 1;
						horFirNum2 = horFirNum2 + 1;
						horFirNum3 = horFirNum3 + 1;
						verFirNum1 = verFirNum1 + 3;
						verFirNum2 = verFirNum2 + 3;
						verFirNum3 = verFirNum3 + 3;
						diogFirNum1 = diogFirNum1 + 4;
						diogFirNum2 = diogFirNum2 + 2;		
					}
					
					//number check to know if player X has won 
					int horXAns1 = 0;
					int horXAns2 = 0;
					int horXAns3 = 0;
					int verXAns1 = 0;
					int verXAns2 = 0;
					int verXAns3 = 0;
					int diogXAns1 = 0;
					int diogXAns2 = 0;
					
					//loop to look and find X wins possible
					for(int i = 0; i < this.trackMovArr(); i = i + 2)
					{
						for(int a = 0; a < horWin1.length; a++)
						{
							if(movesArray[i] == horWin1[a])
							{
								horXAns1 += 1;
								if(horXAns1 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < horWin2.length; a++)
						{
							if(movesArray[i] == horWin2[a])
							{
								horXAns2 += 1;
								if(horXAns2 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < horWin3.length; a++)
						{
							if(movesArray[i] == horWin3[a])
							{
								horXAns3 += 1;
								if(horXAns3 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < verWin1.length; a++)
						{
							if(movesArray[i] == verWin1[a])
							{
								verXAns1 += 1;
								if(verXAns1 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < verWin2.length; a++)
						{
							if(movesArray[i] == verWin2[a])
							{
								verXAns2 += 1;
								if(verXAns2 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < verWin3.length; a++)
						{
							if(movesArray[i] == verWin3[a])
							{
								verXAns3 += 1;
								if(verXAns3 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < diogWin1.length; a++)
						{
							if(movesArray[i] == diogWin1[a])
							{
								diogXAns1 += 1;
								if(diogXAns1 == 3)
								{
									return Mark.X;
								}
							}
						}
						for(int a = 0; a < diogWin2.length; a++)
						{
							if(movesArray[i] == diogWin2[a])
							{
								diogXAns2 += 1;
								if(diogXAns2 == 3)
								{
									return Mark.X;
								}
							}
						}
					}//end of first for loop that keeps track of X
					

					
					//number check to know if player O has won 
							int horOAns1 = 0;
							int horOAns2 = 0;
							int horOAns3 = 0;
							int verOAns1 = 0;
							int verOAns2 = 0;
							int verOAns3 = 0;
							int diogOAns1 = 0;
							int diogOAns2 = 0;
							
							//loop to look and find O wins possible
							for(int i = 1; i < this.trackMovArr(); i = i + 2)
							{
								for(int a = 0; a < horWin1.length; a++)
								{
									if(movesArray[i] == horWin1[a])
									{
										horOAns1 += 1;
										if(horOAns1 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < horWin2.length; a++)
								{
									if(movesArray[i] == horWin2[a])
									{
										horOAns2 += 1;
										if(horOAns2 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < horWin3.length; a++)
								{
									if(movesArray[i] == horWin3[a])
									{
										horOAns3 += 1;
										if(horOAns3 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < verWin1.length; a++)
								{
									if(movesArray[i] == verWin1[a])
									{
										verOAns1 += 1;
										if(verOAns1 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < verWin2.length; a++)
								{
									if(movesArray[i] == verWin2[a])
									{
										verOAns2 += 1;
										if(verOAns2 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < verWin3.length; a++)
								{
									if(movesArray[i] == verWin3[a])
									{
										verOAns3 += 1;
										if(verOAns3 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < diogWin1.length; a++)
								{
									if(movesArray[i] == diogWin1[a])
									{
										diogOAns1 += 1;
										if(diogOAns1 == 3)
										{
											return Mark.O;
										}
									}
								}
								for(int a = 0; a < diogWin2.length; a++)
								{
									if(movesArray[i] == diogWin2[a])
									{
										diogOAns2 += 1;
										if(diogOAns2 == 3)
										{
											return Mark.O;
										}
									}
								}
							}//end of first for loop that keeps track of O
						
						return null;
			
			
		}
		public String toString()
		{
			String temp = getMark(0,0) + "|" + getMark(0,1) + "|" + getMark(0,2) + "\n"
					+ "______\n" + getMark(1,0) + "|" + getMark(1,1) + "|" + getMark(1,2) + "\n"
					+ "______\n" + getMark(2,0) + "|" + getMark(2,1) + "|" + getMark(2,2);
			
			return temp;
		}
	}
