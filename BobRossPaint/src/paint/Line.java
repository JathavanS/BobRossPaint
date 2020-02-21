package paint;


import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class representing a line segment between two points
 */
public class Line extends Shape {
	
	private Point firstPoint; 
	private Point secondPoint;
	
	//private ArrayList<Point> points;
	
	/**
	 * Create a new Line connecting the two specified points
	 * @param firstPoint	One endpoint of this Line
	 * @param secondPoint	The other endpoint of this Line
	 */
	public Line(Point firstPoint, Point secondPoint) {
		super(false);
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;	
	}
	
	/**
	 * Set the first endpoint of this Line to the given point
	 * @param point	The Point to set to
	 */
	public void setFirstPoint(Point point) {
		this.firstPoint = point;
	}
	
	/**
	 * Return the first endpoint of this Line
	 * @return	 The first endpoint of this Line
	 */
	public Point getFirstPoint() {
		return firstPoint;
	}
	
	/**
	 * Set the second endpoint of this Line to the given point
	 * @param point	The Point to set to
	 */
	public void setSecondPoint(Point point) {
		this.secondPoint = point;
	}
	
	/**
	 * Return the second endpoint of this Line
	 * @return	 The second endpoint of this Line
	 */
	public Point getSecondPoint() {
		return secondPoint;
	}
	
	/**
	 * Draw this Line onto the given GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x1 = this.firstPoint.getX();
		int y1 = this.firstPoint.getY();
		int x2 = this.secondPoint.getX();
		int y2 = this.secondPoint.getY();
		g.strokeLine(x1, y1, x2, y2);
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
