package paint;

import javafx.scene.input.MouseEvent;
import paint.PaintModel;
import paint.PaintPanel;
import paint.Point;
import paint.Polyline;
import paint.ShapeManipulatorStrategy;

public class PolylineManipulatorStrategy implements ShapeManipulatorStrategy {
	private Polyline polyline;

	private Point anchorPoint;
	/**
	 * Handle mouse input from the user, and pass this to the PaintPanel specified
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	@Override
	public void handle(PaintPanel paintPanel, MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			handleMouseClicked(paintPanel, event);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			handleMouseMoved(paintPanel, event);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			handleMouseDragged(paintPanel, event);
		
	}
	}
	
	/**
	 * When the user clicks the mouse, start a new Polyline. When user double clicks, end Polyline.
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 * 
	 */

	private void handleMouseClicked(PaintPanel paintPanel, MouseEvent event) {
		if (event.getClickCount() > 1) {
			this.polyline = null;
		}else {
			PaintModel model = paintPanel.getPaintModel();
			this.anchorPoint= new Point((int) event.getX(), (int) event.getY());
			Point secondPoint= this.anchorPoint;
			this.polyline = new Polyline(anchorPoint,secondPoint);
			this.polyline.addPoint(anchorPoint);
			model.addDrawingCommand(this.polyline);
		}
	}

	
	/**
	 * When the user drags the mouse, add points to the Polyline
	 * @param paintPanel 	The PaintPanel calling this
	 * @param event 		The MouseEvent this is called using
	 */
	private void handleMouseMoved(PaintPanel paintPanel, MouseEvent event) {
		PaintModel model = paintPanel.getPaintModel();
		if(this.polyline!=null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.polyline.setSecondPoint(point);
			this.polyline.addPoint(point);
			//model.addDrawingCommand(this.polyline);
		}
		paintPanel.repaint();
	}
	
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		PaintModel model = paintPanel.getPaintModel();
		if(this.polyline!=null) {
			Point point = new Point((int) event.getX(), (int) event.getY());
			this.polyline.setSecondPoint(point);
			this.polyline.addPoint(point);
			//model.addDrawingCommand(this.polyline);
		}
		paintPanel.repaint();
	}

}
