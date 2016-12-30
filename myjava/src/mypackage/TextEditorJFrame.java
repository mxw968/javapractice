package mypackage;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import mypackage.ExtensionFileFilter;

public class TextEditorJFrame extends EditorJFrame
{
	private File file;
	protected JFileChooser fchooser;
	public TextEditorJFrame(File file)
	{
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.fchooser = new JFileChooser(new File("唐诗",""));
		this.fchooser.setFileFilter(new ExtensionFileFilter("文本文件(*.txt)","txt"));
		this.file = file;
		if(file == null)
			this.file = new File("");
		this.readFrom(this.file,this.text);
		this.setTitle("文本编辑器    "+this.file.getName());
	}

	public TextEditorJFrame()
	{
		this(new File(""));
	}

	public void readFrom(File file,JTextArea text)
	{
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			text.setText("");
			String s;
			while((s=br.readLine())!=null)
				text.append(s+"\r\n");
			br.close();
			fr.close();
		}
		
		catch(IOException ioex){}
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		super.actionPerformed(ev);
		actionMenuItem(ev);
	}
	
	protected void actionMenuItem(ActionEvent ev)
	{
		String mitem = ev.getActionCommand();
		if(mitem.equals("新建"))
		{
			this.file = new File("");
			this.setTitle("文本编辑器    ");
			this.text.setText("");
			return;
		}
		
		if(mitem.equals("打开") && fchooser.showOpenDialog(this) == 0)
		{
			this.file = fchooser.getSelectedFile();
			this.setTitle("文本编辑器    "+this.file.getName());
			this.readFrom(this.file,this.text);
			return;
		}
		
		if(mitem.equals("保存") && !this.file.getName().equals(""))
			this.writeTo(this.file,this.text);
		else if((mitem.equals("保存") && this.file.getName().equals("") || mitem.equals("另存为")) && fchooser.showSaveDialog(this) == 0)
		{
			this.file = fchooser.getSelectedFile();
			if(!file.getName().endsWith(".txt"))
				this.file = new File(this.file.getAbsolutePath()+".txt");
			this.writeTo(this.file,this.text);
			this.setTitle("文本编辑器     "+this.file.getName());
		}
	}
	
	public void writeTo(File file,JTextArea text)
	{
		try
		{
			FileWriter fw = new FileWriter(file);
			fw.write(text.getText());
			fw.close();
		}
		
		catch(IOException ioex)
		{
			JOptionPane.showMessageDialog(this,"IO错,写入\""+file.getName()+"\"文件不成功");
		}
	}
	
	public static void main(String[] args)
	{
		new TextEditorJFrame();
	}
}
