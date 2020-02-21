package paint;


import javafx.scene.layout.GridPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *  Contains sliders that modifies Width, Height, and brizzle count of Thick brush brush
 * 	bristleCount can bechanged for the regular paint brush as well
 * 
 */

public class BrushSizeChooserPanel extends GridPane implements ChangeListener<Number> {
	
	private View view; 
	private double widthVal = 1;
	private double heightVal = 1;
	private double bristleCount = 1;
	Slider widthSlider;
	Slider heightSlider;
	Slider brissleSlider;
	
	/**
	 * Construct a new LineWidthChooserPanel with a slider for lineWidthVal
	 * @param view	The View of this LineWidthChooserPanel
	 */
	public BrushSizeChooserPanel(View view) {
		this.view = view;
		
		widthSlider = new Slider();
		widthSlider.setMin(1);
		widthSlider.setMax(20);
		widthSlider.setValue(1);
		widthSlider.setSnapToTicks(true);
		widthSlider.setShowTickLabels(true);
		widthSlider.setShowTickMarks(true);
		widthSlider.setBlockIncrement(1);
		widthSlider.setMajorTickUnit(10);
		widthSlider.setMinorTickCount(9);
		this.add(widthSlider, 1, 0);
		
		heightSlider = new Slider();
		heightSlider.setMin(1);
		heightSlider.setMax(20);
		heightSlider.setValue(1);
		heightSlider.setSnapToTicks(true);
		heightSlider.setShowTickLabels(true);
		heightSlider.setShowTickMarks(true);
		heightSlider.setBlockIncrement(1);
		heightSlider.setMajorTickUnit(10);
		heightSlider.setMinorTickCount(9);
		this.add(heightSlider, 1, 1);
		
		brissleSlider = new Slider();
		brissleSlider.setMin(1);
		brissleSlider.setMax(10);
		brissleSlider.setValue(1);
		brissleSlider.setSnapToTicks(true);
		brissleSlider.setShowTickLabels(true);
		brissleSlider.setShowTickMarks(true);
		brissleSlider.setBlockIncrement(1);
		brissleSlider.setMajorTickUnit(3);
		brissleSlider.setMinorTickCount(2);
		this.add(brissleSlider, 1, 2);
		// Add ChangeListener to this Slider
		widthSlider.valueProperty().addListener(this);
		heightSlider.valueProperty().addListener(this);
		brissleSlider.valueProperty().addListener(this);
		/**
		 * Listener for a slider that updates LineWidth to a newValue
		 */
		widthSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		        	widthVal = (double) newValue;
		        	setWidthVal(widthVal);
		    }
		});
		
		heightSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		        	heightVal = (double) newValue;
		        	setHeightVal(heightVal);
		    }
		});
		
		brissleSlider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    		bristleCount = (double) newValue;
		    		setBristleCount(bristleCount);
		    }
		});
    	
		Label widthText = new Label("Width:");
		this.add(widthText, 0, 0);
		Label heightText = new Label("Height:");
		this.add(heightText, 0, 1);
		Label bristleText = new Label("Density:");
		this.add(bristleText, 0, 2);
		
	}
	

	public void setWidthVal(double widthVal) {
		this.widthVal = widthVal;
	}
	
	
	public double getWidthVal() {
		return this.widthVal;
	}
	
	public void setHeightVal(double heightVal) {
		this.heightVal = heightVal;
	}
	
	public double getHeightVal() {
		return this.heightVal;
	}
	
	public void setBristleCount(double bristleCount) {
		this.bristleCount = bristleCount;
	}
	
	public double getbristleCount() {
		return this.bristleCount;
	}


	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
}