package mypackage;

import javax.swing.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GUIRWFile extends JFrame implements ActionListener
{
	JTextArea txtMessage;
	JButton btnRead,btnWrite;
	
	public GUIRWFile()
	{
		super("文本读写");
		txtMessage = new JTextArea(15,30);
		btnRead = new JButton("读取");
		btnWrite = new JButton("写入");
		this.setLayout(new FlowLayout());
		this.add(new JLabel("文件内容: "));
		this.add(txtMessage);
		this.add(btnRead);
		this.add(btnWrite);
		btnRead.addActionListener(this);
		btnWrite.addActionListener(this);
		this.setSize(400,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		JButton btn = (JButton)ev.getSource();
		String fileName = "F:\\test.txt";
		if(btn.getText().equals("读取"))
		{
			try
			{
				InputStream is = new FileInputStream(fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while((line = br.readLine()) != null)
				{
					buffer.append(line + "\n");
				}
				
				txtMessage.setText(buffer.toString());
				br.close();
				is.close();
			}
			
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"系统I/O错误");
			}
		}
			
			else if(btn.getText().equals("写入"))
			{
				try
				{
					String msg = txtMessage.getText();
					OutputStream os = new FileOutputStream(fileName);
					PrintStream ps = new PrintStream(os);
					ps.print(msg);
					ps.close();
					os.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,"系统I/O错误");
				}
			}
		}
	

	public static void main(String[] args)
	{
		new GUIRWFile();
	}
}