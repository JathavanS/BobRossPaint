package paint;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * A LineWidthChooserPanel contains slider that modifies lineWidth of drawn objects
 * 
 * 
 */

public class LineWidthChooserPanel extends GridPane implements ChangeListener<Number> {
	
	private View view; 
	private double lineWidthVal = 1;
	
	private LineWidthCommand lineWidthCommand;
	
	/**
	 * Construct a new LineWidthChooserPanel with a slider for lineWidthVal
	 * @param view	The View of this LineWidthChooserPanel
	 */
	public LineWidthChooserPanel(View view) {
		this.view = view;
		
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(10);
		slider.setValue(1);
		slider.setSnapToTicks(true);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setBlockIncrement(1);
		slider.setMajorTickUnit(3);
		slider.setMinorTickCount(2);
		this.add(slider, 0, 1);
		
		// Add ChangeListener to this Slider
		slider.valueProperty().addListener(this);
		
		/**
		 * Listener for a slider that updates LineWidth to a newValue
		 */
		slider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		        	lineWidthVal = (double) newValue;
		        	setLineWidth(lineWidthVal);
		    }
		});
    	
		Label lineWidthText = new Label("Line Thickness: ");
		this.add(lineWidthText, 0, 0);
	}
	
	/**
	 * Sets lineWidthVal to a specified value
	 * @param lineWidthVal value of line width
	 */
	public void setLineWidth(double lineWidthVal) {
		this.lineWidthVal = lineWidthVal;
	}
	
	/**
	 * Gets lineWidthVal of this
	 */
	public double getLineWidth() {
		return this.lineWidthVal;
	}
	
	/**
	 * Update the line width when the user moves the slider
	 */
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		// Get the line width
		this.lineWidthVal = (double) newValue;
		PaintModel model = this.view.getPaintPanel().getPaintModel();
		
		if (this.lineWidthCommand == null || model.getLastCommand() == null || !(model.getLastCommand() instanceof LineWidthCommand)) {
			this.lineWidthCommand = new LineWidthCommand(this.getLineWidth());
			model.addLineWidthCommand(this.lineWidthCommand);
		}
		else {
			this.lineWidthCommand.setLineWidth(this.lineWidthVal);
		}
	}
	
	
	
}


