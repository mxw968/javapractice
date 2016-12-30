package mypackage;

public class IntArray {
	public static int[] random(int n,int max)
	{
		int value[]=new int[n];
		for(int i=0;i<value.length;i++)
			value[i]=(int)(Math.random()*max);
		return value;
	}
	public static void print(int value[])
	{
		for(int i=0;i<value.length;i++)
			System.out.print(" "+value[i]);
		System.out.println();
	}
	public static void selectsort(int value[])
	{
		for(int i=0;i<value.length-1;i++)
		{
			int min=1;
			for(int j=i;j<value.length;j++)
				if(value[j]<value[min])
					min=j;
			if(i!=min)
			{
				int temp=value[i];
				value[i]=value[min];
				value[min]=temp;
			}
		}
	}
	
	public static int[] merge(int X[],int Y[])
	{
		int Z[]=new int[X.length+Y.length],i=0,j=0,k=0;
		while(i<X.length&&j<Y.length)
		{
			if(X[i]<Y[j])
				Z[k++]=X[i++];
			else
				Z[k++]=Y[j++];
		}
		while(i<X.length)
			Z[k++]=X[i++];
		while(j<Y.length)
			Z[k++]=Y[j++];
		return Z;
	}
	
	public static void main(String[] args) {
		int n1=7,max1=100;
		int table1[]=random(n1,max1);
		System.out.print("table1:");
		print(table1);
		selectsort(table1);
		int table2[]=random(6,100);
		System.out.print("value:");
		print(table2);
		selectsort(table2);
		System.out.print("sorted value:");
		print(table2);
		System.out.print("merge:");
		print(merge(table1,table2));
	}

}
