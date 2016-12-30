package mypackage;

import java.io.*;
import javax.swing.JOptionPane;

public class FileStream 
{
	public static void copy(String filename1,String filename2)
	{
		try
		{
			FileInputStream fin = new FileInputStream(filename1);
			FileOutputStream fout = new FileOutputStream(filename2);
			byte buffer[] = new byte[512];
			int count = 0;
			while((count = fin.read(buffer)) != -1)
				fout.write(buffer,0,count);
			fin.close();
			fout.close();
		}
		
		catch(FileNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null,filename1+"\"文件不存在，不能复制。");
		}
		
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null,"IO异常,复制\""+"\"文件未成功");
		}
	}
}
