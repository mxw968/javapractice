package mypackage;

import java.net.*;
import java.io.*;

public class ChatTCPServer 
{

	public ChatTCPServer(int port,String name)throws IOException 
	{
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept();
		new ChatTCPSocketJFrame(name,client);
		server.close();
	}

	public static void main(String[] args)throws IOException
	{
		new ChatTCPServer(2001,"»¨ÏÉ×Ó");
	}

}
