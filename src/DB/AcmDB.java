package DB;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class Sql {

    static String SELECT(String s)
    {
        return "";
    }

    static String INSERT(String s)
    {
        return "";
    }
    static String UPDATA(String s)
    {
        return "";
    }
}

public class AcmDB {

    //操作数据库基本量
    static final String url = "jdbc:mysql://localhost:3306/acm";
    static final String username = "root";
    static final String password = "a5663322";
    static final String sql_select ="SELECT * FROM ";
    //加载驱动
    static  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println("JDBC驱动类加载失败");
        }

    }
    public static Connection getConnection(){
        try {
            //获取连接
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //做题数统计
    static public int [] userpass(String s)
    {
        Connection con=null;
        PreparedStatement  st =null;
        ResultSet rs =null;
        int num[] = new int[12];
        try {
            con = getConnection();
            st = con.prepareStatement("SELECT * FROM  information WHERE 学号=?");
            st.setString(1,s);
            rs = st.executeQuery();
            while(rs.next()) {
                num[0]=rs.getInt("一月份做题数");
                num[1]=rs.getInt("二月份做题数");
                num[2]=rs.getInt("三月份做题数");
                num[3]=rs.getInt("四月份做题数");
                num[4]=rs.getInt("五月份做题数");
                num[5]=rs.getInt("六月份做题数");
                num[6]=rs.getInt("七月份做题数");
                num[7]=rs.getInt("八月份做题数");
                num[8]=rs.getInt("九月份做题数");
                num[9]=rs.getInt("十月份做题数");
                num[10]=rs.getInt("十一月份做题数");
                num[11]=rs.getInt("十二月份做题数");

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            close(con,st,rs);
        }
        return num;
    }
    //展示信息
    static public List show(String s,int op)
    {
        Connection con=null;
        PreparedStatement  st =null;
        ResultSet rs =null;
        List<Object> list = new ArrayList<Object>();
        try
        {
            con = getConnection();
            //人员
            if(op==1) {
                st = con.prepareStatement("SELECT * FROM acmer WHERE 账号=?");
                st.setString(1, s);
                rs = st.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("姓名"));
                    list.add(rs.getString("账号"));
                    list.add(rs.getString("职位"));
                    list.add(rs.getString("密码"));
                    list.add(rs.getString("洛谷"));
                    list.add(rs.getString("Codeforces"));
                    list.add(rs.getString("Vjudge"));
                    list.add(rs.getTimestamp("注册时间"));
                    list.add(rs.getTimestamp("最后上线时间"));

                }

            }
            //管理员
            if(op==2)
            {
                st = con.prepareStatement("SELECT * FROM manager WHERE 账号=?");
                st.setString(1,s);
                rs=st.executeQuery();
                while (rs.next())
                {
                    list.add(rs.getString("姓名"));
                    list.add(rs.getString("账号"));
                    list.add(rs.getString("密码"));
                    list.add(rs.getString("职位"));
                }
                System.out.println(list);
            }
            //成员所有信息
            if(op==3)
            {
                st = con.prepareStatement("SELECT * FROM acmer");
                rs = st.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("姓名"));
                    list.add(rs.getString("账号"));
                    list.add(rs.getString("职位"));
                    list.add(rs.getString("密码"));
                    list.add(rs.getString("洛谷"));
                    list.add(rs.getString("Codeforces"));
                    list.add(rs.getString("Vjudge"));
                    list.add(rs.getTimestamp("注册时间"));
                    list.add(rs.getTimestamp("最后上线时间"));

                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            close(con,st,null);
        }
        return list;
    }
    //查找用户名是否相等
        static public boolean find(String user_id,int op) {
        Connection con=null;
        PreparedStatement  st =null;
        ResultSet rs =null;
        //是否存在
        boolean re_id =false;
        //身份选择
        String ops="";
        if(op==1)  ops="acmer";
        else if(op==2)ops="manager";
        try {
            con = getConnection();
            st = con.prepareStatement("SELECT 账号 FROM  "+ops);
            rs =st.executeQuery();
            Set<String> id_set = new HashSet<>();
            while(rs.next())
            {
                id_set.add(rs.getString("账号"));
            }
            if(id_set.contains(user_id))
                re_id=true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            close(con,st,rs);
        }
        return re_id;
    }
    //查找表里多少个人
    static public  int count(int op)
    {   int num=0;

        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        String s="";
        if(op==1)
            s="acmer";
        else if(op==2)
            s="manager";
        else s="information";

        try {
            connection =getConnection();
            statement = connection.prepareStatement("SELECT * FROM "+s);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                num++;
            }
        }
        catch (Exception e) {
            System.out.println("数据库链接失败");
        }
        finally {
            close(connection,statement,resultSet);
        }


        return num;


    }
    //统计人员做题平均数
    static public int[] arv()
    {
        int num []= new int[12];

        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int user=0;
        try {
            connection =getConnection();
            statement = connection.prepareStatement("SELECT * FROM information");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int m1=resultSet.getInt("一月份做题数");
                int m2=resultSet.getInt("二月份做题数");
                int m3=resultSet.getInt("三月份做题数");
                int m4=resultSet.getInt("四月份做题数");
                int m5=resultSet.getInt("五月份做题数");
                int m6=resultSet.getInt("六月份做题数");
                int m7=resultSet.getInt("七月份做题数");
                int m8=resultSet.getInt("八月份做题数");
                int m9=resultSet.getInt("九月份做题数");
                int m10=resultSet.getInt("十月份做题数");
                int m11=resultSet.getInt("十一月份做题数");
                int m12=resultSet.getInt("十二月份做题数");
                num[0]+=m1;
                num[1]+=m2;
                num[2]+=m3;
                num[3]+=m4;
                num[4]+=m5;
                num[5]+=m6;
                num[6]+=m7;
                num[7]+=m8;
                num[8]+=m9;
                num[9]+=m10;
                num[10]+=m11;
                num[11]+=m12;
                user++;
            }
        }

        catch (Exception e) {
            System.out.println("数据库链接失败");
        }
        finally {
            close(connection,statement,resultSet);
        }

        for(int i=0;i<12;i++)
            if(user!=0)
                num[i]/=user;
        return num;
    }
    //账号密码键值对
    static   public      class KeyValuePair {
        public String first;
        public Object second;
        public KeyValuePair() {
        }
     public    KeyValuePair(String s, String y) {
            first = s;
            second = y;
        }
    }
    //返回 账号密码 键值对队列
    static public List<KeyValuePair> login( String part,int op)
    {

        String ops;//身份选择
        if(op==1)  ops="acmer";
        else if(op==2)ops="manager";
        else ops="information";
        //定义键值对表
        List<KeyValuePair> list = new ArrayList<KeyValuePair>();
        //数据库各操作对象声明并制空
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection =getConnection();
            statement = connection.prepareStatement("SELECT * FROM "+ops);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String user = resultSet.getString("账号");
                String partname = resultSet.getString(part);
                list.add(new KeyValuePair(user, partname));
            }

        } catch (Exception e) {
            System.out.println("数据库链接失败");
        }
        finally {
            close(connection,statement,resultSet);
        }


        return list;
    }
    //更改
   public static void change(String user,String name ,String pass, String post,String lg,String cf,String vj, int op)
   {
       Connection con=null;
       PreparedStatement  st =null;

       try
       {

           con = getConnection();
         if(op==1) {
             st = con.prepareStatement("UPDATE acmer set 姓名 =?,密码=?,职位=?,洛谷=?,Codeforces=?,Vjudge=? WHERE 账号=?");
             st.setString(1,name);
             st.setString(2,pass);
             st.setString(3,post);
             st.setString(4,lg);
             st.setString(5,cf);
             st.setString(6,vj);
             st.setString(7,user);

         }
        else {
             st = con.prepareStatement("UPDATE manager set 姓名=?,密码=?,职位=? WHERE 账号=?");
             st.setString(1,name);
             st.setString(2,pass);
             st.setString(3,post);
             st.setString(4,user);
         }
        st.executeUpdate();
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
       finally {
           close(con,st,null);
       }
   }
   //删除
   public  static  boolean dele(String s,int op)
   {
       Connection connection=null;
       PreparedStatement statement=null;
       boolean flag=false;
       try {
           connection=getConnection();
           statement = connection.prepareStatement("DELETE FROM acmer WHERE 账号=?");
           statement.setString(1,s);
           flag= statement.execute();

       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
       finally {
           close(connection,statement,null);
       }
       return flag;
   }
   //更新人员登录时间
  static   public void updata(String id)
  {     Connection con=null;
        PreparedStatement  st =null;
        try
        {
            long time = System.currentTimeMillis();
            java.sql.Timestamp now = new java.sql.Timestamp(time);
           con = getConnection();
           st =con.prepareStatement("update acmer set 最后上线时间 =?  where 账号=?");
           st.setTimestamp(1,now);
           st.setString(2,id);
           st.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            close(con,st,null);
        }
    }
    //注册
   static public void regist(String id,String name,String password,String repass,String post,String cf,String lg ,String vj,String key,int op)
    {   Connection cn =null;
        PreparedStatement st =null;
        //成员注册
        if(op==1)
        {
            long time = System.currentTimeMillis();
            java.sql.Timestamp now = new java.sql.Timestamp(time);

           try {
               cn = getConnection();
               st = cn.prepareStatement("insert into acmer VALUES(?,?,?,?,?,?,?,?,?);");
               st.setString(1,name);
               st.setString(2,id);
               st.setString(3,post);
               st.setString(4,password);
               st.setString(5,lg);
               st.setString(6,cf);
               st.setString(7,vj);
               st.setTimestamp(8, now );
               st.setTimestamp(9,  now);
               st.executeUpdate();
               st=cn.prepareStatement("INSERT  into information VALUES (?,0,0,0,0,0,0,0,0,0,0,0,0)");
               st.setString(1,id);
               st.executeUpdate();
           }
           catch (SQLException e)
           {
               e.printStackTrace();
           }
           finally {
               close(cn,st,null);
           }
        }
        //管理员注册
         else
        {
            try {
                cn = getConnection();
                st = cn.prepareStatement("INSERT  into  manager VALUES (?,?,?,?)");
                st.setString(1,name);
                st.setString(2,id);
                st.setString(3,password);
                st.setString(4,post);
                st.executeUpdate();

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally {
                close(cn,st,null);
            }

        }

    }


    //关闭资源
   static public void close (Connection cn ,PreparedStatement st,ResultSet rs)
    {
        try {
            if (rs != null)
                rs.close();
            if(st!=null)
                st.close();
            if(cn!=null)
                cn.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
