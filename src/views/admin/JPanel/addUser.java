package views.admin.JPanel;

import com.soldier.dao.*;
import com.soldier.dao.impl.*;
import com.soldier.entitys.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/11 22:48
 * @Desc:
 */
public class addUser extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel = new JPanel(new GridLayout(10, 1));
    private JLabel title;  //标题
    private String[] sex = {"男", "女"};
    private String[] politics = {"党员", "团员", "无"};
    private List<Title> titleList = new ArrayList<Title>();
    private List<Institute> instituteList = new ArrayList<Institute>();

    private UserDao userDao = new UserDaoImpl();
    private TeacherDao teacherDao = new TeacherDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private TitleDao titleDao = new TitleDaoImpl();
    private InstituteDao instituteDao = new InstituteDaoImpl();

    private JTextField ThisuserType;
    private JTextField ThispassWord;
    private JTextField ThisName;
    private JComboBox<String> ThissexCombox;  //性别
    private JTextField Thistel; //联系方式
    private JTextField Thisemail;  //邮箱
    private JComboBox<String> ThisinstituteCombox;  //所属学院
    private JTextField ThisteacherId;  //教师工号
    private JComboBox<String> ThistitleCombox;  //教师职称
    private JTextField ThisstudentNo;  //学生学号
    private JTextField Thisscore;  //学生成绩
    private JComboBox<String> ThispoliticsCombox;  //学生政治面貌

    public addUser(int Type) {  //2-老师，3-学生
        add(borderPanel);
        borderPanel.add(panel, BorderLayout.CENTER);  //面板中心

        JButton okButton = new JButton("确定添加");
        borderPanel.add(okButton, BorderLayout.SOUTH);  //面板底部
        try {
            titleList = titleDao.findAll();
            instituteList = instituteDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] institute = new String[instituteList.size()];
        for (int i = 0; i < instituteList.size(); i++) {
            institute[i] = instituteList.get(i).getInstituteName();
        }


        title = new JLabel("=================添加用户!=================", JLabel.CENTER);
        borderPanel.add(title, BorderLayout.NORTH);  //面板顶部
        JPanel panelMain = new JPanel();
        JLabel labelMain = new JLabel("用户类型：");
        ThisuserType = new JTextField(20);
        ThisuserType.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panelMain.add(labelMain);
        panelMain.add(ThisuserType);
        panel.add(panelMain);

        JPanel panelPS = new JPanel();
        JLabel labelPS = new JLabel("密        码：");
        ThispassWord = new JTextField(20);
        panelPS.add(labelPS);
        panelPS.add(ThispassWord);
        panel.add(panelPS);

        JPanel panelA = new JPanel();
        JLabel labelA = new JLabel("姓        名：");
        ThisName = new JTextField(20);
        panelA.add(labelA);
        panelA.add(ThisName);
        panel.add(panelA);

        JPanel panelB = new JPanel();
        JLabel labelB = new JLabel("性        别：");
        ThissexCombox = new JComboBox<String>(sex);
        panelB.add(labelB);
        panelB.add(ThissexCombox);
        panel.add(panelB);

        JPanel panelD = new JPanel();
        JLabel labelD = new JLabel("联系电话：");
        Thistel = new JTextField(20);
        panelD.add(labelD);
        panelD.add(Thistel);
        panel.add(panelD);

        JPanel panelE = new JPanel();
        JLabel labelE = new JLabel("邮        箱：");
        Thisemail = new JTextField(20);
        panelE.add(labelE);
        panelE.add(Thisemail);
        panel.add(panelE);

        JPanel panelF = new JPanel();
        JLabel labelF = new JLabel("所属学院：");
        ThisinstituteCombox = new JComboBox<String>(institute);
        panelF.add(labelF);
        panelF.add(ThisinstituteCombox);
        panel.add(panelF);

        if (Type == 2) {
            addTeacher();
            ThisuserType.setText("教师");
        } else {
            addStudent();
            ThisuserType.setText("学生");
        }

        /**
         * 添加用户
         */
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sure = JOptionPane.showConfirmDialog(null, "确认修改？", "确认对话框", JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION) {  //“是”:0,“否”:1,“取消”:2
                    String passWord = ThispassWord.getText();
                    int instituteId = ThisinstituteCombox.getSelectedIndex() + 1;  //下标默认从0开始
                    int sexType = ThissexCombox.getSelectedIndex() + 1;
                    String sex = "男";
                    if (sexType == 2) {
                        sex = "女";
                    }
                    String email = Thisemail.getText();
                    String tel = Thistel.getText();
                    if (Type == 2) {
                        if (passWord.equals("") || ThisName.getText().equals("") || tel.equals("") || email.equals("") || ThisteacherId.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "用户信息必须完整，不能有空！");
                            return;
                        }
                        String teacherId = ThisteacherId.getText();
                        int titleId = ThistitleCombox.getSelectedIndex() + 1;  //下标默认从0开始
                        String teacherName = ThisName.getText();
                        String userName = ThisteacherId.getText();
                        User user = new User(userName, passWord, 2);
                        Teacher teacher = new Teacher(teacherId, titleId, instituteId, teacherName, sex, tel, email);
                        try {
                            userDao.register(user);
                            teacherDao.add(teacher);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "添加成功！");
                    } else if (Type == 3) {
                        if (passWord.equals("") || ThisName.getText().equals("") || tel.equals("") || email.equals("") ||
                                ThisstudentNo.getText().equals("") || Thisscore.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "用户信息必须完整，不能有空！");
                            return;
                        }
                        String userName = ThisstudentNo.getText();
                        String studentNo = ThisstudentNo.getText();
                        String studentName = ThisName.getText();
                        int politicsType = ThispoliticsCombox.getSelectedIndex() + 1;  //下标默认从0开始
                        int score = Integer.valueOf(Thisscore.getText());

                        User user = new User(userName, passWord, 3);
                        Student student = new Student(studentNo, instituteId, studentName, sex, politicsType, email, tel, score);

                        try {
                            userDao.register(user);
                            studentDao.add(student);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "添加成功！");
                    }
                }
            }
        });


    }

    public void addTeacher() {
        String[] titleName = new String[titleList.size()];
        for (int i = 0; i < titleList.size(); i++) {
            titleName[i] = titleList.get(i).getTitleName();
        }

        JPanel panel0 = new JPanel();
        JLabel label0 = new JLabel("教师工号：");
        ThisteacherId = new JTextField(20);
        panel0.add(label0);
        panel0.add(ThisteacherId);
        panel.add(panel0);

        JPanel panelC = new JPanel();
        JLabel labelC = new JLabel("职        称：");
        ThistitleCombox = new JComboBox<String>(titleName);
        panelC.add(labelC);
        panelC.add(ThistitleCombox);
        panel.add(panelC);

    }

    public void addStudent() {
        JPanel stu_PanelA = new JPanel();
        JLabel stu_LabelA = new JLabel("学生学号：");
        ThisstudentNo = new JTextField(20);
        stu_PanelA.add(stu_LabelA);
        stu_PanelA.add(ThisstudentNo);
        panel.add(stu_PanelA);

        JPanel stu_PanelB = new JPanel();
        JLabel stu_LabelB = new JLabel("成        绩：");
        Thisscore = new JTextField(20);
        stu_PanelB.add(stu_LabelB);
        stu_PanelB.add(Thisscore);
        panel.add(stu_PanelB);


        JPanel stu_PanelC = new JPanel();
        JLabel stu_LabelC = new JLabel("政治面貌：");
        ThispoliticsCombox = new JComboBox<String>(politics);
        stu_PanelC.add(stu_LabelC);
        stu_PanelC.add(ThispoliticsCombox);
        panel.add(stu_PanelC);
    }


}
