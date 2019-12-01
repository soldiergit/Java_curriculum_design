package views.student.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.StudentDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.entitys.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentInfo extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel1 = new JPanel(new GridLayout(6,1,5,2));
    private StudentDao studentDao = new StudentDaoImpl();
    private InstituteDao instituteDao = new InstituteDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();
    private String[] studentSex = {"男","女"};
    String[] politicsName = {"党员","团员","无"};
    private String[] instituteName = {"宝石与艺术设计学院","外国语学院","商学院","机械化工学院","教师教育学院",
            "大数据与软件工程学院","电子与信息工程学院","文学与传媒学院","法学与公共管理学院","马克思主义学院"};

    public StudentInfo(Student student) {

        add(borderPanel);
        borderPanel.add(panel1,BorderLayout.CENTER);  //面板中心
        JLabel info = new JLabel("学生个人信息",JLabel.CENTER);
        info.setSize(10,20);
        info.setFont(new java.awt.Font("Dialog",1,20));
        info.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        info.setBackground(Color.gray);
        borderPanel.add(info,BorderLayout.NORTH);  //面板顶边

        JPanel panel0 = new JPanel();
        JLabel label0 = new JLabel("学        号：");
        JTextField studentNo = new JTextField(student.getStudentNo());
        studentNo.setColumns(20);
        studentNo.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel0.add(label0);
        panel0.add(studentNo);
        panel1.add(panel0);

        JPanel panelA = new JPanel();
        JLabel labelA = new JLabel("姓        名：");
        JTextField studentName = new JTextField(student.getStudentName());
        studentName.setColumns(20);
        studentName.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panelA.add(labelA);
        panelA.add(studentName);
        panel1.add(panelA);

        JPanel panelB = new JPanel();
        JLabel labelB = new JLabel("性        别：");
        JComboBox<String> combox1 = new JComboBox<String>(studentSex);
        if (student.getSex().equals("男"))  combox1.setSelectedIndex(0);
        else combox1.setSelectedIndex(1);
        panelB.add(labelB);
        panelB.add(combox1);
        panel1.add(panelB);

        JPanel panelC = new JPanel();
        JLabel labelC = new JLabel("政治面貌：");
        String typeName = "无";
        JComboBox<String> combox2 = new JComboBox<String>(politicsName);
        if (student.getPoliticsType() ==1)  combox2.setSelectedIndex(0);
        else if (student.getPoliticsType() ==2)  combox2.setSelectedIndex(1);
        else combox2.setSelectedIndex(2);
        panelC.add(labelC);
        panelC.add(combox2);
        panel1.add(panelC);

        JPanel panelD = new JPanel();
        JLabel labelD = new JLabel("联系电话：");
        JTextField tel = new JTextField(student.getTel());
        tel.setColumns(20);
        panelD.add(labelD);
        panelD.add(tel);
        panel1.add(panelD);

        JPanel panelE = new JPanel();
        JLabel labelE = new JLabel("邮        箱：");
        JTextField email = new JTextField(student.getEmail());
        email.setColumns(20);
        panelE.add(labelE);
        panelE.add(email);
        panel1.add(panelE);

        JPanel panelF = new JPanel();
        JLabel labelF = new JLabel("所属学院：");
        JComboBox<String> combox3 = new JComboBox<String>(instituteName);
        int selectC = student.getInstituteId();
        combox3.setSelectedIndex(selectC-1);
        panelF.add(labelF);
        panelF.add(combox3);
        panel1.add(panelF);

        JButton updateButton = new JButton("确认修改");
        borderPanel.add(updateButton,BorderLayout.SOUTH);  //面板底部
        updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                String sex = (String) combox1.getSelectedItem();
                int instituteId = student.getInstituteId();
                int politicsType = student.getPoliticsType();
                for (int i=0; i<politicsName.length;i++) {
                    if (combox2.getSelectedItem().equals(politicsName[i])) {
                        politicsType = i+1;
                        break;
                    }
                }
                for (int i=0; i<instituteName.length;i++) {
                    if (combox3.getSelectedItem().equals(instituteName[i])) {
                        instituteId = i+1;
                        break;
                    }
                }
                Student student1 = new Student(student.getStudentNo(),instituteId,student.getStudentName(),sex,politicsType,email.getText(),tel.getText());
                int sure = JOptionPane.showConfirmDialog(null,"确认修改？","确认对话框",JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION) {  //“是”:0,“否”:1,“取消”:2
                    try {
                        studentDao.update(student1);
                    } catch (SQLException e1) {
                        System.out.println("StudentInfo:修改学生信息失败!");
                        e1.printStackTrace();
                    }
                }
            }
        });
        JButton NoButton = new JButton("取消修改");
        NoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (student.getSex().equals("男"))  combox1.setSelectedIndex(0);
                else combox1.setSelectedIndex(1);

                if (student.getPoliticsType() ==1)  combox2.setSelectedIndex(0);
                else if (student.getPoliticsType() ==2)  combox2.setSelectedIndex(1);
                else combox2.setSelectedIndex(2);

                combox3.setSelectedIndex(selectC-1);
            }
        });
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalStrut(100));  //设置菜单(JMenu)间距
        menuBar.add(updateButton);
        menuBar.add(Box.createHorizontalStrut(150));  //设置菜单(JMenu)间距
        menuBar.add(NoButton);
        borderPanel.add(menuBar,BorderLayout.SOUTH);  //面板底部

    }

}
