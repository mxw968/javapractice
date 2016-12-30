package mypackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class DynamicTextLabel extends JFrame
{
	private static final long serialVersionUID = -2035256617544621708L;
    private JPanel contentPane;
    private JLabel label;
    

    public static void main(String[] args) 
    {
        try 
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch (Throwable e) 
        {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() 
        {
            public void run()
            {
                try
                {
                    DynamicTextLabel frame = new DynamicTextLabel();
                    frame.setVisible(true);
                } 
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public DynamicTextLabel() 
    {
        setTitle("���ֵ������Ч��");// ���ô���ı���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ���ʱ�Ķ���
        setBounds(100, 100, 400, 70);// ���ô����λ�úʹ�С
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 25));// �������ı߿�
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));// �������Ĳ���
        
        label = new JLabel("�ٶȿռ�--shensuiliunian");// ������ǩ
        contentPane.add(label);
        
        new Thread(new DynamicThread()).start();// �������߳�
    }
    
    private class DynamicThread implements Runnable 
    {// ʹ���ڲ�����ɱ�ǩ�ƶ�����
        
        @Override
        public void run() 
        {
            while (true) 
            {
                for (int i = 0; i < 400; i++) 
                {
                    try 
                    {
                        Thread.sleep(10);// �߳�����0.01��
                    } 
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                    label.setLocation(i, 5);// �ƶ���ǩ
                    if (i % 20 == 0) {
                        int r = new Random().nextInt(256);
                        int g = new Random().nextInt(256);
                        int b = new Random().nextInt(256);
                        
                        label.setForeground(new Color(r, g, b));
                        
                    }
                }
            }
        }
    }
}

