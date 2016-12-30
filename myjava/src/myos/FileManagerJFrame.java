package myos;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mypackage.TextEditorJFrame;

public class FileManagerJFrame extends JFrame implements ActionListener
{
	private File dir;  //�ļ����󣬱�ʾָ��Ŀ¼
	private File[] files; //����ָ��Ŀ¼�������ļ�
	private JTextField text_dir;//��ַ������ʾĿ¼·��
	private JList list_files;//�б����ʾָ��Ŀ¼�������ļ�����Ŀ¼
	
	public FileManagerJFrame()
	{
		super("�ļ�������");
		this.setSize(400,300);
		this.setLocation(200,140);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.dir = new File(".","");//������ʾ��ǰĿ¼���ļ�����
		this.text_dir = new JTextField(this.dir.getAbsolutePath());//��ʾĿ¼·��
		this.getContentPane().add(this.text_dir,"North");
		this.text_dir.addActionListener(this);
		
		this.files = this.dir.listFiles(); //����ָ��Ŀ¼�������ļ�����
		String[] filenames = this.dir.list();//����ָ��Ŀ¼�������ļ����ַ���
		this.list_files = new JList(filenames);//�����ļ����ַ�����ʾ���б����
		this.getContentPane().add(this.list_files);//�����Զ��巽������Ӳ˵�
		this.addMenu();
		
		this.setVisible(true);
	}
	
	private void addMenu()  //������˵�
	{
		JMenuBar menubar = new JMenuBar();//�˵���
		this.setJMenuBar(menubar); //�������Ӳ˵���
		
		JMenu menu_file = new JMenu("�ļ�");//�˵�
		menubar.add(menu_file); //�˵����м���˵�
		JMenuItem menuitem_open = new JMenuItem("��");//�����˵���
		menu_file.add(menuitem_open);//�˵�����뵽�˵�
		menuitem_open.addActionListener(this);//Ϊ�˵���ע�ᵥ���¼�������
		JMenuItem menuitem_sendto = new JMenuItem("���Ƶ�G:\\����");
		menu_file.add(menuitem_sendto);
		menuitem_sendto.addActionListener(this);
		JMenuItem menuitem_delete = new JMenuItem("ɾ��");
		menu_file.add(menuitem_delete);
		menuitem_delete.addActionListener(this);
		
	}
	
	public static void copyFile(File file,File file2) //�����ļ�����������������
	{
		{//��file�ļ����ݸ���file�ļ��У���д��ʽ
			try
			{
				FileInputStream fin = new FileInputStream(file);//�����ļ�����������
				FileOutputStream fout = new FileOutputStream(file2);//�����ļ����������
				byte[] buffer = new byte[512]; //�ֽڻ�����
				int count = 0;
				do
				{
					count = fin.read(buffer);//��ȡ������
					if(count != -1)
						fout.write(buffer);
				} while (count != -1);
				fin.close(); //�ر�������
				fout.close();//�ر������
				
				file2.setLastModified(file.lastModified()); //�����ļ�������޸�ʱ������Ϊԭ�ļ�������޸�ʱ��
			}
			catch(IOException ioex)
			{
				System.out.println("����"+file.getName()+"�ļ�δ�ɹ���");
				return;
			}
		}
	}
	
	
	public void actionPerformed(ActionEvent e) //�����¼��������
	{
		if(e.getSource() == this.text_dir)
		{
			this.dir = new File(this.text_dir.getText());
			this.files = this.dir.listFiles();
			String[] filenames = this.dir.list();
			this.list_files.setListData(filenames); //���������б���е�������
		}
		
		if(e.getActionCommand() == "��") //�����˵���ʱ
		{ 
			int i = this.list_files.getSelectedIndex();//�����б���1��ѡ�����������ţ���0��ʼ;û��ѡ��ʱ����-1
			if(i != -1)
				if(this.files[i].isFile())
				{
					String fname =(String)this.list_files.getSelectedValues();//�����б���һ��ѡ�е��������ֵ��û��ѡ��ʱ����null
					int j = fname.indexOf('.');
					if(j>0)
					{
						String extend = fname.substring(j+1);//���.֮�����չ���ַ���
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
