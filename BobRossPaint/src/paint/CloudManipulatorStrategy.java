package paint;

import javafx.scene.input.MouseEvent;
import paint.Cloud;
import paint.PaintModel;
import paint.PaintPanel;
import paint.Point;
import paint.ShapeManipulatorStrategy;
/**
 * Strategy for the Cloud
 * 
 *
 */
public class CloudManipulatorStrategy implements ShapeManipulatorStrategy {
	private Cloud cloud;
	@Override
	public void handle(PaintPanel paintPanel, MouseEvent event) {
		// TODO Auto-generated method stub
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			handleMousePressed(paintPanel, event);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			handleMouseDragged(paintPanel, event);
		}
		
	}
	/**
	 * Called when the user presses the mouse button
	 * @param paintPanel 
	 * @param event: Mouse event
	 */
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event)
	{
		PaintModel paintModel = paintPanel.getPaintModel();
		double width;
		//if there is no LineWidthCommand in the paintModel the width is set to 1
		// else it is set to that lineWidth
		if(paintModel.getLastLineWidthCommand() == null)
		{
			width = 1.0;
		}
		else
		{
			width = paintModel.getLastLineWidthCommand().getLineWidth();
		}
		Point centre = new Point((int) event.getX(), (int) event.getY());
		this.cloud = new Cloud(centre,width);
		//adds the cloud to the paintModel's list
		paintModel.addDrawingCommand(this.cloud);
	}
	/**
	 * called when the user drags the mouse when it is pressed
	 * @param paintPanel
	 * @param event: MouseEvent
	 */
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event)
	{
		Point point = new Point((int) event.getX(), (int) event.getY());
		//adds the points to the cloud based on the mouse position
		this.cloud.addPoints(point);
		paintPanel.repaint();// repaints the canbas to show the updated cloud
	}

}
