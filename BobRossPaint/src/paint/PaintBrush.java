package paint;


import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

/**
 * Paintbrush drawing tool.
 * This is not considered a Shape but rather a drawing tool. It does not follow the design of a Shape.
 *
 *
 */
public class PaintBrush extends DrawingCommand{
	//The bristle of the brush are represented by squiggles
	private ArrayList<Squiggle> squiggles;
	private ArrayList<Point> squiggleStartPoints;
	private Random rand;
	private double width;
	private Point start;
	private double bristleCount;
	/**
	 * Construct paintBrush
	 * @param centre: current mouse position
	 * @param width: used to determine size of brush
	 * @bristleCount: denisity of brush
	 */
	public PaintBrush(Point centre, double width, double bristleCount)
	{
		this.squiggles = new ArrayList<Squiggle>();
		this.squiggleStartPoints = new ArrayList<Point>();
		this.width = width;
		this.rand = new Random();
		this.start = centre;
		this.bristleCount = bristleCount;
		this.startBrush(centre);
		
	}
	/**
	 * Initializes the squiggles used to represent the bristles of the paintBrush
	 * @param centre
	 */
	protected void startBrush(Point centre)
	{
		Squiggle s;
		for(int i = 0;i < 8 + 2*this.width + 2*this.bristleCount;i++)
		{
			//the squiggles are created randomly and folow a normal distribution
			int x = (int)(rand.nextGaussian()*(2.5 + this.width) + centre.getX());
			int y = (int)(rand.nextGaussian()*(2.5 + this.width) + centre.getY());
			s = new Squiggle();
			Point p = new Point(x,y);
			s.addPoint(p);
			this.squiggles.add(s);
			this.squiggleStartPoints.add(p);
		}
	}
	/**
	 * Extends the squiggles to continue the brushStroke
	 * @param centre
	 */
	public void addStrokes(Point centre)
	{
		for(int i = 0; i < this.squiggles.size(); i++)
		{
			Squiggle s = this.squiggles.get(i);
			Point squiggleStartPoint = this.squiggleStartPoints.get(i);
			//Adjust each squiggle relative to it's own position
			int x =  squiggleStartPoint.getX() + centre.getX() - this.start.getX();
			int y =  squiggleStartPoint.getY() + centre.getY() - this.start.getY();
			s.addPoint(new Point(x,y));
		}
	}
	/**
	 * draws the paintBrush
	 */
	@Override
	public void execute(GraphicsContext g) {
		double originalWidth = g.getLineWidth();
		//each individual squiggle has a lineWidth of 1
		g.setLineWidth(1);
		for(Squiggle s:this.squiggles)
		{
			s.execute(g);
			
		}
		//sets the lineWidth back to the original setting
		g.setLineWidth(originalWidth);
		
	}
	public ArrayList<Squiggle> getSquiggles()
	{
		return this.squiggles;
	}
	public double getWidth()
	{
		return this.width;
	}
	public ArrayList<Point> getSquiggleStartPoints()
	{
		return this.squiggleStartPoints;
	}
	public Random getRandom()
	{
		return this.rand;
	}
	public double getBristleCount()
	{
		return this.bristleCount;
	}
	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}

}
