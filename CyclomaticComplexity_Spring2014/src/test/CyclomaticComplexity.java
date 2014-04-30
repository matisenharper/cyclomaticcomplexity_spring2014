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
	
	File file =TicTacToe_SingleStudent_Test.CCInputData.getFile();
	Student student = TicTacToe_SingleStudent_Test.CCInputData.getStudent();
	//File file = new File("./src/tictactoe/TicTacToeBoardImpl_Rocha.java");
	GraphView gp = GraphView.setData(file, student);
	ModalDialog dlg = new ModalDialog(gp, student);
	//frame.getContentPane().add(gp, BorderLayout.CENTER); added to jdialog
	//frame.setLocation(0,0); added to jdialog
	//frame.setSize(800, 600); added to jdialog
	//frame.setVisible(true); added to jdialog
}
