package Engine;

import cyclomaticComplexity.GraphModel;

public class GraphEngine implements GraphEngineInterface{

	public GraphEngine(){
	}
	
	@Override
	public GraphModel getGraphModel(String method) {
		GraphModel graph = new GraphModel();
		
		return graph;
	}
	
}
