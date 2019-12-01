package views.teacher.JPanel.button;


import com.soldier.dao.StudentDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.entitys.Subject;
import views.MyButton.MyButton;
import views.teacher.JPanel.add_or_updateThisSubject;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 按钮的渲染器,添加按钮响应事件
 */
public class TeacherSubjectButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    private MyButton button;  //自定义的button
    private JButton isReleased_or_admissionStudent;  //将"已发布的选题"按钮  和 “录取学生按钮”传过来
    private String buttonName;
    private CardLayout layout;
    private JPanel cardPanel;
    private JTable table;  //把选题信息列表传过来
    private String teacherId;  //保存老师工号

    public TeacherSubjectButtonEditor(JButton isReleased_or_admissionStudent, CardLayout layout, JPanel cardPanel, JTable table, String teacherId, String buttonName) {
        this.isReleased_or_admissionStudent = isReleased_or_admissionStudent;
        this.buttonName = buttonName;
        button = new MyButton(buttonName);
        button.addActionListener(this);
        this.layout = layout;
        this.cardPanel = cardPanel;
        this.table = table;
        this.teacherId = teacherId;
    }

    public TeacherSubjectButtonEditor(CardLayout layout, JPanel cardPanel, JTable table, String teacherId, String buttonName) {
        this.buttonName = buttonName;
        button = new MyButton(buttonName);
        button.addActionListener(this);
        this.layout = layout;
        this.cardPanel = cardPanel;
        this.table = table;
        this.teacherId = teacherId;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonName.equals("修改")) {
            int theSubjectId = (int) table.getValueAt(button.getRow(), 0);
            System.out.println("教师：==将要==添加或修改第" + button.getRow() + "行的选题！--SubjectId：" + theSubjectId);
            cardPanel.add(new add_or_updateThisSubject(layout, cardPanel, teacherId, theSubjectId), "updateThisSubject");  //添加或修改选题的卡片
            layout.show(cardPanel, "updateThisSubject");
        } else if (buttonName.equals("录取")) {
            int subjectId = (int) table.getValueAt(button.getRow(), 0);
            String studentNo = (String) table.getValueAt(button.getRow(), 2);
            String admissionSituationType = (String) table.getValueAt(button.getRow(), 7);
            System.out.println("教师：==将要==录取第" + button.getRow() + "行的学生！--SubjectId：" + subjectId + "studentNo：" + studentNo);
            int sure = JOptionPane.showConfirmDialog(null, "确认录取？", "录取学生确认框", JOptionPane.YES_NO_OPTION);
            if (sure == 0) {  //“是”:0,“否”:1,“取消”:2
                SubjectDao subjectDao = new SubjectDaoImpl();
                StudentDao studentDao = new StudentDaoImpl();
                Subject subject = new Subject();
                try {
                    subject = subjectDao.findById(subjectId);
                    subjectDao.updateAlreadyFillNum(subjectId,teacherId,subject.getAlreadyFillNum());
                    if (admissionSituationType.equals("第一志愿")) {
                        studentDao.updateAdmissionSituation(1,studentNo);
                    } else if (admissionSituationType.equals("第二志愿")) {
                        studentDao.updateAdmissionSituation(2,studentNo);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "成功录取学生！");
                System.out.println("教师：==查看==录取学生列表（成功录取学生后）");
                isReleased_or_admissionStudent.doClick();
            }
        }
    }

}
