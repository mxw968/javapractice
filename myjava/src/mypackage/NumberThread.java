package mypackage;

public class NumberThread extends Thread
{
	private int first;
	
	public NumberThread(String name,int first)
	{
		super(name);
		this.first = first;
	}
	
	public void run()
	{
		System.out.print("\n"+this.getName());
		for(int i = first; i < 50; i +=2)
			System.out.print(i+" ");
		System.out.println(this.getName()+"����! ");	
	}

	public static void main(String[] args) 
	{
		System.out.println("current Thread="+Thread.currentThread().getName());
		NumberThread thread_odd = new NumberThread("�����߳�",1);
		NumberThread thread_even = new NumberThread("ż���߳�",2);
		thread_odd.start();
		thread_even.start();
		System.out.println("activeCount="+Thread.activeCount());
	}

}
