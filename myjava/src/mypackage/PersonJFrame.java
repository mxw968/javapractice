package mypackage;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.event.*;

import mypackage.Equalable;

public class PersonJFrame extends JFrame implements ActionListener,ListSelectionListener
{
	protected JList<Person> jlist;
	protected DefaultListModel<Person> listmodel;
	protected PersonJPanel person;
	protected JComboBox<String> comboxs[];
	private static Equalable equal[] = {new EqualName(),new EqualBirthday()};
	private static Comparator comparators[] = {new CompareName(),new CompareBirthday()};
	
	public PersonJFrame(PersonJPanel person,Person value[])
	{
		super("Person对象信息管理");
		this.setSize(740,300);       //设置窗口大小
		this.setLocationRelativeTo(null);         //将窗口置于屏幕中央
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.person = person;
		JPanel rightpanel = new JPanel(new BorderLayout());     
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.person,rightpanel);
		                                 //水平分割窗格，左右边各添加一个面板
		split.setDividerLocation(140);     //设置水平分割条的位置
		this.getContentPane().add(split);   //框架内容窗格添加分割窗格
		
		this.listmodel = new DefaultListModel<Person>();         //创建空的列表框模型
		if(value != null)
			for(int j = 0; j < value.length ; j++)
				this.listmodel.addElement(value[j]);             //列表框模型添加数据项
		this.jlist = new JList<Person>(this.listmodel);          //创建列表框，指定列表框模型
		this.jlist.addListSelectionListener(this);               //注册列表框选择事件监听器
		rightpanel.add(new JScrollPane(this.jlist));          //面板添加包含列表框的滚动窗格
		JPanel buttonpanel = new JPanel();                 //按钮面板，默认流布局
		rightpanel.add(buttonpanel,"South");            //南边添加按钮面板
		
		String str[][] = {{"添加","删除","删除选中项"},{"查找关键字","排序关键字"},{"姓名","出生日期"}};
		for(int i = 0; i < str[0].length; i++)
		{
			JButton button = new JButton(str[0][i]);
			button.addActionListener(this);
			buttonpanel.add(button);
		}
		
		this.comboxs = new JComboBox[str[1].length];
		
		for(int i = 0;i < str[1].length ;i++)
		{
			buttonpanel.add(new JLabel(str[1][i]));
			buttonpanel.add(this.comboxs[i] = new JComboBox<String>(str[2]));
			this.comboxs[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	
	public void valueChanged(ListSelectionEvent ev)
	{
		this.person.set(this.jlist.getSelectedValue());
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getActionCommand().equals("添加"))
			this.listmodel.addElement(this.person.get());
		
		if(ev.getActionCommand().equals("删除"))
			this.listmodel.removeElement(this.person.get());
		if(ev.getActionCommand().equals("删除选中项"))
		{
			if(this.listmodel.getSize() == 0)
				JOptionPane.showMessageDialog(this,"列表框为空，不能删除");
			else
			{
				int i = this.jlist.getSelectedIndex();
				if(i == -1)
					JOptionPane.showMessageDialog(this,"请选中列表框数据项");
				else
					this.listmodel.removeElement(i);
			}
		}
		
		if(ev.getSource() == this.comboxs[0])
		{
			int i = this.comboxs[0].getSelectedIndex();
			if(i < equal.length)
				search(this.person.get(),equal[i]);
		}
		
		if(ev.getSource() == this.comboxs[1])
		{
			int i = this.comboxs[1].getSelectedIndex();
			if(i<comparators.length)
				sort(comparators[i]);
		}	
	}
 
	public<T> void search(T obj, Equalable<? super T>eq)
	{
		int n = this.listmodel.getSize();
		for(int i = 0; i < n; i++)
			if(eq.equals(obj,(T)this.listmodel.elementAt(i)))
			{
				this.jlist.setSelectedIndex(i);
				return;
			}			
	}
	
	public<T> void sort(java.util.Comparator<? super T>c)
	{
		for(int i = 0; i < this.listmodel.getSize()-1;i++)
		{
			int min = i;
			for(int j = i + 1; j < this.listmodel.getSize();j++)
				if(c.compare((T)listmodel.getElementAt(j),(T)listmodel.getElementAt(min))<0)
					min = j;
			if(min!=i)
			{
				Person temp = this.listmodel.getElementAt(i);
				this.listmodel.setElementAt(this.listmodel.getElementAt(min),i);
				this.listmodel.setElementAt(temp, min);
			}
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Person value[] = {new Person("李小明",new MyDate(1994,3,15))};
		new PersonJFrame(new PersonJPanel(),value);

	}

}
