package mypackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;

public class MoneyRateTable extends JFrame implements ActionListener,TableCellRenderer//����ÿ�е���Ⱦ��
{
	private JTextField texts[];     //ת�����   
	protected JMenu menus[];               //�˵�����
	private DefaultTableModel tablemodel; //���ģ��
	private JButton button;        //ת����ť

	public MoneyRateTable() 
	{
		// TODO Auto-generated constructor stub
		super("���ʱ�");
		this.setBounds(300,240,780,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"South");
		String str[] = {"����������һ���"},str_text[] = {"100"},str_result[] = {"�һ������"},str_text2[] = {""};
		this.texts = new JTextField[str_text.length];
		int i = 0;
		for(i = 0; i < str_text.length; i++)
		{
			panel.add(new JLabel(str[i]));			
			texts[i] = new JTextField(str_text[i],6);
			panel.add(texts[i]);
			
		}
		button = new JButton("����");
		panel.add(button);
		button.addActionListener(this);
		for(i = 0; i < str_text.length; i++)
		{
			panel.add(new JLabel(str_result[i]));
			texts[i] = new JTextField(str_text2[i],6);
			panel.add(texts[i]);
		}
		
		String titles[]={"����","���л���(Ԫ)","����","�ɶҵû��ҽ��(Ԫ��"};
		this.tablemodel = new DefaultTableModel(titles,3);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.initialize();
		this.setVisible(true);
			
	}
	
	public void initialize()
	{
		this.tablemodel.setRowCount(3);
		this.tablemodel.setValueAt("ŷԪ",0, 0);
		this.tablemodel.setValueAt("��Ԫ",1, 0);
		this.tablemodel.setValueAt("Ӣ��",2, 0);
		for(int i = 0;i < 3;i++)
		    this.tablemodel.setValueAt("100",i, 1);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
			
			this.tablemodel.setValueAt("7.394",0,2);
			this.tablemodel.setValueAt("6.4904",1,2);
			this.tablemodel.setValueAt("9.3634",2,2);
			this.tablemodel.setValueAt(100*7.394,0,3);
			this.tablemodel.setValueAt(100*6.4904,1,3);
			this.tablemodel.setValueAt(100*9.3634,2,3);
			
			/*for(int i = 0;i < 3;i++)
				this.tablemodel.setValueAt("100",i, 1);*/
			
			//this.tablemodel.setValueAt("ŷԪ",0, 0);
			
			
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MoneyRateTable();

	}

}


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















