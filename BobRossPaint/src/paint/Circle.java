package paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class representing a Circle
 */
public class Circle extends Shape {
	
	private Point centre;
	private int radius;

	/**
	 * Create a new Circle with the specified descriptors
	 * @param centre	The centre of this Circle
	 * @param radius	The radius of this Circle
	 * @param color		The color to draw this Circle with
	 * @param lineWidth	The line width to draw this Circle with
	 * @param filled	Whether to draw this Circle filled in
	 */
	public Circle(Point centre, int radius, boolean filled) {
		super(filled);
		this.centre = centre;
		this.radius = radius;
	}
	
	/**
	 * Return the centre point of this Circle
	 * @return	The centre of this Circle
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Set the centre of this Circle to the specified Point
	 * @param centre	The Point to set the centre to
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/**
	 * Return the radius of this Circle
	 * @return	The radius of this Circle
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Set the radius of this Circle to the specified radius
	 * @param centre	The radius to set to
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Draw this Circle onto the specified GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x = this.centre.x;
		int y = this.centre.y;
		if(this.isFilled()) {
			g.fillOval(x-radius, y - radius, 2*radius, 2*radius);
		}
		else {
			g.strokeOval(x-radius, y-radius, 2*radius, 2*radius);
		}
		
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
