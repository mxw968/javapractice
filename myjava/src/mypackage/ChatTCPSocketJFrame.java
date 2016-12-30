package mypackage;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;

public class ChatTCPSocketJFrame extends JFrame implements ActionListener
{
	private String name;
	private JTextArea text_receiver;
	private JTextField text_sender;
	private PrintWriter cout;
	private JButton[] buttons;
	

	
	public ChatTCPSocketJFrame(String name,Socket socket)throws IOException 
	{
		super("聊天室  "+name+" "+InetAddress.getLocalHost()+":"+socket.getLocalPort());
		this.setBounds(320,240,400,240);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.text_receiver = new JTextArea();
		this.text_receiver.setEditable(false);
		this.getContentPane().add(new JScrollPane(this.text_receiver));
		
		JToolBar toolbar = new JToolBar();
		this.getContentPane().add(toolbar,"South");
		toolbar.add(this.text_sender = new JTextField(30));
		String[] buttonstr = {"发送","离线"};
		buttons = new JButton[buttonstr.length];
		for(int i = 0; i < buttonstr.length; i++)
		{
			toolbar.add(buttons[i] = new JButton(buttonstr[i]));
			buttons[i].addActionListener(this);
		}
		this.setVisible(true);
		this.name = name;
		this.cout = new PrintWriter(socket.getOutputStream(),true);
		this.cout.println(name);
		//将Socket的字节输入流转换成字符流，默认GBK字符集，再创建缓冲字符输入流
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = "连接" + br.readLine() + "成功";
		while(line != null && !line.endsWith("bye"))
		{
			text_receiver.append(line + "\r\n");
			line = br.readLine();
		}
		
		br.close();
		this.cout.close();
		socket.close();
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
	}

	public ChatTCPSocketJFrame(String name,String host,int port)throws IOException 
	{
		this(name,new Socket(host,port));
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand().equals("发送"))
		{
			this.cout.println(name+"说： "+text_sender.getText()); //通过输出流发送给对方
			text_receiver.append("我说: "+text_sender.getText() + "\n");
			text_sender.setText("");
		}
		
		if(ev.getActionCommand().equals("离线"))
		{
			text_receiver.append("我离线\n");
			this.cout.println(name+"离线\n"+"bye\n");
			buttons[0].setEnabled(false);
			buttons[1].setEnabled(false);			
		}
	}
	public static void main(String[] args) throws IOException 
	{
		new ChatTCPSocketJFrame("小蜜蜂","127.0.0.1",2001);
		//new  ChatTCPSocketJFrame();
	}

}
