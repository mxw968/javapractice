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
		super("�ļ�������");
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
		
		String titles[] = {"����","��С","����","�޸�����"};
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
		String menustr[] = {"�ļ�","�༭","�鿴","����"};
		String menuitemstr[][] = {{"��","������","ɾ��","ˢ��","����","|","�˳�"},{"����","����","ճ��","|","���Ƶ������ļ���"},{"�����ϼ�"},{}};
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
		
		JMenu menu_new = new JMenu("�½�");
		menus[0].insert(menu_new,0);
		String menuitemstr_new[] = {"�ļ���","�ı��ĵ�"};
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
				this.tablemodel.setValueAt("�ļ���",i,2);
			String d = sdf.format(new Date(files[i].lastModified()));
			this.tablemodel.setValueAt(d,i,3);
		}
	}
	
	public void actionPerformed(ActionEvent ev) 
	{
		if(ev.getSource() == this.text)
			this.dir = new File(this.text.getText());
		
		else if(ev.getActionCommand().equals("�����ϼ�"));
		{
			this.dir = this.dir.getParentFile();
			if(this.dir != null)
				this.text.setText(this.dir.getAbsolutePath());
			else JOptionPane.showMessageDialog(this,"û���ϼ�Ŀ¼");
		}
		
		else if(ev.getActionCommand().equals("�ļ���"))
			new File(this.dir,"�½��ļ���").mkdir();
		else if(ev.getActionCommand().equals("�ı��ĵ�"))
		{
			try
			{
				new File(this.dir,"�½��ı��ĵ�.txt").createNewFile();
			}
			catch(IOException ioex){}
		}
		
		int i = this.jtable.getSelectedRow();
		
		if(ev.getActionCommand().equals("������") && i != -1)
		{
			String name = (String)this.tablemodel.getValueAt(i,0);
			String filename = JOptionPane.showInputDialog(this,"�ļ���",name);
			if(filename != null && filename != "")
			{
				if(this.files[i].isFile() && !(filename.endsWith(".txt") || filename.endsWith(".TXT")))
					filename += ".txt";
				this.files[i].renameTo(new File(this.dir,filename));
			}
		}
		
		else if(ev.getActionCommand().equals("��") && i != -1)
		{
			if(this.files[i].isFile())
			{
				String fname = this.files[i].getName().toLowerCase();
				if(fname.endsWith(".txt") || fname.endsWith(".java"))
					new TextEditorJFrame(this.files[i]);
				else JOptionPane.showMessageDialog(this,"���ܴ����������ļ�");
			}
			
			else
			{
				this.dir = this.files[i];
				this.text.setText(this.dir.getAbsolutePath());     
			}
		}
		
		else if(ev.getActionCommand().equals("ɾ��") && i != -1)
		{
			if(this.files[i].isFile())
			{
				if(JOptionPane.showConfirmDialog(this,"ɾ��\""+this.files[i].getName()+"\"�ļ�? ") == 0)
					this.files[i].delete();
			}
			
			else if(JOptionPane.showConfirmDialog(this,"ɾ��\""+this.files[i].getName()+"\"�ļ�����������Ŀ¼���ļ�? ") == 0)
				this.deleteDir(files[i]);
		}
		
		if(ev.getActionCommand().equals("���Ƶ������ļ���") && i != -1)
		{
			if(this.files[i].isFile())
			{
				File dir_copyto = new File(this.dir,"\\����");
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

