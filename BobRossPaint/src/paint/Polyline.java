package paint;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A PolyLine class
 * 
 * 
 */
public class Polyline extends Shape {
	private Point firstPoint; 
	private Point secondPoint;
	
	private ArrayList<Point> points;
	/**
	 * Create a new Polyline with the specified color and line width
	 * @param color		The Color to draw this Polyline with
	 * @param firstPoint	The first point of the Polyline
	 *  @param secondPoint	The second point of the Polyline
	 *  @param points 		A list of points to keep track of Polyline
	 */
	
	public Polyline(Point firstPoint, Point secondPoint) {
		super(false);
		this.firstPoint=firstPoint;
		this.secondPoint=secondPoint;
		this.points = new ArrayList<Point>();
		
	}
	/**
	 * Setter method that sets the first point
	 * @param firstPoint
	 * 	set the first Point of Polyline to firstPoint
	 */
	public void setFirstPoint(Point firstPoint) {
		this.firstPoint=firstPoint;
	}
	/**
	 * Getter method that gets the first point
	 * @return secondPoint	The first Point of Polyline
	 */
	public Point getFirstPoint() {
		return firstPoint;
	}
	/**
	 * Setter method that sets the second point
	 * @param secondPoint
	 * 	set the second Point of Polyline to secondPoint
	 */
	public void setSecondPoint(Point secondPoint) {
		this.secondPoint=secondPoint;
	}
	/**
	 * Getter method that gets the second point
	 * @return secondPoint	The second Point of Polyline
	 */
	public Point getSecondPoint() {
		return secondPoint;
	}
	/**
	 * Add a new point to this Polyline
	 * @param point	The Point to add
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}


	
	@Override
	public void execute(GraphicsContext g) {

		for (int j = 0; j < this.points.size()-1; j++) {
			firstPoint = this.points.get(0);
			secondPoint =this.points.get(this.points.size()-1);
			g.strokeLine(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
				
		}

	}
	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}
}
