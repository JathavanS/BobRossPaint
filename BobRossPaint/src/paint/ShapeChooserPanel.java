package paint;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	
	private ShapeManipulatorStrategyFactory shapeManipulatorStrategyFactory;
	
	public ShapeChooserPanel(View view) {

		this.view = view;
		this.shapeManipulatorStrategyFactory = new ShapeManipulatorStrategyFactory();
		
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Line",
				"Polyline", "Cloud", "PaintBrush","ThickBrush"};

		
		// Images for buttons
		Image circleIcon = new Image("file:src/images/circle_icon.png");
		Image rectangleIcon = new Image("file:src/images/rectangle_icon.png");
		Image squareIcon = new Image("file:src/images/square_icon.png");
		Image squiggleIcon = new Image("file:src/images/squiggle_icon.png");
		Image lineIcon = new Image("file:src/images/line_icon.png");
		Image polylineIcon = new Image("file:src/images/polyline_icon.png");
		Image cloudIcon = new Image("file:src/images/cloud_brush_icon.png");
		Image thinBrushIcon = new Image("file:src/images/thin_brush_icon.png");
		Image thickBrushIcon = new Image("file:src/images/thick_brush_icon.png");

		Image[] buttonImages = { circleIcon, rectangleIcon, squareIcon, squiggleIcon, lineIcon, polylineIcon, cloudIcon,
				 thinBrushIcon,thickBrushIcon };

		ToggleGroup toggleGroup = new ToggleGroup();
		
		int row = 0;
		for (int i = 0; i < buttonLabels.length; i++) {
			ToggleButton button = new ToggleButton();
			button.setToggleGroup(toggleGroup);
			
			// Make the radio button look like a regular button
			button.setId(buttonLabels[i]);
			
			ImageView buttonView = new ImageView(buttonImages[i]);
			buttonView.setScaleX(0.75);
			buttonView.setScaleY(0.75);
			button.setGraphic(buttonView);
			
			button.setMinWidth(64);
			button.setMaxHeight(64);
			
			this.add(button, 0, row);
			row++;
			
			button.setOnAction(this);
			
			// Toggle the first button
			if (i == 0) {
				button.setSelected(true);
			}
		}


	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((ToggleButton) event.getSource()).getId();
		PaintPanel paintPanel = this.view.getPaintPanel();
		
		paintPanel.setMode(this.shapeManipulatorStrategyFactory.getShapeManipulatorStrategy(command));
		System.out.println(command);
		
		// Prevent a selected button from un-toggling
		ToggleButton button = (ToggleButton) event.getSource();
		button.setSelected(true);
	}
}
		