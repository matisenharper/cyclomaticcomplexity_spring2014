package test;


import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToeBoardImpl_Kart;
import tictactoe.TicTacToeBoardUtils;
import MISC.Student;

import inputOutput.GraphView;





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
	private TicTacToeBoard ticTacToeBoard_GOLD_STANDARD;

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

	@Parameters 
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
		GOLD_STANDARD_MESSAGE = messagePrefix;
	
		for(int i = 0; i < movesArray.length; i++)
		{
			int gridIndex = movesArray[i];
			int row = TicTacToeBoardUtils.getRow(gridIndex);
			int column = TicTacToeBoardUtils.getColumn(gridIndex);
			ticTacToeBoard_GOLD_STANDARD.setMark(row, column);
			GOLD_STANDARD_MESSAGE = messagePrefix + "\n" + "Sub-test: " + ticTacToeBoard_GOLD_STANDARD;
			ticTacToeBoard_STUDENT.setMark(row, column);
			
		}
		GOLD_STANDARD_MESSAGE = messagePrefix;
	}

	@Test
	public void cyclomaticComplexityJDialogExample()
	{
		System.out.println("start of jdialog with jpanel for "+student);
		ModalDialog dlg = new ModalDialog(new GraphView(), "title", "Hebah's GUI/Cyclomatic Complexity View");
		System.out.println("End of jdialog with jpanel "+student);
	}
	
	@Points(value=5)
	@Test
	public void emptyBoardTest()
	{
		
		int[] movesArray = new int[]{};
		String messagePrefix = "Get all questions correct about the game: " + TicTacToeBoardUtils.toString(movesArray);
		runTest(movesArray, messagePrefix);	
	}

	@Points(value=5)
	@Test(timeout=3000)
	public void oneSymbolTest()
	{	
		int[] movesArray = new int[]{2};
		String messagePrefix = "Get all questions correct about the game: " + TicTacToeBoardUtils.toString(movesArray);
		runTest(movesArray, messagePrefix);
	}	

	@Points(value=5)
	@Test(timeout=3000)
	public void twoSymbolTest()
	{
		int[] movesArray = new int[]{4,8};
		String messagePrefix = "Get all questions correct about the game: " + TicTacToeBoardUtils.toString(movesArray);
		runTest(movesArray, messagePrefix);
		
	}}