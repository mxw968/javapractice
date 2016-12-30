package mypackage;

import mypackage.MyDate;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PersonJPanel extends JPanel implements ActionListener
{
	private JTextField texts[];
	private JRadioButton radios[];
	public JComboBox<String> combox_province,combox_city;
	private static String provinces[]={"����ʡ","�㽭ʡ"};
	private static String cities[][]={{"�Ͼ���","������","������"},{"������","������","������"}};
	
	public PersonJPanel() 
	{
		this.setBorder(new TitledBorder("Person"));
		this.setLayout(new GridLayout(5,1));
		String str[][]={{"����","1990��01��01��"},{"��","Ů"}};
		this.texts = new JTextField[str[0].length];
		for(int i = 0; i < this.texts.length; i++)
			this.add(this.texts[i] = new JTextField(str[0][i]));
		JPanel panel_rb = new JPanel(new GridLayout(1,2));
		this.add(panel_rb);
		ButtonGroup bgroup = new ButtonGroup();
		this.radios = new JRadioButton[str[1].length];
		for(int i = 0; i < this.radios.length ; i++)
		{
			panel_rb.add(this.radios[i] = new JRadioButton(str[1][i]));
			bgroup.add(this.radios[i]);
		}
		this.radios[0].setSelected(true);
		this.add(this.combox_province = new JComboBox<String>(PersonJPanel.provinces));
		this.add(this.combox_city = new JComboBox<String>(PersonJPanel.cities[0]));
		this.combox_province.addActionListener(this);
	}
	
	public void set(Person p)
	{
		if(p == null)
			return;
		this.texts[0].setText(p.name);
		this.texts[1].setText(p.birthday.toString());
		if(p.sex.equals("��"))
			this.radios[0].setSelected(true);
		else
			this.radios[1].setSelected(true);
		this.combox_province.setSelectedItem(p.province);
		this.combox_city.setSelectedItem(p.city);
	}

	public Person get()
	{
		String sex = radios[0].isSelected() ? radios[0].getText() : radios[1].getText();
		return new Person(texts[0].getText(),new MyDate(texts[1].getText()),sex,(String)combox_province.getSelectedItem(),(String)combox_city.getSelectedItem());
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		int i = this.combox_province.getSelectedIndex();
		if(cities != null && i != -1)
		{
			this.combox_city.removeAllItems();
			for(int j = 0; j < PersonJPanel.cities[i].length; j++)
				this.combox_city.addItem(PersonJPanel.cities[i][j]);
		}
	}
}
