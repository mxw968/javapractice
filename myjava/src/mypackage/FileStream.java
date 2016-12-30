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
			JOptionPane.showMessageDialog(null,filename1+"\"�ļ������ڣ����ܸ��ơ�");
		}
		
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null,"IO�쳣,����\""+"\"�ļ�δ�ɹ�");
		}
	}
}
