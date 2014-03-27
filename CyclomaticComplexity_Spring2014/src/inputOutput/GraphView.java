package inputOutput;

import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.accessibility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;//  JavaDoc;
import java.io.ObjectOutputStream;//  JavaDoc;
import java.io.ObjectInputStream;//  JavaDoc;
import java.io.IOException;//  JavaDoc;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class GraphView extends JFrame {

	public GraphView() 
	{

		setTitle("Student Name");
		setSize(950, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);        
	}

	/*To Show jpeg in GUI LOOK AT:
	 * http://nf.nci.org.au/facilities/software/Matlab/techdoc/creating_guis/ch_ove16.html
	 * 
	 * http://www.mathworks.com/matlabcentral/answers/24009-display-jpeg-file-in-gui
	 * 
	 * OR DO:*/
	public static class ImageShow extends JPanel
	{
		private ImageIcon image;
		private JLabel label;

		ImageShow()
		{
			setLayout(new FlowLayout());
			image = new ImageIcon(getClass().getResource("demo.jpg"));
			label = new JLabel(image);
			add(label);
		}

	}


	private static void createButton(String buttonName, GraphView graph)
	{
		JPanel buttonPanel= new JPanel();
		//ButtonGroup methodGroup = new ButtonGroup();
		JRadioButton button = new JRadioButton(buttonName, false);
		
		
		buttonPanel.add(button);
		graph.add(buttonPanel);
		

	}


	public static void MethodHeaderLooker(File file, GraphView graph)
	{
		//method header example to look for
		final String REGEX = "/public [a-zA-Z1-9]([a-zA-Z1-9])private [a-zA-Z1-9]([a-zA-Z1-9])/";
		
		//File filejava = new File(file);
		System.out.println("running methodheaderlooker");
		
		try {
			System.out.println("running methodheaderlooker2");
			Scanner scanner = new Scanner("Tic.txt");
			
			//now read the file line by line...
			int lineNum = 0;
			while (scanner.hasNextLine()) 
			{
				System.out.println("running methodheaderlooker3");
				String line = scanner.nextLine();
				lineNum++;
				System.out.println(line);
				
				//determine whether line contains a method
				//if method is contained, create button with method name
				if(line.contains("public")|| line.contains("private"))
				{
					//create button named after method that was found in 'line'
					createButton(line,graph);
					
				}
			
			}
			scanner.close();
		} catch(Exception e) { 
			System.out.println("catching");
			//handle this
		}
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

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				File file=new File("Tic.txt");
				GraphView graph = new GraphView();
				graph.setVisible(true);
				showImage(graph);
				createButton("Test Button",graph);
				MethodHeaderLooker(file, graph);
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

	private static void showImage(GraphView graph)
	{
		ImageShow gui = new ImageShow();
		//gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//gui.setVisible(true);
		graph.add(gui);
		graph.pack();
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