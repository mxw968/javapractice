package mypackage;
import mypackage.MyInteger;

public class Input 
{
	public static String readLine() throws java.io.IOException
	{
		System.out.print("����һ���ַ������Իس����з�������");
		byte buffer[]=new byte[512];
		int count=System.in.read(buffer);
		System.in.close();
		return(count==-1)?null:new String(buffer,0,count-2);
	}
	
	public static void main(String[] args)throws java.io.IOException
	{
		String s=readLine();
		int value=MyInteger.parseInt(s);
		System.out.println("MyInteger.toString("+value+",2)="+MyInteger.toString(value,2));
		System.out.println("MyInteger.toString("+value+",16)="+MyInteger.toString(value,16));
		
	}

}
