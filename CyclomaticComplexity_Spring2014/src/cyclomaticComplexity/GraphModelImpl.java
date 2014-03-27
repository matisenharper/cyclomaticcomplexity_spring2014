package cyclomaticComplexity;

import java.util.ArrayList;

public class GraphModelImpl {

	int nodecount;
	ArrayList<int[]> edges;
	ArrayList<String> nodes;
	
	public GraphModelImpl(){
		nodecount = 0;
		edges = new ArrayList<int[]>();
		nodes = new ArrayList<String>();
	}
	
	public void addNode(String type) {
		nodecount++;
		nodes.add(type);
	}

	public void addEdge(int start, int end) {
		int array[] = {start,end};
		edges.add(array);
	}

	public ArrayList<int[]> getPaths() {
		return edges;
	}

	public ArrayList<String> getNodes() {
		return nodes;
	}

	public int getNodeCount() {
		return nodecount;
	}
}
