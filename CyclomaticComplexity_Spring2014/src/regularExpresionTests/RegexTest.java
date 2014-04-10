package regularExpresionTests;

import java.io.*;
import java.lang.reflect.Method;

import Milestone1_Runner.COSC3327_STUDENT_SMALL;
import Milestone1_Runner.Student;
import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.MethodCode;
import engine.MethodCodeImpl;

public class RegexTest {

    public static void main(String[] args){
        
    	File file = new File("./src/regularExpresionTests/Test.java");
    	Method method = null;
    	
    	GraphEngine g = new GraphEngineImpl();
    	Student author = COSC3327_STUDENT_SMALL.SMITH;
    	MethodCode code = new MethodCodeImpl(author,file,method);
    	
    	g.getGraphModel(code);
    	
    	//s = s.replaceAll("//.*?\\\n", "\n");
    	//s = s.replaceAll("/\\*(.*?\\s*?)*?\\*/", "\n");
    	
    	
    	
    	/*System.out.print(s);
    	
    	Pattern pattern = 
        Pattern.compile("if\\s*\\(");
    	
    	Matcher matcher = pattern.matcher(s);

        boolean found = false;
        while (matcher.find()) {
        	int end = matcher.end();
        	char[] cs = s.toCharArray();
        	for(int i = 1;i>0;end++){
        		if(cs[end] =='{'){
        			i++;
        		}else if(cs[end] =='}'){
        			i--;
        		}
        	}
        	System.out.println(s.substring(matcher.end(), end-1));
        	System.out.println("I found the text \""+matcher.group()+"\" starting at index "+matcher.start()+" and ending at index "+end);
            found = true;
        }
        if(!found){
            System.out.println("No match found");
        }*/
    }
    
    
}