package engine;

import java.lang.reflect.Method;

import Milestone1_Runner.Student;

public interface MethodCode {
	Student getAuthor();
	Method getMethod();
	String getBody();
}
