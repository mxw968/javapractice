package mypackage;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class MoneyJFrame extends JFrame implements CaretListener
{
	private JTextField text_money,text_str;
	private MessageJDialog jdialog;

	public MoneyJFrame() 
	{
		super("金额的中文大写形式");
		this.setBounds(300,240,360,90);
		this.setResizable(false);
		this.setBackground(java.awt.Color.lightGray);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(new JLabel("金额"));
		text_money=new JTextField("12345678.90",22);
		this.getContentPane().add(text_money);
		text_money.addCaretListener(this);
		this.getContentPane().add(new JLabel("中文大写形式"));
		text_str=new JTextField(22);
		text_str.setHorizontalAlignment(JTextField.RIGHT);
		text_str.setEditable(false);
		this.getContentPane().add(text_str);
		caretUpdate(null);
		this.setVisible(true);
		jdialog=new MessageJDialog();
	}
	
	private class MessageJDialog extends JDialog
	{
		private JLabel jlabel;
		
		public MessageJDialog()
		{
			super(MoneyJFrame.this,"提示",true);
			this.setSize(300,80);
			jlabel=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(jlabel);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		
		private void show(String message)
		{
			jlabel.setText(message);
			this.setLocation(MoneyJFrame.this.getX()+100,MoneyJFrame.this.getY()+100);
			this.setVisible(true);
		}
	}
	
	public void caretUpdate(CaretEvent ev)
	{
		try
		{
			double x=Double.parseDouble(text_money.getText());
			text_str.setText(RMBtoString(x));
		}
		
		catch(NumberFormatException nfex)
		{
			jdialog.show("\""+text_money.getText()+"\"不能转换成浮点数，请重新输入!");
		}
		
		finally{}
	}
	
	public static String RMBtoString(double x)
	{
		String yuan="亿千百拾万千佰拾元角分";
		String digit="零壹贰叁肆伍陆柒捌玖";
		String result="";
		int y=(int)(x*100);
		for(int i=yuan.length()-1;y>0&&i>0;i--,y/=10)
			result=""+digit.charAt(y%10)+yuan.charAt(i)+result;
		return result;
	}

	public static void main(String[] args)
	{
		new MoneyJFrame();

	}

}
