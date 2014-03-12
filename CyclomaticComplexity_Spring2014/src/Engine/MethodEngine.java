package Engine;

import java.io.File;

public class MethodEngine implements MethodEngineInterface{
	
	MethodManager methods;
	
	public MethodEngine(){
		methods = new MethodManager();
	}

	@Override
	public MethodManager getMethodManager(File program) {
		return methods;
	}

}