/*public class EditorJFrame extends JFrame implements ActionListener,MouseListener
{
private JComboBox<String>combox_name;
private JComboBox<Integer>combox_size;
private JCheckBox checkbox[];
private JRadioButton radiobs[];
protected Color colors[] = {Color.red,Color.green,Color.blue};
private String colorstr[]={"red","green","blue"};
protected JTextArea text;
protected JPopupMenu popupmenu;
protected JMenu menus[];
private JCheckBoxMenuItem cbmenuitem[];

public EditorJFrame() 
{
	super("�ı��༭��");
	this.setSize(800,600);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	JToolBar toolbar = new JToolBar();
	this.getContentPane().add(toolbar,"North");
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	String fontsName[] = ge.getAvailableFontFamilyNames();
	this.combox_name = new JComboBox<String>(fontsName);
	this.combox_name.addActionListener(this);
	toolbar.add(this.combox_name);
	
	Integer sizes[] = {20,30,40,50,60,70};
	this.combox_size = new JComboBox<Integer>(sizes);
	this.combox_size.setEditable(true);
	this.combox_size.addActionListener(this);
	toolbar.add(this.combox_size);
	
	String stylestr[] = {"����","б��"};
	this.checkbox = new JCheckBox[stylestr.length];
	for(int i = 0; i < stylestr.length; i++)
	{
		this.checkbox[i] = new JCheckBox(stylestr[i]);
		toolbar.add(this.checkbox[i]);
		this.checkbox[i].addActionListener(this);
	}
	
	ButtonGroup bgroup_color = new ButtonGroup();
	this.radiobs = new JRadioButton[this.colorstr.length];
	for(int i = 0; i < this.radiobs.length; i++)
	{
		this.radiobs[i] = new JRadioButton(this.colorstr[i]);
		this.radiobs[i].setForeground(this.colors[i]);
		this.radiobs[i].addActionListener(this);
		bgroup_color.add(this.radiobs[i]);	
		toolbar.add(this.radiobs[i]);
	}
	this.radiobs[0].setSelected(true);
	
	JButton bopen = new JButton("��",new ImageIcon("open.png"));
	bopen.addActionListener(this);
	toolbar.add(bopen);
	JButton bsave = new JButton("����",new ImageIcon("save.png"));
	bsave.addActionListener(this);
	toolbar.add(bsave);
	
	this.text = new JTextArea("");
	this.text.addMouseListener(this);
	this.getContentPane().add(new JScrollPane(this.text));
	this.text.setForeground(colors[0]);
	this.addMenu();	
	this.setVisible(true);
}

private void addMenu()
{
	JMenuBar menubar = new JMenuBar();
	this.setJMenuBar(menubar);
	String menustr[] = {"�ļ�","�༭","����","��ʽ","����","����","����"};
	String menuitemstr[][] = {{"�½�","��","����","���Ϊ","|","�˳�"},{"����","�ָ�","|","����","����","ճ��","|","����","�滻"},{"����","�ı�"},{"����"},{"����ͳ��","�Զ�����","ƴд���"},{},{}};
	this.menus = new JMenu[menustr.length];
	JMenuItem menuitems[][] = new JMenuItem[menuitemstr.length][];
	for(int i = 0; i < menuitemstr.length; i++)
	{
		this.menus[i] = new JMenu(menustr[i]);
		menubar.add(this.menus[i]);
		menuitems[i] = new JMenuItem[menuitemstr[i].length];
		for(int j = 0; j < menuitemstr[i].length; j++)
			if(menuitemstr[i][j].equals("|"))
				this.menus[i].addSeparator();
			else
			{
				menuitems[i][j] = new JMenuItem(menuitemstr[i][j]);
				this.menus[i].add(menuitems[i][j]);
				menuitems[i][j].addActionListener(this);
			}
	}
	menuitems[0][1].setIcon(new ImageIcon("open.png"));
	menuitems[0][2].setIcon(new ImageIcon("save.png"));
	
	JMenu menu_style = new JMenu("����");
	menus[3].add(menu_style);
	String stylestr[] = {"����","б��"};
	this.cbmenuitem = new JCheckBoxMenuItem[stylestr.length];
	for(int i =0; i < stylestr.length; i++)
	{
		this.cbmenuitem[i] = new JCheckBoxMenuItem(stylestr[i]);
		menu_style.add(this.cbmenuitem[i]);
		this.cbmenuitem[i].addActionListener(this);
	}
	
	JMenu menu_color = new JMenu("��ɫ");
	menus[3].add(menu_color);
	ButtonGroup buttongroup = new ButtonGroup();
	for(int i = 0; i < this.colorstr.length; i++)
	{
		JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(this.colorstr[i]);
		buttongroup.add(rbmi);
		menu_color.add(rbmi);
		rbmi.setForeground(this.colors[i]);
		rbmi.addActionListener(this);
	}
	
	this.popupmenu = new JPopupMenu();
	String menuitems_cut[] = {"����","����","ճ��"};
	JMenuItem popmenuitem[] = new JMenuItem[menuitems_cut.length];
	for(int i = 0; i < popmenuitem.length; i ++)
	{
		popmenuitem[i] = new JMenuItem(menuitems_cut[i]);
		this.popupmenu.add(popmenuitem[i]);
		popmenuitem[i].addActionListener(this);
	}
	
	popmenuitem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
	this.text.add(this.popupmenu);
}

public void actionPerformed(ActionEvent ev)
{
	if(ev.getSource()instanceof JRadioButton)
		for(int i = 0; i < this.radiobs.length; i++)
			if(this.radiobs[i].isSelected())
			{
				this.text.setForeground(this.colors[i]);
				return;
			}
	
	if(ev.getSource()instanceof JMenuItem)
	{
		if(ev.getActionCommand().equals("�˳�"))
			if(JOptionPane.showConfirmDialog(this,"��ֹ��ǰ��������?","ȷ��",JOptionPane.YES_NO_OPTION) == 0)
				System.exit(0);
		if(ev.getActionCommand().equals("����"))
			this.text.cut();
		if(ev.getActionCommand().equals("����"))
			this.text.copy();
		if(ev.getActionCommand().equals("ճ��"))
			this.text.paste();			
	}
	
	if(ev.getSource()instanceof JComboBox<?> || ev.getSource()instanceof JCheckBox || ev.getSource()instanceof JMenuItem)
	{
		int size = 0;
		try
		{
			String fontname = (String)this.combox_name.getSelectedItem();
			Object obj = this.combox_size.getSelectedItem();
			
			if(obj instanceof Integer)
				size = ((Integer)obj).intValue();
			if(obj instanceof String)
				size = Integer.parseInt((String)obj);
			if(size < 4 || size > 120)
				throw new Exception("SizeException");
			java.awt.Font font = this.text.getFont();
			int style = font.getStyle();
			if(ev.getActionCommand().equals("����"))
				style = style ^ 1;
			if(ev.getActionCommand().equals("б��"))
				style = style ^ 2;
			this.text.setFont(new Font(fontname,style,size));
			
			if(ev.getActionCommand().equals("comboBoxEdited"))
				insert(this.combox_size,size);
		}
		
		catch(NumberFormatException nfex)
		{
			if(ev.getActionCommand().equals("comboBoxEdited"))
				JOptionPane.showMessageDialog(this,"\""+(String)this.combox_size.getSelectedItem()+"\"����ת��������������������!");
		}
		
		catch(Exception ex)
		{
			if(ev.getActionCommand().equals("comboBoxEdited") && ex.getMessage().equals("SizeException"))
				JOptionPane.showMessageDialog(this,size+" �ֺŲ�����,����������");
		}
	}
}

public<T extends Comparable<? super T>>void insert(JComboBox<T>combox,T value)
{
	int begin = 0,end = combox.getItemCount()-1,mid = end;
	while(begin <= end)
	{
		mid = (begin+end)/2;
		if(value.compareTo(combox.getItemAt(mid)) == (0)
			return;
		if(value.compareTo(combox.getItemAt(mid)) < (0)
			end = mid - 1;
		else begin = mid + 1;
	}
	
	combox.insertItemAt(value, begin);
}

public void mouseClicked(MouseEvent ev)
{
	if(ev.getButton() == 3)
		this.popupmenu.show(this.text,ev.getX(),ev.getY());
}

public void mousePressed(MouseEvent ev){}
public void mouseReleased(MouseEvent ev){}
public void mouseEntered(MouseEvent ev){}
public void mouseExited(MouseEvent ev){}
public void mouseDragged(MouseEvent ev){}

public  static void main(String[] args)
{
	new EditorJFrame();
}

}
*/





























