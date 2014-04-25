package engine;

import java.io.IOException;
import java.util.List;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;

public class MethodCodeImpl implements MethodCode {

	public MethodDeclaration method;
	
	
	public MethodCodeImpl(MethodDeclaration m){
		method = m;
	}
	
	
	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public String getReturnClass() {
		return method.getType().toString();
	}

	@Override
	public String[] getParameterClasses() {
		List<Parameter> l = method.getParameters();
		String[] parameters = new String[l.size()];
		int i = 0;
		for (Parameter p : l) {
			parameters[i] = p.getType().toString();
			i++;
		}
		return parameters;
	}


	@Override
	public String getBody() {
		return method.getBody().toString();
	}

	public BlockStmt getBodyBlock() {
		return method.getBody();
	}

	@Override
	public String getAccessType() throws IOException {
		String at = "";
		switch(method.getModifiers()){
			case 0:
				at = "";
				break;
			case 1:
				at = "public";
				break;
			case 2:
				at = "private";
				break;
			case 4:
				at = "protected";
				break;
			case 8:
				at = "static";
				break;
			case 9:
				at = "public static";
				break;
			case 10:
				at = "private static";
				break;
			case 12:
				at = "protected static";
				break;
			default:
				throw new IOException("Access Modifier not Implemented");
			
		}
		return at;
	}
	
	@Override
	public String toString(){
		return method.toString().replace(method.getBody().toString(), "");
	}

	
}
