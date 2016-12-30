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
	private JTabbedPane tab;  //ѡ�����ÿҳ��ʾһ���ļ�
	private String charset = "GBK";

	public HtmlJFrame() 
	{
		super("�鿴ָ��URL��Webҳ�༭����HTML�ĵ�");
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
			JOptionPane.showMessageDialog(this,"�ַ���ָ��δ֪Э�����");
		}
		
		catch(FileNotFoundException fe)
		{
			JOptionPane.showMessageDialog(this,"\""+urls[i].getFile()+"\"�ļ�������");
		}
		catch(IOException ioex)
		{
			JOptionPane.showMessageDialog(this,"IO��,��ȡ"+urls[i].getFile()+"�ļ����ɹ�");
		}
	}
	
	public void stateChanged(ChangeEvent ev)
	{
		this.charset = "GBK";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		int i = tab.getSelectedIndex();
		String protocol = urls[i].getProtocol();
		String str = "Э��:" + protocol + ", ";
		try
		{
			if(protocol.equalsIgnoreCase("file"))
			{
				File file = new File(urls[i].getFile());
				text.setText(str + "����:" + InetAddress.getLocalHost().toString()  + ", �ļ�: " + file.length() + "B, �޸�����:" + sdf.format(new Date(file.lastModified())));
			} 
			
			if(protocol.equalsIgnoreCase("http"))
			{
				URLConnection urlconn = urls[i].openConnection();
				String content = urlconn.getContentType();
				int j = content.indexOf("charset=");
				if(j != -1)
					charset = content.substring(j + 8);
				text.setText(str + "����: " + urls[i].getHost() + ", �˿�:"+ urls[i].getDefaultPort() + ", IP��ַ: " + InetAddress.getByName(urls[i].getHost()).getHostAddress() + ", �ļ�: " + urls[i].getFile() + ", ����:" + urlconn.getContentLength() + "B,����:" + urlconn.getContentType() + ", �ַ���:" + charset + ", �޸�����:" + sdf.format(new Date(urlconn.getLastModified())));
			}
		}
		catch(UnknownHostException ioe)
		{
			JOptionPane.showMessageDialog(this, "�Ҳ���ָ��������IP��ַ");
		}
		
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(this,"IO��, ��ȡ" + urls[i].getFile() + "�ļ����ɹ�");
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
