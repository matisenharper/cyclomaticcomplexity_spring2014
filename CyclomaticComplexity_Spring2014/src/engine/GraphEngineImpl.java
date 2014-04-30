/*
 * Created by Esteban Daines
 * 
 * This implementation utilizes Javaparser-1.0.8
 * 
 * Statements Implemented:
 * - if(){}
 * - if(){}else{}
 * - if() ;
 * - for(){}
 * - for() ;
 * - while(){}
 * - while() ;
 * - switch(){}
 * - break;
 * - return;
 * - Method calls
 * - Variable declarations
 * - Variable Assignation
 */
package engine;

import japa.parser.ast.BlockComment;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.LineComment;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.TypeParameter;
import japa.parser.ast.body.AnnotationDeclaration;
import japa.parser.ast.body.AnnotationMemberDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.EmptyMemberDeclaration;
import japa.parser.ast.body.EmptyTypeDeclaration;
import japa.parser.ast.body.EnumConstantDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.InitializerDeclaration;
import japa.parser.ast.body.JavadocComment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.body.VariableDeclaratorId;
import japa.parser.ast.expr.ArrayAccessExpr;
import japa.parser.ast.expr.ArrayCreationExpr;
import japa.parser.ast.expr.ArrayInitializerExpr;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.BinaryExpr;
import japa.parser.ast.expr.BooleanLiteralExpr;
import japa.parser.ast.expr.CastExpr;
import japa.parser.ast.expr.CharLiteralExpr;
import japa.parser.ast.expr.ClassExpr;
import japa.parser.ast.expr.ConditionalExpr;
import japa.parser.ast.expr.DoubleLiteralExpr;
import japa.parser.ast.expr.EnclosedExpr;
import japa.parser.ast.expr.FieldAccessExpr;
import japa.parser.ast.expr.InstanceOfExpr;
import japa.parser.ast.expr.IntegerLiteralExpr;
import japa.parser.ast.expr.IntegerLiteralMinValueExpr;
import japa.parser.ast.expr.LongLiteralExpr;
import japa.parser.ast.expr.LongLiteralMinValueExpr;
import japa.parser.ast.expr.MarkerAnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.expr.NullLiteralExpr;
import japa.parser.ast.expr.ObjectCreationExpr;
import japa.parser.ast.expr.QualifiedNameExpr;
import japa.parser.ast.expr.SingleMemberAnnotationExpr;
import japa.parser.ast.expr.StringLiteralExpr;
import japa.parser.ast.expr.SuperExpr;
import japa.parser.ast.expr.ThisExpr;
import japa.parser.ast.expr.UnaryExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.AssertStmt;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.BreakStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.ContinueStmt;
import japa.parser.ast.stmt.DoStmt;
import japa.parser.ast.stmt.EmptyStmt;
import japa.parser.ast.stmt.ExplicitConstructorInvocationStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.ForeachStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.stmt.LabeledStmt;
import japa.parser.ast.stmt.ReturnStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.SwitchEntryStmt;
import japa.parser.ast.stmt.SwitchStmt;
import japa.parser.ast.stmt.SynchronizedStmt;
import japa.parser.ast.stmt.ThrowStmt;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.stmt.TypeDeclarationStmt;
import japa.parser.ast.stmt.WhileStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.VoidType;
import japa.parser.ast.type.WildcardType;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import cyclomaticComplexity.GraphModel;
import cyclomaticComplexity.GraphModelImpl;

public class GraphEngineImpl implements GraphEngine{

	public GraphEngineImpl(){
		StatementParser.initializeGraph();
	}
	
