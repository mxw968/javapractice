package mypackage;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.io.*;

/*public class ExchangeRate extends JFrame implements ActionListener,TableCellRenderer//设置每列的渲染器
{
	private JTextField texts[];     //转换金额      
	private JButton button;        //转换按钮
	private DefaultTableModel tablemodel; //表格模型
	public ExchangeRate()
	{
		super("汇率表");
		this.setBounds(300,240,780,200);
		this.setBackground(java.awt.Color.PINK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		
		//String str[] = {"兑换金额 ","元     持有货币","  兑换货币","汇率","  兑换结果"};
		//String str_text[] = {"10","人民币","欧元","0.05"};
		String str[] = {"持有货币"};
		String str_text[] = {"人民币"};
		this.texts = new JTextField[str_text.length];
		int i = 0;
		for(i = 0; i < str_text.length; i++)
		{
			panel.add(new JLabel(str[i]));
			texts[i] = new JTextField(str_text[i],6);
			panel.add(texts[i]);
		}
		//String titles[] = {""
		//this.tablemodel = new DefaultTableModel(titles,0);
		button = new JButton("计算");
		panel.add(button);
		button.addActionListener(this);
		
		String titles[]={"币种","持有货币(元)","汇率","可兑得货币金额(元）"};
		this.tablemodel = new DefaultTableModel(titles,3);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
			
		
	}

	public void actionPerformed(ActionEvent ev)
	{
			this.tablemodel.setRowCount(3);
			this.tablemodel.setValueAt("欧元",0, 0);
			this.tablemodel.setValueAt("美元",1, 0);
			this.tablemodel.setValueAt("英镑",2, 0);
			this.tablemodel.setValueAt("7.394",0,2);
			this.tablemodel.setValueAt("6.4904",1,2);
			this.tablemodel.setValueAt("9.3634",2,2);
			this.tablemodel.setValueAt(100*7.394,0,3);
			this.tablemodel.setValueAt(100*6.4904,1,3);
			this.tablemodel.setValueAt(100*9.3634,2,3);
			
			for(int i = 0;i < 3;i++)
				this.tablemodel.setValueAt("100",i, 1);
			
			//this.tablemodel.setValueAt("欧元",0, 0);
			
			
	}

	public static void main(String[] args) 
	{
		ExchangeRate er = new ExchangeRate();
	}

}*/

public class ExchangeRate extends JFrame implements ActionListener,TableCellRenderer//设置每列的渲染器
{
	private JTextField texts[];     //转换金额      
	private JButton button,button_s;        //转换按钮
	private DefaultTableModel tablemodel; //表格模型
	public ExchangeRate()
	{
		super("汇率表");
		this.setBounds(300,240,780,200);
		this.setBackground(java.awt.Color.PINK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		
		//String str[] = {"兑换金额 ","元     持有货币","  兑换货币","汇率","  兑换结果"};
		//String str_text[] = {"10","人民币","欧元","0.05"};
		String str[] = {"持有货币"};
		String str_text[] = {"人民币"};
		this.texts = new JTextField[str_text.length];
		int i = 0;
		for(i = 0; i < str_text.length; i++)
		{
			panel.add(new JLabel(str[i]));
			texts[i] = new JTextField(str_text[i],6);
			panel.add(texts[i]);
		}
		//String titles[] = {""
		//this.tablemodel = new DefaultTableModel(titles,0);
		button = new JButton("计算");
		panel.add(button);
		button.addActionListener(this);
		button_s = new JButton("保存");
		panel.add(button_s);
		button_s.addActionListener(this);
		String titles[]={"原币种","现币种","汇率","金额","兑换后"};
		this.tablemodel = new DefaultTableModel(titles,3);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
			
		
	}

	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource() == button)
		{
			String filePath = "f:\\汇率.txt";
	        readTxtFile(filePath,tablemodel);			
		}		
		
		if(ev.getSource() == button_s)
			this.writeTo(tablemodel);
			
	}
	
	 public static void readTxtFile(String filePath,DefaultTableModel tablemodel) throws IOException
	 {
		 
		
		 try {
			   FileReader fr = new FileReader(filePath);
			   BufferedReader br = new BufferedReader(fr);
			   String Ls;
			   System.out.println("读取中，文件的内容如下：");
			   while(( Ls = br.readLine())!=null)
			   {
					   for(int j = 0; j < 2;j++)
						   tablemodel.setValueAt(Ls, 0,j);
			   }
			   br.close();   
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			  } 
	 }

	public void writeTo(DefaultTableModel tablemodel)
	 {
		 try
		 {
			 FileOutputStream fout = new FileOutputStream("F:/te.txt");
			 DataOutputStream dos = new DataOutputStream(fout);
			 dos.writeUTF((String) tablemodel.getValueAt(0, 0));
			 dos.close();			 
			 fout.close();		 
		 }
		 catch(Exception e)
		 {
			 
		 }
		 
	 }
	 
	 public static void main(String[] args) 
	{
		ExchangeRate er = new ExchangeRate();
	}

}

/*import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
 
/**
 * @author 码农小江
 * H20121012.java
 * 2012-10-12下午11:40:21
 */
/*public class H20121012 {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
 /*   public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "L:\\Apache\\htdocs\\res\\20121012.txt";
//      "res/";
        readTxtFile(filePath);
    }
     
}*/
