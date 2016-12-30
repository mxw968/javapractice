package mypackage;

import java.util.Scanner;

import mypackage.MyDate;

public class BankAccount 
{
	private int account;
	private double balance;    //账号 身份证 余额 
	private long id;
	private MyDate date;               //开户日期 
	private String name;               //储户姓名
	public BankAccount(int account,long id,int balance,MyDate date,String name) //构造函数
	{
		this.set(account,id,balance,date,name);
		System.out.println("开户成功！");
	}
	
	public void set(int account,long id,double balance,MyDate date,String name)  //成员函数实现开户
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
			System.out.println("很抱歉您的余额不足。");
	}
	public void finalize()
	{
		System.out.println("该账户被注销。");
	}
	
	public void Check()
	{
		
	}
	public String  toString()         //输出储户信息
	{
		return "储户账号: "+account+"\n储户姓名: "+name+"\n开户时间: "+(date==null?"":date.toString())+"\n储户身份证号码: "+id+"\n存款余额: "+balance;
	}

	public static void main(String[] args) 
	{
		BankAccount b1=new BankAccount(100001,320611199507232131l,0,new MyDate(2016,3,25),"兰馨");
		
		//Scanner input=new Scanner(System.in);
		System.out.println("请问您需要存款的金额为？ ");
		//System.out.println("请输入储户姓名: ");
		b1.Deposit();
		System.out.println("请问您需要取款的金额为？ ");
		b1.Withdraw();
		

		//System.out.println("开户成功，储户信息如下: ");
		//System.out.println("\n姓名:"+name);*/
		System.out.println(b1.toString());
	}

}
