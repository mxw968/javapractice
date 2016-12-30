package mypackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


public class UserJFrame  extends JFrame  implements ActionListener,ItemListener
{
    private int number=1;        //���    
    private JTextField text_number, text_name;
                       //��š������ı���
    private JRadioButton radiobutton_male, radiobutton_female;       //�Ա�ť
    private JComboBox combobox_province, combobox_city;              //ʡ�ݡ�������Ͽ�
    private JButton button_add;       //��Ӱ�ť
    private JTextArea text_user;      //�ı���
    public UserJFrame()
    {  super("�����û���Ϣ");
        
        this.setSize(360,200);
        this.setLocation(300,240);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                //�������ڹرհ�ťʱ��������������
        this.getContentPane().setLayout(new GridLayout(1,2));        
                   //���񲼾֣�1��2�У����ҷָ�����
        
        text_user = new JTextArea(); 
                                //�����ı���
        this.getContentPane().add(text_user); 
                       //ռ�ݴ�����벿��
        JPanel panel = new JPanel(new GridLayout(6,1));         //������񲼾֣�6��1��
        this.getContentPane().add(panel);  
                          //ռ�ݴ����Ұ벿��
        
        text_number = new JTextField("1"); 
                          //����ı���
        text_number.setEditable(false);  
                            //���ɱ༭������Զ�����
        panel.add(text_number);

        text_name = new JTextField("����");
        panel.add(text_name);
        JPanel panel_radiobutton = new JPanel(new GridLayout(1,2));  //��ѡ��ť����壬���񲼾֣�1��2��
        panel.add(panel_radiobutton);

        ButtonGroup buttongroup = new
                             ButtonGroup();      //��ť��
        radiobutton_male = new JRadioButton(
                             "��",true);       //������ѡ��ť
        buttongroup.add(radiobutton_male); 
                                  //��ѡ��ť��ӵ���ť��
            panel_radiobutton.add(radiobutton_male);  
                                //��ѡ��ť��ӵ������
            
         radiobutton_female = new JRadioButton("Ů");
         buttongroup.add(radiobutton_female);
         panel_radiobutton.add(radiobutton_female);

         Object province[]={"����ʡ", "�㽭ʡ"};
         combobox_province = new JComboBox(province);                 //ʡ����Ͽ�
         combobox_province.setEditable(true); 
                               //���ÿɱ༭��Ĭ�ϲ��ɱ༭ 
         combobox_province.addItemListener(this);  
                        //ע����Ͽ��ѡ���¼�������
         panel.add(combobox_province);

         Object city[]={"�Ͼ���", "������", "������"};
         combobox_city = new JComboBox(city);
                               //������Ͽ�
         panel.add(combobox_city);
         button_add = new JButton("���");
         button_add.addActionListener(this);
         panel.add(button_add);

         this.setVisible(true);
     }

     public void itemStateChanged(ItemEvent e)
       //����Ͽ�������б���ѡ��������ʱ����ִ��
     {              //ʵ��ItemListener�ӿ��еķ���
         if (combobox_province.getSelectedIndex()==0)
                     //��ʡ����Ͽ���ѡ����"����ʡ"
         {  combobox_city.removeAllItems();  
                         //���������Ͽ���ԭ��������
             combobox_city.addItem("�Ͼ���"); 
                         //������Ͽ����������
             combobox_city.addItem("������");
             combobox_city.addItem("������");
         }
         
         if (combobox_province.getSelectedIndex()==1)                 //ѡ����"�㽭ʡ"
         {
             combobox_city.removeAllItems();
             combobox_city.addItem("������");
             combobox_city.addItem("������");
             combobox_city.addItem("������");
         }
     }

     public static void main(String[] args)
     {
    	 new UserJFrame();
     }
}