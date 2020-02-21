package paint;

import javafx.scene.input.MouseEvent;
import paint.PaintPanel;

public interface ShapeManipulatorStrategy {
	/**
	 * Handle mouse input from the user
	 * @param paintPanel	The PaintPanel calling this
	 * @param event			The MouseEvent this is called using
	 */
	public void handle(PaintPanel paintPanel, MouseEvent event);

}
