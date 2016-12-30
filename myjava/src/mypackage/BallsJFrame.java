package mypackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*public class BallsJFrame extends JFrame implements ChangeListener
{
	private BallsCanvas canvas;
	private JSpinner spinner;
	//BallsJFrame.BallsCanvas;
	
	public BallsJFrame(Color colors[])
	{
		super("弹弹球");
		this.setBounds(300,200,400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas = new BallsCanvas(colors,100);
		this.getContentPane().add(this.canvas);//我们可以使用getContentPane()方法得到JFrame的内容面板
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"South");
		panel.add(new JLabel("Delay"));
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setStepSize(20); //设置数值序列步长为20
		this.spinner = new JSpinner(model);
		this.spinner.setValue(100);
		this.spinner.addChangeListener(this);  //微调文本行响应改变事件
		panel.add(this.spinner);
		this.setVisible(true);		
	}
	
	public void stataChange(ChangeEvent ev)
	{
		this.canvas.setDelay(Integer.parseInt(""+this.spinner.getValue()));
	}

	public static void main(String[] args) 
	{
		Color colors[] = {Color.red,Color.blue,Color.magenta,Color.cyan};
		new BallsJFrame(colors);
	}
}
	class BallsCanvas extends Canvas implements ActionListener, FocusListener
	{
		private Ball balls[];
		private Timer timer;
		
		private static class Ball
		{
			int x,y;
			Color color;
			boolean up,left;
			Ball(int x,int y,Color color)
			{
				this.x = x;
				this.y = y;
				this.color = color;
				up = left = false;
			}
		}
		
		public BallsCanvas(Color colors[],int delay)
		{
			this.balls = new Ball[colors.length];
			for(int i = 0; i < colors.length; i++,x+=40)
				balls[i] = new Ball(x,x,colors[i]);
			this.addFocusListener(this);
			timer = new Timer(delay,this);
			timer.start();
		}
		
		public void setDelay(int delay)
		{
			timer.setDelay(delay);
		}
		
		public void paint(Graphics g)
		{
			for(int i = 0; i < balls.length; i++)
			{
				g.setColor(balls[i].color);
				balls[i].x = balls[i].left ? balls[i].x-10 :balls[i].x+10;
				if(balls[i].x <= 0 || balls[i].x >= this.getWidth())
					balls[i].left = !balls[i].left;
				balls[i].y = balls[i].up ? balls[i].y - 10 : balls[i].y + 10;
				if(balls[i].y <= 0 || balls[i].y >= this.getHeight())
					balls[i].up = !balls[i].up;
				g.fillOval(balls[i].x,balls[i].y,20,20);
			}
		}
		
		public void actionPerformed(ActionEvent ev)
		{
			repaint();
		}
		
		public void focusGained(FocusEvent ev)
		{
			timer.stop();
		}
		
		public void focusLost(FocusEvent ev)
		{
			timer.restart();
		}
	}*/

public class BallsJFrame extends JFrame implements ChangeListener
{
	private BallsCanvas canvas;
	private JSpinner spinner;
	
	public BallsJFrame(Color colors[])
	{
		super("弹弹球");
		this.setBounds(300,200,400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.canvas = new BallsCanvas(colors,100);
		this.getContentPane().add(this.canvas);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"South");
		panel.add(new JLabel("Delay"));
		SpinnerNumberModel model = new SpinnerNumberModel();
		model.setStepSize(20);
		this.spinner.setValue(100);
		this.spinner.addChangeListener(this);
		panel.add(this.spinner);
		this.setVisible(true);
	}
	
	public void stateChanged(ChangeEvent ev)
	{
		this.canvas.setDelay(Integer.parseInt(""+this.spinner.getValue()));
	}
	
	public static void main(String[] args)
	{
		Color colors[] = {Color.red,Color.green,Color.blue,Color.magenta,Color.cyan};
		new BallsJFrame(colors);
	}
}

class BallsCanvas extends Canvas implements ActionListener,FocusListener
{
	private Ball balls[];
	private Timer timer;
	
	private static class Ball
	{
		int x,y;
		Color color;
		boolean up,left;
		Ball(int x, int y,Color color)
		{
			this.x = x;
			this.y = x;
			this.color = color;
			up = left = false;
		}
		
	}
	
	public BallsCanvas(Color colors[],int delay)
	{
		this.balls = new Ball[colors.length];
		for(int i = 0,x = 40; i < colors.length; i++,x += 40)
			balls[i] = new Ball(x,x,colors[i]);
		this.addFocusListener(this);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void setDelay(int delay)
	{
		timer.setDelay(delay);
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0;i < balls.length; i++)
		{
			g.setColor(balls[i].color);
			balls[i].x = balls[i].left ? balls[i].x - 10 : balls[i].x + 10;
			if(balls[i].x <= 0 || balls[i].x >= this.getWidth())
				balls[i].left = !balls[i].left;
			balls[i].y = balls[i].up ? balls[i].y - 10 : balls[i].y + 10;
			if(balls[i].y <= 0 || balls[i].y >= this.getHeight())
				balls[i].up = !balls[i].up;
			g.fillOval(balls[i].x,balls[i].y,20,20);
		}
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		repaint();
	}
	
	public void focusGained(FocusEvent ev)
	{
		timer.stop();
	}
	
	public void focusLost(FocusEvent ev)
	{
		timer.restart();
	}
}

