package mypackage;

import java.applet.Applet;
import java.awt.*;

public class MyTextField extends Applet
{ 
	private TextField message,passwordField,message2;
	private String password;
	public void init()
	{
		password = "zhengque";
		message = new TextField("Enter password:");
		message.setEditable(false);  //�����ı����Ƿ���Ա��޸�
		passwordField = new TextField(20);
		passwordField.setEchoCharacter('*'); //setEchoChar(char c) ������ʾ�ַ�Ϊ c
		message2 = new TextField(40);
		message2.setEditable(false);
		add(message);
		add(message2);
		add(passwordField);
		//this.setVisible(true);
	}

	public boolean action(Event e,Object o)
	{
		if(e.target == passwordField)
			if(e.arg.equals(password))
				message2.setText("������ȷ"); //setText(String s) �����ı�Ϊ s 
			else
				message2.setText("����������벻��ȷ");
		return true;
	}
	/*public static void main(String[] args) 
	{
		new MyTextField();

	}*/

}
