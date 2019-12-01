package views.student.JPanel;

import com.soldier.dao.StudentDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.entitys.Student;
import com.soldier.entitys.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生填报志愿
 */
public class StudentChooseSubject extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel1 = new JPanel(new GridLayout(6,1,5,2));
    private SubjectDao subjectDao;
    private StudentDao studentDao;
    private List<Subject> subjectList;
    private Student student;

    public StudentChooseSubject(CardLayout layout, JPanel cardPanel, String studentNo) {

        add(borderPanel);
        borderPanel.add(panel1,BorderLayout.CENTER);  //面板中心
        JLabel title = new JLabel("志愿填报:(注意：志愿填报后将无法更改！)",JLabel.CENTER);
        title.setSize(10,20);
        title.setFont(new java.awt.Font("Dialog",1,20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.BLUE);
        title.setForeground(Color.white);
        borderPanel.add(title,BorderLayout.NORTH);  //面板顶边

        subjectDao = new SubjectDaoImpl();
        studentDao = new StudentDaoImpl();
        subjectList = new ArrayList<Subject>();
        try {
            student = studentDao.findById(studentNo);
            subjectList = subjectDao.findAll();
        } catch (SQLException e) {
            System.out.println("StudentChooseSubject:获取选题数据失败！");
            e.printStackTrace();
        }
        Integer[] allSubjectId = new Integer[subjectList.size()+1];
        for (int i=0; i<subjectList.size()+1; i++) {
            if (i == 0) {
                allSubjectId[i] = null;
            } else {
                allSubjectId[i] = Integer.valueOf(subjectList.get(i-1).getSubjectId());
            }
        }
        JLabel beCareful = new JLabel("志愿为空时--表示您还没有填报志愿！", JLabel.CENTER);
        beCareful.setForeground(Color.red);
        panel1.add(beCareful);

        JLabel first = new JLabel("第一志愿：");
        first.setForeground(Color.red);
        panel1.add(first);
        JComboBox<Integer> combox1 = new JComboBox<Integer>(allSubjectId);
        for (int i=0;i<allSubjectId.length; i++) {
            if (allSubjectId[i] == student.getFirstChooseId()) {
                combox1.setSelectedIndex(i);
            }
        }
        if (!"null".equals(String.valueOf(student.getFirstChooseId())) && student.getFirstChooseId()!=0) {  //志愿不为空，则不可选
            combox1.setEnabled(false);
        }
        panel1.add(combox1);

        JLabel second = new JLabel("第二志愿：");
        second.setForeground(Color.red);
        panel1.add(second);
        JComboBox<Integer> combox2 = new JComboBox<Integer>(allSubjectId);
        for (int i=0;i<allSubjectId.length; i++) {
            if (allSubjectId[i] == student.getSecondChooseId()) {
                combox2.setSelectedIndex(i);
            }
        }
        if (!"null".equals(String.valueOf(student.getSecondChooseId())) && student.getSecondChooseId()!=0) {
            combox2.setEnabled(false);
        }
        panel1.add(combox2);

        JButton updateButton = new JButton("确认填报");
        borderPanel.add(updateButton,BorderLayout.SOUTH);  //面板底部
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wantFirstChooseId = (int) combox1.getSelectedItem();
                int wantSecondChooseId = (int) combox2.getSelectedItem();
                Student student = new Student();
                try {
                    student = studentDao.findById(studentNo);
                } catch (SQLException e1) {
                    System.out.println("StudentChooseSubject：获取学生信息失败！");
                    e1.printStackTrace();
                }
                //当数据库中学生第一志愿为空
                if ("null".equals(String.valueOf(student.getFirstChooseId())) || student.getFirstChooseId()==0) {
                    if(!"null".equals(String.valueOf(wantFirstChooseId)) || student.getFirstChooseId()!=0) {  //页面被学生修改
                        int sure = JOptionPane.showConfirmDialog(null,"确认修改？","确认对话框",JOptionPane.YES_NO_OPTION);
                        if (sure == 0) {
                            try {
                                studentDao.addFirstChoose(wantFirstChooseId,student.getStudentNo());
                                JOptionPane.showMessageDialog(null, "第一志愿填报成功！");
                                combox1.setEnabled(false);
                            } catch (SQLException e1) {
                                System.out.println("修改学生第一志愿失败！");
                                e1.printStackTrace();
                            }
                        }
                    }
                }
                //当数据库中学生第二志愿为空
                else if ("null".equals(String.valueOf(student.getSecondChooseId())) || student.getSecondChooseId()==0) {
                    if(!"null".equals(String.valueOf(wantSecondChooseId)) || student.getSecondChooseId()!=0) {  //页面被学生修改
                        int sure = JOptionPane.showConfirmDialog(null,"确认修改？","确认对话框",JOptionPane.YES_NO_OPTION);
                        if (sure == 0) {
                            try {
                                studentDao.addSecondChoose(wantSecondChooseId,student.getStudentNo());
                                JOptionPane.showMessageDialog(null, "第二志愿填报成功！");
                                combox2.setEnabled(false);
                            } catch (SQLException e1) {
                                System.out.println("修改学生第二志愿失败！");
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

}
