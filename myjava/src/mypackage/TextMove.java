package mypackage;
import javax.swing.*;
public class TextMove 
{
	JLabel lab;
    int i;
    String str;

	public TextMove() 
	{
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("TextMove");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200);
        str = "I'm moving...";
        lab = new JLabel(str);
        frame.add(lab);
        frame.setVisible(true);
        i=0;
        while(true)
        {
                           
            str = "        "+str;           
            try
            {
                Thread.sleep(1000);
                lab.setText(str);
            }
            catch(InterruptedException e)
            {
            }
        }
	}
	public static void main(String args[])
    {
        new TextMove();
    }

}
