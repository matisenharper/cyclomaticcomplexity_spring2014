package test;

import java.io.File;

import Spring2014Users.Student;

public class CCInputDataImpl implements
		CCInputData {
	File file;
	Student student;
	
	public CCInputDataImpl(File file,Student student) {
		this.file = file;
		this.student = student;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}

	@Override
	public Student getStudent() {
		// TODO Auto-generated method stub
		return student;
	}

}
