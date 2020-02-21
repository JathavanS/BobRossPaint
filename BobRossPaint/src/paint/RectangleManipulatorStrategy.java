package paint;

import javafx.scene.input.MouseEvent;
import paint.PaintPanel;
import paint.Point;
import paint.Rectangle;
import paint.ShapeManipulatorStrategy;

public class RectangleManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private Point anchorPoint;
	private Rectangle rectangle;
	
	/**
	 * Handle mouse input from the user, and pass this to the PaintPanel specified
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	@Override
	public void handle(PaintPanel paintPanel, MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			handleMousePressed(paintPanel, event);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			handleMouseDragged(paintPanel, event);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			handleMouseReleased(paintPanel, event);
		}
	}
	
	/**
	 * When the user presses the mouse, create a new Rectangle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		this.anchorPoint = new Point(x, y);
		this.rectangle = new Rectangle(x, y, 0, 0, paintPanel.getFilled());
		paintPanel.getPaintModel().addDrawingCommand(this.rectangle);
		paintPanel.repaint(); // CHANGE THIS!!!!
	}
	
	/**
	 * When the user drags the mouse, update the Rectangle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		if (this.rectangle != null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.rectangle.setCorners(this.anchorPoint, point);
		}
		paintPanel.repaint(); // CHANGE THIS!!!!
	}
	
	/**
	 * When the user releases the mouse, add the Rectangle to the PaintModel
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseReleased(PaintPanel paintPanel, MouseEvent event) {
		if (this.rectangle != null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.rectangle.setCorners(this.anchorPoint, point);
			this.rectangle = null;
		}
	}

}
