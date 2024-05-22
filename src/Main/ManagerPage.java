package Main;

import DB.AcmDB;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerPage {
    public ManagerPage(String s)
    {   //框架
        JFrame jf = new JFrame("管理员界面");
        //表头
        Object[] titles = {"姓名","账号","职位","密码","洛谷","Codeforces","Vjudge","注册时间","最后上线时间"};
        //人数
        int num =AcmDB.count(1);
        //表格内容
        Object[][] data = new Object[num] [9];
        List<Object>list = new ArrayList<Object>();
        list = AcmDB.show(s,3);//获取数据库人员全部信息
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<9;j++) {
                data[i][j] = list.get(i*9+j);
            }
        }
        //框架布局
        jf.setLayout(new GridLayout(1,2));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setSize(1600,900);
        jf.setLocationRelativeTo(null);
        //左面板
        JPanel ljPanel = new JPanel();
        ljPanel.setLayout(new GridLayout(2,1));
        //标签
        List<Object> user_info = new ArrayList<Object>();
        user_info=AcmDB.show(s,2);
        JLabel jLabel1 = new JLabel("姓名："+user_info.get(0));
        JLabel jLabel2 = new JLabel("职位："+user_info.get(3));
        //面板封装
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        //左面板加入
        ljPanel.add(jPanel);
        ljPanel.add(CHoice.choice());
        //表格性质
        JTable jTable = new JTable(data,titles);

        jf.add(ljPanel);
        jf.add(new JScrollPane(jTable));

        jf.setVisible(true);
    }
}
