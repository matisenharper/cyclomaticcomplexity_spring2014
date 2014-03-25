package engine;

import java.io.File;
import java.lang.reflect.Method;

import cyclomaticComplexity.GraphModel;

public interface GraphEngine {
	
	//Returns a GraphModel of method representing the method given
	//pre: method is a String representing a compiled method of a class
	//post: GraphModel returned represents the path of the method
	public GraphModel getGraphModel(File program, Method method);
}
