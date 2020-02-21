package paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A ColorCommand is a DrawingCommand that tells a GraphicsContext what Color
 * to draw with.
 * 
 */
public class ColorCommand extends DrawingCommand {
	
	/**
	 * 
	 */
	//private Color color;
	private double red;
	private double green;
	private double blue;
	private double opacity;
	
	/**
	 * Create a new ColorCommand with the specified Color
	 * @param color
	 */
	public ColorCommand(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.opacity = color.getOpacity();
	}
	
	public void changeOpacity(double new_opacity)
	{
		this.opacity = new_opacity;
	}
	
	/**
	 * Prepare the specified GraphicsContext to draw this Color
	 * @param g	The GraphicsContext to set
	 */
	@Override
	public void execute(GraphicsContext g) {
		Color new_color = new Color(this.red, this.green, this.blue, this.opacity);
		g.setStroke(new_color);
		g.setFill(new_color);
	}
	
	/**
	 * Set this ColorCommand's Color to the specified Color
	 * @param color	The Color to set to
	 */
	public void setColor(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.opacity = color.getOpacity();
	}
	
	/**
	 * Return the Color of this ColorCommand
	 * @return	The Color of this ColorCommand
	 */
	public Color getColor() {
		Color new_color = new Color(this.red, this.green, this.blue, this.opacity);
		return new_color;
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return false;
	}

}
