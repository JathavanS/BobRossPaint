package paint;


import javafx.scene.canvas.GraphicsContext;

/**
 * A LineWidthCommand is a DrawingCommand that tells a GraphicsContext what line width
 * to draw with.
 * 
 */
public class LineWidthCommand extends DrawingCommand {
	
	/**
	 * 
	 */
	
	private double lineWidth;
	
	/**
	 * Create a new LineWidthCommand with the specified line width
	 * @param color
	 */
	public LineWidthCommand(double lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	/**
	 * Prepare the specified GraphicsContext to stroke with this line width
	 * @param g	The GraphicsContext to set
	 */
	@Override
	public void execute(GraphicsContext g) {
		g.setLineWidth(this.lineWidth);
	}
	
	/**
	 * Set this LineWidthCommand's line width to the specified line width
	 * @param lineWidth	The width to set to
	 */
	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	/**
	 * Return the lien width  of this LineWidthCommand
	 * @return	The line width of this LineWidthCommand
	 */
	public double getLineWidth() {
		return this.lineWidth;
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return false;
	}

}
