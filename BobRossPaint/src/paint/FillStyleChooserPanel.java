package paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

/**
 * A selection panel with two buttons allowing the user to select
 * between 'fill' and 'outline' to draw shapes. If the user selects
 * 'fill', draw solid shapes. If the user selects 'outline', draw
 * only the outlines of shapes.
 * 
 */
public class FillStyleChooserPanel extends GridPane implements EventHandler<ActionEvent> {
	
	private View view;
	
	/**
	 * Create a new FillStyleChooserPanel
	 * 
	 * @param view This FillStyleCHooserPanel's View
	 */
	public FillStyleChooserPanel(View view) {
		this.view = view;
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		ToggleButton solidButton = new ToggleButton();
		solidButton.setId("Solid");
		solidButton.setText("Solid");
		solidButton.setMinWidth(64);
		this.add(solidButton, 0, 0);
		solidButton.setOnAction(this);
		solidButton.setToggleGroup(toggleGroup);
		
		ToggleButton outlineButton = new ToggleButton();
		outlineButton.setId("Outline");
		outlineButton.setText("Outline");
		outlineButton.setMinWidth(64);
		this.add(outlineButton, 0, 1);
		outlineButton.setOnAction(this);
		outlineButton.setToggleGroup(toggleGroup);
		
		
		/**
		ToggleButton cursorButton= new ToggleButton();
		cursorButton.setId("BrushCursor");
		cursorButton.setText("BrushCursor");
		cursorButton.setMinWidth(64);
		this.add(cursorButton, 0, 3);
		cursorButton.setOnAction(this);
		cursorButton.setToggleGroup(toggleGroup);
		
		ToggleButton cursor2Button= new ToggleButton();
		cursor2Button.setId("DefaultCursor");
		cursor2Button.setText("DefaultCursor");
		cursor2Button.setMinWidth(64);
		this.add(cursor2Button, 0, 4);
		cursor2Button.setOnAction(this);
		cursor2Button.setToggleGroup(toggleGroup);
		
		**/
		
	
		
	
	}
	
	@Override
	public void handle(ActionEvent event) {
		String command = ((ToggleButton) event.getSource()).getId();
		if(command.equals("Solid")) {
			this.view.getPaintPanel().setFill(true);
		}
		else if(command.equals("Outline")) {
			this.view.getPaintPanel().setFill(false);
		}
		
	
	
		System.out.println(command);
		
		// Prevent the user from un-toggling a selected button
		ToggleButton button = (ToggleButton) event.getSource();
		button.setSelected(true);
	}

}