package paint;

import javafx.scene.input.MouseEvent;
import paint.Circle;
import paint.PaintModel;
import paint.PaintPanel;
import paint.Point;
import paint.ShapeManipulatorStrategy;

public class CircleManipulatorStrategy implements ShapeManipulatorStrategy{
	private Circle circle;
	
	/**
	 * Handle mouse input from the user, and pass this to the PaintPanel specified
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 * 
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
	 * When the user presses the mouse, create a new Circle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		PaintModel paintModel = paintPanel.getPaintModel();
		Point centre = new Point((int) event.getX(), (int) event.getY());
		int radius = 0;
		this.circle = new Circle(centre, radius, paintPanel.getFilled());
		paintModel.addDrawingCommand(this.circle);
	}
	
	/**
	 * When the user drags the mouse, update the Circle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		// Update the radius of the circle
		if (this.circle != null) {
			int x = this.circle.getCentre().getX();
			int y = this.circle.getCentre().getY();
			int radius = distance(x, y, (int) event.getX(), (int) event.getY());
			this.circle.setRadius(radius);
		}
		paintPanel.repaint(); // CHANGE THIS!!!!
	}
	
	/**
	 * When the user releases the mouse, set this circle to null
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseReleased(PaintPanel paintPanel, MouseEvent event) {
		if (this.circle != null) {
			this.circle = null;
		}
		paintPanel.repaint();  // CHANGE THIS!!!!
	}
	
	/**
	 * Calculate the Euclidean distance between points (x0, y0) and (x1, y1)
	 * @param x0	The x coordinate of the first point
	 * @param y0	The y coordinate of the first point
	 * @param x1	The x coordinate of the second point
	 * @param y1	The y coordinate of the second point
	 * @return The Euclidean distance between points (x0, y0) and (x1, y1)
	 */
	private int distance(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}
}
