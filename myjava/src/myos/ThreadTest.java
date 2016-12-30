package myos;

class IsPrime extends Thread
{
	int n;
	public IsPrime(int n)
	{
		this.n = n;
	}
	public void run()
	{
		boolean is = true;
		for(int i =2;i<n;i++)
		{
			if(n%i==0)
			{
				is=false;
				break;
			}
		}
		System.out.println(n+(is?"是素数":"不是素数"));
	}
}

class Sort extends Thread
{
	int a[],len;
	public Sort()
	{
		a = new int[10];
		len = 10;
		for(int i = 0;i<=9;i++)
		{
			a[i] = (int)(Math.random()*100);
		}
	}
	
	public void run()
	{
		boolean exchanged = true;
		for(int i = 1;i<len&&exchanged;i++)
		{
			exchanged = false;
			for(int j = 0;j<len-i-1;j++)
			{
				if(a[j]>a [j+1])
				{
					int t=a [j];
					a [j] = a [j+1];
					a [j+1] =t;
					exchanged = true;
				}
			}
			for(int j =0;j<len;j++)
			{
				System.out.println(a [j]+" ");
			}
			System.out.println();
		}
	}
}
public class ThreadTest 
{
	public static void main(String[] args) {
		Thread t1 = new IsPrime(1234567);
		Thread t2 = new Sort();
		t1.start();
		t2.start();
		t2.setPriority(8);
		System.out.println("当前共有"+t2.activeCount()+"个线程");
	}
}