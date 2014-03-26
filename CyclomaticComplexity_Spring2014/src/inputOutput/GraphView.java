package inputOutput;

import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.awt.*;

import javax.swing.plaf.*;
import javax.accessibility.*;

import java.io.Serializable;//  JavaDoc;
import java.io.ObjectOutputStream;//  JavaDoc;
import java.io.ObjectInputStream;//  JavaDoc;
import java.io.IOException;//  JavaDoc;
import java.lang.reflect.Method;
import java.util.Random;



public class GraphView extends JFrame {

	public GraphView() 
	{

		setTitle("Student Name");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);        
	}
	
	private JRadioButton createButton(String buttonName)
	{
		JPanel buttonPanel= new JPanel();
		JRadioButton button = new JRadioButton(buttonName, false);
		
		

		return button;
	}


	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GraphView graph = new GraphView();
				graph.setVisible(true);
			}
		});
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