package inputOutput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Spring2014Users.Student;
import engine.MethodCode;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodUtils;
import engine.MethodUtilsImpl;


public class GraphView extends JFrame
{
	//this just for testing
	static Method[] buttonArray = {null};
	//

	public GraphView() 
	{
		setSize(950, 800);
	}

	private static class ImageShow extends JPanel
	{
		private ImageIcon image;
		private JLabel label;

		//constructor
		ImageShow(JPanel graph)
		{
			setLayout(new BorderLayout());
			image = new ImageIcon(getClass().getResource("demo.jpg"));
			label = new JLabel(image);
			add(label);
		}
	}

	private static int getcal (int i)
	{
		JPanel buttons= new JPanel();
		return i;
	}
	
	//creates array of methods
	private static MethodData[] gettingSignature(File program, Student S)
	{
		MethodUtils m = new MethodUtilsImpl();
		MethodData[] methodSignature =m.getMethods( program, S);
		System.out.print("methodSignature "+methodSignature.toString());
		return methodSignature;
	}
	private MethodData toGetCodeBody (Student author,MethodCode method)
	{
		MethodData body = new MethodDataImpl(author, method);
		System.out.print("toGetCodeBody"+body);
		return body;
	}
	private static Image gettingImage (String body)
	{
		Image img = cyclomaticComplexity.GraphImage.getImage(body); 
		System.out.print("gettingImage"+img);
		return img;
	}
	private static int gettingComplexity (MethodData body1)
	{
		int cal = cyclomaticComplexity.CyclomaticComplexityMethodCalc.getCyclomaticComplexity(body1); 
		return cal;
	}

	private static void createButton(String methodName, JPanel buttons)//, Student studentName, File file)
	{
		JPanel buttonPanel= new JPanel();
		
		System.out.println("createButton method ");
		JRadioButton button = new JRadioButton();

		button.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent  e, File file2, MethodData buttonName2 )
				{
					e.getID();
					System.out.println("stuff pringting out" +buttonName2.toString() +" "+file2.toString()+ " " +e.getID() );

					String name = buttonName2.toString();

					System.out.println("button is still not working " + name);
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
				}
			});	
	}

	private static JTextField complexityBox (int i)
	{
		JTextField calPanel = new JTextField(27);
		calPanel.addActionListener(null);
		System.out.println("  complexityBox working  ");
		return calPanel;
	}

	private static void MethodHeaderLooker(File file, JPanel graph) throws IOException
	{
		//method header example to look for
		//final String REGEX = "/public [a-zA-Z1-9]([a-zA-Z1-9])private [a-zA-Z1-9]([a-zA-Z1-9])/";
		//File filejava = new File(file);
		System.out.println("running methodheaderlooker");

		try 
		{
			FileInputStream input = new FileInputStream(file);
			assert input != null : "input is null! : Check that the resources folder is on the classpath, the file name is correct, and the file is in the resources folder";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
			String str;
			do
			{
				str = bufferedReader.readLine();
				if(str != null) System.out.println(str);
			}
			while(str != null);
		}

		catch(FileNotFoundException e) { 
			System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
			System.out.println("catching" + e);
			System.out.println(e.getClass());
			//handle this 
		}
	}

	public static void main(String args []) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//initialize file for debugging code
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				URL url = classLoader.getResource("inputOutput/Tic.txt");
				assert url != null : "url is null!";
				File file = new File(url.getPath());
				System.out.println(file.getAbsolutePath());
				
				Student author = Student.HAJAR;
				
				setData(file, author);

				
			}
		});


		/*http://stackoverflow.com/questions/13667041/reading-a-txt-file-in-a-java-gui
		 * 
		 * pattern = Pattern.compile(REGEX);
			matcher = pattern.matcher(INPUT);

			System.out.println("Current REGEX is: "
					+ REGEX);
			System.out.println("Current INPUT is: "
					+ INPUT);

			System.out.println("lookingAt(): "
					+ matcher.lookingAt());
			System.out.println("matches(): "
					+ matcher.matches());*/
	}

	/*public static void cyclomaticComplexityJDialogExample()
	{
		System.out.println("start of jdialog with jpanel for ");
		ModalDialog dlg = new ModalDialog(new GraphView(), "title", "Hebah's GUI/Cyclomatic Complexity View");
		System.out.println("End of jdialog with jpanel ");
	}*/


	private static void showImage(JPanel graph)
	{
		ImageShow gui = new ImageShow(graph);
		graph.add(gui);

	}

	
	private static void setData (File file, Student studentName)
	{
		MethodData[] methodArray =gettingSignature(file, studentName);
		buttonMan(methodArray);
		System.out.println("Method array(setData):  "+ methodArray);
		
		//MethodUtilsImpl.getMethods(file, studentName);	
	}
	
	//will gather data to make each button
	private static void buttonMan(MethodData[] methodMan)
	{
		int count =	0;
		
		GraphView graph = new GraphView();//M will do this
		JPanel imagePanel = new JPanel();
		JPanel buttons= new JPanel();
		JPanel calPanel = new JPanel();
		graph.add(buttons,BorderLayout.WEST);
		graph.add(imagePanel,BorderLayout.EAST);
		graph.add(calPanel,BorderLayout.SOUTH);

		imagePanel.setBackground( Color.cyan);
		calPanel.setBackground( Color.blue);
		buttons.setBackground(Color.GREEN);
		
		buttons.setVisible(true);
		graph.setVisible(true);
		calPanel.setVisible(true);
		
		complexityBox(9);
		
		while (count <= methodMan.length)
		{
			String tempmethod = methodMan[count].getName().toString();
			createButton(tempmethod, buttons);
			System.out.println("tempmethod count " + count +" methodMan Length " + methodMan.length);
			System.out.println("tempmethod:   " + tempmethod);
			count ++;
		}
	}

}

