package regularExpresionTests;

import java.io.*;
import java.lang.reflect.Method;

import Milestone1_Runner.COSC3327_STUDENT_SMALL;
import Milestone1_Runner.Student;
import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.MethodData;
import engine.MethodDataImpl;
import engine.MethodCode;
import engine.MethodUtils;
import engine.MethodUtilsImpl;

public class RegexTest {

    public static void main(String[] args){
        
    	File file = new File("./src/engine/MethodCodeImpl.java");
    	MethodCode method = null;
    	
    	GraphEngine g = new GraphEngineImpl();
    	Student author = COSC3327_STUDENT_SMALL.SMITH;
    	MethodData code = new MethodDataImpl(author,method);
    	
    	//g.getGraphModel(code);
    	MethodUtils m = new MethodUtilsImpl();
    	MethodCode[] methods = m.getMethods(file);
    	
    	for(MethodCode me : methods){
        	 System.out.println(me.getBody());
        }
    }    
}