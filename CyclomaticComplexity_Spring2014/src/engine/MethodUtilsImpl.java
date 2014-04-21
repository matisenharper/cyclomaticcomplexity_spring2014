package engine;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodUtilsImpl  implements MethodUtils{
	
	public MethodUtilsImpl(){
	}
	
	@Override
	public MethodCode[] getMethods(File program) {
		CompilationUnit cu = null;
		try {
			cu = JavaParser.parse(program);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        MethodVisitor mv = new MethodVisitor();
        mv.visit(cu, null);
        
        ArrayList<MethodDeclaration> list = mv.getMethods();
       
        MethodCode[] methods = new MethodCode[list.size()];
        
        int i = 0;
        for(MethodDeclaration method : list){
        	methods[i] = new MethodCodeImpl(method);
        	i++;
        }
		return methods;
	}
	
	private static class MethodVisitor extends VoidVisitorAdapter {
		
		ArrayList<MethodDeclaration> methodlist;
		
		public MethodVisitor(){
			methodlist = new ArrayList<MethodDeclaration>();
		}
		
        @Override
        public void visit(MethodDeclaration m, Object arg) {
            methodlist.add(m);
        }
        
        public ArrayList<MethodDeclaration> getMethods(){
        	return methodlist;
        }
    }
}
