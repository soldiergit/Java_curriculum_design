package views.admin.JPanel.button;


import com.soldier.dao.StudentDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Student;
import com.soldier.entitys.Teacher;
import views.MyButton.MyButton;
import views.student.JPanel.StudentInfo;
import views.teacher.JPanel.TeacherInfo;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 按钮的渲染器,添加按钮响应事件
 */
public class adminButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private CardLayout layout;
    private JPanel cardPanel;
    private MyButton button;
    private JButton tea_or_stuBTN;
    private int updateType;  //1-修改老师，2-修改学生

    public adminButtonEditor(CardLayout layout, JPanel cardPanel, JTable table, JButton tea_or_stuBTN, int updateType) {
        button = new MyButton("修改");
        button.addActionListener(this);
        this.table=table ;
        this.layout=layout ;
        this.cardPanel=cardPanel ;
        this.tea_or_stuBTN=tea_or_stuBTN ;
        this.updateType=updateType;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) {
        //将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        int sure = JOptionPane.showConfirmDialog(null,"确认修改？","修改用户信息确认框",JOptionPane.YES_NO_OPTION);
        if (sure == 0) {  //“是”:0,“否”:1,“取消”:2
            try {
                if (updateType == 1) {  //修改老师
                    Teacher teacher = new Teacher();
                    TeacherDao teacherDao = new TeacherDaoImpl();

                    teacher = teacherDao.findById((String) table.getValueAt(button.getRow(), 0));
                    cardPanel.add(new TeacherInfo(teacher), "teacherInfo");
                    layout.show(cardPanel, "teacherInfo");

                } else if ((updateType == 2)) {  //修改学生
                    Student student = new Student();
                    StudentDao studentDao = new StudentDaoImpl();
                    student = studentDao.findById((String) table.getValueAt(button.getRow(), 0));
                    cardPanel.add(new StudentInfo(student), "studentInfo");
                    layout.show(cardPanel, "studentInfo");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
