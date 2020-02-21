package paint;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * EventHandler to clear a canvas, from a PaintModel
 * 
 */
public class ClearCanvasEventHandler implements EventHandler<ActionEvent> {
	
	private PaintPanel paintPanel;
	
	/**
	 * Create a new ClearCanvasEventHandler to clear the Canvas of the given PaintPanel
	 * @param paintPanel	The PaintPanel to clear the Canvas of
	 */
	public ClearCanvasEventHandler(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
	}
	
	/**
	 * When this event activates, clear the canvas
	 * of the given paintPanel
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
		paintPanel.getPaintModel().clear();
	}

}
