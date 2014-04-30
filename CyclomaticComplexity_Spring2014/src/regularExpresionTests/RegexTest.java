package regularExpresionTests;

import inputOutput.GraphView;
import inputOutput.ImagePanel;
import inputOutput.ImageScroller;
import inputOutput.TextScroller;
import japa.parser.ast.body.MethodDeclaration;

import java.awt.BorderLayout;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cyclomaticComplexity.CyclomaticComplexityMethodCalc;
import cyclomaticComplexity.GraphModel;
import cyclomaticComplexity.GraphModelImpl;
import MISC.Student;
import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.GraphViz;
import engine.MethodCodeImpl;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodCode;
import engine.MethodUtils;
import engine.MethodUtilsImpl;

public class RegexTest {

    public static void main(String[] args){
    	
    	int asdf = 3;
        
    	File file = new File("./src/tictactoe/TicTacToeBoardImpl_Hoffpauir.java");
    	
    	GraphEngine g = new GraphEngineImpl();
    	Student author = Spring2014Users.Student.BOB;
    	
    	MethodUtils m = new MethodUtilsImpl();
    	MethodData[] methods = m.getMethods(file,author);
    	
    	GraphModel graph = new GraphModelImpl();
    	
    	for(MethodData me : methods){
    		graph = g.getGraphModel(me);
    		System.out.print(me.getName());
        }
    	
    	graph = g.getGraphModel(methods[asdf]);
    	
    	ArrayList<int[]> ali = graph.getPaths();
    	ArrayList<String> as = graph.getNodes();
    	
    	String text = "";
    	JTextArea label = new JTextArea();
    	for (int i = 0; i < as.size();i++){
			text = text + ("("+i+")("+as.get(i)+")")+"\n";
		}
    	text = text + "Complexity = " + CyclomaticComplexityMethodCalc.getCyclomaticComplexity(methods[asdf]);
    	label.setText(text);
		
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		for (int i = 0; i < ali.size();i++){
			gv.addln(ali.get(i)[0]+" -> "+ali.get(i)[1]+";");
		}
		gv.addln(gv.end_graph());
		
		String type = "png";
		File out = new File("out." + type);   // out.gif in this example
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
		
		JFrame frame = new JFrame("Simple GUI"); 
		/*ImagePanel img = new ImagePanel(out);
		ImageScroller scroller = new ImageScroller(img);
		TextScroller scroller2 = new TextScroller(label);
		
		frame.getContentPane().add(scroller, BorderLayout.WEST); 
		frame.getContentPane().add(scroller2, BorderLayout.EAST); 
		frame.setLocation(0,0);
		frame.setSize(800, 600); 
		frame.setVisible(true);
		*/
		
		GraphView gp = GraphView.setData(file, author);
		frame.getContentPane().add(gp, BorderLayout.CENTER); 
		frame.setLocation(0,0);
		frame.setSize(800, 600); 
		frame.setVisible(true);
		
		//GraphViz Syntax .dot
		//digraph G {
		//A -> B;
		//}
		
    }
}