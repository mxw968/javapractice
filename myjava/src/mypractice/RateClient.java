package mypractice;

import java.io.*;
import java.net.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.color.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;

public class RateClient extends JFrame implements ActionListener
{
	private PrintWriter cout;
	private DefaultTableModel tablemodel;
	private JPanel panel;  
	private JButton[] buttons;            //���尴ť����
	private JToolBar toolbar;
	private JLabel totalMoney,label,day;         

	public RateClient(Socket socket) throws IOException
	{
		// TODO Auto-generated constructor stub
		super("�й���������");
		this.setBounds(200,200,500,240);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		label = new JLabel("�й��������У�����ߵ����У�������������");
		panel.add(label,"North");
		new Thread(new DynamicThread()).start();       //�����߳�
		
		
/////////////////////���Ӵ��������ı����/////////////////////////////		
		String titles[] = {"������","���׵�λ","������","���ոû��Ҷһ����"};
		this.tablemodel = new DefaultTableModel(titles,8);
		JTable jtable = new JTable(this.tablemodel);
		jtable.setEnabled(true);
		this.getContentPane().add(new JScrollPane(jtable));

////////////////////�ڵײ����ӹ�����,���������ӽ������ڼ���ǩ�Լ��ı����Ͱ�ť����/////////////////////////
		toolbar = new JToolBar();
		this.getContentPane().add(toolbar,"South");
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1;
		int date = today.get(Calendar.DATE);
		day = new JLabel("������:  "+year+"��"+month+"��"+date+"��           ");
		toolbar.add(day);
		toolbar.add(this.totalMoney = new JLabel("��������ոû��Ҷһ��ܶ�:  "));

		String[] buttonstr={"�ύ","�˳�"};
		buttons = new JButton[buttonstr.length];
		for(int i = 0; i< buttonstr.length;i++)
		{
			toolbar.add(buttons[i] = new JButton(buttonstr[i]));
			buttons[i].addActionListener(this);
		}


		this.cout = new PrintWriter(socket.getOutputStream(),true);
		this.cout.println();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.setVisible(true);
		String line = br.readLine();
 		int x = 0,y=0;
        while((line = br.readLine()) != null )
        {
         	tablemodel.setValueAt(line, x,y++); 
         	
         	 while(y == 3)
         	 {
         		 x++;
         		 y = 0;
         	 }
          }           
		
		br.close();
		this.cout.close();
	}

//////////////////////�����Ч��//////////////////////////////
	private class DynamicThread implements Runnable
	{
		public void run()
		{
			while(true)
			{
				for(int i = 0; i <400;i++)
				{
					try
					{
						Thread.sleep(40);					
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					label.setLocation(i,5);
					if(i%20==0)
					{
						int r = new Random().nextInt(256);
						int g = new Random().nextInt(256);
						int b = new Random().nextInt(256);					
						label.setForeground(new Color(r,g,b));						
					}
				}
			}
		}
	}

///////////////////////�����¼�������/////////////////////////////
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand().equals("�ύ"))
		{
			//this.cout.println(money.getText());		
			for(int i = 0;i<7;i++)
				this.cout.println(this.tablemodel.getValueAt(i, 3));
				
			
		}
		
		if(ev.getActionCommand().equals("�˳�"))
			if(JOptionPane.showConfirmDialog(this,"��ֹ��ǰ��������? ","ȷ��",JOptionPane.YES_NO_CANCEL_OPTION) == 0)
				System.exit(0);
	}
	
	public RateClient(String host,int port)throws IOException
	{
		this(new Socket(host,port));
	}
	
	
	public static void main(String[] args)throws IOException
	{
		new RateClient(new Socket("127.0.0.1",8080));
	}
}
