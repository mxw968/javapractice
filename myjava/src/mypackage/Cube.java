
package mypackage;

public class Cube extends Object implements Area,Volume 
{   
	protected double length,width,height;
	public Cube(double length,double width,double height) 
	{
		this.length=length;
		this.width=width;
		this.height=height;
	}
	
	public double area()
	{
		return 2*this.length*this.width+2*this.width*this.height+2*this.length*this.height;
	}
	
	public double volume()
	{
		return this.length*this.width*this.height;
	}

	public static void main(String[] args) 
	{
		Cube c=new Cube(2,3,4);
		System.out.println("长方体的表面积是: "+c.area()+"长方体的体积是: "+c.volume());

	}

}
