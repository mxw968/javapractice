package mypackage;

import java.io.*;

public class FileStream_byte1 
{
	public static void main(String[] args) throws IOException
	{
		String filename = "fileStream.byte";
		FileOutputStream fout = new FileOutputStream(filename);
		fout.write(-1);
		fout.close();
		
		FileInputStream fin = new FileInputStream(filename);
		int i;
		while ((i=fin.read())!=-1)
			System.out.print(" "+i);
		fin.close();
	}

}
