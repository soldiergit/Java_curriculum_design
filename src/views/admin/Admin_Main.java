package views.admin;

import com.soldier.dao.UserDao;
import com.soldier.dao.impl.UserDaoImpl;
import com.soldier.entitys.User;
import login.Login;
import views.admin.JPanel.StudentList;
import views.admin.JPanel.TeacherList;
import views.admin.JPanel.addUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Main extends JFrame {

    private JPanel panelMain = new JPanel(new BorderLayout());  //创建内容面 包容器，指定使用 边界布局
    private final CardLayout layout = new CardLayout(5,5); // 创建卡片布局，卡片间水平和竖直间隔为 10
    private User user;
    private UserDao userDao = new UserDaoImpl();

    private TeacherList teacher = new TeacherList();
    private StudentList student = new StudentList();

    public Admin_Main() {
        //登录窗口设置
        setSize(1000,600);//窗口大小
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);//退出
        setTitle("学生端");//标题

        JMenuBar menuBar = new JMenuBar();  //创建菜单条
        JButton homeBtn = new JButton("返回首页");
        JButton teacherBtn = new JButton("教师列表");
        JButton studentBtn = new JButton("学生列表");
        JButton addTeaBtn = new JButton("添加教师");
        JButton addStuBtn = new JButton("添加学生");
        JButton outLogin = new JButton("退出登录");

        menuBar.add(homeBtn);               //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));  //设置菜单(JMenu)间距
        menuBar.add(teacherBtn);
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(studentBtn);
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(addTeaBtn);
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(addStuBtn);
        menuBar.add(Box.createHorizontalStrut(350));
        menuBar.add(outLogin);              //将按钮添加到菜单条

        JPanel panel1 = new JPanel();
        panel1.add(menuBar);
        panelMain.add(panel1,BorderLayout.NORTH);  //主容器的北边

        JPanel cardPanel = new JPanel(layout);  //卡片布局，一次只能看一张卡片
        panelMain.add(cardPanel,BorderLayout.CENTER);  //将主容器的中心设为卡片布局

        JPanel panel2_1 = new JPanel();
        JLabel welcome = new JLabel("尊敬的管理员，您好！");
        welcome.setFont(new Font("Dialog", 1, 26));  //设置字体
        welcome.setForeground(Color.red);  //设置颜色
        panel2_1.add(welcome);

        cardPanel.add(panel2_1,"welcome");
        cardPanel.add(teacher,"teacherList");  //教师列表
        cardPanel.add(student,"studentList");  //学生列表
        cardPanel.add(new addUser(2),"addTeacher");  //添加老师
        cardPanel.add(new addUser(3),"addStudent");  //添加学生

        /**
         * 设置菜单项的单击事件
         */
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(cardPanel,"welcome");
            }
        });
        teacherBtn.addActionListener(new ActionListener() {  //教师列表
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看教师列表");
                teacher.initShow(teacherBtn,layout,cardPanel);
                teacher.updateTable();
                layout.show(cardPanel,"teacherList");
            }
        });
        studentBtn.addActionListener(new ActionListener() {  //学生列表
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("学生列表");
                student.initShow(studentBtn,layout,cardPanel);
                student.updateTable();
                layout.show(cardPanel,"studentList");
            }
        });
        addTeaBtn.addActionListener(new ActionListener() {  //添加老师
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加老师");
                layout.show(cardPanel,"addTeacher");
            }
        });
        addStuBtn.addActionListener(new ActionListener() {  //添加学生
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("添加学生");
                layout.show(cardPanel,"addStudent");
            }
        });
        outLogin.addActionListener(new ActionListener() {  //退出登录
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(result == JOptionPane.OK_OPTION){
                    dispose();//关闭当前页面
                    new Login().setVisible(true);
                }
            }
        });
        setContentPane(panelMain);  //将主容器添加到窗口
    }
}
