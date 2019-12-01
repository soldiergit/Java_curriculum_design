package views.teacher.JPanel;

import com.soldier.dao.*;
import com.soldier.dao.impl.*;
import com.soldier.entitys.Institute;
import com.soldier.entitys.Teacher;
import com.soldier.entitys.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherInfo extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel1 = new JPanel(new GridLayout(6,1,5,2));
    private TeacherDao teacherDao;
    private InstituteDao instituteDao;
    private TitleDao titleDao;
    private List<Title> titleList = new ArrayList<Title>();
    private List<Institute> instituteList = new ArrayList<Institute>();
    private String[] studentSex = {"男","女"};
    private String[] titleName ;
    private String[] instituteName;

    public TeacherInfo(Teacher teacher) {

        titleDao = new TitleDaoImpl();
        teacherDao = new TeacherDaoImpl();
        instituteDao = new InstituteDaoImpl();
        titleList = getTitleList(titleDao,titleList);
        instituteList = getInstituteList(instituteDao,instituteList);
        titleName = new String[titleList.size()];
        instituteName = new String[instituteList.size()];
        for (int i=0; i<titleList.size(); i++) {
            titleName[i] = titleList.get(i).getTitleName();
        }
        for (int i=0; i<instituteList.size(); i++) {
            instituteName[i] = instituteList.get(i).getInstituteName();
        }

        add(borderPanel);
        borderPanel.add(panel1,BorderLayout.CENTER);  //面板中心
        JLabel info = new JLabel("教师个人信息",JLabel.CENTER);
        info.setSize(10,20);
        info.setFont(new Font("Dialog",1,20));
        info.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        info.setBackground(Color.gray);
        borderPanel.add(info,BorderLayout.NORTH);  //面板顶边

        JPanel panel0 = new JPanel();
        JLabel label0 = new JLabel("教师工号：");
        JTextField studentNo = new JTextField(teacher.getTeacherId());
        studentNo.setColumns(20);
        studentNo.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel0.add(label0);
        panel0.add(studentNo);
        panel1.add(panel0);

        JPanel panelA = new JPanel();
        JLabel labelA = new JLabel("姓        名：");
        JTextField studentName = new JTextField(teacher.getTeacherName());
        studentName.setColumns(20);
        studentName.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panelA.add(labelA);
        panelA.add(studentName);
        panel1.add(panelA);

        JPanel panelB = new JPanel();
        JLabel labelB = new JLabel("性        别：");
        JComboBox<String> combox1 = new JComboBox<String>(studentSex);
        if (teacher.getSex().equals("男"))  combox1.setSelectedIndex(0);
        else combox1.setSelectedIndex(1);
        panelB.add(labelB);
        panelB.add(combox1);
        panel1.add(panelB);

        JPanel panelC = new JPanel();
        JLabel labelC = new JLabel("职        称：");
        JComboBox<String> combox2 = new JComboBox<String>(titleName);
        int selectC = teacher.getTitleId();
        combox2.setSelectedIndex(selectC-1);
        panelC.add(labelC);
        panelC.add(combox2);
        panel1.add(panelC);

        JPanel panelD = new JPanel();
        JLabel labelD = new JLabel("联系电话：");
        JTextField tel = new JTextField(teacher.getTel());
        tel.setColumns(20);
        panelD.add(labelD);
        panelD.add(tel);
        panel1.add(panelD);

        JPanel panelE = new JPanel();
        JLabel labelE = new JLabel("邮        箱：");
        JTextField email = new JTextField(teacher.getEmail());
        email.setColumns(20);
        panelE.add(labelE);
        panelE.add(email);
        panel1.add(panelE);

        JPanel panelF = new JPanel();
        JLabel labelF = new JLabel("所属学院：");
        JComboBox<String> combox3 = new JComboBox<String>(instituteName);
        int selectF = teacher.getInstituteId();
        combox3.setSelectedIndex(selectF-1);
        panelF.add(labelF);
        panelF.add(combox3);
        panel1.add(panelF);

        JButton updateButton = new JButton("确认修改");
        borderPanel.add(updateButton,BorderLayout.SOUTH);  //面板底部
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sex = (String) combox1.getSelectedItem();
                int titleId = teacher.getTitleId();
                int instituteId = teacher.getInstituteId();
                for (int i=0; i<titleName.length;i++) {
                    if (combox2.getSelectedItem().equals(titleName[i])) {
                        titleId = i+1;
                        break;
                    }
                }
                for (int i=0; i<instituteName.length;i++) {
                    if (combox3.getSelectedItem().equals(instituteName[i])) {
                        instituteId = i+1;
                        break;
                    }
                }
                Teacher teacher1 = new Teacher(teacher.getTeacherId(),titleId,instituteId,teacher.getTeacherName(),sex,tel.getText(),email.getText());
                int sure = JOptionPane.showConfirmDialog(null,"确认修改？","确认对话框",JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION) {  //“是”:0,“否”:1,“取消”:2
                    try {
                        teacherDao.update(teacher1);
                    } catch (SQLException e1) {
                        System.out.println("StudentInfo:修改教师信息失败!");
                        e1.printStackTrace();
                    }
                }
            }
        });
        JButton NoButton = new JButton("取消修改");
        NoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (teacher.getSex().equals("男"))  combox1.setSelectedIndex(0);
                else combox1.setSelectedIndex(1);
                combox2.setSelectedIndex(selectC-1);
                combox3.setSelectedIndex(selectF-1);
            }
        });
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(Box.createHorizontalStrut(100));  //设置菜单(JMenu)间距
        menuBar.add(updateButton);
        menuBar.add(Box.createHorizontalStrut(150));  //设置菜单(JMenu)间距
        menuBar.add(NoButton);
        borderPanel.add(menuBar,BorderLayout.SOUTH);  //面板底部

    }

    public List<Title> getTitleList(TitleDao titleDao, List<Title> titleList) {
        try {
            titleList = titleDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titleList;
    }

    public List<Institute> getInstituteList(InstituteDao instituteDao, List<Institute> instituteList) {
        try {
            instituteList = instituteDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instituteList;
    }

}
