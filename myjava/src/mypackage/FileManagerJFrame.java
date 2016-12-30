package mypackage;

import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.SimpleDateFormat;

public class FileManagerJFrame extends JFrame implements ActionListener 
{
	private File dir;
	private File files[];
	private JTextField text;
	private JTable jtable;
	private DefaultTableModel tablemodel;
 
	public FileManagerJFrame() 
	{
		super("文件管理器");
		this.setBounds(300,200,600,480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addMenu();
		this.dir = new File(".");
		String path = this.dir.getAbsolutePath();
		path = path.substring(0,path.length()-1);
		this.dir = new File(path);
		this.text = new JTextField(this.dir.getAbsolutePath());
		this.getContentPane().add(this.text,"North");
		this.text.addActionListener(this);
		
		String titles[] = {"名称","大小","类型","修改日期"};
		this.tablemodel = new DefaultTableModel(titles,0);
		this.jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(this.jtable));
		listFilesToTableModel();
		this.setVisible(true);
	}
	
	private void addMenu()
	{
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		String menustr[] = {"文件","编辑","查看","帮助"};
		String menuitemstr[][] = {{"打开","重命名","删除","刷新","搜索","|","退出"},{"剪切","复制","粘贴","|","复制到备份文件夹"},{"返回上级"},{}};
		JMenu menus[] = new JMenu[menustr.length];
		for(int i = 0;i < menuitemstr.length; i++)
		{
			menus[i] = new JMenu(menustr[i]);
			menubar.add(menus[i]);
			for(int j = 0;j < menuitemstr[i].length; j++)
			{
				if(menuitemstr[i][j].equals("|"))
					menus[i].addSeparator();
				else
				{
					JMenuItem menuitem = new JMenuItem(menuitemstr[i][j]);
					menus[i].add(menuitem);
					menuitem.addActionListener(this);
				}	
			}
		}
		
		JMenu menu_new = new JMenu("新建");
		menus[0].insert(menu_new,0);
		String menuitemstr_new[] = {"文件夹","文本文档"};
		for(int i = 0; i < menuitemstr_new.length; i++)
		{
			JMenuItem menuitem = new JMenuItem(menuitemstr_new[i]);
			menu_new.add(menuitem);
			menuitem.addActionListener(this);
		}
	}

	private void listFilesToTableModel()
	{
		this.files = this.dir.listFiles();
		for(int i = this.tablemodel.getRowCount()-1; i >= 0; i--)
			this.tablemodel.removeRow(i);
		this.tablemodel.setRowCount(this.files.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		for(int i = 0; i < this.files.length; i++)
		{
			this.tablemodel.setValueAt(this.files[i].getName(),i,0);
			if(this.files[i].isFile())
				this.tablemodel.setValueAt(this.files[i].length()+"B",i,1);
			if(this.files[i].isDirectory())
				this.tablemodel.setValueAt("文件夹",i,2);
			String d = sdf.format(new Date(files[i].lastModified()));
			this.tablemodel.setValueAt(d,i,3);
		}
	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		if(ev.getSource() == this.text)
			this.dir = new File(this.text.getText());
		
		else if(ev.getActionCommand().equals("返回上级"));
		{
			this.dir = this.dir.getParentFile();
			if(this.dir != null)
				this.text.setText(this.dir.getAbsolutePath());
			else JOptionPane.showMessageDialog(this,"没有上级目录");
		}
		
		else if(ev.getActionCommand().equals("文件夹"))
			new File(this.dir,"新建文件夹").mkdir();
		else if(ev.getActionCommand().equals("文本文档"))
		{
			try
			{
				new File(this.dir,"新建文本文档.txt").createNewFile();
			}
			catch(IOException ioex){}
		}
		
		int i = this.jtable.getSelectedRow();
		
		if(ev.getActionCommand().equals("重命名") && i != -1)
		{
			String name = (String)this.tablemodel.getValueAt(i,0);
			String filename = JOptionPane.showInputDialog(this,"文件名",name);
			if(filename != null && filename != "")
			{
				if(this.files[i].isFile() && !(filename.endsWith(".txt") || filename.endsWith(".TXT")))
					filename += ".txt";
				this.files[i].renameTo(new File(this.dir,filename));
			}
		}
		
		else if(ev.getActionCommand().equals("打开") && i != -1)
		{
			if(this.files[i].isFile())
			{
				String fname = this.files[i].getName().toLowerCase();
				if(fname.endsWith(".txt") || fname.endsWith(".java"))
					new TextEditorJFrame(this.files[i]);
				else JOptionPane.showMessageDialog(this,"不能打开这种类型文件");
			}
			
			else
			{
				this.dir = this.files[i];
				this.text.setText(this.dir.getAbsolutePath());     
			}
		}
		
		else if(ev.getActionCommand().equals("删除") && i != -1)
		{
			if(this.files[i].isFile())
			{
				if(JOptionPane.showConfirmDialog(this,"删除\""+this.files[i].getName()+"\"文件? ") == 0)
					this.files[i].delete();
			}
			
			else if(JOptionPane.showConfirmDialog(this,"删除\""+this.files[i].getName()+"\"文件夹中所有子目录和文件? ") == 0)
				this.deleteDir(files[i]);
		}
		
		if(ev.getActionCommand().equals("复制到备份文件夹") && i != -1)
		{
			if(this.files[i].isFile())
			{
				File dir_copyto = new File(this.dir,"\\备份");
				if(!dir_copyto.exists())
					dir_copyto.mkdir();
				File f2 = new File(dir_copyto,this.files[i].getName());
				if(!f2.exists() || this.files[i].lastModified() > f2.lastModified())
				{
					FileStream.copy(this.files[i].getAbsolutePath(),f2.getAbsolutePath());
					f2.setLastModified(this.files[i].lastModified());
				}
			}
		}
		listFilesToTableModel();
	}
	
	
	public void deleteDir(File dir)
	{
		File files[] = dir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				deleteDir(files[i]);
			files[i].delete();
		}
		dir.delete();
	}

	public static void main(String[] args) 
	{
		new FileManagerJFrame();
	}

}

