package paint;


import javafx.scene.input.MouseEvent;

public class SquareManipulatorStrategy implements ShapeManipulatorStrategy {
	private Point anchorPoint;
	private Square square;

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
	 * When the user presses the mouse, create a new Square
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		this.anchorPoint = new Point(x, y);
		this.square = new Square(x, y, 0, paintPanel.getFilled());
		paintPanel.getPaintModel().addDrawingCommand(this.square);
		paintPanel.repaint(); // CHANGE THIS!!!!
	}
	
	/**
	 * When the user drags the mouse, update the Square
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		if (this.square != null) {
			//Point point = new Point(Math.max((int) event.getX(), (int) event.getY()),Math.max((int) event.getX(), (int) event.getY()) );
			Point point = new Point((int)event.getX(),(int)event.getY());
			this.square.setCorners(this.anchorPoint, point);
		}
		paintPanel.repaint(); // CHANGE THIS!!!!
	}
	
	/**
	 * When the user releases the mouse, add the Square to the PaintModel
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseReleased(PaintPanel paintPanel, MouseEvent event) {
		if (this.square != null) {
			//Point point = new Point(Math.max((int) event.getX(), (int) event.getY()),Math.max((int) event.getX(), (int) event.getY()) );
			Point point = new Point((int)event.getX(),(int)event.getY());
			this.square.setCorners(this.anchorPoint, point);
			this.square = null;
		}
	}

}
