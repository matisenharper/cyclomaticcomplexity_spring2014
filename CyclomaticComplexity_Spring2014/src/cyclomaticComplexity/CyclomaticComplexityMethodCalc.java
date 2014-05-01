package cyclomaticComplexity;

import java.util.ArrayList;

import engine.GraphEngine;
import engine.GraphEngineImpl;
import engine.MethodData;

public class CyclomaticComplexityMethodCalc 
{
	public static int getCyclomaticComplexity(MethodData body)
	{
		int cyclomaticComplexity;
		int nodeCount;
		int edgeCount;

		GraphEngine g = new GraphEngineImpl();
		GraphModel graph = new GraphModelImpl();
		graph = g.getGraphModel(body);

		ArrayList<int[]> edges= graph.getPaths();
		ArrayList<String> nodes= graph.getNodes();

		edgeCount=edges.size();
		nodeCount=nodes.size();

		//v(G) = e-n+2
		cyclomaticComplexity= edgeCount-nodeCount+2;
		return cyclomaticComplexity;
	}
}
