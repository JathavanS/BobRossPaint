package paint;


import java.util.Random;
/**
 * A Thick paintbrush drawing tool. Similar to the regular paintbrush but 
 * can be stretched in the horizontal and vertical direction
 * This is not considered a Shape but rather a drawing tool. It does not follow the design for a Shape. 
 * 
 *
 */
public class ThickBrush extends PaintBrush{

	/**
	 * Constructs ThickBrush
	 * @param centre: current mouse position
	 * @bristleCount: denisity of brush
	 */
	private double height = 0.0;
	private double bristleCount = 0.0;
	public ThickBrush(Point centre, double width,  double height, double bristleCount) {
		super(centre, width, bristleCount);
		this.height = height;
		this.startBrush(centre);
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	
	/**
	 * initialzes the squiggles that are used to represent each brissel on the brush
	 */
	@Override
	protected void startBrush(Point centre)
	{
		if(this.getHeight() != 0.0)
		{
			Random rand = super.getRandom();
			Squiggle s;
			double width = super.getWidth();
			//the brush will be bigger in the horizontal direction compared to the vertical direction
			double length = this.getHeight();

	
			int actualBristleCount = 10 + (int)((this.getWidth() + this.getHeight())*0.6 + 2.5*this.getBristleCount());
			width += 2;
			length += 2;
			for(int i = 0;i < actualBristleCount ;i++)
			{
				//The squiggles are created randomly and follow a uniform distribution
				int x = (int)((2*rand.nextDouble()-1)*(length) + centre.getX());
				int y = (int)((2*rand.nextDouble()-1)*(width) + centre.getY());
				s = new Squiggle();
				Point p = new Point(x,y);
				s.addPoint(p);
				super.getSquiggles().add(s);
				super.getSquiggleStartPoints().add(p);
			}
		}
	}
	

}
