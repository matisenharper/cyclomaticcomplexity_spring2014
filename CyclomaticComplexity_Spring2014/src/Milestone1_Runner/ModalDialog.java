//from http://www.java2s.com/Tutorial/Java/0240__Swing/ASimpleModalDialog.htm
//with modifications for jPanel
package Milestone1_Runner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class ModalDialog extends JDialog implements ActionListener {
  public ModalDialog(JPanel parent, String title, String message) {
    super();
    if (parent != null) {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
    }
    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(message));
    getContentPane().add(messagePane);
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("OK"); 
    buttonPane.add(button); 
    button.addActionListener(this);
    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack(); 
    setModal(true);
    setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {
    setVisible(false); 
    dispose(); 
  }
  public static void main(String[] a) {
    ModalDialog dlg = new ModalDialog(new JPanel(), "title", "message");
  }
}