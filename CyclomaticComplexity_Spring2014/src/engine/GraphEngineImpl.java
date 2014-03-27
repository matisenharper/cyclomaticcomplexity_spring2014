package engine;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import regularExpresionTests.GraphModelTestMatcher;
import cyclomaticComplexity.GraphModel;
import cyclomaticComplexity.GraphModelImpl;

public class GraphEngineImpl implements GraphEngine{

	GraphModel graph;
	
	public GraphEngineImpl(){
		graph = new GraphModelImpl();
	}
	
	@Override
	public GraphModel getGraphModel(File program, Method method) {
		
		MethodEngine me = new MethodEngineImpl();
		String code = me.getCode(program, method);
		
		generateGraphModel(code,graph.getNodeCount());
		
		ArrayList<int[]> ali = graph.getPaths();
		
		for (int i = 0; i < ali.size();i++){
			System.out.print("("+ali.get(i)[0]+","+ali.get(i)[1]+")");
		}
		
		System.out.print("\n\n");
		
		ArrayList<String> as = graph.getNodes();
		
		for (int i = 0; i < as.size();i++){
			System.out.print("("+as.get(i)+")");
		}
		
		return null;
	}
	
	public int generateGraphModel(String method, int tempnode){
		int i;
		boolean finish = false;
		int start = -1;
		int end = -1;
		int first = -1;
		int startcurly = -1;
		int lastnode = 0;
		
		Pattern[] patterns = ExpresionMatcher.getPatternExpresions();
		Matcher expresion;
		
		String code = ExpresionMatcher.removeComments(method);
		
		while(!finish){
			first = -1;
			start = -1;
			end = -1;
			for(i = 0;i < patterns.length;i++){
				expresion = patterns[i].matcher(code);
				if(expresion.find()){
					if(start < 0 || expresion.start() <= start){
						start = expresion.start();
						end = expresion.end();
						first = i;
					}
				}
			}
			
			if(start == -1){
				finish = true;
				addNode("stat", tempnode, lastnode);
			}else{
				switch(first){
				case 0:
					startcurly = ExpresionMatcher.getNextCurly(code,ExpresionMatcher.getEndofParenthesis(code, end));
					end = ExpresionMatcher.getEndofCurly(code, startcurly);
					addNode("if", tempnode, lastnode);
					tempnode = graph.getNodeCount();
					lastnode = generateGraphModel(code.substring(startcurly, end-1),tempnode);
					break;
				case 1:
					startcurly = end;
					end = ExpresionMatcher.getEndofCurly(code, startcurly);
					tempnode = generateGraphModel(code.substring(startcurly, end-1),tempnode);
					break;
				case 2:
					startcurly = ExpresionMatcher.getNextCurly(code,ExpresionMatcher.getEndofParenthesis(code, end));
					end = ExpresionMatcher.getEndofCurly(code, startcurly);
					addNode("while", tempnode, lastnode);
					tempnode = graph.getNodeCount();
					lastnode = generateGraphModel(code.substring(startcurly, end-1),tempnode);
					break;
				case 3:
					startcurly = ExpresionMatcher.getNextCurly(code,ExpresionMatcher.getEndofParenthesis(code, end));
					end = ExpresionMatcher.getEndofCurly(code, startcurly);
					addNode("for", tempnode, lastnode);
					tempnode = graph.getNodeCount();
					lastnode = generateGraphModel(code.substring(startcurly, end-1),tempnode);
					break;
				}
				code = code.substring(end);
			}
		}
		return graph.getNodeCount();
	}
	
	
	void addNode(String type, int tempnode,int lastnode){
		graph.addNode(type);
		if(lastnode > 0){
			graph.addEdge(lastnode-1, graph.getNodeCount()-1);
		}
		if(tempnode > 0){
			graph.addEdge(tempnode-1, graph.getNodeCount()-1);
		}
		
	}
}
