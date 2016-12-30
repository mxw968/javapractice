package mypackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class TestEdit extends JFrame implements ActionListener
{
	FileDialog open = new FileDialog(this,"打开文件",FileDialog.LOAD);
	String fileName;
	Button b_dakai = new Button("打开");
	TextArea text = new TextArea();
    
	public TestEdit()
	{
		super("文件的打开");
		this.setBounds(400,200,400,300);
		b_dakai.addActionListener(this);
		this.add(text);
		this.add(b_dakai,"South");
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b_dakai)
		{
			this.open.show();
			fileName = this.open.getDirectory() + this.open.getFile();
			if(fileName != null)
			{
				this.readFile(fileName);
			}
		}
	}
	
	public void readFile(String fileName)
	{
		try
		{
			File file = new File(fileName);
			FileReader readin = new FileReader(file);
			int size = (int)file.length();
			int charsRead = 0;
			char[] content = new char[size];
			while(readin.ready())
			{
				charsRead += readin.read(content,charsRead,size - charsRead);
			}
			
			readin.close();
			text.setText(new String(content,0,charsRead));
		}
		
		catch(IOException e)
		{
			System.out.println("Error Opening file");
		}
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new EncryptJFrame().addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			};
		});

	}

}
