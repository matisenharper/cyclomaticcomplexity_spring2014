/**
 * 
 */
package test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import inputOutput.GraphView;

@Retention(value=RUNTIME)
@Target(value=METHOD)
public @interface CyclomaticComplexity {
	ModalDialog dlg = new ModalDialog(new GraphView(), "Student", "message");
}
