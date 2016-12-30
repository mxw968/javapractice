package mypackage;

import mypackage.Figure;

public class CloseFigure
{
	protected String shape;
	protected CloseFigure(Point point,String shape)
	{
		super(point);
		this.shape=shape;
	}
	
	protected CloseFigure()
	{
		this(new Point(),"δ֪");
	}
	
	public abstract double perimeter();
	public abstract double area();
	public void print()
	{
		System.out.println(this.toString()+",�ܳ�"+String.format("%1.2f,���%1.2f",this.perimeter(),this.area()));
	}
}
