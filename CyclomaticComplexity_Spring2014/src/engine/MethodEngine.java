package engine;

import java.io.File;
import java.lang.reflect.Method;

import cyclomaticComplexity.GraphModel;

public interface MethodEngine  {
	public GraphModel getMethodManager(File program, Method method);
}
