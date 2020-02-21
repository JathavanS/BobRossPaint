package paint;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A Button that allows the user to select a color. This Button
 * displays on itself that color.
 * 
 * 
 */
public class ColorChooserButton extends Button implements EventHandler<MouseEvent> {
	
	// This ColorChooserButton's color
	// Clicking this Button should 'choose' this color
	private Color color;
	
	// Rectangle border settings
	private static final double STROKE_WIDTH = 2;
	private static final Color DEFAULT_STROKE_COLOR = Color.WHITE;
	private static final Color MOUSE_HOVER_STROKE_COLOR = Color.LIGHTBLUE.desaturate();
	
	// Square graphic to display color
	Rectangle squareGraphic;
	
	/**
	 * Create a new ColorChooserButton with the specified Color. Clicking on
	 * this ColorChooserButton should "choose" this color.
	 * @param color	The Color for this ColorChooserButton
	 * @param size	The size of the graphic inside this ColorChooserButton
	 */
	public ColorChooserButton(Color color, int size) {
		super();
		this.color = color;
		
		// Set the size
		this.setMinSize(size, size);
		this.setMaxSize(size, size);
		
		// Put a square in this Button to show the color
		this.squareGraphic = new Rectangle(0, 0, size - 4, size - 4); // javafx.scene.shape.Rectangle
		this.squareGraphic.setFill(color);
		this.squareGraphic.setStroke(DEFAULT_STROKE_COLOR);
		this.squareGraphic.setStrokeWidth(STROKE_WIDTH);
		this.setGraphic(squareGraphic);
		
		// Set mouse events to change color
		this.setOnMouseEntered(this);
		this.setOnMouseExited(this);
		
		// Remove default background
		this.setBackground(Background.EMPTY);
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT)));
	}
	
	/**
	 * Set the Color of this ColorChooserButton to the specified color
	 * @param color The Color to set to
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Return the Color of this ColorChooserButton
	 * @return The Color of this ColorChooserButton
	 */
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			this.squareGraphic.setStroke(MOUSE_HOVER_STROKE_COLOR);
		}
		else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			this.squareGraphic.setStroke(DEFAULT_STROKE_COLOR);
		}
	}
	
	
}
