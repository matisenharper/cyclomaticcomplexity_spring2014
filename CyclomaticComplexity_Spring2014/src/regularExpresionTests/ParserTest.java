package regularExpresionTests;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.stmt.IfStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.IOException;

public class ParserTest {
    public static void method1() {
        int i = 1;
        System.out.println(i);
    }

    public static void method2() {
        String s = "hello";
        System.out.println(s);
    }

    public static void main(String[] args) throws ParseException, IOException {
        File f = new File("").getAbsoluteFile();
        File srcRoot = new File(f, "src/");
        String srcFilename = ParserTest.class.getName().replaceAll("\\.", "/") + ".java";
        File src = new File("./src/regularExpresionTests/Test.java");
        System.out.println(f);
        System.out.println(srcRoot);
        System.out.println(src);
        getMethodLineNumbers(src);
    }

    private static void getMethodLineNumbers(File src) throws ParseException, IOException {
        CompilationUnit cu = JavaParser.parse(src);
        new MethodVisitor().visit(cu, null);
        
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter {
        @Override
        public void visit(IfStmt m, Object arg) {
        	System.out.println("Start of IF");
            System.out.println(m.getThenStmt());
            System.out.println("End of IF");
        }
        
        @Override
        public void visit(ForStmt m, Object arg) {
        	System.out.println("Start of For");
            System.out.println(m.getBody());
            System.out.println("End of For");
        }
    }
}