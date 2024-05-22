package Main;
import DB.AcmDB;
import user.acmer;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class RegistAcmer {
    //用户数据
    String user_id,user_pass,user_repass,user_name,user_post,user_cfid,user_lgid,user_vjid,user_key;
    RegistAcmer()
    {   //框架
        JFrame jfRegist = new JFrame("注册");//名
        jfRegist.setBounds(1000,400,640,850);//宽高，相对位置
        jfRegist.setLayout(new GridLayout(10,1));//定位
        jfRegist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfRegist.setLocationRelativeTo(null);//居中
        jfRegist.setResizable(false);//不可鼠标拖动
        jfRegist.setVisible(true);//显示
        //标签
        JLabel id=new JLabel("学号");//2
        JLabel pass = new JLabel("密码");//2
        JLabel repass = new JLabel("再次输入密码");//6
        JLabel name =new JLabel("姓名");//2
        JLabel post = new JLabel("           职位       ");//2
        JLabel cf_id = new JLabel("Codeforces id ");//13
        JLabel lg_id = new JLabel("        洛谷 id     ");//5
        JLabel vj_id = new JLabel("        Vjudge id   ");//9
        //JLabel man = new JLabel("    管理员密钥    ");//5

        //文本输入框
        JTextField jTF_id=new JTextField(20);
        JPanel jPanel_id = new JPanel();
        jPanel_id.add(id);
        jPanel_id.add(jTF_id);

        JTextField jTF_name= new JTextField(20);
        JPanel jPanel_name = new JPanel();
        jPanel_name.add(name);
        jPanel_name.add(jTF_name);

        JPanel jPanel3= new JPanel();
        jPanel3.add(jPanel_id);
        jPanel3.add(jPanel_name);
        jfRegist.add(jPanel3);

        JTextField jTF_pass= new JTextField(20);
        JPanel jPanel_pass = new JPanel();
        jPanel_pass.add(pass);
        jPanel_pass.add(jTF_pass);

        JTextField jTF_repass= new JTextField(20);
        JPanel jPanel_repass = new JPanel();
        jPanel_repass.add(repass);
        jPanel_repass.add(jTF_repass);

        JPanel jPanel2= new JPanel();
        jPanel2.add(jPanel_pass);
        jPanel2.add(jPanel_repass);
        jfRegist.add(jPanel2);


        JTextField jTF_post= new JTextField(20);
        JPanel jPanel_post = new JPanel();
        jPanel_post.add(post);
        jPanel_post.add(jTF_post);
        jfRegist.add(jPanel_post);

        JTextField jTF_cf_id= new JTextField(20);
        JPanel jPanel_cf_id = new JPanel();
        jPanel_cf_id.add(cf_id);
        jPanel_cf_id.add(jTF_cf_id);
        jfRegist.add(jPanel_cf_id);

        JTextField jTF_lg_id= new JTextField(20);
        JPanel jPanel_lg_id = new JPanel();
        jPanel_lg_id.add(lg_id);
        jPanel_lg_id.add(jTF_lg_id);
        jfRegist.add(jPanel_lg_id);

        JTextField jTF_vj_id= new JTextField(20);
        JPanel jPanel_vj_id = new JPanel();
        jPanel_vj_id.add(vj_id);
        jPanel_vj_id.add(jTF_vj_id);
        jfRegist.add(jPanel_vj_id);

//        JTextField jTF_man= new JTextField(20);
//        JPanel jPanel_man= new JPanel();
//        jPanel_man.add(man);
//        jPanel_man.add(jTF_man);
//        jfRegist.add(jPanel_man);
        //按钮
        JButton ok = new JButton("确定");
        JButton no = new JButton("取消");
        JPanel jPanelButt =new JPanel();
        jPanelButt.add(ok);
        jPanelButt.add(no);
        jfRegist.add(jPanelButt);
        //确定按钮交互
        ok.addActionListener(new AbstractAction() {
            int num=0;//输入文本框个数
            acmer user= new acmer();
            @Override
            public void actionPerformed(ActionEvent e) {
                user_id=jTF_id.getText();
                user_name=jTF_name.getText();
                user_pass=jTF_pass.getText();
                user_repass=jTF_repass.getText();
                user_post=jTF_post.getText();
                user_cfid=jTF_cf_id.getText();
                user_lgid=jTF_lg_id.getText();
                user_vjid=jTF_vj_id.getText();
                //判断文本框是否填写完整
                if(user_id.isEmpty()||user_pass.isEmpty()||user_repass.isEmpty()||user_post.isEmpty()||user_cfid.isEmpty()||user_lgid.isEmpty()||user_vjid.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入完整信息");
                }
                //如文本框填写完整 判断两次密码是否相同
               else {
                    if (!(user_repass.equals(user_pass)))
                        JOptionPane.showMessageDialog(null, "密码输入不同");
                        //判断是否账号已存在
                    else {
                        if (AcmDB.find(user_id, 1) && user_pass.equals(user_repass)) {

                            JOptionPane.showMessageDialog(null, "该账号已存在");
                        }
                        //调用工具 进行注册
                        else {
                            AcmDB.regist(user_id, user_name, user_pass, user_repass, user_post, user_cfid, user_lgid, user_vjid, "", 1);
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
