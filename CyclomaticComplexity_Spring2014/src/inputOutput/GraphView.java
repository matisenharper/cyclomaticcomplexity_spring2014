package inputOutput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cyclomaticComplexity.CyclomaticComplexityMethodCalc;
import cyclomaticComplexity.GraphImage;
import Spring2014Users.Student;
import engine.MethodCode;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodUtils;
import engine.MethodUtilsImpl;


public class GraphView extends JFrame
{
	//this just for testing
	static MethodData[] methodArray = {null};
	static int selected;
	static JPanel imagePanel;
	static GraphView graph;
	//

	public GraphView() 
	{
		setSize(950, 800);
		selected = -1;
		this.setLayout(new BorderLayout());
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
		return methodSignature;
	}
	private MethodData toGetCodeBody (Student author,MethodCode method)
	{
		MethodData body = new MethodDataImpl(author, method);
		System.out.print("toGetCodeBody"+body);
		return body;
	}
	private static void changeImage ()
	{
		File imgfile = GraphImage.getImage(methodArray[selected]);
		imagePanel.removeAll();
		System.out.println("sdfg");
		ImagePanel img = new ImagePanel(imgfile);
		ImageScroller scroller = new ImageScroller(img);
		imagePanel.setLayout(new BorderLayout());
		imagePanel.add(scroller);
		
		graph.revalidate();
		
		
	}
	private static int gettingComplexity (MethodData body1)
	{
		int cal = cyclomaticComplexity.CyclomaticComplexityMethodCalc.getCyclomaticComplexity(body1); 
		return cal;
	}

	private static void createButton(String methodName,JPanel buttons, ButtonGroup group, int methodIndex)//, Student studentName, File file)
	{
				
		System.out.println("createButton method ");
		JRadioButton button = new methodRadioButton(methodName, methodIndex);
		button.setBackground(Color.decode("#16E5C4"));
		buttons.add(button);
		group.add(button);
		

		RadioListener listens=new RadioListener();
		button.addActionListener(listens);		
	}

	
	static class RadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
		System.out.println(e.getSource().toString());
		GraphView.selected = ((methodRadioButton)e.getSource()).i;
		GraphView.changeImage();
	}
	}
	private static JTextField complexityBox (int i)
	{
		JTextField calPanel = new JTextField("Complexity Number",getcal(20));
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

	
	public static void setData (File file, Student studentName)
	{
		methodArray =gettingSignature(file, studentName);
		buttonMan(methodArray);
		
		
		System.out.println("Method array(setData):  "+ methodArray);
		
		
		/*********************************/
		//Now when the button is called do:
		
		//use
		//toGetCodeBody (Student author,MethodCode method)
		//get it when the button is clicked to send it to get the pic
		
		//use
		//gettingImage (String body)
		//send the body to get the pic and display
		
		//use
		//gettingComplexity (MethodData body1)
		//to get the complexity Number and display
		
		//text = text + "Complexity = " + CyclomaticComplexityMethodCalc.getCyclomaticComplexity(methods[asdf]);
    	//label.setText(text);
		
		/**********************************/
		
		//MethodUtilsImpl.getMethods(file, studentName);	
	}
	
	//will gather data to make each button
	private static void buttonMan(MethodData[] methodMan)
	{
		int count =	0;
		
		graph = new GraphView();
		imagePanel = new JPanel();
		JPanel buttons= new JPanel();
		ButtonGroup group=new ButtonGroup();
		JPanel calPanel = new JPanel();
		JPanel ForColor = new JPanel();
		
		selected = 0;
		File imgfile = GraphImage.getImage(methodArray[selected]); 
		ImagePanel img = new ImagePanel(imgfile);
		ImageScroller scroller = new ImageScroller(img);
		scroller.setVisible(true);
		imagePanel.setLayout(new BorderLayout());
		imagePanel.add(scroller);
		
		graph.add(buttons,BorderLayout.WEST);
		graph.add(imagePanel,BorderLayout.EAST);
		graph.add(calPanel,BorderLayout.SOUTH);
		graph.add(ForColor,BorderLayout.CENTER);
		buttons.setLayout(new GridLayout((methodMan.length-1),1));
		
		graph.setBackground(Color.decode("#2014FF"));
		
		ForColor.setBackground(Color.decode("#16E5C4"));
		imagePanel.setBackground( Color.decode("#16E5C4"));
		calPanel.setBackground(Color.decode("#16E5C4"));
		buttons.setBackground(Color.decode("#16E5C4"));
		
		 //imagePanel = new JPanel(getClass().getResource("demo.jpg"));
		
		buttons.setVisible(true);
		graph.setVisible(true);
		calPanel.setVisible(true);
		//buttons.setLayout(new GridLayout((methodMan.length-1),1));
		
		
		complexityBox(getcal(9));
		System.out.println(complexityBox(getcal(9)).toString()+"checking complexity");
		
		while (count < methodMan.length)
		{
			String tempmethod = methodMan[count].getName().toString();
			int complexity=CyclomaticComplexityMethodCalc.getCyclomaticComplexity(methodMan[count]);
			
			createButton(tempmethod, buttons, group, count);
			System.out.println("tempmethod count " + count +" methodMan Length " + methodMan.length);
			System.out.println("tempmethod:   " + tempmethod);
			count ++;
		}
	}
	
}

