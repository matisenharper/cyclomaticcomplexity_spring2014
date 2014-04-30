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
public class ModalDialog extends JDialog implements ActionListener {
  public ModalDialog(JPanel parent) {
    super();
    if (parent != null) {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
      this.setSize(parent.getWidth(), parent.getHeight());
    }
    JPanel messagePane = new JPanel();
    messagePane.add(parent);
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
  //public static void main(String[] a) {
  //  ModalDialog dlg = new ModalDialog(new GraphView(), "title", "message");
    
    
    
    /*ImageIcon img = new ImageIcon(OuterClass.class.getResource("Logo_Horizontal_blueGold"));
    dlg .setIconImage(img.getImage());*/
  //}
}