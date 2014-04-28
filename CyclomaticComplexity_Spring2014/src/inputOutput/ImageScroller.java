package inputOutput;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class ImageScroller extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageScroller(Component c){
		super(c);
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(580, 0);
    }
}
