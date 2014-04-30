//from http://www.java2s.com/Tutorial/Java/0240__Swing/ASimpleModalDialog.htm
//with modifications for jPanel
package test;

import inputOutput.GraphView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Spring2014Users.Student;
public class ModalDialog extends JDialog implements ActionListener {
  public ModalDialog(JPanel parent, Student student) {
    super();
    this.setTitle(student.toString());
    JPanel messagePane = new JPanel();
    messagePane.setLayout(new BorderLayout());
    if (parent != null) {
      //Dimension parentSize = parent.getSize(); 
      //Point p = parent.getLocation(); 
      messagePane.setSize(parent.getWidth(), parent.getHeight());
      setLocation(0,0);
      this.setSize(parent.getWidth(), parent.getHeight());
      this.setLayout(new BorderLayout());
      messagePane.add(parent);
      this.setResizable(false);
      
      
    }
    getContentPane().add(messagePane, BorderLayout.CENTER);
    //JPanel buttonPane = new JPanel();
    //JButton button = new JButton("OK"); 
   // buttonPane.add(button); 
    //button.addActionListener(this);
    //getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    revalidate(); 
    setModal(true);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {
    setVisible(false); 
    dispose(); 
  }
  
  
  //public static void main(String[] a) {
  //  ModalDialog dlg = new ModalDialog(new GraphView(), "title", "message");
    
    
    
    /*ImageIcon img = new ImageIcon(OuterClass.class.getResource("Logo_Horizontal_blueGold"));
    dlg .setIconImage(img.getImage());*/
  //}
}