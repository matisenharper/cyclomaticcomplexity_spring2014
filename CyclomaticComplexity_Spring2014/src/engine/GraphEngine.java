package engine;

import cyclomaticComplexity.GraphModel;

public interface GraphEngine {
	
	//Returns a GraphModel of method representing the method given
	//pre: method is a MethodData of a compiled method of a class
	//post: GraphModel returned represents the possible paths of the method
	public GraphModel getGraphModel(MethodData method);
}
