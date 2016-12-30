package mypackage;

import java.util.Scanner;

import mypackage.MyDate;

public class BankAccount 
{
	private int account;
	private double balance;    //�˺� ���֤ ��� 
	private long id;
	private MyDate date;               //�������� 
	private String name;               //��������
	public BankAccount(int account,long id,int balance,MyDate date,String name) //���캯��
	{
		this.set(account,id,balance,date,name);
		System.out.println("�����ɹ���");
	}
	
	public void set(int account,long id,double balance,MyDate date,String name)  //��Ա����ʵ�ֿ���
	{
		this.account=account;
		this.id=id;
		this.balance=balance;
		this.date=date;
		this.name=name==null?"":name;
	}
	
	public void Deposit()
	{
		Scanner input=new Scanner(System.in);
		double money=input.nextDouble();
		balance+=money;
		
	}
	
	public void Withdraw()
	{
		Scanner input=new Scanner(System.in);
		double money=input.nextDouble();
		balance-=money;
		if(balance<0)
			System.out.println("�ܱ�Ǹ�������㡣");
	}
	public void finalize()
	{
		System.out.println("���˻���ע����");
	}
	
	public void Check()
	{
		
	}
	public String  toString()         //���������Ϣ
	{
		return "�����˺�: "+account+"\n��������: "+name+"\n����ʱ��: "+(date==null?"":date.toString())+"\n�������֤����: "+id+"\n������: "+balance;
	}

	public static void main(String[] args) 
	{
		BankAccount b1=new BankAccount(100001,320611199507232131l,0,new MyDate(2016,3,25),"��ܰ");
		
		//Scanner input=new Scanner(System.in);
		System.out.println("��������Ҫ���Ľ��Ϊ�� ");
		//System.out.println("�����봢������: ");
		b1.Deposit();
		System.out.println("��������Ҫȡ��Ľ��Ϊ�� ");
		b1.Withdraw();
		

		//System.out.println("�����ɹ���������Ϣ����: ");
		//System.out.println("\n����:"+name);*/
		System.out.println(b1.toString());
	}

}
