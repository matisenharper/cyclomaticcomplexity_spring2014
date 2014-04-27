package regularExpresionTests;

import japa.parser.ast.body.MethodDeclaration;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import cyclomaticComplexity.GraphModel;
import cyclomaticComplexity.GraphModelImpl;
import MISC.Student;
import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.MethodCodeImpl;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodCode;
import engine.MethodUtils;
import engine.MethodUtilsImpl;

public class RegexTest {

    public static void main(String[] args){
        
    	File file = new File("./src/regularExpresionTests/Test.java");
    	
    	GraphEngine g = new GraphEngineImpl();
    	Student author = Spring2014Users.Student.BOB;
    	
    	MethodUtils m = new MethodUtilsImpl();
    	MethodData[] methods = m.getMethods(file,author);
    	
    	GraphModel graph = new GraphModelImpl();
    	
    	for(MethodData me : methods){
    		graph = g.getGraphModel(me);
        }
    	
    	ArrayList<int[]> ali = graph.getPaths();
		
		for (int i = 0; i < ali.size();i++){
			System.out.print("("+ali.get(i)[0]+","+ali.get(i)[1]+")");
		}
		
		System.out.print("\n\n");
		
		ArrayList<String> as = graph.getNodes();
		
		String text = "Hello world";
        try {
        	File f = new File("example.dot");
        	BufferedWriter output = new BufferedWriter(new FileWriter(f));
        	output.write("digraph G {\n");
          	for (int i = 0; i < ali.size();i++){
  				output.write(ali.get(i)[0]+" -> "+ali.get(i)[1]+";\n");
  			}
          	output.write("}");
          	output.close();
        } catch ( IOException e ) {
        	e.printStackTrace();
        }
		
		for (int i = 0; i < as.size();i++){
			System.out.println("("+i+")("+as.get(i)+")");
		}
		
		//GraphViz Syntax .dot
		//digraph G {
		//A -> B;
		//}
		
    }    
}