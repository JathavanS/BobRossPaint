package paint;


import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * A class representing a 2D Shape which can be drawn
 * 
 */
public abstract class Shape extends DrawingCommand {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 4708922872882305964L;
	/**
	 * 
	 */
	private boolean filled;
	
	/**
	 * Create a new Shape with the specified color, fill style, and line width
	 * @param color		The color to draw this shape with
	 * @param lineWidth	The width of the line to draw this shape with
	 * @param filled	True if this shape is to be drawn in filled (if applicable).
	 * False if this shape is to be drawn with outline
	 */
	public Shape(boolean filled) {	
		this.filled = filled;
	}

	
	/**
	 * Returns True if this shape is to be drawn in filled (if applicable).
	 * False if this shape is to be drawn with outline.
	 * @return True if this shape is to be drawn in filled (if applicable).
	 * False if this shape is to be drawn with outline.
	 */
	public boolean isFilled() {
		return this.filled;
	}


	
	
}
