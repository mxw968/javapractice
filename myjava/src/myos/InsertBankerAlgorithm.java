package myos;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InsertBankerAlgorithm extends JFrame
{
	private DefaultTableModel tablemodel; 
	private static int n = 4;
	private int i,j,a=0,c=0;
	private int pneed[][],claim[][],allocation[][],resource[],available[],useall[]={0,0,0};
	private String resourceArrayString,avaiableArrayString;
	private ArrayList<String> list;
	public InsertBankerAlgorithm()
	{
		super("银行家自动演示2.0");
		this.setSize(590,350);
		this.setLocationRelativeTo(getOwner()); 
		
		//数组定义
		pneed = new int[n][3];
		claim = new int[n][3];
		allocation = new int[n][3];
		resource = new int[3];
		available = new int[3];
		
		
		//调用随机数函数赋值
		for(i = 0;i < 3 ;i++)
		{
			resource[i]=this.systemresource();	
		}
		
		for(i = 0;i < n ;i++)
		{
			for(j = 0; j < 3;j++)
			{
				claim[i][j] = this.maxresource();
				allocation[i][j] = this.usedresource();
			}			
		}
		
		for(j = 0;j<3;j++)
		{
			for(i = 0;i<n;i++)
			{
				useall[j] = useall[j] + allocation[i][j];
				pneed[i][j] = claim[i][j] - allocation[i][j];
			}
			available[j] = resource[j] - useall[j];
		}
		
		init();
		this.setVisible(true);
		if(available[0]<0||available[1]<0||available[2]<0)
			JOptionPane.showMessageDialog(null,"申请不当");
		else
		{
			JOptionPane.showMessageDialog(null,"请求安全，分配完毕");
		}
		
	}
		
		
	
	public void init()
	{
		String titles[] = {" ","磁带机","绘图仪","打印机"};
		this.tablemodel = new DefaultTableModel(titles,26);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		
		//resourceArrayString = Arrays.toString(resource);
		this.tablemodel.setValueAt("系统资源总量",0, 0);
		for(int i = 0; i < 3;i++)
		{
			this.tablemodel.setValueAt(resource[i],0, i+1);			
		}
		this.tablemodel.setValueAt("各进程需要最大资源",1, 0);
		for(int i = 0; i < n;i++)
		{
			this.tablemodel.setValueAt("进程"+(i+1),i+2,0);			
		}
		
		for(i = 0;i<n;i++)
		{
			for(j = 0;j < 3;j++)
				this.tablemodel.setValueAt(claim[i][j],i+2,j+1);		
		}
		this.tablemodel.setValueAt("各进程占用系统资源分布",n+2, 0);
		for(int i = 0; i < n;i++)
		{
			this.tablemodel.setValueAt("进程"+(i+1),i+n+3,0);			
		}
		
		for(i = 0;i < n;i++)
		{
			for(j = 0;j < 3;j++)
				this.tablemodel.setValueAt(allocation[i][j],i+n+3,j+1);		
		}
		
		this.tablemodel.setValueAt("已使用资源",n*2+3, 0);
		for(i = 0;i < 3;i++)
		{
			this.tablemodel.setValueAt(useall[i],n*2+3,i+1);		
		}
		this.tablemodel.setValueAt("剩余资源",n*2+4, 0);
		for(i = 0;i<3;i++)
		{
			this.tablemodel.setValueAt(available[i],n*2+4,i+1);		
		}
		
		this.tablemodel.setValueAt("各进程仍需资源如下",n*2+5, 0);
		
		for(int i = 0; i < n;i++)
		{
			this.tablemodel.setValueAt("进程"+(i+1),n*2+6+i,0);			
		}
		for(i = 0;i<n;i++)
		{
			for(j = 0;j < 3;j++)
				this.tablemodel.setValueAt(pneed[i][j],n*2+6+i,j+1);		
		}
	
	}
	
	//系统资源量
	int systemresource()
	{
		int systemresources;
		systemresources = (int) (Math.random()*10%10+20);
		return systemresources;
	}
	
	//已使用资源量
	int usedresource()
	{
		int usedresources;
		usedresources = (int) (Math.random()*10%3+3);
		return usedresources;
	}
	
	//各进程所需最大资源
	int maxresource()
	{
		int maxresources;
		maxresources = (int) (Math.random()*10%3+6);
		return maxresources;
	}
	
	public static void main(String[] args) {
		new InsertBankerAlgorithm();
	}
	
}

