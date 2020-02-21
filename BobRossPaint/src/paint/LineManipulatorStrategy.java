package paint;


import javafx.scene.input.MouseEvent;

public class LineManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private Line line;
	private Point anchorPoint;
		
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
	 * When the user presses the mouse, start a new Line
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		PaintModel model = paintPanel.getPaintModel();
		int x = (int) event.getX();
		int y = (int) event.getY();
		this.anchorPoint = new Point(x, y);
		Point secondPoint = new Point(x, y);
		this.line = new Line(anchorPoint, secondPoint);
		model.addDrawingCommand(this.line);
	}
	
	/**
	 * When the user drags the mouse, add points to the Line
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		if (this.line!=null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.line.setSecondPoint(point);
		}
		paintPanel.repaint();
	}

	private void handleMouseReleased(PaintPanel paintPanel, MouseEvent event) {
		if (this.line!=null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.line.setSecondPoint(point);
			this.line = null;
		}
		paintPanel.repaint();
	}

}
