package engine;

import java.util.List;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;

public class MethodCodeImpl implements MethodCode {

	private MethodDeclaration method;
	
	
	public MethodCodeImpl(MethodDeclaration m){
		method = m;
	}
	
	
	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public String getType() {
		return method.getType().toString();
	}

	@Override
	public String[] getParameterTypes() {
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

	
}
