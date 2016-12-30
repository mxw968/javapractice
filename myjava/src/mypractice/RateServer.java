package mypractice;

import java.io.*;
import java.net.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RateServer extends JFrame implements ActionListener
{
	private int port;
	private DefaultTableModel tablemodel;             //定义表格模型
	private PrintWriter cout;
	private JPanel panel;                            //定义面板
	private JButton[] buttons;                      //定义面板数组
	private JMenu menus[];                          //定义菜单数组
	private MessageJDialog jdialog;                //对话框，内部类对象
	
	
	public RateServer(int port)throws IOException 
	{
		// TODO Auto-generated constructor stub
		super("中国人民银行终端服务器系统");
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept();
		//System.out.println("连接成功");
		this.setBounds(700,200,500,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

///////////////////////////添加表格，对表格初始化172页例6.6/////////////////////////////
		JPanel panel = new JPanel();
		this.getContentPane().add(panel);
		String titles[] = {"外汇币种","交易单位","卖出价","兑换金额","换算结果"};
		this.tablemodel = new DefaultTableModel(titles,8);
		JTable jtable = new JTable(this.tablemodel);
		jtable.setEnabled(true);
		this.getContentPane().add(new JScrollPane(jtable));
		for(int i = 0; i < 7;i ++)
		{
			this.tablemodel.setValueAt("欧元",0, 0);
			this.tablemodel.setValueAt("735.31",0, 2);
			this.tablemodel.setValueAt("美元",1, 0);
			this.tablemodel.setValueAt("659.63",1, 2);
			this.tablemodel.setValueAt("英镑",2, 0);
			this.tablemodel.setValueAt("961.37",2, 2);
			this.tablemodel.setValueAt("日元",3, 0);
			this.tablemodel.setValueAt("5.9536",3, 2);
			this.tablemodel.setValueAt("澳元",4, 0);
			this.tablemodel.setValueAt("478.667",4, 2);
			this.tablemodel.setValueAt("韩元",5, 0);
			this.tablemodel.setValueAt("0.552",5, 2);
			this.tablemodel.setValueAt("泰铢",6, 0);
			this.tablemodel.setValueAt("18.464",6, 2);
			this.tablemodel.setValueAt("100",i, 1);
			this.tablemodel.setValueAt("总计",7, 0);
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

/////////////////////////////////添加菜单,书本164页例6.5//////////////////////////////////
	public void addMenu()                    
	{
		JMenuBar menubar = new JMenuBar();           //菜单栏
		this.setJMenuBar(menubar);                  //框架上添加菜单栏
		String[] menustr = {"文件","帮助"};
		String[][] menuitemstr = {{"今日汇率","计算","总计","保存","|","退出"},{"关于"}};
		this.menus = new JMenu[menustr.length];          //菜单数组
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

/////////////////////////////动作事件处理方法////////////////////////////////
	public void actionPerformed(ActionEvent ev)               
	{		
		if(ev.getSource()instanceof JMenuItem)
		{
			if(ev.getActionCommand().equals("今日汇率"))
			{
				int i,j;
				for( i = 0;i < 7;i++)
					 for(j =0; j < 3;j++)
						 this.cout.println(tablemodel.getValueAt(i,j));				
			}
			
			if(ev.getActionCommand().equals("计算"))
			{			
				this.result(tablemodel);
			}
			
			if(ev.getActionCommand().equals("保存"))
			{
				this.save(tablemodel);
			}
			
			if(ev.getActionCommand().equals("总计"))
			{
				this.total(tablemodel);
			}
			
			if(ev.getActionCommand().equals("退出"))
				if(JOptionPane.showConfirmDialog(this,"终止当前程序运行? ","确认",JOptionPane.YES_NO_CANCEL_OPTION) == 0)
					System.exit(0);
				
			if(ev.getActionCommand().equals("关于"))
			{
				jdialog = new MessageJDialog();
			}
		}
	}

////////////////////计算兑换后的金额/////////////////////////////////////
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

////////////////////////将汇率表保存到指定文件中/////////////////////////////
	 public void save(DefaultTableModel tablemodel) 
	 {
		 try
		 {
			 FileWriter fw = new FileWriter("F:/今日汇率.txt");
			 fw.write("币种  交易单位 卖出价  兑换金额  换算结果"+"\r\n");
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
			 JOptionPane.showMessageDialog(this,"IO错,写入文件不成功");
		 }
		 
	 }
	 

////////////////////点击帮助关于时弹出程序信息，书本150页例6.3////////////////////////////////
	 private class MessageJDialog extends JDialog
	 {
		 private JLabel jlabel;
		 public MessageJDialog()
		 {
			 //RateServer.this引用外部类的当前对象（即对话框所依附的框架窗口；true表示模式窗口
			 super(RateServer.this,"关于",true);
			 this.setSize(400,100);
			 this.setLocation(RateServer.this.getX()+100,RateServer.this.getY()+100);
			 jlabel = new JLabel("这是周杰做的一个简单的货币汇率转换及统计",JLabel.CENTER);
			 this.getContentPane().add(jlabel);
			 this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			 this.setVisible(true);
		 }
	 }
	
	 public static void main(String[] args)     //主函数创建服务器端对象
	 {
		try
		{
			RateServer server = new  RateServer(8080);
		}
		catch(Exception e)
		{
			System.out.println("测试服务器端监听出错"+e.getMessage());
			
		}
	}

}
