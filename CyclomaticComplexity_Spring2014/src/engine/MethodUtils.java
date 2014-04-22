package engine;

import java.io.File;
import java.lang.reflect.Method;

import Milestone1_Runner.Student;

public interface MethodUtils {
	
	//Pre: program is a file of a compiled java class.
	//Returns a list of methods signatures within the class.
	MethodData[] getMethods(File program, Student s);
}
