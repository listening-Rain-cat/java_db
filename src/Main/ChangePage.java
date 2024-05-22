package Main;

import DB.AcmDB;
//import com.mysql.cj.xdevapi.DeleteStatement;
//import com.orsoncharts.data.JSONUtils;
//import user.acmer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangePage {
    ChangePage(String s)
    {
        JFrame jFrame = new JFrame("更改信息");
        jFrame.setBounds(1000,400,740,250);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        //设置布局
        jFrame.setLayout(new FlowLayout());
        //标签
        JLabel name =new JLabel("姓名");
        name.setHorizontalAlignment(0);
        JLabel pass =new JLabel("密码");
        pass.setHorizontalAlignment(0);
        JLabel repass = new JLabel("再次输入密码");
        repass.setHorizontalAlignment(0);
        JLabel post =new JLabel("职位");
        post.setHorizontalAlignment(0);
        JLabel lg = new JLabel("洛谷 id");
        lg.setHorizontalAlignment(0);
        JLabel cf = new JLabel("Codeforces id");
        cf.setHorizontalAlignment(0);
        JLabel vg =new JLabel("Vjudge id");
        vg.setHorizontalAlignment(0);
        //文本输入
        JTextField jt_n = new JTextField(30);
        JTextField jt_pa = new JTextField(30);
        JTextField jt_repa = new JTextField(30);
        JTextField jt_po = new JTextField(30);
        JTextField jt_lg = new JTextField(30);
        JTextField jt_cf = new JTextField(30);
        JTextField jt_vg = new JTextField(30);
        //面板
        JPanel jPanel1= new JPanel();
        jPanel1.setLayout(new GridLayout(1,2));
        jPanel1.add(name);
        jPanel1.add(jt_n);

        JPanel jPanel2 =new JPanel();
        jPanel2.setLayout(new GridLayout(1,2));
        jPanel2.add(pass);
        jPanel2.add(jt_pa);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new GridLayout(1,2));
        jPanel3.add(repass);
        jPanel3.add(jt_repa);

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new GridLayout(1,2));
        jPanel4.add(post);
        jPanel4.add(jt_po);

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout(new GridLayout(1,2));
        jPanel5.add(lg);
        jPanel5.add(jt_lg);

        JPanel jPanel6 = new JPanel( );
        jPanel6.setLayout(new GridLayout(1,2));
        jPanel6.add(cf);
        jPanel6.add(jt_cf);

        JPanel jPanel7 =new JPanel();
        jPanel7.setLayout(new GridLayout(1,2));
        jPanel7.add(vg);
        jPanel7.add(jt_vg);

        //按钮
        JButton ok = new JButton("确定");
        JButton no = new JButton("取消");
        JPanel jPanelButt =new JPanel();
        jPanelButt.add(ok);
        jPanelButt.add(no);

        //框架加入面板
        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.add(jPanel6);
        jFrame.add(jPanel7);
        jFrame.add(jPanelButt);
        //确定按钮交互
        ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  user_name =jt_n.getText();
                String  user_pa =jt_pa.getText();
                String user_repass =jt_repa.getText();
                String user_po =jt_po.getText();
                String user_lg=jt_lg.getText();
                String user_cf =jt_cf.getText();
                String user_vg =jt_vg.getText();
                boolean flag= false;
                if(user_name.isEmpty()||user_pa.isEmpty()||user_repass.isEmpty()||user_po.isEmpty()||user_lg.isEmpty()||user_cf.isEmpty()||user_vg.isEmpty())
                    flag=true;
                if(flag)
                    JOptionPane.showMessageDialog(null, "请输入完整信息 ");
                else   if(!user_pa.equals(user_repass)&&!flag)
                    JOptionPane.showMessageDialog(null, "密码输入不同");
                     else {
                    AcmDB.change(s, user_name, user_pa, user_po, user_lg, user_cf, user_vg, 1);
                    JOptionPane.showMessageDialog(null, " 修改成功");
                }


            }
        });
        //取消按钮交互
        no.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new AcmerPage(s);
            }
        });

    }

}
