package Main;

import DB.AcmDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;
public class CHoice {
    static  public JPanel choice() { //每周选人
       JPanel choice = new JPanel();
        Random random = new Random();
        int num = AcmDB.count(1);//获取总人数
        int t[] = new int[7];//获得随机编号
     Set<Integer> set = new LinkedHashSet<Integer>();
     if(AcmDB.count(1)<7) //当人员数小于7
     {
      for (int i = 0; i < 7; i++) {
       t[i] = random.nextInt(num);
       //System.out.println(t[i]);
      }
     }
    else
     {
      while (set.size()!=7)
      { int te=random.nextInt(num);
         // System.out.println(te);
          set.add(te);
      }

      int k=0;
         for (Integer i:set)
         {
             t[k++]=i;
         }
     }
    // System.out.println(set);
        //获得人员 姓名键值对
        List<AcmDB.KeyValuePair> list = AcmDB.login("姓名", 1);
        //选人面板
        choice.setLayout(new GridLayout(8, 1));
        //标签
        JLabel jLabel = new JLabel("每周题单讲题人员");
        jLabel.setHorizontalAlignment(0);
        choice.add(jLabel);
        //创建按钮
        JButton btn1 = new JButton("周日：" + list.get(t[0]).second);
        JButton btn2 = new JButton("周一：" + list.get(t[1]).second);
        JButton btn3 = new JButton("周二：" + list.get(t[2]).second);
        JButton btn4 = new JButton("周三：" + list.get(t[3]).second);
        JButton btn5 = new JButton("周四：" + list.get(t[4]).second);
        JButton btn6 = new JButton("周五：" + list.get(t[5]).second);
        JButton btn7 = new JButton("周六：" + list.get(t[6]).second);
        //随机数不重复值

        //选人面板中添加按钮
        choice.add(btn1);
        choice.add(btn2);
        choice.add(btn3);
        choice.add(btn4);
        choice.add(btn5);
        choice.add(btn6);
        choice.add(btn7);
        return choice;
    }

}


