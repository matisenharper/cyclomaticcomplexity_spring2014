package engine;

import java.io.File;
import java.lang.reflect.Method;

import Milestone1_Runner.Student;

public class MethodCodeImpl implements MethodCode {

	Student author;
	Method method;
	String body;
	
	public MethodCodeImpl(Student author, File program, Method method) {
		this.author = author;
		this.method = method;
		
		MethodUtils mu = new MethodUtilsImpl();
		
		body = mu.getBody(program, method);
	}
	
	@Override
	public Student getAuthor() {
		return author;
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public String getBody() {
		return body;
	}

}
