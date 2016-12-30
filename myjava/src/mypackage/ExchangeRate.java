package mypackage;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import java.io.*;

/*public class ExchangeRate extends JFrame implements ActionListener,TableCellRenderer//����ÿ�е���Ⱦ��
{
	private JTextField texts[];     //ת�����      
	private JButton button;        //ת����ť
	private DefaultTableModel tablemodel; //���ģ��
	public ExchangeRate()
	{
		super("���ʱ�");
		this.setBounds(300,240,780,200);
		this.setBackground(java.awt.Color.PINK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		
		//String str[] = {"�һ���� ","Ԫ     ���л���","  �һ�����","����","  �һ����"};
		//String str_text[] = {"10","�����","ŷԪ","0.05"};
		String str[] = {"���л���"};
		String str_text[] = {"�����"};
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
		button = new JButton("����");
		panel.add(button);
		button.addActionListener(this);
		
		String titles[]={"����","���л���(Ԫ)","����","�ɶҵû��ҽ��(Ԫ��"};
		this.tablemodel = new DefaultTableModel(titles,3);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
			
		
	}

	public void actionPerformed(ActionEvent ev)
	{
			this.tablemodel.setRowCount(3);
			this.tablemodel.setValueAt("ŷԪ",0, 0);
			this.tablemodel.setValueAt("��Ԫ",1, 0);
			this.tablemodel.setValueAt("Ӣ��",2, 0);
			this.tablemodel.setValueAt("7.394",0,2);
			this.tablemodel.setValueAt("6.4904",1,2);
			this.tablemodel.setValueAt("9.3634",2,2);
			this.tablemodel.setValueAt(100*7.394,0,3);
			this.tablemodel.setValueAt(100*6.4904,1,3);
			this.tablemodel.setValueAt(100*9.3634,2,3);
			
			for(int i = 0;i < 3;i++)
				this.tablemodel.setValueAt("100",i, 1);
			
			//this.tablemodel.setValueAt("ŷԪ",0, 0);
			
			
	}

	public static void main(String[] args) 
	{
		ExchangeRate er = new ExchangeRate();
	}

}*/

public class ExchangeRate extends JFrame implements ActionListener,TableCellRenderer//����ÿ�е���Ⱦ��
{
	private JTextField texts[];     //ת�����      
	private JButton button,button_s;        //ת����ť
	private DefaultTableModel tablemodel; //���ģ��
	public ExchangeRate()
	{
		super("���ʱ�");
		this.setBounds(300,240,780,200);
		this.setBackground(java.awt.Color.PINK);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		
		//String str[] = {"�һ���� ","Ԫ     ���л���","  �һ�����","����","  �һ����"};
		//String str_text[] = {"10","�����","ŷԪ","0.05"};
		String str[] = {"���л���"};
		String str_text[] = {"�����"};
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
		button = new JButton("����");
		panel.add(button);
		button.addActionListener(this);
		button_s = new JButton("����");
		panel.add(button_s);
		button_s.addActionListener(this);
		String titles[]={"ԭ����","�ֱ���","����","���","�һ���"};
		this.tablemodel = new DefaultTableModel(titles,3);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
			
		
	}

	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource() == button)
		{
			String filePath = "f:\\����.txt";
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
			   System.out.println("��ȡ�У��ļ����������£�");
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
 * @author ��ũС��
 * H20121012.java
 * 2012-10-12����11:40:21
 */
/*public class H20121012 {
    /**
     * ���ܣ�Java��ȡtxt�ļ�������
     * ���裺1���Ȼ���ļ����
     * 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
     * 3����ȡ������������Ҫ��ȡ�����ֽ���
     * 4��һ��һ�е������readline()��
     * ��ע����Ҫ���ǵ����쳣���
     * @param filePath
     */
 /*   public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("�Ҳ���ָ�����ļ�");
        }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
            e.printStackTrace();
        }
     
    }
     
    public static void main(String argv[]){
        String filePath = "L:\\Apache\\htdocs\\res\\20121012.txt";
//      "res/";
        readTxtFile(filePath);
    }
     
}*/
