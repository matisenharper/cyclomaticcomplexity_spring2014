/**
 * 
 */
package test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import Spring2014Users.Student;
import inputOutput.GraphView;

@Retention(value=RUNTIME)
@Target(value=METHOD)
public @interface CyclomaticComplexity {
	
	File file = new File("./src/tictactoe/TicTacToeBoardImpl_Rocha.java");
	Student author = Student.ROCHA;
	GraphView gp = GraphView.setData(file, author);
	ModalDialog dlg = new ModalDialog(gp, "Student", "message");
	//frame.getContentPane().add(gp, BorderLayout.CENTER); 
	//frame.setLocation(0,0);
	//frame.setSize(800, 600); 
	//frame.setVisible(true);
}
