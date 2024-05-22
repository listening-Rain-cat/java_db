package Main;

import DB.AcmDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistManager {
    String M_name,M_user,M_pass,M_post;
    RegistManager()
    {
        //框架
        JFrame jfRegist = new JFrame("注册");//名
        jfRegist.setBounds(1000,400,640,850);//宽高，相对位置
        jfRegist.setLayout(new GridLayout(7,1));//定位
        jfRegist.setLocationRelativeTo(null);//居中
        jfRegist.setResizable(false);//不可鼠标拖动
        jfRegist.setVisible(true);//显示
        //标签
        JLabel name = new JLabel("              姓名 ");
        JLabel user = new JLabel("              账号 ");
        JLabel password = new JLabel("              密码 ");
        JLabel repassword = new JLabel("再次输入密码");
        JLabel post = new JLabel("              职位 ");
        JLabel key =new JLabel("              密钥 ");
        //文本框
        JTextField tName = new JTextField(20);
        JTextField tUser = new JTextField(20);
        JTextField tPass = new JTextField(20);
        JTextField tRpass = new JTextField(20);
        JTextField tPost = new JTextField(20);
        JTextField tKey = new JTextField(20);

        //面板
        JPanel pName = new JPanel();
        pName.add(name);
        pName.add(tName);
        JPanel pUser = new JPanel();
        pUser.add(user);
        pUser.add(tUser);
        JPanel pPass = new JPanel();
        pPass.add(password);
        pPass.add(tPass);
        JPanel pRpass = new JPanel();
        pRpass.add(repassword);
        pRpass.add(tRpass);
        JPanel pPost = new JPanel();
        pPost.add(post);
        pPost.add(tPost);
        JPanel pKey = new JPanel();
        pKey.add(key);
        pKey.add(tKey);

        //向框架填面板
        jfRegist.add(pName);
        jfRegist.add(pUser);
        jfRegist.add(pPass);
        jfRegist.add(pRpass);
        jfRegist.add(pPost);
        jfRegist.add(pKey);

        //按钮
        JButton ok = new JButton("确定");
        JButton no = new JButton("取消");
        JPanel jPanelButt =new JPanel();
        jPanelButt.add(ok);
        jPanelButt.add(no);
        jfRegist.add(jPanelButt);
        //确定按钮交互
        ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //输入框有空
                if(tName.getText().isEmpty()||tUser.getText().isEmpty()||tPass.getText().isEmpty()||tPost.getText().isEmpty()||tKey.getText().isEmpty()||tRpass.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "请输入完整信息");
                else {
                    //判断两次密码是否相同
                    if(!(tPass.getText().equals(tRpass.getText())))
                        JOptionPane.showMessageDialog(null, "密码输入不同");
                    //判断密钥是否正确
                    else if(!tKey.getText().equals("key"))
                    {
                        JOptionPane.showMessageDialog(null, "密钥错误");
                    }
                    else
                    {   //判断账号是否重复
                        if(AcmDB.find(tUser.getText(),2))
                        {
                            JOptionPane.showMessageDialog(null, "该账号名已存在");
                        }
                        else
                        //调用工具 进行注册
                        {
                            AcmDB.regist(tUser.getText(), tName.getText(), tPass.getText(), tRpass.getText(), tPost.getText(), "", "","", "",2);
                            JOptionPane.showMessageDialog(null, "注册成功");
                        }
                    }
                }
            }
        });
        //取消按钮交互
        no.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfRegist.setVisible(false);
                new See();
            }
        });
    }

}
