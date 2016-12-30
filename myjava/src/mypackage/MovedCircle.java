package mypackage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class MovedCircle extends JFrame implements Runnable 
{
	static int i = 10;  
	static int j = 440;
	
	public MovedCircle() 
	{
		super("ÔÂÁÁ");
		this.setSize(300,300);
		this.setBounds(500,200,800,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		/*this.getContentPane().add(new Circle());
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"South");*/
		this.setVisible(true);
	}
	
	public void paint(Graphics g) 
	{   
		super.paint(g);   
		g.setColor(Color.white);   
		g.fillRect(0, 0, 500, 500);   
		g.setColor(Color.red);   
		g.fillOval(i, j, 60, 60);  
		g.setColor(Color.BLACK);   
		
	}   
	
	/*private class CircleJPanel implements Runnable
	{
		Thread thread;
		int sleeptime;
		
		CircleJPanel()
		
		
		
	}*/
	
	public void run() 
	{  
		while (true) 
		{    
			try 
			{     
				Thread.sleep(500);    
			} 
			catch (InterruptedException e) 
			{     
				e.printStackTrace();    
			}    
			if (i >= 155) 
			{     
				i += 5;     
				j += 15;    }    
			if (i < 155) 
			{     
				i += 5;     
				j -= 15;    
			}    
			if (i >= 305) 
			{     
				i = 10;     
				j = 440;    
			}    
			//System.out.println(i + " " + j);    
			this.repaint();   
			}  
		}   

	public static void main(String[] args) 
	{
		//MovedCircle frame = new MovedCircle();
		new Thread(new MovedCircle()).start(); 
		//frame.setVisible(true);
	}

}

