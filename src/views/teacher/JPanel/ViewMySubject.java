package views.teacher.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
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
 * @Date: 2018/12/9 20:33
 * @Desc:
 */
public class ViewMySubject extends JPanel {
    private CardLayout layout;
    private JPanel cardPanel;
    private Teacher teacher;
    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    private JPanel panel = new JPanel();
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableModel model;

    private TeacherDao teacherDao;
    private SubjectDao subjectDao;
    private InstituteDao instituteDao;
    private List<Subject> subjectList;

    private Object[] name = {"选题编号", "选题名称", "人数上限", "已录取数量", "选题要求", "   "};
    private Object[][] data;

    public void initShow(CardLayout layout, JPanel cardPanel, Teacher teacher) {
        add(borderPanel);
        borderPanel.add(panel, BorderLayout.CENTER);  //面板中心
        JLabel title = new JLabel("=================个人发布选题=================", JLabel.CENTER);
        title.setSize(10, 20);
        title.setFont(new Font("Dialog", 1, 20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.BLUE);
        title.setForeground(Color.white);
        borderPanel.add(title, BorderLayout.NORTH);  //面板顶边

        teacherDao = new TeacherDaoImpl();
        subjectDao = new SubjectDaoImpl();
        instituteDao = new InstituteDaoImpl();
        subjectList = new ArrayList<Subject>();
        int mySubjectNum = 0;
        try {
            subjectList = subjectDao.findAll();
            for (Subject subject22 : subjectList) {
                if (subject22.getTeacherId().equals(teacher.getTeacherId()) || subject22.getTeacherId() == teacher.getTeacherId()) {
                    mySubjectNum++;
                }
            }
            data = new Object[mySubjectNum][6];
            data = (Object[][]) initData(teacher, subjectDao, subjectList, mySubjectNum, data);
        } catch (SQLException e) {
            System.out.println("subjectInfo:初始化数组失败！");
            e.printStackTrace();
        }

        model = new DefaultTableModel(data, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {  //设置按钮列可编辑
                    return true;
                }
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800,400));
        scrollPane.setViewportView(table);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  //设置表格中的内容居中显示
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //设置表格自动调整长度模式
        /**  将第6列设置为按钮  并设置列宽*/
        table.getColumnModel().getColumn(5).setCellEditor(new TeacherSubjectButtonEditor(layout, cardPanel,table, teacher.getTeacherId(),"修改"));
        table.getColumnModel().getColumn(5).setCellRenderer(new TeacherSubjectButtonRenderer("修改"));
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        panel.add(scrollPane);  //将滚动窗口添加到面板中
    }

    public void updateTable() {
        table.validate();
        table.updateUI();
    }

    public Object initData(Teacher teacher, SubjectDao subjectDao, List<Subject> subjectList, int mySubjectNum,
                           Object[][] data) throws SQLException {
        subjectList = subjectDao.findAll();
        for (int i = 0; i < mySubjectNum; i++) {
            if (subjectList.get(i).getTeacherId().equals(teacher.getTeacherId()) || subjectList.get(i).getTeacherId() == teacher.getTeacherId()) {
                data[i][0] = subjectList.get(i).getSubjectId();
                data[i][1] = subjectList.get(i).getSubjectName();
                data[i][2] = subjectList.get(i).getSubjectNum();
                data[i][3] = subjectList.get(i).getAlreadyFillNum();
                data[i][4] = subjectList.get(i).getSubjectAsk();
            }
        }
        return data;
    }
}