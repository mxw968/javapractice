package mypackage;
import  mypackage.Point;

public class Line {
	public Point start,end;
	public Line(Point start,Point end)
	{ 
		this.start = start;
		this.end = end;
	}
	
	public String toString()
	{
		return this.getClass().getName()+"("+this.start +","+this.end.toString()+")";
	}
	

	public static void main(String args[]) 
	{
		Point start=new Point(100,100);
		Point end = new Point(100,200);
		System.out.println(new Line(start,end).toString());

	}

}
