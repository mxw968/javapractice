package mypackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


public class UserJFrame  extends JFrame  implements ActionListener,ItemListener
{
    private int number=1;        //编号    
    private JTextField text_number, text_name;
                       //编号、姓名文本行
    private JRadioButton radiobutton_male, radiobutton_female;       //性别按钮
    private JComboBox combobox_province, combobox_city;              //省份、城市组合框
    private JButton button_add;       //添加按钮
    private JTextArea text_user;      //文本区
    public UserJFrame()
    {  super("输入用户信息");
        
        this.setSize(360,200);
        this.setLocation(300,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                //单击窗口关闭按钮时，结束程序运行
        this.getContentPane().setLayout(new GridLayout(1,2));        
                   //网格布局，1行2列，左右分隔窗口
        
        text_user = new JTextArea(); 
                                //创建文本区
        this.getContentPane().add(text_user); 
                       //占据窗口左半部分
        JPanel panel = new JPanel(new GridLayout(6,1));         //面板网格布局，6行1列
        this.getContentPane().add(panel);  
                          //占据窗口右半部分
        
        text_number = new JTextField("1"); 
                          //编号文本行
        text_number.setEditable(false);  
                            //不可编辑，编号自动生成
        panel.add(text_number);

        text_name = new JTextField("姓名");
        panel.add(text_name);
        JPanel panel_radiobutton = new JPanel(new GridLayout(1,2));  //单选按钮子面板，网格布局，1行2列
        panel.add(panel_radiobutton);

        ButtonGroup buttongroup = new
                             ButtonGroup();      //按钮组
        radiobutton_male = new JRadioButton(
                             "男",true);       //创建单选按钮
        buttongroup.add(radiobutton_male); 
                                  //单选按钮添加到按钮组
            panel_radiobutton.add(radiobutton_male);  
                                //单选按钮添加到子面板
            
         radiobutton_female = new JRadioButton("女");
         buttongroup.add(radiobutton_female);
         panel_radiobutton.add(radiobutton_female);

         Object province[]={"江苏省", "浙江省"};
         combobox_province = new JComboBox(province);                 //省份组合框
         combobox_province.setEditable(true); 
                               //设置可编辑，默认不可编辑 
         combobox_province.addItemListener(this);  
                        //注册组合框的选择事件监听器
         panel.add(combobox_province);

         Object city[]={"南京市", "苏州市", "无锡市"};
         combobox_city = new JComboBox(city);
                               //城市组合框
         panel.add(combobox_city);
         button_add = new JButton("添加");
         button_add.addActionListener(this);
         panel.add(button_add);

         this.setVisible(true);
     }

     public void itemStateChanged(ItemEvent e)
       //在组合框的下拉列表中选择数据项时触发执行
     {              //实现ItemListener接口中的方法
         if (combobox_province.getSelectedIndex()==0)
                     //在省份组合框中选择了"江苏省"
         {  combobox_city.removeAllItems();  
                         //清除地区组合框中原所有内容
             combobox_city.addItem("南京市"); 
                         //地区组合框添加数据项
             combobox_city.addItem("苏州市");
             combobox_city.addItem("无锡市");
         }
         
         if (combobox_province.getSelectedIndex()==1)                 //选择了"浙江省"
         {
             combobox_city.removeAllItems();
             combobox_city.addItem("杭州市");
             combobox_city.addItem("宁波市");
             combobox_city.addItem("温州市");
         }
     }

     public static void main(String[] args)
     {
    	 new UserJFrame();
     }
}