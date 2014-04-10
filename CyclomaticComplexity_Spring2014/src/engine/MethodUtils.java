package engine;

import java.io.File;
import java.lang.reflect.Method;

public interface MethodUtils {
	
	//Pre: program is a file of a compiled java class.
	//Returns a list of methods signatures within the class.
	Method[] getMethods(File program);
	
	//Pre: method is the signature of a method in program.
	//Returns a String representing the inside body of the method.
	String getBody(File program, Method method);
}
