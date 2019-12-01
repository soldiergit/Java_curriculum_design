package views.teacher.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.StudentDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Student;
import com.soldier.entitys.Subject;
import com.soldier.entitys.Teacher;
import views.teacher.JPanel.button.TeacherSubjectButtonEditor;
import views.teacher.JPanel.button.TeacherSubjectButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/11 12:00
 * @Desc: 教师录取学生
 */
public class TeacherAdmissionStudent extends JPanel {
    private CardLayout layout;
    private JPanel cardPanel;
    private Student student;
    private Teacher teacher;

    private Subject subjectFirst;
    private Subject subjectSecond;
    private List<Student> studentListFirst;
    private List<Student> studentListSecond;

    private Object[] name = {"选题编号", "选题名称", "学生学号", "学生姓名", "邮箱", "联系电话", "学习成绩", "志愿顺序", "   "};
    private Object[][] data;
    private String firstChooseId = "firstChooseId";  //用于根据志愿查学生
    private String secondChooseId = "secondChooseId";

    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableModel model;

//    public TeacherAdmissionStudent(CardLayout layout, JPanel cardPanel, String teacherId) {
//        this.layout = layout;
//        this.cardPanel = cardPanel;
////        initTable(teacherId);
//    }

    public void initShow(CardLayout layout, JPanel cardPanel, String teacherId) {
        StudentDao studentDao = new StudentDaoImpl();
        TeacherDao teacherDao = new TeacherDaoImpl();
        SubjectDao subjectDao = new SubjectDaoImpl();
        InstituteDao instituteDao = new InstituteDaoImpl();
        studentListFirst = new ArrayList<Student>();
        studentListSecond = new ArrayList<Student>();
        int stuNumFirst = 0;
        int stuNumSecond = 0;
        try {
            teacher = teacherDao.findById(teacherId);
            studentListFirst = studentDao.findAllByChoose(firstChooseId);  //所有填报了第一志愿的学生
            studentListSecond = studentDao.findAllByChoose(secondChooseId);//所有填报了第二志愿的学生

            /** 遍历两个list，求出所有报了这个老师的学生数量 */
            for (Student student1 : studentListFirst) {
                subjectFirst = subjectDao.findById(student1.getFirstChooseId());
                if (subjectFirst.getTeacherId().equals(teacherId)) {
                    stuNumFirst++;
                }
            }
            for (Student student2 : studentListSecond) {
                subjectSecond = subjectDao.findById(student2.getSecondChooseId());
                if (subjectSecond.getTeacherId().equals(teacherId)) {
                    stuNumSecond++;
                }
            }
            data = new Object[stuNumFirst + stuNumSecond][9];
            data = (Object[][]) initData(stuNumFirst,stuNumSecond,studentListFirst, studentListSecond, data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model = new DefaultTableModel(data, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 8) {  //设置按钮列可编辑
                    return true;
                }
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        scrollPane.setViewportView(table);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  //设置表格中的内容居中显示
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //设置表格自动调整长度模式
        /**  将第9列设置为按钮  并设置列宽*/
        table.getColumnModel().getColumn(8).setCellEditor(new TeacherSubjectButtonEditor(layout, cardPanel,table, teacher.getTeacherId(), "录取"));
        table.getColumnModel().getColumn(8).setCellRenderer(new TeacherSubjectButtonRenderer("录取"));
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(70);
        table.getColumnModel().getColumn(8).setPreferredWidth(70);
        add(scrollPane);  //将滚动窗口添加到面板中
    }

    public void updateTable() {
        table.validate();
        table.updateUI();
    }

    public Object initData(int stuNumFirst, int stuNumSecond, List<Student> studentListFirst, List<Student> studentListSecond, Object[][] data) throws SQLException {
        SubjectDao subjectDao = new SubjectDaoImpl();
        Subject subject;
        int j=0;
        for (int i = 0; i < stuNumFirst; i++) {
            subject = subjectDao.findById(studentListFirst.get(i).getFirstChooseId());
            data[i][0] = subject.getSubjectId();
            data[i][1] = subject.getSubjectName();
            data[i][2] = studentListFirst.get(i).getStudentNo();
            data[i][3] = studentListFirst.get(i).getStudentName();
            data[i][4] = studentListFirst.get(i).getEmail();
            data[i][5] = studentListFirst.get(i).getTel();
            data[i][6] = studentListFirst.get(i).getScore();
            data[i][7] = "第一志愿";
        }
        for (int i = stuNumFirst; i < (stuNumFirst+stuNumSecond); i++) {
            subject = subjectDao.findById(studentListSecond.get(j).getSecondChooseId());
            data[i][0] = subject.getSubjectId();
            data[i][1] = subject.getSubjectName();
            data[i][2] = studentListSecond.get(j).getStudentNo();
            data[i][3] = studentListSecond.get(j).getStudentName();
            data[i][4] = studentListSecond.get(j).getEmail();
            data[i][5] = studentListSecond.get(j).getTel();
            data[i][6] = studentListSecond.get(j).getScore();
            data[i][7] = "第二志愿";
            j +=1;
        }
        return data;
    }
}
