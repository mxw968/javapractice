package mypackage;

import java.awt.*;

public class LoginFrame extends Frame
{   
	public LoginFrame() 
	{
		super("User Login");
		this.setSize(200,130);
		this.setLocation(300,240);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new FlowLayout());
		
		this.add(new Label("UserID"));
		this.add(new TextField("user1",10));
		this.add(new Label("Password"));
		this.add(new TextField(10));
		this.add(new Button("OK"));
		this.add(new Button("Cancel"));
		this.setVisible(true);
		
	}

	public static void main(String[] args) 
	{
		new LoginFrame();

	}

}
