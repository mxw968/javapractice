package mypackage;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class FilesListJFrame extends JFrame implements ActionListener   //带过滤器的文件列表
{
	private JTextField text_path,text_status;
	private JComboBox<String> combox;
	private JList<File>jlist;
	private DefaultListModel<File>listModel;
	private int count = 0,size = 0;
	
	public FilesListJFrame() 
	{
		// TODO Auto-generated constructor stub
		super("音乐播放器的文件列表");
		this.setBounds(300,240,650,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.listModel = new DefaultListModel<File>();
		this.jlist = new JList<File>(this.listModel);
		this.getContentPane().add(new JScrollPane(this.jlist));
		JToolBar toolbar = new JToolBar();
		this.getContentPane().add(toolbar,"North");
		toolbar.add(text_path = new JTextField("E:\\Music"));
		text_path.addActionListener(this);
		this.getContentPane().add(text_status = new JTextField(),"South");
		
		String filternames[] = {"","*.mp3","*.wav","*.*"};
		this.combox = new JComboBox<String>(filternames);
		this.combox.setEditable(true);
		this.combox.addActionListener(this);
		toolbar.add(this.combox);
		actionPerformed(new ActionEvent(this.combox,0,"*.mp3"));
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ev)
	{
		this.listModel.removeAllElements();
		String filter = (String)this.combox.getSelectedItem();
		count = 0;
		size = 0;
		addList(new File(this.text_path.getText()),new PrefixExtFileFilter(filter));
		text_status.setText("共有 "+count+" 个文件,总字节数为 "+size);
	}
	
	public void addList(File dir,PrefixExtFileFilter filter)
	{
		File files[] = dir.listFiles();
		count += files.length;
		for(int i = 0; i < files.length; i++)
		{
			this.listModel.addElement(files[i]);
			size += files[i].length();
		}
		
		files = dir.listFiles();
		for(int i = 0; i < files.length; i++)
			if(files[i].isDirectory())
				addList(files[i],filter);
	
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new FilesListJFrame();

	}

}
