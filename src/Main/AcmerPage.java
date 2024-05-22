package Main;

import DB.AcmDB;

import java.awt.*;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
public class AcmerPage {
    AcmerPage(String s)
    {   //框架
        JFrame frame = new JFrame("人员界面");
        frame.setBounds(300,100,1500,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //布局 两列 左侧基本信息与交互 右侧 折线图与每周题单
        frame.setLayout(new GridLayout(1,2));
        //定义左右面板
        JPanel ljPanel =new JPanel();
        JPanel rjPanel= new JPanel();

        //右面板布局
        rjPanel.setLayout(new GridLayout(2,1));

        //右面板添加 选人与折线图
        rjPanel.add(CHoice.choice());
        rjPanel.add( AcmerData.tu(s));

        //左面板布局
        ljPanel.setLayout(new GridLayout(10,1));
        //标签
        JLabel welcome = new JLabel("欢迎使用算法协会小程序");
        welcome.setHorizontalAlignment(0);
        List<Object> user_info = new ArrayList<Object>();
        user_info=AcmDB.show(s,1);
        JLabel name = new JLabel("姓名："+user_info.get(0));
        JLabel post = new JLabel("职位："+user_info.get(2));
        JLabel lg = new JLabel("洛谷ID："+user_info.get(4));
        JLabel cf = new JLabel("CFID："+user_info.get(5));
        JLabel vj = new JLabel("VJID："+user_info.get(6));
        JLabel regist_time = new JLabel("注册时间："+user_info.get(7));
        JLabel login_tim = new JLabel("上线时间："+user_info.get(8));
        //标签全部值中
        name.setHorizontalAlignment(0);
        post.setHorizontalAlignment(0);
        lg.setHorizontalAlignment(0);
        cf.setHorizontalAlignment(0);
        vj.setHorizontalAlignment(0);
        regist_time.setHorizontalAlignment(0);
        login_tim.setHorizontalAlignment(0);
        //左面版加入标签
        ljPanel.add(welcome);
        ljPanel.add(name);
        ljPanel.add(post);
        ljPanel.add(lg);
        ljPanel.add(cf);
        ljPanel.add(vj);
        ljPanel.add(regist_time);
        ljPanel.add(login_tim);
        //修改信息按钮
        JButton change = new JButton("修改个人信息");
        ljPanel.add(change);
        change.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new ChangePage(s);
            }
        });
        //框架加入左右面板
        frame.add(ljPanel);
        frame.add(rjPanel);
        frame.setVisible(true);

    }
}
