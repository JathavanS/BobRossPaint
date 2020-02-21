package paint;


import javafx.scene.input.MouseEvent;

public class PaintBrushManipulatorStrategy implements ShapeManipulatorStrategy{
	private PaintBrush paintBrush;
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
		this.paintBrush.addStrokes(point);
		paintPanel.repaint();
		
	}
	private void handleMousePressed(PaintPanel paintPanel, MouseEvent event) {
		// TODO Auto-generated method stub
		PaintModel paintModel = paintPanel.getPaintModel();
		double width;
		if(paintModel.getLastLineWidthCommand() == null)
		{
			width = 1.0;
		}
		else
		{
			width = paintModel.getLastLineWidthCommand().getLineWidth();
		}
		Point centre = new Point((int) event.getX(), (int) event.getY());
		double bristleCount = paintPanel.getBrushDensity();
		this.paintBrush = new PaintBrush(centre,width, bristleCount);
		paintModel.addDrawingCommand(this.paintBrush);
	}
	
}
