package test;


import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import test.Parameterized_Kart.Parameters;
import test.TicTacToe_SingleStudent_Test.Points;
import tictactoe.Mark;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Kart;
import tictactoe.TicTacToeBoardUtils;
import MISC.Student;

@RunWith(Parameterized_Kart.class)
public class TicTacToeBoardAllTests_NEW {
	private String GOLD_STANDARD_MESSAGE;
	public static final String PACKAGE_NAME = "tictactoe";//change per the assignment
	public static final String PREFIX = "TicTacToeBoard";//prefix to the progRAM BEING READ IN
	public static final String SUFFIX = "";
	private static final Set<Student> ALL_STUDENTS = new HashSet<Student>(Arrays.asList(Spring2014Users.Student.values()));

	private static String STUDENT_INSTANCE_IS_NULL_MESSAGE = "Could not construct an instance of TicTacToeBoardImpl";

	private Student student;
	private Class studentImplClass;
	private TicTacToeBoard ticTacToeBoard_STUDENT;
	

	//	protected static List<String> problemKeyList = null;
	//	protected static Map<Object, Integer> problemKeyToPointValueMap = null;

	public TicTacToeBoardAllTests_NEW(Student student) throws ClassNotFoundException
	{
		String NAMING_CONVENTIONS_IMPL = "Impl";
		String NAMING_CONVENTIONS_SEPARATOR = "_";
		
		System.out.println("Constructor called: student = " + student + " student.getFileNameSuffix() = " + student.getFileNameSuffix());
		//assert student.getLastName() == student.getFileNameSuffix();
		this.student = student;
		try
		{
			studentImplClass = Class.forName(PACKAGE_NAME + "." + PREFIX + NAMING_CONVENTIONS_IMPL + NAMING_CONVENTIONS_SEPARATOR /*+ AccentUtils.stripAccentsFromString(student.getFileNameSuffix())*/ + SUFFIX);
			System.out.println("studentImplClass = " + studentImplClass + " student.getFileSuffix() = " + student.getFileNameSuffix());
		}catch(ClassNotFoundException cnfe)
		{
			System.out.println("studentImplClass = " + studentImplClass + " student.getFileSuffix() = " + student.getFileNameSuffix());
			studentImplClass = null;
		}
	}

	private TicTacToeBoard getStudentTicTacToeBoard(Student student)
	{
		if(studentImplClass == null) return null;
		Object newInstance = null;
		try {
			newInstance = studentImplClass.getConstructor().newInstance();
			System.out.println("new Instance = " + newInstance);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (TicTacToeBoard)newInstance;
	}

	public TicTacToeBoard getGoldStandardTicTacToeBoard()
	{
		return new TicTacToeBoardImpl_Kart();
	}

	@Parameters //gets all students
	public static List<Object[]> parameterList() {
		List<Object[]> parameterList = new ArrayList<Object[]>();
		for(Student student : Spring2014Users.Student.values())
		{
			parameterList.add(new Object[]{student});
		}
		return parameterList;
	}

	@BeforeClass	//before *all* students
	public static void beforeAllStudents()
	{
	}
	@CyclomaticComplexity
	@AfterClass		//after *all* students
	public static void afterAllStudents() throws FileNotFoundException
	{
		try
		{
			for(Student student : ALL_STUDENTS)
			{
				System.out.println("student = " + student);

				
			}
			//writeOutScoreboard();
		}
		catch(Throwable throwable)
		{
			JOptionPane.showMessageDialog(null, throwable.getMessage());
		}
	}
	
	@Rule
	public TestRule watchman = new TestWatcher()
	{
		private boolean testSucceeded;
		private Throwable e;

		@Override
		protected void failed(Throwable e, Description description) {
			System.out.println("FAILED. Only executed when a test fails. description = " + description);
			testSucceeded = false;
			this.e = e;
		}

		@Override
		protected void succeeded(Description description) {
			
			testSucceeded = true;
		}

		@Override
		protected void finished(Description description) {
			//recordPointValue
			//recordResults
			testSucceeded = false;
			e = null;
		}
	};
	
	/**********************************************************************************************************************/	
	private void runTest(int[] movesArray, String messagePrefix)
	{
		TicTacToeBoard ticTacToeBoard_STUDENT;
	}
	@Points(value=5)
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
	@Points(value=5)
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
	@Points(value=5)
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
	@Points(value=5)
	@Test(expected=AssertionError.class)
	public void setMark_OutOfRange()
	{
		final int ROW1 = 4;
		final int COLUMN1 = 4;
		ticTacToeBoard_STUDENT.setMark(ROW1, COLUMN1);
	}
	@Points(value=5)
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
	@Before
	public void initBeforeEachTestMethod()
	{
		System.out.println("BEFORE : student = " + student);
		ticTacToeBoard_STUDENT = getStudentTicTacToeBoard(student);
	}

	@After
	public void cleanUpAfterEachTestMethod()
	{
		System.out.println("AFTER : student = " + student);	
	}
}
