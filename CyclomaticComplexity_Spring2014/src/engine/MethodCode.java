package engine;

import java.io.IOException;

public interface MethodCode {
	public String getAccessType() throws IOException;
	public String getReturnClass();
	public String getName();
	public String[] getParameterClasses();
	public String getBody();
	public String toString();
}
