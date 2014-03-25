package inputOutput;

import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRadioButton;


import java.awt.*;

import javax.swing.plaf.*;
import javax.accessibility.*;

import java.io.Serializable;//  JavaDoc;
import java.io.ObjectOutputStream;//  JavaDoc;
import java.io.ObjectInputStream;//  JavaDoc;
import java.io.IOException;//  JavaDoc;
import java.lang.reflect.Method;
import java.util.Random;


public class GraphView<MyPanel>  extends JComponent
{
        public static final int FRAMEHEIGHT=900;
        public static final int FRAMEWIDTH=1000;
        int line [] = new int[500];

        MyPanel graphPanel;
        GraphView buttonPanel;

        Thread runner;

        //this will see how meny methods I have and keep adding radio button until there is no more methods
        
        
        //call the method that will create the number of buttons
        JRadioButton RadioButton()
        {
        	//it should return radio buttons with the name of methods
			return null;
		}

/*private JRadioButton RadioButton(Methods methodName)
{
        //Method m = new Method();
        JRadioButton button = new JRadioButton();
        while (number of methods in class are not over )
        {
                button = new JRadioButton (methodName);
        }
        return button;
}*/

public void refresh()
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