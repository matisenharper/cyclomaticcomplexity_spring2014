package engine;

import japa.parser.JavaParser;
import japa.parser.ParseException;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cyclomaticComplexity.GraphModel;
import cyclomaticComplexity.GraphModelImpl;

public class GraphEngineImpl implements GraphEngine{

	public GraphEngineImpl(){
		StatementParser.initializeGraph();
	}
	
	@Override
	public GraphModel getGraphModel(MethodData method) {
		
		String code = method.getMethod().getBody();
		
		//generateGraphModel(code);
		new StatementParser(StatementParser.getGraph().getNodeCount() - 1).visit(((MethodCodeImpl)method.getMethod()).method.getBody(), null);
		
		ArrayList<int[]> ali = StatementParser.getGraph().getPaths();
		
		for (int i = 0; i < ali.size();i++){
			System.out.print("("+ali.get(i)[0]+","+ali.get(i)[1]+")");
		}
		
		System.out.print("\n\n");
		
		ArrayList<String> as = StatementParser.getGraph().getNodes();
		
		for (int i = 0; i < as.size();i++){
			System.out.println("("+as.get(i)+")");
		}
		
		return null;
	}
	
	
	/**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class StatementParser extends VoidVisitorAdapter {
    	private static GraphModel graph;
    	private int lastnode;
    	
    	public StatementParser(int ln){
    		lastnode = ln;
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

		@Override
		public void visit(AnnotationDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(AnnotationMemberDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayAccessExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayCreationExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ArrayInitializerExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(AssertStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}
		
		@Override
		public void visit(AssignExpr n, Object arg) {
			graph.addNode("Assign Expresion");
			if(lastnode > -1){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
        	lastnode = graph.getNodeCount()-1;
		}


		@Override
		public void visit(BinaryExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(BlockComment n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(BlockStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(BooleanLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(BreakStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(CastExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(CatchClause n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(CharLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassOrInterfaceDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ClassOrInterfaceType n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(CompilationUnit n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ConditionalExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ConstructorDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ContinueStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(DoStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(DoubleLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyMemberDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EmptyTypeDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EnclosedExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EnumConstantDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(EnumDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ExplicitConstructorInvocationStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ExpressionStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(FieldAccessExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(FieldDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ForeachStmt n, Object arg) {
			graph.addNode("foreach");
			int forstart = graph.getNodeCount()-1;
			int forend = -1;
			StatementParser parser;
        	
        	if(lastnode > -1){
        		graph.addEdge(lastnode, forstart);
        	}
        	
        	lastnode = graph.getNodeCount()-1;
        	
        	if(n.getBody() != null){
        		parser = new StatementParser(forstart);
                parser.visit(n.getBody(), null);
                forend = parser.getLastnode();
        	}
        	
        	graph.addNode("endForeach");
        	
        	if(forend > -1 && forend != lastnode){
        		graph.addEdge(forend, graph.getNodeCount()-1);
        	}
        	
        	graph.addEdge(forstart,graph.getNodeCount()-1);
        	lastnode = graph.getNodeCount()-1;
		}

		@Override
		public void visit(ForStmt n, Object arg) {
			graph.addNode("for");
			int forstart = graph.getNodeCount()-1;
			int forend = -1;
			StatementParser parser;
        	
        	if(lastnode > -1){
        		graph.addEdge(lastnode, forstart);
        	}
        	
        	lastnode = graph.getNodeCount()-1;
        	
        	if(n.getBody() != null){
        		parser = new StatementParser(forstart);
                parser.visit(n.getBody(), null);
                forend = parser.getLastnode();
        	}
        	
        	graph.addNode("endFor");
        	
        	if(forend > -1 && forend != lastnode){
        		graph.addEdge(forend, graph.getNodeCount()-1);
        	}
        	
        	graph.addEdge(forstart,graph.getNodeCount()-1);
        	lastnode = graph.getNodeCount()-1;
		}

		@Override
        public void visit(IfStmt n, Object arg) {
        	graph.addNode("if");
        	int ifstart = graph.getNodeCount()-1;
        	int ifend = -1;
        	int elseend = -1;
        	StatementParser parser;
        	
        	if(lastnode > -1){
        		graph.addEdge(lastnode, ifstart);
        	}
        	lastnode = graph.getNodeCount()-1;
        	
        	if(n.getThenStmt() != null){
        		parser = new StatementParser(ifstart);
                parser.visit(n.getThenStmt(), null);
        		ifend = parser.getLastnode();
        	}
        	if(n.getElseStmt() != null){
        		parser = new StatementParser(ifstart);
        		parser.visit(n.getElseStmt(), null);
                elseend = parser.getLastnode();
        	}
        	
        	graph.addNode("endIf");
        	
        	if(ifend > -1 && ifend != lastnode){
        		graph.addEdge(ifend, graph.getNodeCount()-1);
        	}
        	if(elseend != ifend && elseend > -1 && elseend != lastnode){
        		graph.addEdge(elseend, graph.getNodeCount()-1);
        	}else{
        		graph.addEdge(ifstart,graph.getNodeCount()-1);
        	}
        	
        	lastnode = graph.getNodeCount()-1;
        }
        
        @Override
		public void visit(ImportDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(InitializerDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(InstanceOfExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(IntegerLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(IntegerLiteralMinValueExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(JavadocComment n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(LabeledStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(LineComment n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(LongLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(LongLiteralMinValueExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(MarkerAnnotationExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(MemberValuePair n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(MethodCallExpr n, Object arg) {
			graph.addNode("Method Call");
			if(lastnode > -1){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
        	lastnode = graph.getNodeCount()-1;
		}
		
		@Override
		public void visit(MethodDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(NameExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(NormalAnnotationExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(NullLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ObjectCreationExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(PackageDeclaration n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(Parameter n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(PrimitiveType n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(QualifiedNameExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ReferenceType n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ReturnStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(SingleMemberAnnotationExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(StringLiteralExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(SuperExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(SwitchEntryStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(SwitchStmt n, Object arg) {
			super.visit(n, arg);
		}

		@Override
		public void visit(SynchronizedStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ThisExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(ThrowStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(TryStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(TypeDeclarationStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(TypeParameter n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(UnaryExpr n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(VariableDeclarationExpr n, Object arg) {
        	graph.addNode("Variable Declaration");
        	if(lastnode > -1){
        		graph.addEdge(lastnode, graph.getNodeCount()-1);
        	}
        	lastnode = graph.getNodeCount()-1;
		}

		@Override
		public void visit(VariableDeclarator n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(VariableDeclaratorId n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(VoidType n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(WhileStmt n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		@Override
		public void visit(WildcardType n, Object arg) {
			// TODO Auto-generated method stub
			super.visit(n, arg);
		}

		private void visit(Statement stmt, Object arg) {
			stmt.accept(this, arg);
		}

		public static CompilationUnit getCompilationUnit(String code){
			InputStream in = new ByteArrayInputStream( code.getBytes() );
	        CompilationUnit cu = null;
	        
	        try {
	            // parse the file
	            cu = JavaParser.parse(in);
	        } catch (ParseException e) {
				//e.printStackTrace();
			} finally {
	            try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        return cu;
		}
    }
}
