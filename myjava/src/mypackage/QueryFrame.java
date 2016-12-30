package mypackage;

import java.awt.*;
import java.awt.event.*;

public class QueryFrame extends Frame implements ActionListener
{
	private TextField text_char,text_uni;
	private Button button_char,button_uni;
	public QueryFrame()
	{
		super("Unicode×Ö·û²éÑ¯Æ÷");
		this.setBounds(300,240,280,90);
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(2,3,2,2));
		this.add(new Label("×Ö·û",Label.RIGHT));
		text_char=new TextField("ºº×Ö",10);
		this.add(text_char);
		button_char=new Button("²éÑ¯UnicodeÂë");
		this.add(button_char);
		button_char.addActionListener(this);
		this.add(new Label("Unicode±àÂë",Label.RIGHT));
		text_uni=new TextField(10);
		this.add(text_uni);
		button_uni=new Button("²éÑ¯×Ö·û");
		this.add(button_uni);
		button_uni.addActionListener(this);
		this.addWindowListener(new WinClose());
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==button_char)
		{
			String str=text_char.getText();
			if(str.equals(""))
				return;
			char ch=str.charAt(0);
			text_char.setText(""+ch);
			text_uni.setText(""+(int)ch);
		}
		else if(ev.getSource()==button_uni)
		{
			String str=text_uni.getText();
			if(str.equals(""))
				return;
			int uni=Integer.parseInt(str);
			text_char.setText(""+(char)uni);
		}
	}

	public static void main(String[] args) 
	{
		new QueryFrame();
	}
	
	class WinClose implements WindowListener
	{
		public void windowClosing(WindowEvent ev)
		{
			System.exit(0);
		}
		
		public void windowOpened(WindowEvent ev){}
		public void windowActivated(WindowEvent ev){}
		public void windowDeactivated(WindowEvent ev){}
		public void windowClosed(WindowEvent ev){}
		public void windowIconified(WindowEvent ev){}
		public void windowDeiconified(WindowEvent ev){}
	}

}
