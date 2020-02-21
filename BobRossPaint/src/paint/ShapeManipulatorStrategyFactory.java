package paint;


import javafx.scene.image.Image;

/**
 * Factory to create ShapeManipulatorStrategies
 * @author Hamid
 */
public class ShapeManipulatorStrategyFactory {
	
	
	/**
	 * Create a new ShapeManipulatorStrategy to create Shapes of the given type
	 * @param shapeType	A String describing the ShapeManipulatorStrategy to make
	 * @return	A ShapeManipulatorStrategy object from the given shapeType
	 */
	public ShapeManipulatorStrategy getShapeManipulatorStrategy(String shapeType) {
		if (shapeType.equalsIgnoreCase("Circle")) {
			return new CircleManipulatorStrategy();
		}
		if (shapeType.equalsIgnoreCase("Rectangle")) {
			return new RectangleManipulatorStrategy();
		}
		if (shapeType.equalsIgnoreCase("Squiggle")) {
			return new SquiggleManipulatorStrategy();
		}
		if (shapeType.equalsIgnoreCase("Square")) {
			return new SquareManipulatorStrategy();
		}
		if (shapeType.equalsIgnoreCase("Polyline")) {
			return new PolylineManipulatorStrategy();                  
		}
		if (shapeType.equalsIgnoreCase("Cloud"))
		{
			return new CloudManipulatorStrategy();
		}

		if(shapeType.equalsIgnoreCase("PaintBrush"))
		{
			return new PaintBrushManipulatorStrategy();
		}
		if(shapeType.equalsIgnoreCase("ThickBrush"))
		{
			return new ThickBrushManipulatorStrategy();
		}
		if (shapeType.equalsIgnoreCase("Line")) {
			return new LineManipulatorStrategy();                  
		}
		return null;
	}
}

