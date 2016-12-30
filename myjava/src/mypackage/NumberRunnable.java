package mypackage;

public class NumberRunnable implements Runnable
{
	private int first;
	public NumberRunnable(int first)
	{
		this.first = first;
	}
	public void run()
	{
		System.out.println();
		for(int i = first; i < 50; i +=2)
			System.out.print(i+" ");
		System.out.println("����! ");	
	}
	
	public static void main(String[] args)
	{
		NumberRunnable target = new NumberRunnable(1);
		Thread thread_odd = new Thread(target,"�����߳�");
		thread_odd.start();
		new Thread(new NumberRunnable(2),"ż���߳�").start();
	}

}
