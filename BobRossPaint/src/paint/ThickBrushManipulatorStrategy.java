package paint;


import javafx.scene.input.MouseEvent;

public class ThickBrushManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private ThickBrush thickBrush;
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
	private void handleMouseDragged(PaintPanel paintPanel, MouseEvent event) {
		// TODO Auto-generated method stub
		Point point = new Point((int) event.getX(), (int) event.getY());
		this.thickBrush.addStrokes(point);
		paintPanel.repaint();
		
	}
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		// TODO Auto-generated method stub
		PaintModel paintModel = paintPanel.getPaintModel();
		double width = paintPanel.getBrushWidth();
		double height = paintPanel.getBrushHeight();
		double bristleCount = paintPanel.getBrushDensity();
		
		Point centre = new Point((int) event.getX(), (int) event.getY());
		
		this.thickBrush = new ThickBrush(centre,width, height, bristleCount);
		paintModel.addDrawingCommand(this.thickBrush);
	}

}