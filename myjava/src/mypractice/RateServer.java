package mypractice;

import java.io.*;
import java.net.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RateServer extends JFrame implements ActionListener
{
	private int port;
	private DefaultTableModel tablemodel;             //������ģ��
	private PrintWriter cout;
	private JPanel panel;                            //�������
	private JButton[] buttons;                      //�����������
	private JMenu menus[];                          //����˵�����
	private MessageJDialog jdialog;                //�Ի����ڲ������
	
	
	public RateServer(int port)throws IOException 
	{
		// TODO Auto-generated constructor stub
		super("�й����������ն˷�����ϵͳ");
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept();
		//System.out.println("���ӳɹ�");
		this.setBounds(700,200,500,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

///////////////////////////��ӱ�񣬶Ա���ʼ��172ҳ��6.6/////////////////////////////
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		String titles[] = {"������","���׵�λ","������","�һ����","������"};
		this.tablemodel = new DefaultTableModel(titles,8);
		JTable jtable = new JTable(this.tablemodel);
		jtable.setEnabled(true);
		this.getContentPane().add(new JScrollPane(jtable));
		for(int i = 0; i < 7;i ++)
		{
			this.tablemodel.setValueAt("ŷԪ",0, 0);
			this.tablemodel.setValueAt("735.31",0, 2);
			this.tablemodel.setValueAt("��Ԫ",1, 0);
			this.tablemodel.setValueAt("659.63",1, 2);
			this.tablemodel.setValueAt("Ӣ��",2, 0);
			this.tablemodel.setValueAt("961.37",2, 2);
			this.tablemodel.setValueAt("��Ԫ",3, 0);
			this.tablemodel.setValueAt("5.9536",3, 2);
			this.tablemodel.setValueAt("��Ԫ",4, 0);
			this.tablemodel.setValueAt("478.667",4, 2);
			this.tablemodel.setValueAt("��Ԫ",5, 0);
			this.tablemodel.setValueAt("0.552",5, 2);
			this.tablemodel.setValueAt("̩��",6, 0);
			this.tablemodel.setValueAt("18.464",6, 2);
			this.tablemodel.setValueAt("100",i, 1);
			this.tablemodel.setValueAt("�ܼ�",7, 0);
		}

		
		this.cout = new PrintWriter(client.getOutputStream(),true);
		this.cout.println();
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		this.addMenu();
		this.setVisible(true);
		
		int x = 0,y=0;
		String line = br.readLine();
         while((line = br.readLine()) != null )
         {
        	 tablemodel.setValueAt(line, x,3); 
        	 x++;    	
         }             
         
		br.close();
		this.cout.close();
		server.close();
	}

/////////////////////////////////��Ӳ˵�,�鱾164ҳ��6.5//////////////////////////////////
	public void addMenu()                    
	{
		JMenuBar menubar = new JMenuBar();           //�˵���
		this.setJMenuBar(menubar);                  //�������Ӳ˵���
		String[] menustr = {"�ļ�","����"};
		String[][] menuitemstr = {{"���ջ���","����","�ܼ�","����","|","�˳�"},{"����"}};
		this.menus = new JMenu[menustr.length];          //�˵�����
		JMenuItem menuitems[][] = new JMenuItem[menuitemstr.length][];
		for(int i= 0; i <menuitemstr.length;i++)
		{
			this.menus[i] = new JMenu(menustr[i]);
			menubar.add(this.menus[i]);
			menuitems[i] = new JMenuItem[menuitemstr[i].length];
			for(int j = 0; j < menuitemstr[i].length;j++)
			{
				if(menuitemstr[i][j].equals("|"))
					this.menus[i].addSeparator();
				else 
				{
					menuitems[i][j] = new JMenuItem(menuitemstr[i][j]);
					this.menus[i].add(menuitems[i][j]);
					menuitems[i][j].addActionListener(this);
				}
			}			
		}		
	}

/////////////////////////////�����¼�������////////////////////////////////
	public void actionPerformed(ActionEvent ev)               
	{		
		if(ev.getSource()instanceof JMenuItem)
		{
			if(ev.getActionCommand().equals("���ջ���"))
			{
				int i,j;
				for( i = 0;i < 7;i++)
					 for(j =0; j < 3;j++)
						 this.cout.println(tablemodel.getValueAt(i,j));				
			}
			
			if(ev.getActionCommand().equals("����"))
			{			
				this.result(tablemodel);
			}
			
			if(ev.getActionCommand().equals("����"))
			{
				this.save(tablemodel);
			}
			
			if(ev.getActionCommand().equals("�ܼ�"))
			{
				this.total(tablemodel);
			}
			
			if(ev.getActionCommand().equals("�˳�"))
				if(JOptionPane.showConfirmDialog(this,"��ֹ��ǰ��������? ","ȷ��",JOptionPane.YES_NO_CANCEL_OPTION) == 0)
					System.exit(0);
				
			if(ev.getActionCommand().equals("����"))
			{
				jdialog = new MessageJDialog();
			}
		}
	}

////////////////////����һ���Ľ��/////////////////////////////////////
	public void result(DefaultTableModel tablemodel)
	{
		float sum = 1;
		for (int i = 0; i < 7; i++) 
		{  
			Object cellValue1 = tablemodel.getValueAt(i, 1);  
			Object cellValue2 = tablemodel.getValueAt(i, 2);  
            Object cellValue3 = tablemodel.getValueAt(i, 3);            
            
            if (cellValue3 != null) 
            {  
                float floatValue = Float.parseFloat((String) cellValue3)*Float.parseFloat((String) cellValue2)/Float.parseFloat((String) cellValue1);  
                sum *= floatValue;
                this.tablemodel.setValueAt(""+floatValue,i, 4);
            }  
        }  
    }  
	
	public void total(DefaultTableModel tablemodel)
	 {
		float sum = 0;
		for (int i = 0; i < 7; i++) 
		{  
			Object cellValue1 = tablemodel.getValueAt(i, 4);  
            if (cellValue1 != null) 
            {  
                float floatValue = Float.parseFloat((String) cellValue1);
                sum += floatValue;
               
            }  
        } 
		 this.tablemodel.setValueAt(""+sum,7, 4);
	 }

////////////////////////�����ʱ��浽ָ���ļ���/////////////////////////////
	 public void save(DefaultTableModel tablemodel) 
	 {
		 try
		 {
			 FileWriter fw = new FileWriter("F:/���ջ���.txt");
			 fw.write("����  ���׵�λ ������  �һ����  ������"+"\r\n");
			 for(int i = 0; i < 7;i++)
			 {
				 for(int j = 0; j < tablemodel.getColumnCount(); j++)
					 fw.write(tablemodel.getValueAt(i, j).toString()+"    \r");
				 fw.write("\r\n");
					 
			 }			
			 fw.write(tablemodel.getValueAt(7,0).toString()+"                             \r");
			 fw.write(tablemodel.getValueAt(7,4).toString()+" \r");
			 fw.close();
		 }
		 
		 catch(IOException ex)
		 {
			 JOptionPane.showMessageDialog(this,"IO��,д���ļ����ɹ�");
		 }
		 
	 }
	 

////////////////////�����������ʱ����������Ϣ���鱾150ҳ��6.3////////////////////////////////
	 private class MessageJDialog extends JDialog
	 {
		 private JLabel jlabel;
		 public MessageJDialog()
		 {
			 //RateServer.this�����ⲿ��ĵ�ǰ���󣨼��Ի����������Ŀ�ܴ��ڣ�true��ʾģʽ����
			 super(RateServer.this,"����",true);
			 this.setSize(400,100);
			 this.setLocation(RateServer.this.getX()+100,RateServer.this.getY()+100);
			 jlabel = new JLabel("�����ܽ�����һ���򵥵Ļ��һ���ת����ͳ��",JLabel.CENTER);
			 this.getContentPane().add(jlabel);
			 this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			 this.setVisible(true);
		 }
	 }
	
	 public static void main(String[] args)     //�����������������˶���
	 {
		try
		{
			RateServer server = new  RateServer(8080);
		}
		catch(Exception e)
		{
			System.out.println("���Է������˼�������"+e.getMessage());
			
		}
	}

}
