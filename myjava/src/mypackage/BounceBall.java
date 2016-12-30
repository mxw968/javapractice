package mypackage;

import javax.swing.*;

import java.awt.color.*;
import java.awt.*;
import java.awt.event.*;

/*public class BounceBall extends JFrame
{
	JPanel panel = new JPanel();
	Circle c;
	public BounceBall() 
	{
		add(panel);
		panel.setLayout(null);
		c = new Circle(panel);
		c.setBounds(40,140,30,30);
		c = new Circle(panel,Color.red);
		panel.add(c);
		c.setBounds(50,10,30,30);
		this.setTitle("µ¯µ¯Çò");
		this.setBounds(400,140,400,300);		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args)
	{
		new BounceBall();
	}

}

class Circle extends Canvas implements ActionListener
{
	private int TOP = 0;
	private int BOTTOM;
	private int LEFT = 0;
	private int RIGHT;
	private int R = 30;
	private int xSpeed = 8;
	private int ySpeed = 7;
	Timer t = new Timer(1,this);
	JPanel panel;
	Color c;
	
	public void actionPerformed(ActionEvent e)
	{
		RIGHT = panel.getWidth();
		BOTTOM = panel.getHeight();
		java.awt.Point currentPoint = this.getLocation();
		double x = currentPoint.getX();
		double y = currentPoint.getY();
		x = x + xSpeed;
		y = y + ySpeed;
		
		if( (x+R) > RIGHT || x < 0)
		{
			xSpeed = - xSpeed;
		}
		
		if( y < 0 || (y + R) > BOTTOM)
		{
			ySpeed = -ySpeed;
		}
		
		currentPoint.setLocation(x,y);
		this.setLocation(currentPoint);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(c);
		g.fillOval(0,0,R,R);
	}
	
	public Circle(JPanel panel)
	{
		this.panel =panel;
		t.start();
	}
	
	public Circle(JPanel panel,Color c)
	{
		this.panel = panel;
		this.c = c;
		t.start();
	}
}*/


public class BallsJFrame extends JFrame implements  ChangeListener

