package mypackage;

import java.io.File;
import java.io.*;

public class PrefixExtFileFilter implements FileFilter
{
	private String prefix = "",extension = "";

	public PrefixExtFileFilter(String filterstr)
	{
		// TODO Auto-generated constructor stub
		filterstr = filterstr.toLowerCase();
		int i = filterstr.indexOf('*');
		if(i > 0)
			this.prefix = filterstr.substring(0,i);
		i = filterstr.lastIndexOf('.');
		if(i > 0)
		{
			this.extension = filterstr.substring(i + 1);
			if(this.extension.equals("*"))
				this.extension = "";
		}
	}
	
	public PrefixExtFileFilter()
	{
		this("");
	}
	
	public boolean accept(File file)
	{
		if(!file.isFile())
			return false;
		String filename = file.getName().toLowerCase();
		return filename.startsWith(this.prefix) && filename.endsWith(this.extension);
	}

}
