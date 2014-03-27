package cyclomaticComplexity;

import java.util.ArrayList;

public class GraphModelImpl implements GraphModel {

	int nodecount;
	ArrayList<int[]> edges;
	ArrayList<String> nodes;
	
	public GraphModelImpl(){
		nodecount = 0;
		edges = new ArrayList<int[]>();
		nodes = new ArrayList<String>();
	}
	
	@Override
	public void addNode(String type) {
		nodecount++;
		nodes.add(type);
	}

	@Override
	public void addEdge(int start, int end) {
		int array[] = {start,end};
		edges.add(array);
	}

	@Override
	public ArrayList<int[]> getPaths() {
		return edges;
	}

	@Override
	public ArrayList<String> getNodes() {
		return nodes;
	}
	
	@Override
	public int getNodeCount() {
		return nodecount;
	}
}
