package login;

import com.soldier.dao.UserDao;
import com.soldier.dao.impl.UserDaoImpl;
import com.soldier.entitys.User;
import views.admin.Admin_Main;
import views.student.Student_Main;
import views.teacher.Teacher_Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField name;  //用户名文本框
    private JPasswordField psw;  //密码文本框
    private UserDao userDao;

    public Login() {

        //登录窗口设置
        setSize(380,300);//窗口大小
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);//退出
        setTitle("Login");//标题

        JPanel panelMain = new JPanel();
        getContentPane().add(panelMain, BorderLayout.CENTER);
        //主面板 网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙
        panelMain.setLayout(new GridLayout(6, 1, 5, 2));

        JPanel panel1_0 = new JPanel();
        JLabel welcome = new JLabel("欢迎");
        welcome.setFont(new Font("Dialog", 1, 26));  //设置字体
        welcome.setForeground(Color.red);  //设置颜色
        panel1_0.add(welcome);
        panelMain.add(panel1_0);

        JPanel panel_1 = new JPanel();
        panelMain.add(panel_1);
        JLabel lblNewLabel = new JLabel("用户名");
        panel_1.add(lblNewLabel);
        name = new JTextField();
        panel_1.add(name);
        name.setColumns(10);

        JPanel panel_2 = new JPanel();
        panelMain.add(panel_2);
        JLabel label = new JLabel("密    码");
        panel_2.add(label);
        psw = new JPasswordField();
        panel_2.add(psw);
        psw.setColumns(10);

        //操作
        JPanel panel_6 = new JPanel();
        panelMain.add(panel_6);
        JButton isLogin = new JButton("登录");
        panel_6.add(isLogin);
        JButton noLogin = new JButton("重置");
        panel_6.add(noLogin);

        //显示用户信息
        JPanel panel_7 = new JPanel();
        getContentPane().add(panel_7, BorderLayout.SOUTH);
        JLabel adminInfo = new JLabel(" ");
        panel_7.add(adminInfo);

        //登录验证
        isLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                userDao = new UserDaoImpl();
                User user = null;
                String userName = name.getText();
                String passWord = String.valueOf(psw.getPassword());
                try {
                   user = userDao.login(userName,passWord);
                } catch (Exception e1) {
                    System.out.println("获取用户信息失败！无法登陆");
                    e1.printStackTrace();
                }
                if (user != null) {
                    if(userName.equals("") || passWord.equals("")){
                        JOptionPane.showMessageDialog(null, "用户名或密码不允许为空");
                        return;
                    }
                    if(userName.equals(user.getUserName()) && passWord.equals(user.getPassWord())){
                        if (user.getUserType() == 1) {  //管理员
                            new Admin_Main().setVisible(true);
                        }else if (user.getUserType() == 2) {
                            new Teacher_Main(user.getUserName()).setVisible(true);
                        } else {
                            new Student_Main(user.getUserName()).setVisible(true);
                        }
                        JOptionPane.showMessageDialog(null, "登录成功");
                        dispose();//关闭登录页面
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "用户名或密码错误");
                }
            }
        });

        //重置按钮的事件
        noLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                psw.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}

