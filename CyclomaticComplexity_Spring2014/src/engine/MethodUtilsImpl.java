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

import MISC.Student;

public class MethodUtilsImpl  implements MethodUtils{
	
	public MethodUtilsImpl(){
	}
	
	@Override
	public MethodData[] getMethods(File program, Student s) {
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
       
        MethodData[] methods = new MethodData[list.size()];
        
        int i = 0;
        for(MethodDeclaration method : list){
        	MethodCode m = new MethodCodeImpl(method);
        	methods[i] = new MethodDataImpl(s,m);
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
