package paint;


import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
/**
 * Cloud class
 *
 *
 */
public class Cloud extends DrawingCommand{

	private ArrayList<Point> points;
	Random rand;
	double width;
	/**
	 * Cloud is a drawing tool that acts similar to the spray can in ms paint.
	 * This is not considered a Shape but rather a drawing tool
	 * @param centre :current mouse position
	 * @param width: used to determine the "radius" of the cloud 
	 */
	public Cloud(Point centre, double width)
	{
		this.points = new ArrayList<Point>();
		this.rand = new Random();
		addPoints(centre);
		this.width = width;
	}
	/**
	 * adds points that will represent each dot for the cloud
	 * @param centre : current mouse posistion
	 */
	public void addPoints(Point centre)
	{
		for(int i = 0; i < 15;i++)
		{
			//points are determined randomly and follow a normal distribution
			int x = (int)(rand.nextGaussian()*(4 + this.width) + centre.getX());
			int y = (int)(rand.nextGaussian()*(4 + this.width) + centre.getY());
			this.points.add(new Point(x,y));
		}
		
	}
	//draws the points on the canvas.
	@Override
	public void execute(GraphicsContext g) {
		// TODO Auto-generated method stub
		for(Point point: this.points)
		{
			int x = point.getX();
			int y = point.getY();
			g.fillRect(x, y, 1, 1);
		}
		
		
	}
	@Override
	public boolean isUndo() {
		// TODO Auto-generated method stub
		return true;
	}
	


}
