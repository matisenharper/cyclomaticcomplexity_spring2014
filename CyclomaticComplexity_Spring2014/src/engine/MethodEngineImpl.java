package engine;

import java.io.File;
import java.lang.reflect.Method;

import regularExpresionTests.RegexTest;
import cyclomaticComplexity.GraphModel;

public class MethodEngineImpl  implements MethodEngine{
	
	public MethodEngineImpl(){
	}

	@Override
	public String getCode(File program, Method method) {
		String s = RegexTest.getText("C:/Java/Prueba.java");
		return s;
	}

}
