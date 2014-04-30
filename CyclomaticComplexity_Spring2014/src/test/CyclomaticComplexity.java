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
	ModalDialog dlg = new ModalDialog(gp);
	//frame.getContentPane().add(gp, BorderLayout.CENTER); added to jdialog
	//frame.setLocation(0,0); added to jdialog
	//frame.setSize(800, 600); added to jdialog
	//frame.setVisible(true); added to jdialog
}
