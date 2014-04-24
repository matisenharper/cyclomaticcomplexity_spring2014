package inputOutput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import engine.MethodCode;
import engine.MethodData;
import engine.MethodUtils;
import engine.MethodUtilsImpl;
import Milestone1_Runner.ModalDialog;
import Milestone1_Runner.Student;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;


public class GraphView extends JPanel 
{
	//this just for testing
	static Method[] buttonArray = {null};
	//

	public GraphView() 
	{

		setSize(950, 800);
		//setLocationRelativeTo(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE); 
	}

	private static class ImageShow extends JPanel
	{
		private ImageIcon image;
		private JLabel label;

		//constructor
		ImageShow(JPanel graph)
		{
			//this.getContentPane().setLayout(new BorderLayout());
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

	private MethodData[] gettingBody (File program, Student S)
	{
		MethodUtils m = new MethodUtilsImpl();
		MethodData[] body =m.getMethods( program, S);
		//to get the name of the stu
		return body;
	}

	private static Image gettingImage (String body)
	{
		Image img = cyclomaticComplexity.GraphImage.getImage(body); 
		return img;
	}

	private static int gettingComplexity (String body1)
	{
		int cal = cyclomaticComplexity.CyclomaticComplexityMethodCalc.getCyclomaticComplexity(body1); 
		return cal;
	}

	private /*static*/ void createButton(MethodCode[] buttonName, JPanel buttons, Student studentName, File file)
	{
		int count =0;
		JPanel buttonPanel= new JPanel();
		//ButtonGroup methodGroup = new ButtonGroup();
		File file2=file;

		while(count <= buttonName.length)
		{
			String currentButton = buttonName[count].toString();
			MethodCode buttonName2= buttonName[count];
			//get method[count] name and put in methodName then create button named methodName
			//String methodName= Method[count]>>> find way to get string name of method
			JRadioButton button = new JRadioButton(buttonName2.getName().toString(), false);//will assing string to method when we know how
			//getName get the name of the method

			buttonPanel.add(button);
			buttons.add(buttonPanel);

			button.addActionListener(new ActionListener ()
			{
				public void actionPerformed (ActionEvent  e, File file2, MethodCode buttonName2 )
				{
					/**********************************************/
					

					//first send the file to MethodUtilsImpl get method[]
					//then when the button is called send the index 'nameOfMethod' with 
					//student name to MethodDataImpl
					
					
					/**********************************************/
					/*gettingBody( file2, buttonName2);//clone return copy of object

					getcal(gettingComplexity (gettingBody( file2, buttonName2)));	
					ImageShow(gettingImage(gettingBody( file2, buttonName2)));*/
					/*****************************************************/

					System.out.println("button is still not working " + e);
					//the teacher wants RuntimeException
					//it will have to be in try catch
				}

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
		}
	}


	private static JTextField complexityBox (int i)
	{
		//Scroll bar
		System.out.println("complexityBox working");
		/*JButton startButton = new JButton("Get Data");
		startButton.setPreferredSize(new Dimension(8, 9));*/
		JTextField calPanel = new JTextField(27);
		calPanel.addActionListener(null);
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
		/*try {
			//System.out.println("running methodheaderlooker2");
			Scanner scanner = new Scanner(file);

			//now read the file line by line...
			int lineNum = 0;
			while (scanner.hasNextLine()) 
			{
				//System.out.println("running methodheaderlooker3");
				String line = scanner.nextLine();
				lineNum++;
				System.out.println(line);

				//determine whether line contains a method
				//if method is contained, create button with method name

				if(line.contains("public")|| line.contains("private"))
				{
					//System.out.println("methodheaderlooker4");
					//create button named after method that was found in 'line'
					createButton(line,graph);	
				}
			}
			scanner.close();
		} catch(FileNotFoundException e) { 
			System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
			System.out.println("catching" + e);
			System.out.println(e.getClass());
			//handle this 
		}*/
	}
	/*SOME WEBSITES TO HELP:
	 * http://answers.yahoo.com/question/index?qid=20101125121937AAbbXeq
	 * 
	 * public static class MethodHeaderLooker {

		//method header example to look for
		private static final String REGEX = /public ( )private ( )/;
		//the .java file
		private static final String INPUT =
				"fooooooooooooooooo";
		private static Pattern pattern;
		private static Matcher matcher;*/


	public static void main(String args []) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {


				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				URL url = classLoader.getResource("inputOutput/Tic.txt");
				assert url != null : "url is null!";
				File file = new File(url.getPath());
				System.out.println(file.getAbsolutePath());

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
				/*Container c = JFrame.getContentPane();
				c.setBackground(Color.red); */

				//imagePanel.setVisible(true);
				buttons.setVisible(true);
				graph.setVisible(true);
				calPanel.setVisible(true);


				showImage(imagePanel);
				//call setData() to test code. comment out when actually running.
				setData();
				/*static Method[] buttonArray = {null};
				createButton(buttonArray[],buttons);
				try {
					MethodHeaderLooker(file, buttons);
				} catch (IOException e) {
					System.out.println("catch in main" + e);
				};*/

				complexityBox(9);


				cyclomaticComplexityJDialogExample();
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

	public static void cyclomaticComplexityJDialogExample()
	{
		System.out.println("start of jdialog with jpanel for ");
		ModalDialog dlg = new ModalDialog(new GraphView(), "title", "Hebah's GUI/Cyclomatic Complexity View");
		System.out.println("End of jdialog with jpanel ");
	}


	private static void showImage(JPanel graph)
	{
		ImageShow gui = new ImageShow(graph);
		//gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gui.setVisible(true);
		graph.add(gui);

	}

	Student author;
	private void setData (File file, Student studentName)
	{
		//MethodUtilsImpl.getMethods(file);
	}

	//to test
	private static void setData ()
	{
		File file = new File("Tic.txt");
		String studentName = new String("Hebah");
		Method[] buttonArray = new Method[]{null};
	}
}





/*public class GraphView  extends JPanel
{
	public static final int FRAMEHEIGHT=400;
	public static final int FRAMEWIDTH=400;
	int line [] = new int[500];


	Thread runner;


	public static void main (String agrs[])
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				GraphView graph = new GraphView();
				graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				graph.setSize(GraphView.FRAMEWIDTH, GraphView.FRAMEHEIGHT);
				//Display the window.
				graph.setVisible(true);
			}

		});
	}
}
	//this will see how many methods I have and keep adding radio button until there is no more methods


	//call the method that will create the number of buttons
	/*JRadioButton RadioButton()
        {
        	//it should return radio buttons with the name of methods
			return null;
		}*/


//the method names string will come from another method

/*public void refresh()
{
        Random ranNum = new Random ();
        for(int i =0 ; i <500; i++)
        {
                int newNum = ranNum.nextInt(450);
                line[i]= newNum+15;
        }
        repaint();
}

class MyPanel extends GraphView
{

        MyPanel()
        {
                this.setPreferredSize(new Dimension(700,600));
        }

        public void paintComponent(Graphics g)
        { 
                for( int i = 0; i < 500; i++)
                {
                        g.setColor(Color.BLACK);
                        g.fillRect(i, 500-line[i], 1, line[i]);
                }

        }
}
}


//private static final String uiClassID = "PanelUI";
/*public JPanel(LayoutManager layout, boolean isDoubleBuffered) 
{
        setLayout(layout);
        setDoubleBuffered(isDoubleBuffered);
        //setUIProperty("opaque", Boolean.TRUE);
        updateUI();
}

public JPanel(LayoutManager layout) 
{
        this(layout, true);



}


private static void showGUI()
{        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame ("[=] trying the panle[=]");


}*/