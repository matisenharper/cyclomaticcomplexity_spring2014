package inputOutput;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;

    public ImagePanel(File path) {
       try {                
          image = ImageIO.read(path);
       } catch (IOException ex) {
            // handle exception...
       }
       this.setSize(image.getWidth(), image.getHeight());
       this.setBackground(Color.decode("#FFFFFF"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

}