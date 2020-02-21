package paint;


import javafx.scene.input.MouseEvent;

public class SquiggleManipulatorStrategy implements ShapeManipulatorStrategy {
	
	private Squiggle squiggle;
	
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
	}
	
	/**
	 * When the user presses the mouse, start a new Squiggle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		PaintModel model = paintPanel.getPaintModel();
		this.squiggle = new Squiggle();
		model.addDrawingCommand(this.squiggle);
		Point point = new Point((int) event.getX(), (int) event.getY());
		this.squiggle.addPoint(point);
	}
	
	/**
	 * When the user drags the mouse, add points to the Squiggle
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		PaintModel model = paintPanel.getPaintModel();
		Point point = new Point((int) event.getX(), (int) event.getY());
		this.squiggle.addPoint(point);
		paintPanel.repaint();
	}


}
