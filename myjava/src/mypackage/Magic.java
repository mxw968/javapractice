package mypackage;

public class Magic {

	public static void main(String[] args) {
	int n=3,mat[][]=new int[n][n];
	int i=0,j=n/2;
	for(int k=1;k<n*n;k++)
	{
		mat[i][j]=k;
		if(k%n==0)
			i=(i+1)%n;
		else
		{
			i=(i-1+n)%n;
			j=(j+1)%n;
		}
	}
	for(i=0;i<mat.length;i++)
	{
		for(j=0;j<mat[i].length;j++)
			System.out.print(mat[i][j]+"\t");
		System.out.println();
	}
	}

}
