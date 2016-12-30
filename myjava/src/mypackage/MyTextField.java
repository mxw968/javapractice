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
		message.setEditable(false);  //设置文本框是否可以被修改
		passwordField = new TextField(20);
		passwordField.setEchoCharacter('*'); //setEchoChar(char c) 设置显示字符为 c
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
				message2.setText("密码正确"); //setText(String s) 设置文本为 s 
			else
				message2.setText("您输入的密码不正确");
		return true;
	}
	/*public static void main(String[] args) 
	{
		new MyTextField();

	}*/

}
