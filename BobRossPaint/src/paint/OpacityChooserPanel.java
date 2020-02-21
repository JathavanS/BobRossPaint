package paint;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class OpacityChooserPanel extends GridPane implements ChangeListener<Number> {
	
	private View view; 
	private ColorChooserPanel colorChooser;
	private double opacity = 1.0;
	
	
	//private LineWidthCommand lineWidthCommand;
	
	/**
	 * Construct a new LineWidthChooserPanel with a slider for lineWidthVal
	 * @param view	The View of this LineWidthChooserPanel
	 */
	public OpacityChooserPanel(View view, ColorChooserPanel colorChooser) {
		this.view = view;
		this.colorChooser = colorChooser;
		
		Slider slider = new Slider();
		slider.setMin(1);
		slider.setMax(10);
		slider.setValue(10);
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
		        	opacity = (double) newValue / 10;
		        	changeOpacity(opacity);
		    }
		});
    	
		Label lineWidthText = new Label("Opacity: ");
		this.add(lineWidthText, 0, 0);
	}
	
	/**
	 * Sets lineWidthVal to a specified value
	 * @param lineWidthVal value of line width
	 */
	public void changeOpacity(double opacity) {
		this.opacity = opacity;
	}
	
	/**
	 * Gets lineWidthVal of this
	 */
	public double get_Opacity() {
		return this.opacity;
	}
	
	/**
	 * Update the line width when the user moves the slider
	 */
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		// Get the line width
		//this.lineWidthVal = (double) newValue;
		PaintModel model = this.view.getPaintPanel().getPaintModel();
		model.changeOpacity(this.get_Opacity());
		this.colorChooser.updateColor(this.colorChooser.getColor());

	}
	
	
	
}
