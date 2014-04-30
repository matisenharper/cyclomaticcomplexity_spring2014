/*
 * 
 */
package engine;

import java.io.IOException;

public interface MethodCode {
	
	//Returns the Access permissions of the method represented by the instance (ie: public static)
	//pre: instance was implemented with a working code
	//post: String returned represents the access permissions of the method
	//throws IOException in access type is not implemented
	public String getAccessType() throws IOException;
	
	public String getReturnClass();
	public String getName();
	public String[] getParameterClasses();
	public String getBody();
	public String toString();
}
