package mypackage;

import java.util.*;
import java.awt.event.*;

import javax.swing.*;
//import javax.swing.table.DefaultTableModel;

/*public class LoanJFrame extends JFrame implements ActionListener
{
	private JTextField texts[];
	private JSpinner spin_year,spin_month;
	private JButton button;
	private DefaultTableModel tablemodel;
	
	public LoanJFrame() 
	{
		super("银行贷款按月还本付息的计算");
		this.setBounds(300,240,780,400);
		this.setBackground(java.awt.Color.lightGray);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"North");
		
		String str[] = {"贷款金额","元   贷款利率","%/月  贷款年限","年   起始年月","年","月"};
		String str_text[]={"10000","0.5025","5"};
		this.texts = new JTextField[str_text.length];
		int i = 0;
		for(i = 0; i < str_text.length; i++)
		{
			panel.add(new JLabel(str[i]));
			texts[i] = new JTextField(str_text[i],6);
			panel.add(texts[i]);
		}
		
		for(; i < str.length; i++)
			panel.add(new JLabel(str[i]));
		
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int nextmonth = today.get(Calendar.MONTH) + 1;
		nextmonth = nextmonth % 12 + 1;
		if(nextmonth == 1)
			year ++;
		spin_year = new JSpinner();
		spin_year.setValue(year);
		panel.add(spin_year,7);
		spin_month = new JSpinner(new SpinnerNumberModel(nextmonth,1,12,1));
		panel.add(spin_month,9);
		button = new JButton("计算");
		panel.add(button);
		button.addActionListener(this);
		
		String titles[] = {"年月","本金余额(元)","月还本金(元)","月还利息(元)","月还本息(元)"};
		this.tablemodel = new DefaultTableModel(titles,0);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		int months = Integer.parseInt(texts[2].getText())*12;
		int year = Integer.parseInt(""+spin_year.getValue());
		int mon = Integer.parseInt(""+spin_month.getValue());
		double leavings = Double.parseDouble(""+texts[0].getText());
		double pay = leavings/months;
		double rate = Double.parseDouble(""+texts[1].getText());
		this.tablemodel.setRowCount(months);
		for(int i = 0; i < months; i++)
		{
			this.tablemodel.setValueAt(year+"年"+mon+"月",i,0);
			this.tablemodel.setValueAt(String.format("%9.2f",leavings),i,1);
			this.tablemodel.setValueAt(String.format("%9,2f",pay),i,2);
			this.tablemodel.setValueAt(String.format("%9,2f",leavings*rate*0.01),i,3);
			this.tablemodel.setValueAt(String.format("%9,2f",pay+leavings*rate*0.01),i,4);
			if(mon == 12)
				year ++;
			mon = mon % 12 + 1;
			leavings = pay;
		}
	}

	public static void main(String[] args) 
	{
		new LoanJFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}*/

public class LoanJFrame extends JFrame implements ActionListener
{
	//private JSpinner spin_year;
	private JLabel space;
	
	public LoanJFrame()
	{
		super("银行贷款按月还本付息的计算");
		this.setBounds(300,240,780,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		this.getContentPane().add(panel,"South");
		Calendar today = Calendar.getInstance();
		
		int year = today.get(Calendar.YEAR);
		
		space = new JLabel(""+year); 
		this.getContentPane().add(space,"North");
		
		
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ev)
	{	
		
	}
	
	public static void main(String[] args)
	{
		new LoanJFrame();
	}
}