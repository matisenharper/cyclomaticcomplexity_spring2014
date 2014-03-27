package cyclomaticComplexity;

import java.util.ArrayList;

public interface GraphModel{
	
	void addNode(String type);

	void addEdge(int start, int end);

	ArrayList<int[]> getPaths();

	ArrayList<String> getNodes();

	int getNodeCount();
}
