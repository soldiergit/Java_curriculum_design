package views.student;

import com.soldier.dao.StudentDao;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.entitys.Student;
import login.Login;
import views.student.JPanel.StudentInfo;
import views.student.JPanel.StudentChooseSubject;
import views.student.JPanel.StudentViewChoose;
import views.student.JPanel.subjectInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class Student_Main extends JFrame {

    private JPanel panelMain = new JPanel(new BorderLayout());  //创建内容面 包容器，指定使用 边界布局
    private final CardLayout layout = new CardLayout(5,5); // 创建卡片布局，卡片间水平和竖直间隔为 10
    private StudentDao studentDao = new StudentDaoImpl();
    private Student student;

    public Student_Main(String studentNo) {
        try {
            student = studentDao.findById(studentNo);
        } catch (SQLException e) {
            System.out.println("获取学生信息失败！");
            e.printStackTrace();
        }
        //登录窗口设置
        setSize(1000,600);//窗口大小
        setLocationRelativeTo(null);//居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);//退出
        setTitle("学生端");//标题

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
        JMenuItem studentInfo = new JMenuItem("学生个人信息");  //菜单项
        studentInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        JMenu menuWork = new JMenu("毕业设计题目");  //创建菜单
        JMenuItem workInfo = new JMenuItem("题目信息列表");  //菜单项
        JMenuItem addOrUpdateWork = new JMenuItem("填报志愿");
        workInfo.setAccelerator(KeyStroke.getKeyStroke('L'));  //设置题目信息菜单项快捷键是L
        addOrUpdateWork.setAccelerator(KeyStroke.getKeyStroke('U'));  //设置题目信息菜单项快捷键是L

        JButton menuChooseView = new JButton("选题录取情况");  //创建菜单

        menuStudent.add(studentInfo);  //将菜单项添加到菜单
        menuWork.add(workInfo);        //将菜单项添加到菜单
        menuWork.add(addOrUpdateWork); //将菜单项添加到菜单
        menuBar.add(homeBtn);               //将按钮添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));  //设置菜单(JMenu)间距
        menuBar.add(menuStudent);           //将菜单添加到菜单条
        menuBar.add(Box.createHorizontalStrut(10));
        menuBar.add(menuWork);              //将菜单添加到菜单条
        menuBar.add(menuChooseView);              //将菜单添加到菜单条
        menuBar.add(Box.createHorizontalStrut(300));
        menuBar.add(outLogin);              //将按钮添加到菜单条

        JPanel panel1 = new JPanel();
        panel1.add(menuBar);
        panelMain.add(panel1,BorderLayout.NORTH);  //主容器的北边

        JPanel cardPanel = new JPanel(layout);  //卡片布局，一次只能看一张卡片
        panelMain.add(cardPanel,BorderLayout.CENTER);  //将主容器的中心设为卡片布局

        JPanel panel2_1 = new JPanel();
        JLabel welcome = new JLabel("欢迎您："+student.getStudentName()+"同学");
        welcome.setFont(new Font("Dialog", 1, 26));  //设置字体
        welcome.setForeground(Color.red);  //设置颜色
        panel2_1.add(welcome);

        cardPanel.add(panel2_1,"welcome");
        cardPanel.add(new StudentInfo(student),"studentInfo");  //个人信息卡片
        cardPanel.add(new subjectInfo(layout,cardPanel),"subjectInfo");  //选题信息列表卡片
        cardPanel.add(new StudentChooseSubject(layout,cardPanel,student.getStudentNo()),"studentChooseSubject");  //志愿填报卡片
        cardPanel.add(new StudentViewChoose(layout,cardPanel,student.getStudentNo()), "studentViewChoose");  //学生查看录取情况

        /**
         * 设置菜单项的单击事件
        */
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(cardPanel,"welcome");
            }
        });
        studentInfo.addActionListener(new ActionListener() {  //设置"学生个人信息"菜单项的
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看个人信息");
                layout.show(cardPanel,"studentInfo");
            }
        });
        workInfo.addActionListener(new ActionListener() {  //设置"题目信息"菜单项的
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("题目信息");
                layout.show(cardPanel,"subjectInfo");
            }
        });
        addOrUpdateWork.addActionListener(new ActionListener() {  //设置"填报"菜单项的
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("填报/修改");
                layout.show(cardPanel,"studentChooseSubject");
            }
        });
        menuChooseView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查看录取情况！");
                layout.show(cardPanel,"studentViewChoose");
            }
        });
        setContentPane(panelMain);  //将主容器添加到窗口
    }
}
