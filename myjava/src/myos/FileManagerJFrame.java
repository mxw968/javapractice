package myos;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mypackage.TextEditorJFrame;

public class FileManagerJFrame extends JFrame implements ActionListener
{
	private File dir;  //文件对象，表示指定目录
	private File[] files; //保存指定目录中所有文件
	private JTextField text_dir;//地址栏，显示目录路径
	private JList list_files;//列表框，显示指定目录中所有文件和子目录
	
	public FileManagerJFrame()
	{
		super("文件管理器");
		this.setSize(400,300);
		this.setLocation(200,140);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.dir = new File(".","");//创建表示当前目录的文件对象
		this.text_dir = new JTextField(this.dir.getAbsolutePath());//显示目录路径
		this.getContentPane().add(this.text_dir,"North");
		this.text_dir.addActionListener(this);
		
		this.files = this.dir.listFiles(); //返回指定目录中所有文件对象
		String[] filenames = this.dir.list();//返回指定目录中所有文件名字符串
		this.list_files = new JList(filenames);//所有文件名字符串显示在列表框中
		this.getContentPane().add(this.list_files);//调用自定义方法，添加菜单
		this.addMenu();
		
		this.setVisible(true);
	}
	
	private void addMenu()  //添加主菜单
	{
		JMenuBar menubar = new JMenuBar();//菜单栏
		this.setJMenuBar(menubar); //框架上添加菜单栏
		
		JMenu menu_file = new JMenu("文件");//菜单
		menubar.add(menu_file); //菜单栏中加入菜单
		JMenuItem menuitem_open = new JMenuItem("打开");//创建菜单项
		menu_file.add(menuitem_open);//菜单项加入到菜单
		menuitem_open.addActionListener(this);//为菜单项注册单击事件监听器
		JMenuItem menuitem_sendto = new JMenuItem("复制到G:\\备份");
		menu_file.add(menuitem_sendto);
		menuitem_sendto.addActionListener(this);
		JMenuItem menuitem_delete = new JMenuItem("删除");
		menu_file.add(menuitem_delete);
		menuitem_delete.addActionListener(this);
		
	}
	
	public static void copyFile(File file,File file2) //复制文件，适用于任意类型
	{
		{//将file文件内容复制file文件中，重写方式
			try
			{
				FileInputStream fin = new FileInputStream(file);//创建文件输入流对象
				FileOutputStream fout = new FileOutputStream(file2);//创建文件输出流对象
				byte[] buffer = new byte[512]; //字节缓冲区
				int count = 0;
				do
				{
					count = fin.read(buffer);//读取输入流
					if(count != -1)
						fout.write(buffer);
				} while (count != -1);
				fin.close(); //关闭输入流
				fout.close();//关闭输出流
				
				file2.setLastModified(file.lastModified()); //将新文件的最后修改时间设置为原文件的最后修改时间
			}
			catch(IOException ioex)
			{
				System.out.println("复制"+file.getName()+"文件未成功。");
				return;
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent e) //单击事件处理程序
	{
		if(e.getSource() == this.text_dir)
		{
			this.dir = new File(this.text_dir.getText());
			this.files = this.dir.listFiles();
			String[] filenames = this.dir.list();
			this.list_files.setListData(filenames); //重新设置列表框中的数据项
		}
		
		if(e.getActionCommand() == "打开") //单击菜单项时
		{ 
			int i = this.list_files.getSelectedIndex();//返回列表框第1个选中数据项的序号，从0开始;没有选中时返回-1
			if(i != -1)
				if(this.files[i].isFile())
				{
					String fname =(String)this.list_files.getSelectedValues();//返回列表框第一个选中的数据项的值；没有选中时返回null
					int j = fname.indexOf('.');
					if(j>0)
					{
						String extend = fname.substring(j+1);//获得.之后的扩展名字符串
						if(extend.equals("txt") || extend.equals("java"))
							new TextEditorJFrame(this.files[i]); 
					}
				}
		}
	}
	
	public static void main(String[] args)
	{
		new FileManagerJFrame();
	}


}
