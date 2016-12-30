package mypackage;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;

public class HtmlJFrame extends JFrame implements ActionListener,ChangeListener
{
	private URL urls[];
	private JComboBox<String>combox;
	private JTextField text;
	private JTabbedPane tab;  //选项卡窗格，每页显示一个文件
	private String charset = "GBK";

	public HtmlJFrame() 
	{
		super("查看指定URL的Web页编辑器及HTML文档");
		this.setBounds(300,240,640,480);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		String urls[] = {"http://www.phei.com.cn","http://www.baidu.com","http://www.google.com","file://localhost/D:/Program Files/Java/docs/api/index.html"};
		this.combox = new JComboBox<String>(urls);
		this.combox.setEditable(true);
		this.getContentPane().add(this.combox,"North");
		this.combox.addActionListener(this);
		this.getContentPane().add(this.tab = new JTabbedPane());
		this.tab.addChangeListener(this);
		this.getContentPane().add(this.text = new JTextField(),"South");	
		this.setVisible(true);
		this.urls = new URL[20];
		this.combox.setSelectedIndex(0);
	}

	public void actionPerformed(ActionEvent ev)
	{
		String urlname = (String)combox.getSelectedItem();
		int i = tab.getTabCount();
		try
		{
			this.urls[i] = new URL(urlname);
			String filename = this.urls[i].toString();
			for(int j = i-1;j >=0; j--)
			{
				if(this.tab.getTitleAt(j).equals(filename))
				{
					this.tab.setSelectedIndex(j);
					return;
				}
			}
			
			JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			split.setOneTouchExpandable(true);
			split.setDividerLocation(200);
			split.add(new JScrollPane(new JEditorPane(this.urls[i])));
			JTextArea text_content = new JTextArea();
			split.add(new JScrollPane(text_content));
			this.tab.addTab(filename, split);
			this.tab.setSelectedIndex(this.tab.getTabCount() - 1);
			this.readFrom(this.urls[i],this.charset,text_content);
		}
		
		catch(MalformedURLException murle)
		{
			JOptionPane.showMessageDialog(this,"字符串指定未知协议错误");
		}
		
		catch(FileNotFoundException fe)
		{
			JOptionPane.showMessageDialog(this,"\""+urls[i].getFile()+"\"文件不存在");
		}
		catch(IOException ioex)
		{
			JOptionPane.showMessageDialog(this,"IO错,读取"+urls[i].getFile()+"文件不成功");
		}
	}
	
	public void stateChanged(ChangeEvent ev)
	{
		this.charset = "GBK";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		int i = tab.getSelectedIndex();
		String protocol = urls[i].getProtocol();
		String str = "协议:" + protocol + ", ";
		try
		{
			if(protocol.equalsIgnoreCase("file"))
			{
				File file = new File(urls[i].getFile());
				text.setText(str + "本机:" + InetAddress.getLocalHost().toString()  + ", 文件: " + file.length() + "B, 修改日期:" + sdf.format(new Date(file.lastModified())));
			} 
			
			if(protocol.equalsIgnoreCase("http"))
			{
				URLConnection urlconn = urls[i].openConnection();
				String content = urlconn.getContentType();
				int j = content.indexOf("charset=");
				if(j != -1)
					charset = content.substring(j + 8);
				text.setText(str + "主机: " + urls[i].getHost() + ", 端口:"+ urls[i].getDefaultPort() + ", IP地址: " + InetAddress.getByName(urls[i].getHost()).getHostAddress() + ", 文件: " + urls[i].getFile() + ", 长度:" + urlconn.getContentLength() + "B,类型:" + urlconn.getContentType() + ", 字符集:" + charset + ", 修改日期:" + sdf.format(new Date(urlconn.getLastModified())));
			}
		}
		catch(UnknownHostException ioe)
		{
			JOptionPane.showMessageDialog(this, "找不到指定主机的IP地址");
		}
		
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(this,"IO错, 读取" + urls[i].getFile() + "文件不成功");
		}
	}
	
	public void readFrom(URL url,String charset,JTextArea text)throws IOException
	{
		InputStreamReader ir = new InputStreamReader(url.openStream(),charset);
		BufferedReader br = new BufferedReader(ir);
		String line = null;
		while((line = br.readLine()) != null)
			text.append(line+"\r\n");
		br.close();
		ir.close();
	}
	public static void main(String[] args) 
	{
		new HtmlJFrame();

	}

}
