package mypackage;

public class Point {

	public int x,y;
	
	public Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public Point() 
	{
		this(0,0);
	}
	
	public String toString()
	{
		return "Point("+this.x+","+this.y+")";
	}


}
