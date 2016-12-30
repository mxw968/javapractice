package myos;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BankerAlgorithm extends JFrame implements ActionListener
{
	private JButton auto,insert;
	private Container container;
	
	public BankerAlgorithm()
	{
		super("银行家算法选择窗口");
		this.setSize(350,200);
		this.setLocationRelativeTo(getOwner()); 
		
		container  = getContentPane();
		container.setLayout(new GridBagLayout());
		init();
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		
	}
	
	public void init()
	{
		GridBagConstraints gbcautobutton = new GridBagConstraints();
		gbcautobutton.gridx = 0;
		gbcautobutton.gridy = 0;
		//gbcautobutton.gridheight = 3;
		auto = new JButton("自动演示1.0");
		auto.addActionListener(this);
		container.add(auto,gbcautobutton);
		
		GridBagConstraints gbcinsertbutton = new GridBagConstraints();
		gbcinsertbutton.gridx = 1;
		gbcinsertbutton.gridy = 0;
		insert = new JButton("自动演示2.0");
		insert.addActionListener(this);
		container.add(insert,gbcinsertbutton);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == auto)
		{
			new AutoBankerAlgorithm();
			
		}
		if(e.getSource() == insert)
		{
			new InsertBankerAlgorithm();
		}
	}
	
	
	public static void main(String[] args)
	{
		new BankerAlgorithm();
	}
}