/*public class TextEditorJFrame extends EditorJFrame
{
private File file;
protected JFileChooser fchooser;
public TextEditorJFrame(File file)
{
	super();
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.fchooser = new JFileChooser(new File("��ʫ",""));
	this.fchooser.setFileFilter(new ExtensionFileFilter("�ı��ļ�(*.txt)","txt"));
	this.file = file;
	if(file == null)
		this.file = new File("");
	this.readFrom(this.file,this.text);
	this.setTitle("�ı��༭��    "+this.file.getName());
}

public TextEditorJFrame()
{
	this(new File(""));
}

public void readFrom(File file,JTextArea text)
{
	try
	{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		text.setText("");
		String s;
		while((s=br.readLine())!=null)
			text.append(s+"\r\n");
		br.close();
		fr.close();
	}
	
	catch(IOException ioex){}
}

public void actionPerformed(ActionEvent ev)
{
	super.actionPerformed(ev);
	actionMenuItem(ev);
}

protected void actionMenuItem(ActionEvent ev)
{
	String mitem = ev.getActionCommand();
	if(mitem.equals("�½�"))
	{
		this.file = new File("");
		this.setTitle("�ı��༭��    ");
		this.text.setText("");
		return;
	}
	
	if(mitem.equals("��") && fchooser.showOpenDialog(this) == 0)
	{
		this.file = fchooser.getSelectedFile();
		this.setTitle("�ı��༭��    "+this.file.getName());
		this.readFrom(this.file,this.text);
		return;
	}
	
	if(mitem.equals("����") && !this.file.getName().equals(""))
		this.writeTo(this.file,this.text);
	else if((mitem.equals("����") && this.file.getName().equals("") || mitem.equals("���Ϊ")) && fchooser.showSaveDialog(this) == 0)
	{
		this.file = fchooser.getSelectedFile();
		if(!file.getName().endsWith(".txt"))
			this.file = new File(this.file.getAbsolutePath()+".txt");
		this.writeTo(this.file,this.text);
		this.setTitle("�ı��༭��     "+this.file.getName());
	}
}

public void writeTo(File file,JTextArea text)
{
	try
	{
		FileWriter fw = new FileWriter(file);
		fw.write(text.getText());
		fw.close();
	}
	
	catch(IOException ioex)
	{
		JOptionPane.showMessageDialog(this,"IO��,д��\""+file.getName()+"\"�ļ����ɹ�");
	}
}

public static void main(String[] args)
{
	new TextEditorJFrame();
}
}
*/