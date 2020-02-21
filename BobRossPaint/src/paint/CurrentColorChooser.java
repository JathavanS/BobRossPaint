package paint;


import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * A rectangle showing the current selected color
 * 
 */
public class CurrentColorChooser extends ToggleButton {

	private Rectangle colorRectangle;
	
	/**
	 * Create a new CurrentColorChooser with the specified color, width, height
	 * @param color		The Color of this
	 * @param text		The text to display on this button
	 * @param width		The width of this
	 * @param height	The height of this
	 */
	public CurrentColorChooser(Color color, String text, int width, int height) {
		super(text);
		
		// Build the Button
		this.colorRectangle = new javafx.scene.shape.Rectangle(width - 4, width - 4);
		this.colorRectangle.setStroke(Color.GRAY);
		this.setGraphic(this.colorRectangle);
		this.setContentDisplay(ContentDisplay.TOP);
		this.setBackground(Background.EMPTY);
		setColor(color);
	}
	
	/**
	 * Set the color that this displays
	 * @param color	The color to set this to
	 */
	public void setColor(Color color) {
		this.colorRectangle.setFill(color);
	}
	
}
