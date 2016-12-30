package mypackage;

import javax.swing.*;
import java.awt.*;


class CardBuffer 
{
	private int value;
	private boolean isEmpty = true;
	private int order = 0;
	private int number;
	
	CardBuffer(int number)
	{
		this.number = number;
	}
	
	synchronized void put(int i)
	{
		while(!this.isEmpty)
			try{ this.wait();}
		    catch(InterruptedException ex){}
		this.value = i;
		this.isEmpty = false;
		this.notifyAll();
	}
	
	synchronized int get(int order)
	{
		while(this.isEmpty || (this.order != order))
			try{ this.wait();}
		    catch(InterruptedException ex){}
		this.isEmpty = true;
		this.notifyAll();
		this.order = (this.order + 1) % this.number;
		return this.value;
	}
}

class SendCardThread extends Thread
{
	private CardBuffer cardbuffer;
	private int cardMax;
	private int number;
	
	public SendCardThread(CardBuffer cardbuffer, int cardMax,int number)
	{
		this.cardbuffer = cardbuffer;
		this.cardMax = cardMax;
		this.number = number;
		this.setPriority(Thread.MAX_PRIORITY);
	}
	
	public void run()
	{
		for(int i =1;i <= this.cardMax; i++)
			this.cardbuffer.put(i);
		for(int i =1;i <= this.number; i++)
			this.cardbuffer.put(-1);
	}
}

class ReceiveCardJFrame extends JFrame implements Runnable
{
	private CardBuffer cardbuffer;
	private JTextArea text;
	private int order;
	
	public ReceiveCardJFrame(CardBuffer cardbuffer, int order, String title, int x, int y)
	{
		super(title);
		this.setBounds(x,y,250,100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.cardbuffer = cardbuffer;
		this.order = order;
		this.text = new JTextArea();
		this.getContentPane().add(this.text);
		this.text.setLineWrap(true);
		this.text.setEditable(false);
		this.text.setFont(new Font("Arial",Font.PLAIN,20));
		this.setVisible(true);
		new Thread(this).start();
	}
	
	public void run()
	{
		while(true)
		{
			int value = this.cardbuffer.get(this.order);
			if(value == -1)
				return;
			this.text.append(String.format("%4d",value));
			try{ Thread.sleep(100);}
			catch(InterruptedException ex){}
		}
     }
}

public class DealCard
{
	public DealCard(int cardMax,int number)
	{
		CardBuffer cardbuffer = new CardBuffer(number);
		new SendCardThread(cardbuffer,cardMax,number).start();
		String titles[]={"北","东","南","北"};
		int x[]={300,550,300,50},y[]={200,320,440,320};
		for(int i=0;i<number;i++)
			new ReceiveCardJFrame(cardbuffer,i, titles[i],x[i],y[i]);
	}
	
	public static void main(String[] args)
	{
		new DealCard(52,4);
	}
	
}
