package paint;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * Color panel that has all the colors Bob Ross uses in his videos.Does not appear on executon 
 * only when bob ross button is clicked
 *
 *
 */
public class BobRossColorPanel extends GridPane implements EventHandler<ActionEvent>{
	
	private ColorChooserPanel colorChooserPanel;
	
	int rows = 3;
	// Colors used in Bob's show
	private String[] pallette = {"#4E1500","#E32636", "#5F2E1F","#221B15","#DB0000","#0A3410","#102E3C","#0C0040","#021E44","#FFEC00","#C79B00", "#FFB800","#000000","#FFFFFF"};
	int buttonSize;
	/**
	 * Constructs panel
	 * @param colorChooserPanel: reference to original colorPanel
	 * @param buttonSize: size of the buttons
	 */
	public BobRossColorPanel(ColorChooserPanel colorChooserPanel, int buttonSize)
	{
		this.buttonSize = buttonSize;
		this.colorChooserPanel = colorChooserPanel;
		this.setHgap(2);
		this.setVgap(2);
	}
	/**
	 * Adds the buttons on the panel only when called. 
	 */
	public void Populate()
	{
		for(int i = 0; i < this.pallette.length;i++)
		{
			ColorChooserButton button = new ColorChooserButton(Color.web(this.pallette[i]), this.buttonSize);
			button.setOnAction(this);
			this.add(button, i / rows,  i % rows);
		}
	}
	/**
	 * Gets rid of all the buttons for the bob ross colorPanel
	 */
	public void clearPanel()
	{
		this.getChildren().clear();
	}
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() instanceof ColorChooserButton) {
			ColorChooserButton button = (ColorChooserButton) event.getSource();
			Color color = button.getColor();
			this.colorChooserPanel.updateColor(color);
		}
	}

}
