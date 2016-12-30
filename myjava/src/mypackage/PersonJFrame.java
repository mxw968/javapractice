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
		super("Person������Ϣ����");
		this.setSize(740,300);       //���ô��ڴ�С
		this.setLocationRelativeTo(null);         //������������Ļ����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.person = person;
		JPanel rightpanel = new JPanel(new BorderLayout());     
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.person,rightpanel);
		                                 //ˮƽ�ָ�����ұ߸����һ�����
		split.setDividerLocation(140);     //����ˮƽ�ָ�����λ��
		this.getContentPane().add(split);   //������ݴ�����ӷָ��
		
		this.listmodel = new DefaultListModel<Person>();         //�����յ��б��ģ��
		if(value != null)
			for(int j = 0; j < value.length ; j++)
				this.listmodel.addElement(value[j]);             //�б��ģ�����������
		this.jlist = new JList<Person>(this.listmodel);          //�����б��ָ���б��ģ��
		this.jlist.addListSelectionListener(this);               //ע���б��ѡ���¼�������
		rightpanel.add(new JScrollPane(this.jlist));          //�����Ӱ����б��Ĺ�������
		JPanel buttonpanel = new JPanel();                 //��ť��壬Ĭ��������
		rightpanel.add(buttonpanel,"South");            //�ϱ���Ӱ�ť���
		
		String str[][] = {{"���","ɾ��","ɾ��ѡ����"},{"���ҹؼ���","����ؼ���"},{"����","��������"}};
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
		if(ev.getActionCommand().equals("���"))
			this.listmodel.addElement(this.person.get());
		
		if(ev.getActionCommand().equals("ɾ��"))
			this.listmodel.removeElement(this.person.get());
		if(ev.getActionCommand().equals("ɾ��ѡ����"))
		{
			if(this.listmodel.getSize() == 0)
				JOptionPane.showMessageDialog(this,"�б��Ϊ�գ�����ɾ��");
			else
			{
				int i = this.jlist.getSelectedIndex();
				if(i == -1)
					JOptionPane.showMessageDialog(this,"��ѡ���б��������");
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
		Person value[] = {new Person("��С��",new MyDate(1994,3,15))};
		new PersonJFrame(new PersonJPanel(),value);

	}

}
