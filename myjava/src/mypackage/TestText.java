package mypackage;

import java.io.*;

public class TestText 
{

	public static void main(String[] args)
	{
		try
		{
			FileOutputStream f= new FileOutputStream("F:/test.txt");
			f.write("\r\n���,this is a demo".getBytes());
            f.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
