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
		super("���м��㷨ȫ�Զ���ʾ");
		this.setSize(350,450);
		this.setLocationRelativeTo(getOwner()); 
		
		
				
		//���鶨��
		pneed = new int[n][3];
		claim = new int[n][3];
		allocation = new int[n][3];
		resource = new int[3];
		available = new int[3];
		
		//�ı�������
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
		content.append("*****���м��㷨ȫ�Զ���ʾ*****\n");
		content.append("**********************************\n");
		content.append("��������������������\n");
		content.append("__________________________\n");
		content.append("|             |�Ŵ���|��ͼ��|��ӡ��\n");
		for(i = 0;i<n;i++)
		{
			content.append("| ����"+(i+1)+"  |  ");
			for(j = 0;j < 3;j++)
				content.append(claim[i][j]+"       |      ");
			content.append("\n");
			
		}
		content.append("��ǰ����ռ��ϵͳ��Դ�ֲ�����\n");
		content.append("__________________________\n");
		content.append("|                        |�Ŵ���|��ͼ��|��ӡ��\n");
		content.append("|ϵͳ��Դ����|    "+resource[0]+"   |    "+resource[1]+"     |    "+resource[2]+"\n");
		for(i = 0;i<n;i++)
		{
			content.append("|����"+(i+1)+"              |      ");
			for(j = 0;j<3;j++)
			{
				content.append(allocation[i][j]+"      |  ");
			}
			content.append("\n");
			content.append("__________________________\n");
		}
		
		content.append("|��ʹ��            |    "+useall[0]+"    |    "+useall[1]+"    |    "+useall[2]+"    |    "+"\n");
		content.append("__________________________\n");
		content.append("|ʣ����Դ      |  "+available[0]+"  |  "+available[1]+"  |  "+available[2]+"  |  "+"\n");
		content.append("__________________________\n");
		if(available[0]<0||available[1]<0||available[2]<0)
		{
			content.append("���벻��\n");
		}
		else
		{
			content.append("������������Դ����\n");
			content.append("__________________________\n");
			content.append("|            |�Ŵ���|��ͼ��|��ӡ��\n");
			for(i = 0;i<n;i++)
			{
				content.append("| ����"+(i+1)+"  |     ");
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
					content.append("�ź���aΪ��"+a+"\n");
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
					content.append("��ǰϵͳ����ȫ��\n");
					content.append("����"+(i+1)+"�������뵽��Դ\n");
					content.append("����"+(i+1)+"���н��������ͷ�ϵͳ��Դ\n");
					content.append("��ǰʣ����Դ�������\n");
					content.append("|        |�Ŵ���|��ͼ��|��ӡ��\n");
					content.append("ʣ����Դ     |"+available[0]+" | "+available[1]+"|"+available[2]+"|"+"\n");
					content.append("__________________________\n");
					a=0;
				}
				else
				{
					content.append("����"+(i+1)+"����ʧ��\n");
					c++;
				}
				a=0;
				if(c==n)
					content.append("��ǰϵͳ���벻��ȫ\n");
				if(available[0]==resource[0]&&available[1]==resource[1]&&available[2]==resource[2])
					content.append("��ʾ����\n");
			}
		

		this.getContentPane().add(new JScrollPane(content));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
	//ϵͳ��Դ��
	int systemresource()
	{
		int systemresources;
		systemresources = (int) (Math.random()*10%10+20);
		return systemresources;
	}
	
	//��ʹ����Դ��
	int usedresource()
	{
		int usedresources;
		usedresources = (int) (Math.random()*10%3+3);
		return usedresources;
	}
	
	//���������������Դ
	int maxresource()
	{
		int maxresources;
		maxresources = (int) (Math.random()*10%3+6);
		return maxresources;
	}
	
}



