package paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A rectangle class
 * 
 * 
 */
public class Rectangle extends Shape {
	
	private Point position; // The position of the top-left corner of the rectangle
	private int width;
	private int height;
	
	/**
	 * Create a new Rectangle with the specified position, width, and height. The position
	 * is the upper-left corner of the Rectangle. If width < 0 or height < 0, the width or
	 * height of this Rectangle will be 0
	 * 
	 * @param position	The position of the upper-left corner of this Rectangle
	 * @param width		The width (horizontal) of this Rectangle; width >= 0
	 * @param height	The height (vertical) of this Rectangle; height >= 0
	 * @param filled	Whether this is to be drawn filled in
	 */
	public Rectangle(Point position, int width, int height, boolean filled) {
		super(filled);
		this.position = position;
		if (width >= 0) {
			this.width = width;
		}
		else {
			this.width = 0;
		}
		if (height >= 0) {
			this.height = height;
		}
		else {
			this.height = 0;
		}
	}
	
	/**
	 * Create a new Rectangle with the specified position (x, y), width, and height. The position
	 * is the upper-left corner of the Rectangle. If width < 0 or height < 0, the width or
	 * height of this Rectangle will be 0
	 * 
	 * @param x			The x coordinate of the upper left corner of this Rectangle
	 * @param y			The y coordinate of the upper left corner of this Rectangle
	 * @param width		The width (horizontal) of this Rectangle; width >= 0
	 * @param height	The height (vertical) of this Rectangle; height >= 0
	 */
	public Rectangle(int x, int y, int width, int height, boolean filled) {
		this(new Point(x, y), width, height, filled);

	}
	
	/**
	 * Set a and b to be the opposite corners of this rectangle
	 * @param corner0	One corner of this rectangle, opposite to b
	 * @param corner1	One corner of this rectangle, opposite to a
	 */
	public void setCorners(Point corner0, Point corner1) {
		int x0 = corner0.getX();
		int y0 = corner0.getY();
		int x1 = corner1.getX();
		int y1 = corner1.getY();
		int width = x1 - x0;
		int height = y1 - y0;
		// Width and height must be nonnegative
		if (width < 0) {
			x0 = x0 + width;
			width = -width;
		}
		if (height < 0) {
			y0 = y0 + height;
			height = -height;
		}
		this.setX(x0);
		this.setY(y0);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * Draw this Rectangle onto the specified GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x = this.getX();
		int y= this.getY();
		if(this.isFilled()) {
			g.fillRect(x, y, this.getWidth(), this.getHeight());
		}
		else {
			g.strokeRect(x, y, this.getWidth(), this.getHeight());
			
		}
	}
	
	/**
	 * Return the position of the top-left corner of this rectangle
	 * @return The position of the top-left corner of this rectangle
	 */
	public Point getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position of the top-left corner of this Rectangle to the position specified
	 * @param position	The position of the top-left corner to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Set the position of the top-left corner of this Rectangle to the position (x, y) 
	 * specified
	 * @param x	The x coordinate of the top-left corner to set
	 * @param y The y coordinate of the top-left corner to set
	 */
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}
	
	/**
	 * Return the width of this Rectangle
	 * @return The width of this Rectangle
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Set the width of this Rectangle to the width specified
	 * @param width The width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Return the height of this Rectangle
	 * @return The height of this Rectangle
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Set the height of this Rectangle to the height specified
	 * @param height The height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Return the x coordinate of this Rectangle's top-left corner
	 * @return The x coordinate of this Rectangle's top-left corner
	 */
	public int getX() {
		return this.position.getX();
	}
	
	/**
	 * Return the x coordinate of this Rectangle's top-left corner
	 * @return The x coordinate of this Rectangle's top-left corner to set
	 */
	public void setX(int x) {
		this.position.setX(x);
	}
	
	/**
	 * Return the y coordinate of this Rectangle's top-left corner
	 * @return The y coordinate of this Rectangle's top-left corner
	 */
	public int getY() {
		return this.position.getY();
	}
	
	/**
	 * Return the y coordinate of this Rectangle's top-left corner
	 * @return The y coordinate of this Rectangle's top-left corner to set
	 */
	public void setY(int y) {
		this.position.setY(y);
	}

	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
