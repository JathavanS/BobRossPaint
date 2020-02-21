package paint;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	//private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	
	
	
	private boolean mouseDown;
	
	private boolean filled;

	private ShapeManipulatorStrategy shapeManipulatorStrategy = new CircleManipulatorStrategy();
	
	//private double lineWidth = 1; // setting default lineWidth
	
	private Canvas canvas;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(500, 580);
		this.getChildren().add(this.canvas);
		
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: lightgrey");

		this.addEventHandler(MouseEvent.ANY, this);

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
		this.filled = false;
		
		
		
		//canvas color is created white by making a white rectangle
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		g.setFill(Color.WHITE);
		g.fillRect(0.0, 0.0, this.canvas.getWidth(), this.canvas.getHeight());
		
		//Relocates the canvas when the screen size changes. 
		this.canvas.layoutXProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
				canvas.relocate(0.0,0.0);
			}
		});
		this.canvas.layoutYProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
				canvas.relocate(0.0,0.0);
			}
			
		});
		
		// KeyEvent handler to draw text
		//this.canvas.setFocusTraversable(true);
		//this.keyEventHandler = new TextKeyEventHandler(null);
		//this.setOnKeyTyped(this.keyEventHandler);
		
		this.mouseDown = false;
		
	}
	
	public void repaint() {
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		g.setLineWidth(1);

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		//makes canvas white
		g.setFill(Color.WHITE);
		g.fillRect(0.0, 0.0, this.canvas.getWidth(), this.canvas.getHeight());
		
		//g.setFill(this.getColor());
		
		g.setStroke(Color.BLACK);
		
		this.model.draw(g);
	
	}
	
	public void setFill(boolean filled)
	{
		this.filled = filled;
	}


	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}
	

	/**
	 * Controller aspect of this
	 */
	public void setMode(ShapeManipulatorStrategy strategy) {
		this.shapeManipulatorStrategy = strategy;
		
	}
	
	public boolean getMouseDown()
	{
		return this.mouseDown;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
		{
			this.mouseDown = true;
		}
		else if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
		{
			this.mouseDown = false;
		}
		this.shapeManipulatorStrategy.handle(this, event);
	}
	
	
	

	
	/**
	 * Return this PaintModel
	 * @return This PaintPanel's PaintModel
	 */
	public PaintModel getPaintModel() {
		return this.model;
	}
	
	public void originalHeight()
	{
		this.setHeight(500.0);
		
	}
	
	public Canvas getCanvas()
	{
		return this.canvas;
	}
	
	/**
	 * Return True iff the fill status is set to filled. Return False iff
	 * it is set to outline.
	 * @return Whether this fill status is set to filled
	 */
	public boolean getFilled() {
		return this.filled;
	}

	public double getBrushWidth()
	{
		return this.view.getBrushSizeChooserPanel().getWidthVal();
	}
	
	public double getBrushHeight()
	{
		return this.view.getBrushSizeChooserPanel().getHeightVal();
	}
	
	public double getBrushDensity()
	{
		return this.view.getBrushSizeChooserPanel().getbristleCount();
	}
}
