package mypackage;

public class NewTest
{
	public static void main(String[] args)
	{
		int i,j,sum=0,asum=0;
		for (i=0;i<6;i++)
		{
			for(j=0;j<=6-i;j++)
			{
				sum+=j;
			}
				
			
		}
			
		System.out.println("sum="+sum);
	}
}
