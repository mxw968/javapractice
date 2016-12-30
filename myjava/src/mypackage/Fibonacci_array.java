package mypackage;

public class Fibonacci_array {

	public static void main(String[] args) {
		int n=25,i,fib[]=new int[n];
		fib[0]=0;
		fib[1]=1;
		for(i=2;i<n;i++)
			fib[i]=fib[i-1]+fib[i-2];
		for(i=0;i<fib.length;i++)
			System.out.print(" "+fib[i]);
		//System.out.println();
	}

}
