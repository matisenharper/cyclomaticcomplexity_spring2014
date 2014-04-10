package engine;

import cyclomaticComplexity.GraphModel;

public interface GraphEngine {
	
	//Returns a GraphModel of method representing the method given
	//pre: method is a String representing a compiled method of a class
	//post: GraphModel returned represents the path of the method
	public GraphModel getGraphModel(MethodCode method);
}
