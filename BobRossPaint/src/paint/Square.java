package paint;


import javafx.scene.canvas.GraphicsContext;

/**
 * A Square class
 * 
 * @author Hamid
 */
import javafx.scene.paint.Color;

public class Square extends Shape {
	private Point position; // The position of the top-left corner of the rectangle
	
	private int sideLength;
	
	/**
	 * Create a new Square with the specified position, width, and height. The position
	 * is the upper-left corner of the Square. If width < 0 or height < 0, the width or
	 * height of this Rectangle will be 0
	 * 
	 * @param position		The position of the upper-left corner of this Rectangle
	 * @param sideLength	 The sideLenght of this Square; sideLength >= 0
	
	 */
	public Square(Point position,int sideLength, boolean filled) {
		super(filled);
		this.position = position;
		if (sideLength>=0) {
			this.sideLength=sideLength;
		}
	}
	
	/**
	 * Create a new Square with the specified position (x, y), width, and height. The position
	 * is the upper-left corner of the Square. 
	 * 
	 * @param x			The x coordinate of the upper left corner of this Square
	 * @param y			The y coordinate of the upper left corner of this Square
	 * @param sideLength	 The sideLenght of this Square; sideLength >= 0
	
	 */
	public Square(int x, int y, int sideLength, boolean filled) {
		this(new Point(x, y), sideLength, filled);

	}
	
	/**
	 * Set a and b to be the opposite corners of this square
	 * @param corner0	One corner of this square, opposite to b
	 * @param corner1	One corner of this square, opposite to a
	 */
	public void setCorners(Point corner0, Point corner1) {
		int x0 = corner0.getX();
		int y0 = corner0.getY();
		int x1 = corner1.getX();
		int y1 = corner1.getY();
		int horizontal = x1 - x0;
		int vertical = y1 - y0;
		// Width and height must be nonnegative
		
		if(Math.abs(horizontal) < Math.abs(vertical))
		{
			if((vertical < 0 && horizontal > 0) || (vertical > 0 && horizontal < 0) )
			{
				vertical = -horizontal;
			}
			else
			{
				vertical = horizontal;
			}
		}
		else
		{
			if((horizontal < 0 && vertical > 0) ||(horizontal > 0 && vertical < 0) )
			{
				horizontal = -vertical;
			}
			else
			{
				horizontal = vertical;
			}
		}
		
		if (horizontal < 0) {
			x0 = x0 + horizontal;
			//horizontal = -horizontal;
		}
		if (vertical < 0) {
			y0 = y0 + vertical;
			
			//vertical = -vertical;
		}
		this.setX(x0);
		this.setY(y0);
		
		this.setSideLength(Math.abs(vertical)); 
	}
	
	/**
	 * Draw this Square onto the specified GraphicsContext
	 * @param g	The GraphicsContext to draw onto
	 */
	@Override
	public void execute(GraphicsContext g) {
		int x = this.getX();
		int y= this.getY();
		if(this.isFilled()) {
			g.fillRect(x, y, this.getSideLength(), this.getSideLength());
		}
		else {
			g.strokeRect(x, y, this.getSideLength(), this.getSideLength());
		}
	}
	
	/**
	 * Return the position of the top-left corner of this Square
	 * @return The position of the top-left corner of this Square
	 */
	public Point getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position of the top-left corner of this Square to the position specified
	 * @param position	The position of the top-left corner to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Set the position of the top-left corner of this Square to the position (x, y) 
	 * specified
	 * @param x	The x coordinate of the top-left corner to set
	 * @param y The y coordinate of the top-left corner to set
	 */
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}
	

	
	/**
	 * Set the sideLenght of this Square to the width specified
	 * @param width The width to set
	 */
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
	
	/**
	 * Return the side length of this Square
	 * @return The side length of this Square
	 */
	public int getSideLength() {
		return this.sideLength;
	}
	
	
	/**
	 * Return the x coordinate of this Square's top-left corner
	 * @return The x coordinate of this Square's top-left corner
	 */
	public int getX() {
		return this.position.getX();
	}
	
	/**
	 * Return the x coordinate of this Square's top-left corner
	 * @return The x coordinate of this Square's top-left corner to set
	 */
	public void setX(int x) {
		this.position.setX(x);
	}
	
	/**
	 * Return the y coordinate of this Square's top-left corner
	 * @return The y coordinate of this Square's top-left corner
	 */
	public int getY() {
		return this.position.getY();
	}
	
	/**
	 * Return the y coordinate of this Square's top-left corner
	 * @return The y coordinate of this Square's top-left corner to set
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



