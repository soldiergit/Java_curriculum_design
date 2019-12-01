package views.teacher;

import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Teacher;
import login.Login;
import views.teacher.JPanel.TeacherAdmissionStudent;
import views.teacher.JPanel.TeacherInfo;
import views.teacher.JPanel.ViewMySubject;
import views.teacher.JPanel.add_or_updateThisSubject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Teacher_Main extends JFrame {

    private JPanel panelMain = new JPanel(new BorderLayout());  //创建内容面 包容器，指定使用 边界布局
    private final CardLayout layout = new CardLayout(5,5); // 创建卡片布局，卡片间水平和竖直间隔为 10
    private TeacherDao teacherDao = new TeacherDaoImpl();
    private Teacher teacher;
    private ViewMySubject viewMySubject = new ViewMySubject();
    private TeacherAdmissionStudent teacherAdmissionStudent = new TeacherAdmissionStudent();

    public Teacher_Main(String teacherId) {
        try {
            teacher = teacherDao.findById(teacherId);
        } catch (SQLException e) {
            System.out.println("获取老师信息失败！");
            e.printStackTrace();
        }
        //登录窗口设置
        setSize(1000,600);//窗口大小
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);//退出
        setTitle("教师端");//标题

        JMenuBar menuBar = new JMenuBar();  //创建菜单条
        JButton homeBtn = new JButton("返回首页");
        JButton outLogin = new JButton("退出登录");
        outLogin.addActionListener(new ActionListener() {
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
        JMenu menuStudent = new JMenu("信息维护");  //创建菜单
        JMenuItem studentInfo = new JMenuItem("教师个人信息");  //菜单项
        studentInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));  //快捷键
        JButton isReleased = new JButton("已发布的选题");
        JButton addChoose = new JButton("添加选题");
        JButton admissionStudent = new JButton("录取学生");

        menuStudent.add(studentInfo);  //将菜单项添加到菜单
        menuBar.add(homeBtn);               //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));  //设置菜单(JMenu)间距
        menuBar.add(menuStudent);           //将菜单添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(isReleased);               //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));  //设置菜单(JMenu)间距
        menuBar.add(addChoose);               //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));  //设置菜单(JMenu)间距
        menuBar.add(admissionStudent);              //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(300));
        menuBar.add(outLogin);              //将按钮添加到菜单条

        JPanel panel1 = new JPanel();
        panel1.add(menuBar);
        panelMain.add(panel1,BorderLayout.NORTH);  //主容器的北边

        JPanel cardPanel = new JPanel(layout);  //卡片布局，一次只能看一张卡片
        panelMain.add(cardPanel,BorderLayout.CENTER);  //将主容器的中心设为卡片布局

        JPanel panel2_1 = new JPanel();
        JLabel welcome = new JLabel("欢迎您："+teacher.getTeacherName()+"老师");
        welcome.setFont(new Font("Dialog", 1, 26));  //设置字体
        welcome.setForeground(Color.red);  //设置颜色
        panel2_1.add(welcome);

        cardPanel.add(panel2_1,"welcome");
        cardPanel.add(new TeacherInfo(teacher),"teacherInfo");  //个人信息卡片
        cardPanel.add(viewMySubject,"viewMySubject");  //已发布选题的卡片
        cardPanel.add(teacherAdmissionStudent,"teacherAdmissionStudent");  //录取学生

        /** 修改放到按钮的单击事件里做（TeacherSubjectButtonEditor.java）*/
        cardPanel.add(new add_or_updateThisSubject(layout,cardPanel,teacherId,4444),"addSubject");  //添加选题的卡片

        /**
         * 设置菜单 单击事件
         */
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(cardPanel,"welcome");
            }
        });
        studentInfo.addActionListener(new ActionListener() {  //设置"教师个人信息"菜单项的
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看个人信息");
                layout.show(cardPanel,"teacherInfo");
            }
        });
        isReleased.addActionListener(new ActionListener() {  //设置已发布的选题
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("教师：==查看==已发布的选题");
                viewMySubject.initShow(layout,cardPanel,teacher);
                viewMySubject.updateTable();
                layout.show(cardPanel,"viewMySubject");
            }
        });
        addChoose.addActionListener(new ActionListener() {  //添加选题
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("教师：==将要==添加选题");
                layout.show(cardPanel,"addSubject");
            }
        });
        admissionStudent.addActionListener(new ActionListener() {  //录取学生
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("教师：==将要==录取学生！");
                teacherAdmissionStudent.initShow(layout,cardPanel,teacherId);
                teacherAdmissionStudent.updateTable();
                layout.show(cardPanel,"teacherAdmissionStudent");
            }
        });
        setContentPane(panelMain);  //将主容器添加到窗口
    }
}
