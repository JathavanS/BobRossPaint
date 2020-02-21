package paint;


import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A ColorChooserPanel contains a ColorChooserButtonPanel. When the user
 * clicks on a Button on the ColorChooserButtonPanel, this updates and
 * displays that Color. That Color becomes selected.
 * 
 * 
 */
public class ColorChooserPanel extends HBox {
	
	private Color color = Color.BLACK; // The selected color
	private View view;
	private double opacity;
	
	private ColorChooserButtonPanel colorChooserButtonPanel;
	private CurrentColorChooser currentColorChooser;
	
	private ColorCommand colorCommand;
	
	/**
	 * Create a new ColorChooserPanel
	 * @param view	The View of this ColorChooserPanel where this is displayed
	 */
	public ColorChooserPanel(View view) {
		super();
		this.view = view;
		this.opacity = view.getOpacity();
		// Create the color rectangle
		this.currentColorChooser = new CurrentColorChooser(color, "Color", 32, 72);
		this.getChildren().add(this.currentColorChooser);
		
		// Create the ColorChooserButtonPanel
		this.colorChooserButtonPanel = new ColorChooserButtonPanel(this, 22);
		this.getChildren().add(colorChooserButtonPanel);
	}
	
	/**
	 * Set the color of this ColorChooserPanel. Updates the color, displaying it
	 * appropriately
	 * @param color	The Color to set to
	 */
	public void updateColor(Color new_color) {
		
		this.color = new_color;
		this.opacity = view.getOpacity();
		Color set_color = new Color(new_color.getRed(), new_color.getGreen(), new_color.getBlue(), this.opacity);
		
		this.currentColorChooser.setColor(set_color);
		
		// Command stuff
		PaintModel model = this.view.getPaintPanel().getPaintModel();
		if (this.colorCommand == null || model.getLastCommand() == null || !(model.getLastCommand() instanceof ColorCommand)) {
			this.colorCommand = new ColorCommand(set_color);
			model.addDrawingCommand(this.colorCommand);
		}
		else {
			this.colorCommand.setColor(set_color);
		}
	}
	
	/**
	 * Return the selected color
	 * @return	The current color
	 */
	public Color getColor() {
		return this.color;
	}
	
}
