package paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class representing a Squiggle that can be drawn
 */
public class Squiggle extends Shape {
	
	private ArrayList<Point> points;
	
	/**
	 * Create a new Squiggle with the specified color and line width
	 * @param color		The Color to draw this Squiggle with
	 * @param lineWidth	The width of the line to draw this with
	 */
	public Squiggle() {
		super(false);
		this.points = new ArrayList<Point>();
	}
	
	/**
	 * Add a new point to this Squiggle
	 * @param point	The Point to add
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	/**
	 * Get the ArrayList of Points representing this Squiggle
	 * @return This Squiggle's ArrayList of Points
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	
	/**
	 * Draw this Squiggle onto the specified GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	@Override
	public void execute(GraphicsContext g) {
		for (int j = 0; j < this.points.size() - 1; j++) {
			Point p1 = this.points.get(j);
			Point p2 = this.points.get(j + 1);
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}

}