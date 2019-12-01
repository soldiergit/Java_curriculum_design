package views.student.JPanel;

import com.soldier.dao.StudentDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Student;
import com.soldier.entitys.Subject;
import com.soldier.entitys.Teacher;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * 学生填报志愿
 */
public class StudentViewChoose extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel = new JPanel(new GridLayout(6,1,5,2));
    private SubjectDao subjectDao;
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private Student student;
    private int admissionSituation;

    public StudentViewChoose(CardLayout layout, JPanel cardPanel, String studentNo) {

        add(borderPanel);
        borderPanel.add(panel,BorderLayout.CENTER);  //面板中心
        JLabel title = new JLabel("=================录取情况=================",JLabel.CENTER);
        title.setSize(10,20);
        title.setFont(new Font("Dialog",1,20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.white);
        title.setForeground(Color.yellow);
        borderPanel.add(title,BorderLayout.NORTH);  //面板顶边

        subjectDao = new SubjectDaoImpl();
        studentDao = new StudentDaoImpl();
        teacherDao = new TeacherDaoImpl();
        Subject subject = new Subject();
        Teacher teacher = new Teacher() ;
        try {
            student = studentDao.findById(studentNo);
            admissionSituation = studentDao.admissionSituation(studentNo);
        } catch (SQLException e) {
            System.out.println("StudentViewChoose:获取数据失败！");
            e.printStackTrace();
        }

        /**
         * 文本框
         */
        JTextField chooseTypeView = new JTextField();
        JTextField chooseTeacher = new JTextField();
        JTextField chooseName = new JTextField();
        JTextField chooseAsk = new JTextField();
        if (admissionSituation == 0) {
            chooseTypeView.setText("您的志愿还未被老师录取，请耐心等待！");
            chooseTeacher.setText("暂无");
            chooseName.setText("暂无");
            chooseAsk.setText("暂无");
        } else {
            if (admissionSituation == 1) {
                chooseTypeView.setText("您第一志愿已被老师成功录取！");
                int num = student.getFirstChooseId();
                try {
                    subject = subjectDao.findById(num);
                    teacher = teacherDao.findById(subject.getTeacherId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                chooseTeacher.setText(teacher.getTeacherName());
                chooseName.setText(subject.getSubjectName());
                chooseAsk.setText(subject.getSubjectAsk());
            } else {
                chooseTypeView.setText("您第二志愿已被老师成功录取！");
                int num = student.getSecondChooseId();
                try {
                    subject = subjectDao.findById(num);
                    teacher = teacherDao.findById(subject.getTeacherId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                chooseTeacher.setText(teacher.getTeacherName());
                chooseName.setText(subject.getSubjectName());
                chooseAsk.setText(subject.getSubjectAsk());
            }
        }

        JPanel panel0 = new JPanel();
        JLabel label0 = new JLabel("录取志愿：");
        chooseTypeView.setColumns(20);
        chooseTypeView.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel0.add(label0);
        panel0.add(chooseTypeView);
        panel.add(panel0);

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("录取老师：");
        chooseTeacher.setColumns(20);
        chooseTeacher.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel1.add(label1);
        panel1.add(chooseTeacher);
        panel.add(panel1);

        JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("选题名称：");
        chooseName.setColumns(20);
        chooseName.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel3.add(label3);
        panel3.add(chooseName);
        panel1.add(panel3);

        JPanel panel4 = new JPanel();
        JLabel label4 = new JLabel("选题要求：");
        chooseAsk.setColumns(20);
        chooseAsk.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panel4.add(label4);
        panel4.add(chooseAsk);
        panel1.add(panel4);

    }

}
