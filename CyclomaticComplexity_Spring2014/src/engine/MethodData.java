package engine;

import MISC.Student;

public interface MethodData {
	Student getAuthor();
	MethodCode getMethod();
	String getName();
	String getMethodSignature();
}
