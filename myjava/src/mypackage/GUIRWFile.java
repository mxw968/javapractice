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
		super("�ı���д");
		txtMessage = new JTextArea(15,30);
		btnRead = new JButton("��ȡ");
		btnWrite = new JButton("д��");
		this.setLayout(new FlowLayout());
		this.add(new JLabel("�ļ�����: "));
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
		if(btn.getText().equals("��ȡ"))
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
				JOptionPane.showMessageDialog(this,"ϵͳI/O����");
			}
		}
			
			else if(btn.getText().equals("д��"))
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
					JOptionPane.showMessageDialog(this,"ϵͳI/O����");
				}
			}
		}
	

	public static void main(String[] args)
	{
		new GUIRWFile();
	}
}