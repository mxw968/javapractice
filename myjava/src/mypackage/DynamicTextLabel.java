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
        setTitle("文字的跑马灯效果");// 设置窗体的标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗体时的动作
        setBounds(100, 100, 400, 70);// 设置窗体的位置和大小
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 25));// 设置面板的边框
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));// 设置面板的布局
        
        label = new JLabel("百度空间--shensuiliunian");// 创建标签
        contentPane.add(label);
        
        new Thread(new DynamicThread()).start();// 启动新线程
    }
    
    private class DynamicThread implements Runnable 
    {// 使用内部类完成标签移动操作
        
        @Override
        public void run() 
        {
            while (true) 
            {
                for (int i = 0; i < 400; i++) 
                {
                    try 
                    {
                        Thread.sleep(10);// 线程休眠0.01秒
                    } 
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                    label.setLocation(i, 5);// 移动标签
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

