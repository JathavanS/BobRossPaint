package paint;

import javafx.scene.canvas.GraphicsContext;
import java.io.Serializable;
/**
 * An Interface representing something that can be drawn
 * 
 */
public abstract class DrawingCommand implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6322473072758006110L;

	/**
	 * Draw this onto the specified GraphicsContext
	 * @param g	The GraphicsContext that will be used to draw this
	 */
	public abstract void execute(GraphicsContext g);
	
	public abstract boolean isUndo();

}