package mypackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class EncryptJFrame extends JFrame implements ActionListener
{
	private JButton[] buttons;
	private JTextArea space;
	
	public EncryptJFrame()
	{
		super("加密解密");
		this.setBounds(200,200,300,200);
		this.space = new JTextArea();
		this.space.setLineWrap(true);
		this.getContentPane().add(new JScrollPane(this.space));
		
		JToolBar toolbar = new JToolBar();
		this.getContentPane().add(toolbar,"South");

		String[] buttonstr = {"加密","解密"};
		buttons = new JButton[buttonstr.length];
		for( int i = 0;i < buttonstr.length; i ++)
		{
			toolbar.add(buttons[i] = new JButton(buttonstr[i]));
			buttons[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand() == "加密")
		{
			String filePath = "f:\\加密.txt";
	        //readTxtFile(filePath,space);
			writeTo(filePath,space);
		}
				
		if(ev.getActionCommand() == "解密")
		{
			String filePath = "f:\\加密.txt";
			readFrom(filePath,space);
			
		}
	}
	
	public void writeTo(String filePath,JTextArea space)
	{
		try
		{
			FileWriter fw = new FileWriter(filePath);
			String content = space.getText();
			char[] a = content.toCharArray();
			char[] b = new char[a.length];
			for(int i = 0; i < a.length; i++)
			{
				b[i] = (char)(a[i]+1);
			}
			fw.write(b);
			fw.close();
		}
		
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(this,"IO错，写入不成功");
		}
	}
	
	public void readFrom(String filePath,JTextArea space)
	{
		try
		{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			char[] a = s.toCharArray(); 
			char[] ap = new char[a.length];
			for(int i = 0;i < a.length;i++)
			{
				ap[i]=(char)(a[i]-1);
			}
			String s1 = new String(ap);
			space.append("\n"+s1);
			br.close();
			fr.close();
		}
		
		catch(IOException ioex)
		{
			JOptionPane.showMessageDialog(this,"IO错，读取不成功");
		}	
	}
	
	public static void main(String[] args)
	{
		new EncryptJFrame();
	}
}
