import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class RandomIntFileJFrame extends JFrame implements ActionListener
{
	protected String filename;
	protected DefaultTableModel tablemodel;
	protected JTextField text_filename,text_count;
	protected JToolBar toolbar;

	public RandomIntFileJFrame(String filename) 
	{
		super("采用整数文件保存随机数序列");
		this.setBounds(300,240,520,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.filename = filename;
		this.getContentPane().add(toolbar = new JToolBar(),"North");
		toolbar.add(new JLabel("随机数个数"));
		toolbar.add(text_count = new JTextField(0 + "", 5));
		text_count.addActionListener(this);
		JButton button = new JButton("保存");
		toolbar.add(button);
		button.addActionListener(this);
		toolbar.add(text_filename = new JTextField(filename,20));
		
		String titles[] = new String[10];
		for(int i = 0;i < titles.length; i++)
			titles[i] = (i+1) + "";
		this.tablemodel = new DefaultTableModel(titles,1);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		readFrom(this.filename,this.tablemodel);
		this.setVisible(true);
		
	}
	
	public void readFrom(String filename,DefaultTableModel tablemodel)
	{
		try
		{
			FileInputStream fin = new FileInputStream(filename);
			DataInputStream din = new DataInputStream(fin);
			int i = 0,j = 0;
			while(true)
			{
				try
				{
					j = 0;
					while(j < tablemodel.getColumnCount())
						tablemodel.setValueAt(din.readInt(),i,j++);
					i++;
					tablemodel.addRow(new Object[tablemodel.getColumnCount()]);
				}
				
				catch(EOFException ex)
				{
					text_count.setText((i*10+j)+ "");
					break;
				}
			}
			
			din.close();
			fin.close();
		}
		
		catch(IOException e)
		{
			
		}
	}

	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource() == text_count)
		{
			try
			{
				random(Integer.parseInt(this.text_count.getText()));
			}
			catch(NumberFormatException nfex)
			{
				JOptionPane.showMessageDialog(this,"\""+this.text_count.getText()+"\"不能转换为整数，请重新输入!");
			}
		}
		
		if(ev.getActionCommand().equals("保存"))
		{
			writeTo(this.filename,this.tablemodel);
			writeTo("random.txt",this.tablemodel);
		}
	}
	
	private void random(int n)
	{
		this.tablemodel.setRowCount(n/10+1);
		for(int i = 0; i < this.tablemodel.getRowCount(); i++)
			for(int j = 0; j < 10; j++)
				if(i*10+j<n)
					this.tablemodel.setValueAt((int)(Math.random()*100),i,j);
				else
					this.tablemodel.setValueAt(null, i, j);
	}
	
	public void writeTo(String filename,DefaultTableModel tablemodel)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(filename);
			DataOutputStream dout = new DataOutputStream(fout);
			for(int i = 0; i < tablemodel.getColumnCount(); i++)
			{
				for(int j = 0; j < tablemodel.getColumnCount(); j++)
				{
					Object obj = tablemodel.getValueAt(i, j);
					if(obj == null)
						break;
					if(obj instanceof Integer)
						dout.writeInt(((Integer)obj).intValue());
					if(obj instanceof String)
						try
					{
							dout.writeInt(Integer.parseInt((String)obj));
					}
					catch(NumberFormatException nfex)
					{
						
					}
				}
			}
			dout.close();
			fout.close();			
	}
	
	public static void main(String[] args)
	{
		new RandomIntFileJFrame("random");
	}
}
