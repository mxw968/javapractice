package myos;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AutoBankerAlgorithm extends JFrame implements ActionListener
{
	private JTextField fti;
	private JTextArea content;
	private static int n = 3;
	private int i,j,a=0,c=0;
	private int pneed[][],claim[][],allocation[][],resource[],available[],useall[]={0,0,0};
	private JButton auto,insert;
	public AutoBankerAlgorithm()
	{
		super("银行家算法全自动显示");
		this.setSize(350,450);
		this.setLocationRelativeTo(getOwner()); 
		
		
				
		//数组定义
		pneed = new int[n][3];
		claim = new int[n][3];
		allocation = new int[n][3];
		resource = new int[3];
		available = new int[3];
		
		//文本区定义
		content = new JTextArea(100,10);
		content.setLineWrap(true);
		content.setWrapStyleWord(true);
		
		for(i = 0;i < n ;i++)
		{
			resource[i]=this.systemresource();
			for(j = 0; j < n;j++)
			{
				claim[i][j] = this.maxresource();
				allocation[i][j] = this.usedresource();
			}			
		}
		allocation[0][0] = 2;
		allocation[0][1] = 5;
		
		for(j = 0;j<n;j++)
		{
			for(i = 0;i<n;i++)
			{
				useall[j] = useall[j] + allocation[i][j];
				pneed[i][j] = claim[i][j] - allocation[i][j];
			}
			available[j] = resource[j] - useall[j];
		}
		
		content.append("**********************************\n");
		content.append("*****银行家算法全自动演示*****\n");
		content.append("**********************************\n");
		content.append("各进程最大需求情况如下\n");
		content.append("__________________________\n");
		content.append("|             |磁带机|绘图仪|打印机\n");
		for(i = 0;i<n;i++)
		{
			content.append("| 进程"+(i+1)+"  |  ");
			for(j = 0;j < 3;j++)
				content.append(claim[i][j]+"       |      ");
			content.append("\n");
			
		}
		content.append("当前进程占用系统资源分布如下\n");
		content.append("__________________________\n");
		content.append("|                        |磁带机|绘图仪|打印机\n");
		content.append("|系统资源总数|    "+resource[0]+"   |    "+resource[1]+"     |    "+resource[2]+"\n");
		for(i = 0;i<n;i++)
		{
			content.append("|进程"+(i+1)+"              |      ");
			for(j = 0;j<3;j++)
			{
				content.append(allocation[i][j]+"      |  ");
			}
			content.append("\n");
			content.append("__________________________\n");
		}
		
		content.append("|已使用            |    "+useall[0]+"    |    "+useall[1]+"    |    "+useall[2]+"    |    "+"\n");
		content.append("__________________________\n");
		content.append("|剩余资源      |  "+available[0]+"  |  "+available[1]+"  |  "+available[2]+"  |  "+"\n");
		content.append("__________________________\n");
		if(available[0]<0||available[1]<0||available[2]<0)
		{
			content.append("申请不当\n");
		}
		else
		{
			content.append("各进程仍需资源如下\n");
			content.append("__________________________\n");
			content.append("|            |磁带机|绘图仪|打印机\n");
			for(i = 0;i<n;i++)
			{
				content.append("| 进程"+(i+1)+"  |     ");
				for(j = 0;j<3;j++)
				{
					content.append(pneed[i][j]+"     |   ");
					
				}
				content.append("\n");
				content.append("__________________________\n");
			}
			for(i = 0;i<n;i++)
			{
				for(j=0;j<3;j++)
				{
					if(pneed[i][j]<=available[j])
						a++;
					else
						a=0;
					content.append("信号量a为："+a+"\n");
				}
				if(a==3)
				{
					for(j=0;j<3;j++)
					{
						available[j] = available[j]+allocation[i][j];
						pneed[i][j] = 100;
						if(available[j]>resource[j])
							available[j]=resource[j];
					}
					content.append("当前系统请求安全！\n");
					content.append("进程"+(i+1)+"可以申请到资源\n");
					content.append("进程"+(i+1)+"运行结束，并释放系统资源\n");
					content.append("当前剩余资源情况如下\n");
					content.append("|        |磁带机|绘图仪|打印机\n");
					content.append("剩余资源     |"+available[0]+" | "+available[1]+"|"+available[2]+"|"+"\n");
					content.append("__________________________\n");
					a=0;
				}
				else
				{
					content.append("进程"+(i+1)+"申请失败\n");
					c++;
				}
				a=0;
				if(c==n)
					content.append("当前系统申请不安全\n");
				if(available[0]==resource[0]&&available[1]==resource[1]&&available[2]==resource[2])
					content.append("演示结束\n");
			}
		

		this.getContentPane().add(new JScrollPane(content));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
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
	
}