	@Override
	public GraphModel getGraphModel(MethodData method) {
		
		StatementParser.initializeGraph(); 
		StatementParser sp = new StatementParser(StatementParser.getGraph().getNodeCount() - 1);
		sp.visit(((MethodCodeImpl)method.getMethod()).getBodyBlock(), null);
		sp.addEndNode();
		
		
		return StatementParser.getGraph();
	}
	
	
	/**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static class StatementParser extends VoidVisitorAdapter {
    	private static GraphModel graph;
    	private int lastnode;
    	private ArrayList<Integer> alternatelastnodes;
    	private ArrayList<Integer> alternatebreaknodes;
    	private ArrayList<Integer> alternatereturnnodes;
    	
    	public StatementParser(int ln){
    		lastnode = ln;
    		alternatelastnodes = new ArrayList<Integer>();
    		alternatebreaknodes = new ArrayList<Integer>();
    		alternatereturnnodes = new ArrayList<Integer>();
    	}
    	
    	public void addEndNode() {
    		graph.addNode("End");
    		for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
        	
			for(int arn:this.getAlternateReturnnodes()){
				graph.addEdge(arn, graph.getNodeCount()-1);
    		}
			this.clearAlternateReturnnodes();
        	
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			lastnode = graph.getNodeCount()-1;
		}

		public static GraphModel getGraph() {
    		return graph;
		}

		public static void initializeGraph(){
    		graph = new GraphModelImpl();
    	}
    	
		public int getLastnode() {
			return lastnode;
		}
		
		public ArrayList<Integer> getAlternateLastnodes() {
			return alternatelastnodes;
		}
		
		public void addAlternateLastnodes(int aln) {
			if(!alternatelastnodes.contains(aln))
				alternatelastnodes.add(aln);
		}
		
		public void clearAlternateLastnodes() {
			alternatelastnodes.clear();
		}
		
		public ArrayList<Integer> getAlternateBreaknodes() {
			return alternatebreaknodes;
		}

		public void addAlternateBreaknodes(int abn) {
			if(!alternatebreaknodes.contains(abn))
				alternatebreaknodes.add(abn);
		}
		
		public void clearAlternateBreaknodes() {
			alternatebreaknodes.clear();
		}
		
		public ArrayList<Integer> getAlternateReturnnodes() {
			return alternatereturnnodes;
		}
		
		public void addAlternateReturnnodes(int aln) {
			if(!alternatereturnnodes.contains(aln))
				alternatereturnnodes.add(aln);
		}
		
		public void clearAlternateReturnnodes() {
			alternatereturnnodes.clear();
		}
		
		private void visit(Statement stmt, Object arg) {
			stmt.accept(this, arg);
		}
		
		@Override
		public void visit(AssignExpr n, Object arg) {
			graph.addNode("Assign Expresion");
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
			lastnode = graph.getNodeCount()-1;
		}
		
		@Override
		public void visit(BreakStmt n, Object arg) {
			graph.addNode("Break");
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
        	lastnode = -1;
			this.addAlternateBreaknodes(graph.getNodeCount()-1);
		}

		@Override
		public void visit(ForeachStmt n, Object arg) {
			graph.addNode("ForEach");
			int start = graph.getNodeCount()-1;
			int end = -1;
			StatementParser parser;
        	
        	if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, start);
        	}
        	
        	for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, start);
    		}
        	this.clearAlternateLastnodes();
    		
    		lastnode = graph.getNodeCount()-1;
        	
        	if(n.getBody() != null){
        		parser = new StatementParser(start);
                parser.visit(n.getBody(), null);
                end = parser.getLastnode();
                
                for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        	}
        	
        	for(int abn:this.getAlternateBreaknodes()){
    			this.addAlternateLastnodes(abn);
    		}
        	this.clearAlternateBreaknodes();
        	
        	if(end > -1 && end != lastnode){
        		lastnode = end;
        	}
        	if(lastnode != start){
        		this.addAlternateLastnodes(start);
        	}
		}

		@Override
		public void visit(ForStmt n, Object arg) {
			graph.addNode("For");
			int start = graph.getNodeCount()-1;
			int end = -1;
			StatementParser parser;
        	
        	if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, start);
        	}
        	
        	for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, start);
    		}
        	this.clearAlternateLastnodes();
    		
    		lastnode = graph.getNodeCount()-1;
        	
        	if(n.getBody() != null){
        		parser = new StatementParser(start);
                parser.visit(n.getBody(), null);
                end = parser.getLastnode();
                
                for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        	}
        	
        	for(int abn:this.getAlternateBreaknodes()){
    			this.addAlternateLastnodes(abn);
    		}
        	this.clearAlternateBreaknodes();
        	
        	if(end > -1 && end != lastnode){
        		lastnode = end;
        	}
        	if(lastnode != start){
        		this.addAlternateLastnodes(start);
        	}
		}

		@Override
        public void visit(IfStmt n, Object arg) {
        	graph.addNode("If");
        	int ifstart = graph.getNodeCount()-1;
        	int ifend = -1;
        	int elseend = ifstart;
        	StatementParser parser;
        	
        	if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, ifstart);
        	}
        	
        	for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, ifstart);
    		}
        	this.clearAlternateLastnodes();
    		
    		lastnode = graph.getNodeCount()-1;
        	
        	if(n.getThenStmt() != null){
        		parser = new StatementParser(ifstart);
        		
        		parser.visit(n.getThenStmt(), null);
        		
        		ifend = parser.getLastnode();
        		
        		for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        	}
        	if(n.getElseStmt() != null){
        		parser = new StatementParser(ifstart);
        		
        		parser.visit(n.getElseStmt(), null);
                
        		elseend = parser.getLastnode();
                
        		for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        	}
        	
        	if(ifend > -1){
        		lastnode = ifend;
        	}
        	if(elseend != ifend && elseend > -1){
        		this.addAlternateLastnodes(elseend);
        	}
        }
        
		@Override
		public void visit(MethodCallExpr n, Object arg) {
			graph.addNode("Method Call");
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
			lastnode = graph.getNodeCount()-1;
		}
		
		@Override
		public void visit(ReturnStmt n, Object arg) {
			graph.addNode("Return");
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
			this.addAlternateReturnnodes(graph.getNodeCount()-1);
			lastnode = -1;
		}
		
		@Override
		public void visit(SwitchEntryStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(SwitchStmt n, Object arg) {
			graph.addNode("Switch");
			int start = graph.getNodeCount()-1;
			int end = -1;
			StatementParser parser;
			List<SwitchEntryStmt> cases = n.getEntries();
			
        	if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, start);
        	}
        	
        	lastnode = graph.getNodeCount()-1;
        	
        	for(SwitchEntryStmt s:cases){
        		parser = new StatementParser(start);
        		if(end > -1 && end != lastnode){
            		parser.addAlternateLastnodes(end);
            	}
            	parser.visit(s, null);
                
            	end = parser.getLastnode();
                
                for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        		
        		if(s.getLabel()==null){
        			lastnode = end;
        		}
        	}
        	
        	if(end!=-1){
        		this.addAlternateLastnodes(end);
        	}
        	
        	for(int abn:this.getAlternateBreaknodes()){
    			this.addAlternateLastnodes(abn);
    		}
        	this.clearAlternateBreaknodes();
		}
		
		@Override
		public void visit(VariableDeclarationExpr n, Object arg) {
			graph.addNode("Variable Declaration");
			if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
			for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, graph.getNodeCount()-1);
    		}
			this.clearAlternateLastnodes();
			lastnode = graph.getNodeCount()-1;
		}
		
		@Override
		public void visit(WhileStmt n, Object arg) {
			graph.addNode("While");
			int start = graph.getNodeCount()-1;
			int end = -1;
			StatementParser parser;
        	
        	if(lastnode > -1 && !this.getAlternateLastnodes().contains(lastnode)){
        		graph.addEdge(lastnode, start);
        	}
        	
        	for(int aln:this.getAlternateLastnodes()){
        		graph.addEdge(aln, start);
    		}
        	this.clearAlternateLastnodes();
    		
    		lastnode = graph.getNodeCount()-1;
        	
        	if(n.getBody() != null){
        		parser = new StatementParser(start);
                parser.visit(n.getBody(), null);
                end = parser.getLastnode();
                
                for(int abn:parser.getAlternateBreaknodes()){
        			this.addAlternateBreaknodes(abn);
        		}
        		
        		for(int aln:parser.getAlternateLastnodes()){
        			this.addAlternateLastnodes(aln);
        		}
        		
        		for(int arn:parser.getAlternateReturnnodes()){
        			this.addAlternateReturnnodes(arn);
        		}
        	}
        	
        	for(int abn:this.getAlternateBreaknodes()){
    			this.addAlternateLastnodes(abn);
    		}
        	this.clearAlternateBreaknodes();
        	
        	if(end > -1 && end != lastnode){
        		lastnode = end;
        	}
        	if(lastnode != start){
        		this.addAlternateLastnodes(start);
        	}
		}
		
		//Not implemented statements
		@Override
		public void visit(AnnotationDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(AnnotationMemberDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayAccessExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayCreationExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayInitializerExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(AssertStmt n, Object arg) {
			super.visit(n, arg);
		}
		
		@Override
		public void visit(BinaryExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(BlockComment n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(BlockStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(BooleanLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(CastExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(CatchClause n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(CharLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassOrInterfaceType n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(CompilationUnit n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ConditionalExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ConstructorDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ContinueStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(DoStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(DoubleLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyMemberDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyTypeDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EnclosedExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EnumConstantDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(EnumDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ExpressionStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(FieldAccessExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(FieldDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ImportDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(InitializerDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(InstanceOfExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(IntegerLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(IntegerLiteralMinValueExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(JavadocComment n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(LabeledStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(LineComment n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(LongLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(LongLiteralMinValueExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(MarkerAnnotationExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(MemberValuePair n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(MethodDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(NameExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(NormalAnnotationExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(NullLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ObjectCreationExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(PackageDeclaration n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(Parameter n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(PrimitiveType n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(QualifiedNameExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ReferenceType n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(SingleMemberAnnotationExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(StringLiteralExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(SuperExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(SynchronizedStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ThisExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(ThrowStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(TryStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(TypeDeclarationStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(TypeParameter n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(UnaryExpr n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(VariableDeclarator n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(VariableDeclaratorId n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(VoidType n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(WildcardType n, Object arg) {
			super.visit(n, arg);
		}
    }
}
