package cyclomaticComplexity;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTextArea;

import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.GraphViz;
import engine.MethodData;

public class GraphImage 
{
	public static File getImage (MethodData body) throws RuntimeException
	{
		File img;
		GraphEngine g = new GraphEngineImpl();
		GraphModel graph = new GraphModelImpl();
		graph = g.getGraphModel(body);
		
		ArrayList<int[]> ali = graph.getPaths();
    	ArrayList<String> as = graph.getNodes();
    	
		String text = "";
    	JTextArea label = new JTextArea();
    	for (int i = 0; i < as.size();i++){
			text = text + ("("+i+")("+as.get(i)+")")+"\n";
		}
    	text = text + "Complexity = " + CyclomaticComplexityMethodCalc.getCyclomaticComplexity(body);
    	label.setText(text);
		
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		for (int i = 0; i < ali.size();i++){
			gv.addln(ali.get(i)[0]+" -> "+ali.get(i)[1]+";");
		}
		for (int i = 0; i < as.size();i++){
			gv.addln(""+i+" [label=\""+as.get(i)+"\",shape=box,fillcolor=\"#FFFFFF\",style=\"filled,rounded\"]");
		}
    	gv.addln(gv.end_graph());
    	
    	String type = "png";
		File out = new File("out." + type);   // out.gif in this example
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
		
		img=out;
		
		return img;
		
	}
	 
}
