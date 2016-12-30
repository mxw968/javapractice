package mypackage;

import mypackage.Point;

public abstract class Figure 
{
	public Point point;
	protected Figure(Point point)
	{
		this.point=point;
	}
	
	protected Figure()
	{
		this(new Point());
	}
}
