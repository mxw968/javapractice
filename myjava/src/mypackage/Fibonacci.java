package mypackage;

public class Fibonacci {
	
	public static void main(String[] args) {
		short i=0,j=1;
		do
		{
			System.out.print(" "+i+" "+j);
			i=(short)(i+j);
			j=(short)(i+j);
		}while(i>0);
		System.out.println();
		
	}

}
