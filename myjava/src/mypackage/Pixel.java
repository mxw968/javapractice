package mypackage;

import mypackage.Point;

public class Pixel extends Point
{   
	private Color color;
	public static interface ColorConstant
	{
		int BLACK=0xff000000;
		int RED=0xffff0000;
		int GREEN=0xff00ff00;
		int BLUE=0xff0000ff;
		int WHITE=0xffffffff;
	}
	
	public static class Color extends Object implements ColorConstant
	{
		private int value;
		public Color(int value)
		{
			this.value=value;
		}
		
		public Color(int red,int green,int blue)
		{
			this.value=0xff000000|((red&0xFF)<<16)|((green&0xFF)<<8)|blue&0xFF;
		}
		
		public Color()
		{
			this(BLACK);
		}
		
		private String toRGBString()
		{
			return "RGB("+((this.value>>16)&0xFF)+","+((this.value>>8)&0xFF)+","+(this.value&0xFF)+"),0x"+MyInteger.toString(this.value, 16);
		}
	}
	
	public Pixel(Point p,Color color)
	{
		super(p);
		this.color=color;
	}
	
	public Pixel(Point p,int colorvalue)
	{
		this(p,new Color(colorvalue));
	}
	
	public Pixel()
	{
		this(new Point(),ColorConstant.BLACK);
	}
	public String toString()
	{
		return this.getClass().getName()+"ÏñËØ,×ø±ê"+super.toString()+"£¬ÑÕÉ«"+this.color.toRGBString();
	}
	
	public static void main(String args[]) 
	{
		System.out.println(new Pixel().toString());
		Point p=new Point(100,100); 
		Pixel pixel=new Pixel(p,Pixel.ColorConstant.RED);
		System.out.println(pixel.toString());
	}

}
