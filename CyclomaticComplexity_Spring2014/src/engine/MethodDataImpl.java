package engine;

import java.io.File;
import java.lang.reflect.Method;

import Milestone1_Runner.Student;

public class MethodDataImpl implements MethodData {

	Student author;
	MethodCode method;
	
	public MethodDataImpl(Student author,MethodCode method) {
		this.author = author;
		this.method = method;
	}
	
	@Override
	public Student getAuthor() {
		return author;
	}

	@Override
	public MethodCode getMethod() {
		return method;
	}

	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public String getMethodSignature() {
		return method.toString();
	}
}
