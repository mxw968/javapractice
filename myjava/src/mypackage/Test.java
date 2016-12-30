package mypackage;

import java.awt.*; 
import javax.swing.*;  


public class Test extends JFrame implements Runnable 
{  
	static int i = 10;  
	static int j = 440; 
	
	public Test() 
	{   
		this.setSize(500, 500);   
		this.setVisible(true);   
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
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
	
	public static void main(String args[]) 
	{    
		new Thread(new Test()).start();   
	} 
}
