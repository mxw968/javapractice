package mypackage;

import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame extends Frame implements ActionListener
{
	private TextField text;
	private Button button_1,button_2,button_plus,button_cancel;
	
	public CalculatorFrame() 
	{
		super("Caculator");
		
		this.setSize(320,120);
		this.setBackground(java.awt.Color.lightGray);
		this.setLocation(300,240);
		this.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
		text = new TextField(40);
		text.setEditable(false);
		this.add(text);
		
		button_1 = new Button("1");
		button_2 = new Button("2");
		button_plus = new Button("+");
		button_cancel = new Button("C");
		
		this.add(button_1);
		this.add(button_2);
		this.add(button_plus);
		this.add(button_cancel);
		
		button_1.addActionListener(this);
		button_2.addActionListener(this);
		button_plus.addActionListener(this);
		button_cancel.addActionListener(this);
		this.addWindowListener(new WinClose());
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button_cancel)
			text.setText("");
		else
			text.setText(text.getText()+e.getActionCommand());
	}

	public static void main(String[] args) 
	{

	}

}
