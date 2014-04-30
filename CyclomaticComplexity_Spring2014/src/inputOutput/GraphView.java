package inputOutput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import MISC.Student;
import engine.MethodCode;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodUtils;
import engine.MethodUtilsImpl;


public class GraphView extends JPanel
{
	//this just for testing
	static MethodData[] methodArray = {null};
	static int selected;
	static JPanel imagePanel;
	static GraphView graph;
	static JTextField complexityNumber;
	//

	public GraphView() 
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		setSize(width, height);
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
		return body;
	}
	private static void changeImage ()
	{
		File imgfile = GraphImage.getImage(methodArray[selected]);
		imagePanel.removeAll();
		ImagePanel img = new ImagePanel(imgfile);
		ImageScroller scroller = new ImageScroller(img);
		imagePanel.setLayout(new BorderLayout());
		imagePanel.add(scroller);
		
		graph.revalidate();
		
		
	}
	private static void changeComplexity ()
	{
		int cal = cyclomaticComplexity.CyclomaticComplexityMethodCalc.getCyclomaticComplexity(methodArray[selected]); 
		complexityNumber.setText("Complexity: " + cal);	
	}

	private static void createButton(String methodName,JPanel buttons, ButtonGroup group, int methodIndex)//, Student studentName, File file)
	{
		JRadioButton button = new methodRadioButton(methodName, methodIndex);
		button.setBackground(Color.decode("#16E5C4"));
		buttons.add(button);
		group.add(button);
		

		RadioListener listens=new RadioListener();
		button.addActionListener(listens);		
	}

	
	static class RadioListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
		GraphView.selected = ((methodRadioButton)e.getSource()).i;
		GraphView.changeImage();
		GraphView.changeComplexity();
	}
	}
	private static JTextField complexityBox (int i)
	{
		JTextField calPanel = new JTextField("Complexity Number",getcal(i));
		calPanel.addActionListener(null);
		return calPanel;
	}

	private static void MethodHeaderLooker(File file, JPanel graph) throws IOException
	{

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
			}
		});

	}


	private static void showImage(JPanel graph)
	{
		ImageShow gui = new ImageShow(graph);
		graph.add(gui);

	}

	
	public static GraphView setData (File file, Student studentName)
	{
		methodArray =gettingSignature(file, studentName);
		return buttonMan(methodArray);
				
	}
	
	private static GraphView buttonMan(MethodData[] methodMan)
	{
		int count =	0;
		
		graph = new GraphView();
		imagePanel = new JPanel();
		JPanel buttons= new JPanel();
		ButtonGroup group=new ButtonGroup();
		JPanel calPanel = new JPanel();
		JPanel ForColor = new JPanel();
		complexityNumber = new JTextField();
		
		calPanel.add(complexityNumber);
		
		selected = 0;
		File imgfile = GraphImage.getImage(methodArray[selected]); 
		ImagePanel img = new ImagePanel(imgfile);
		ImageScroller scroller = new ImageScroller(img);
		scroller.setVisible(true);
		imagePanel.setLayout(new BorderLayout());
		
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
		
		buttons.setVisible(true);
		graph.setVisible(true);
		calPanel.setVisible(true);
		
		complexityBox(getcal(count));	
		while (count < methodMan.length)
		{
			String tempmethod = methodMan[count].getName().toString();
			int complexity=CyclomaticComplexityMethodCalc.getCyclomaticComplexity(methodMan[count]);
			
			createButton(tempmethod, buttons, group, count);
			count ++;
		}
		return graph;
	}
}

