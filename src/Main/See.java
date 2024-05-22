package Main;

import DB.AcmDB;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
 public  class See  {
   //按钮选择
   int option=0;
    public  See() {
        //框架界面属性
        JFrame jFrame = new JFrame("哈尔滨理工大学荣成校区ACM协会");
        jFrame.setBounds(1000,400,640,850);
        jFrame.setLayout(new FlowLayout());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        //图片
        JPanel jp0 = new JPanel();
        JLabel jl0 =new JLabel(new ImageIcon("src/Main/QQ图片20231218181945.jpg"));
        jp0.add(jl0);
        //单选按钮
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton jRadioButton = new JRadioButton("协会成员");
        JRadioButton jRadioButton1 = new JRadioButton("协会管理员");
        buttonGroup.add(jRadioButton);
        buttonGroup.add(jRadioButton1);
        //账号面板
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("账号");
        JTextField username = new JTextField(52);
        jp1.setLayout(new FlowLayout());
        jp1.add(jl1);
        jp1.add(username);
        //密码面板
        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("密码");
        JPasswordField password = new JPasswordField(52);
        password.setEchoChar('*');
        jp2.setLayout(new FlowLayout());
        jp2.add(jl2);
        jp2.add(password);
        //按钮组件
        JButton login = new JButton("登录");
        JButton regist = new JButton("注册");
        JPanel jp3 = new JPanel();
        jp3.setLayout(new FlowLayout());
        jp3.add(login);
        jp3.add(regist);
        //想框架加入面板组件
        jFrame.add(jp0);
        jFrame.add(jRadioButton);
        jFrame.add(jRadioButton1);
        jFrame.add(jp1);
        jFrame.add(jp2);
        jFrame.add(jp3);

        jFrame.setAlwaysOnTop(false);
        jFrame.setVisible(true);
        //协会成员按钮交互
        //选择 协会成员或协会管理员
        jRadioButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option=1;

            }
        });
        jRadioButton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option=2;
            }
        });
        //option 为选择变量 0为没有选择 1为成员 2为管理原
        //登录
        login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取文本框信息
                String user= username.getText();
                String pass = password.getText();

                    boolean flag = false;
                    for (AcmDB.KeyValuePair i : AcmDB.login("密码",option)) {
                        if(i.second.equals(pass)&&i.first.equals(user)) {
                            flag=true;
                            break;
                        }
                    }

                //没有选择
                if(option==0)
                    JOptionPane.showMessageDialog(null, "请选择身份");
                //如果数据库与输入匹配 则登录成功
                if( flag&&option!=0) {
                    JOptionPane.showMessageDialog(null, "登录成功");
                    if(option==1)//成员界面
                    {
                        AcmDB.updata(user);
                        jFrame.setVisible(false);
                        new AcmerPage(user);
                    }
                    if(option==2)//管理员界面
                    {
                        jFrame.setVisible(false);
                        new ManagerPage(user);
                    }
                }
                //如果选择 账号密码错误
                else   if(option!=0)
                    JOptionPane.showMessageDialog(null, "账号或密码错误");


            }
        });
        //注册
        regist.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(option==1) {
                    jFrame.setVisible(false);
                    new RegistAcmer();
                }
                else {
                    if (option == 2) {
                        jFrame.setVisible(false);
                        new RegistManager();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "请选择注册身份");
                    }
                }

            }
        });




    }

}

