package mypackage;

import mypackage.CloseFigure;

public class Cylinder extends Object implements Area,Volume
{
	protected CloseFigure figure;
	protected double height;
	public Cylinder(CloseFigure figure,double height)
	{
		this.figure=figure;
		this.height=height;
	}
	
	public double area()
	{
		return figure.perimeter()*this.height+2*figure.area();
	}
	
	public double volume()
	{
		return figure.area()*this.height;
	}
	
	public String toString()
	{
		return this.getClass().getName()+"µ×ÃæÊÇ"+this.figure.toString()+"; ¸ß"+this.height;
	}
}
