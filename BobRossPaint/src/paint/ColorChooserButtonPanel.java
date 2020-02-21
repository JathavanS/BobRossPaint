package paint;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A ColorChooserButtonPanel is a panel of Buttons. Each Button has a Color.
 * Clicking on one of those Buttons chooses its color.
 * 
 * 
 */
public class ColorChooserButtonPanel extends GridPane implements EventHandler<ActionEvent> {
	
	private ColorChooserPanel colorChooserPanel;
	
	int rows = 3;
	private ArrayList<Color> colorPalette;
	
	/**
	 * Create a new ColorChooserPanel
	 * @param view 			This ColorChooserPanel's View
	 * @param buttonSize	The size of the buttons
	 */
	public ColorChooserButtonPanel(ColorChooserPanel colorChooserPanel, int buttonSize) {
		this.colorChooserPanel = colorChooserPanel;
		this.setHgap(2);
		this.setVgap(2);
		
		// Build the color palette
		this.colorPalette = this.constructDefaultColorPalette();
		
		// Create the buttons
		for (int i = 0; i < colorPalette.size(); i++) {
			// Create button
			Color color = colorPalette.get(i);
			ColorChooserButton button = new ColorChooserButton(color, buttonSize);
			button.setOnAction(this);
			this.add(button, i / rows,  i % rows);
		}
	}
	
	/**
	 * Handle a button press
	 */
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() instanceof ColorChooserButton) {
			ColorChooserButton button = (ColorChooserButton) event.getSource();
			Color color = button.getColor();
			this.colorChooserPanel.updateColor(color);
		}
	}
	
	/**
	 * Construct the default color palette for this ColorChooserPanel;
	 * @return
	 */
	private ArrayList<Color> constructDefaultColorPalette() {
		ArrayList<Color> colorPalette = new ArrayList<Color>();
		
		// Grays
		int numberOfGrays = 6;
		for (int k = 0; k < numberOfGrays; k++) {
			Color newGray = Color.gray((double) k / (numberOfGrays - 1));
			colorPalette.add(newGray);
		}
		//colorPalette.add(Color.TRANSPARENT);
		
		// Hues
		int numberOfHues = 12;
		for (int hue = 0; hue < 360; hue += 360 / numberOfHues) {
			Color color = Color.hsb(hue, 1, 1);
			colorPalette.add(color.darker());
			colorPalette.add(color);
			colorPalette.add(color.desaturate().desaturate());
		}
		
		return colorPalette;
	}
	
}