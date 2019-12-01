package views.admin.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.StudentDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.StudentDaoImpl;
import com.soldier.entitys.Institute;
import com.soldier.entitys.Student;
import views.admin.JPanel.button.adminButtonEditor;
import views.admin.JPanel.button.adminButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentList extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableModel model;

    private List<Student> studentList = new ArrayList<Student>();
    private StudentDao studentDao = new StudentDaoImpl();

    private Object[] name = {"学号", "所属学院", "姓名", "性别", "政治面貌", "邮箱", "电话", "学习成绩", "   "};
    private Object[][] data;

    public void initShow(JButton studentBtn, CardLayout layout, JPanel cardPanel) {
        try {
            studentList = studentDao.findAll();
            data = new Object[studentList.size()][9];
            data = (Object[][]) initData(studentList, data);
        } catch (SQLException e) {
            System.out.println("StudentList:初始化数组失败！");
            e.printStackTrace();
        }
        add(borderPanel);
        JLabel title = new JLabel("=================教师列表（共：有"+studentList.size()+"名学生）=================", JLabel.CENTER);
        title.setSize(10, 20);
        title.setFont(new Font("Dialog", 1, 20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.BLUE);
        title.setForeground(Color.white);
        borderPanel.add(title, BorderLayout.NORTH);  //面板顶边

        model = new DefaultTableModel(data, name) {
            @Override
            public boolean isCellEditable(int row, int column) {  //设置下标为8的列可编辑
                if (column == 8) {
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

        /**  将第9列设置为按钮  设置宽度*/
        table.getColumnModel().getColumn(8).setCellEditor(new adminButtonEditor(layout,cardPanel,table,studentBtn,2));
        table.getColumnModel().getColumn(8).setCellRenderer(new adminButtonRenderer());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(65);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(70);
        table.getColumnModel().getColumn(8).setPreferredWidth(65);
        add(scrollPane);  //将滚动窗口添加到面板中
    }

    public void updateTable() {
        table.validate();
        table.updateUI();
    }


    public Object initData(List<Student> studentList, Object[][] data) throws SQLException {
        InstituteDao instituteDao = new InstituteDaoImpl();
        Institute institute;
        for (int i = 0; i < studentList.size(); i++) {
            institute = instituteDao.findById(studentList.get(i).getInstituteId());
            data[i][0] = studentList.get(i).getStudentNo();
            data[i][1] = institute.getInstituteName();
            data[i][2] = studentList.get(i).getStudentName();
            data[i][3] = studentList.get(i).getSex();
            if (studentList.get(i).getPoliticsType() == 1) {
                data[i][4] = "党员";
            } else if (studentList.get(i).getPoliticsType() == 2) {
                data[i][4] = "团员";
            } else {
                data[i][4] = "群众";
            }
            data[i][5] = studentList.get(i).getEmail();
            data[i][6] = studentList.get(i).getTel();
            data[i][7] = studentList.get(i).getScore();
        }
        return data;
    }

}
