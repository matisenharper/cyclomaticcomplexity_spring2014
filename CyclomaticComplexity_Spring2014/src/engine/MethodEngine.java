package engine;

import java.io.File;
import java.lang.reflect.Method;

import cyclomaticComplexity.GraphModel;

public interface MethodEngine  {
	
	//Returns a GraphModel of method in the program
	//pre: program is a compiled .java file
	//pre: method is in program
	//post: GraphModel returned represents the graph of the method in program
	public GraphModel getMethodManager(File program, Method method);
}
