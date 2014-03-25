package engine;

import java.io.File;
import java.lang.reflect.Method;

import cyclomaticComplexity.GraphModel;

public interface MethodEngine  {
	
	//Returns the code inside the method in the program
	//pre: program is a compiled .java file
	//pre: method is in program
	//post: String returned represents the code of the method in program
	public String getCode(File program, Method method);
}
