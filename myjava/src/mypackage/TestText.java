package mypackage;

import java.io.*;

public class TestText 
{

	public static void main(String[] args)
	{
		try
		{
			FileOutputStream f= new FileOutputStream("F:/test.txt");
			f.write("\r\nÄãºÃ,this is a demo".getBytes());
            f.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
