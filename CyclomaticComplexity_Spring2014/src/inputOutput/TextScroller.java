package inputOutput;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class TextScroller extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TextScroller(Component c){
		super(c);
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
