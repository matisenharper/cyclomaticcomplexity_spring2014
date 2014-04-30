package inputOutput;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JScrollPane;

public class ImageScroller extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageScroller(Component c){
		super(c);
		this.setSize(c.getWidth(), c.getHeight());
		this.setBackground(Color.decode("#FFFFFF"));
	}
	
	@Override
    public Dimension getPreferredSize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		width = (width*6)/10;
		return new Dimension(width, 0);
    }
}
