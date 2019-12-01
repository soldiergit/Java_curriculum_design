package views.admin.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.TitleDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.dao.impl.TitleDaoImpl;
import com.soldier.entitys.Institute;
import com.soldier.entitys.Teacher;
import com.soldier.entitys.Title;
import views.admin.JPanel.button.adminButtonEditor;
import views.admin.JPanel.button.adminButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherList extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableModel model;

    private List<Teacher> teacherList = new ArrayList<Teacher>();
    private TeacherDao teacherDao = new TeacherDaoImpl();

    private Object[] name = {"教师工号", "教师职称", "所属学院", "教师姓名", "性别", "联系方式", "邮箱", "个人发布选题数", "   "};
    private Object[][] data;

    public void initShow(JButton teacherBtn, CardLayout layout, JPanel cardPanel) {

        try {
            teacherList = teacherDao.findAll();
            data = new Object[teacherList.size()][9];
            data = (Object[][]) initData(teacherList, data);
        } catch (SQLException e) {
            System.out.println("TeacherList:初始化数组失败！");
            e.printStackTrace();
        }
        add(borderPanel);
        JLabel title = new JLabel("=================教师列表（共：有"+teacherList.size()+"名教师）=================", JLabel.CENTER);
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
        table.getColumnModel().getColumn(8).setCellEditor(new adminButtonEditor(layout,cardPanel,table,teacherBtn,1));
        table.getColumnModel().getColumn(8).setCellRenderer(new adminButtonRenderer());
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(70);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(65);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(130);
        table.getColumnModel().getColumn(7).setPreferredWidth(120);
        add(scrollPane);  //将滚动窗口添加到面板中
    }

    public void updateTable() {
        table.validate();
        table.updateUI();
    }


    public Object initData(List<Teacher> teacherList, Object[][] data) throws SQLException {
        TitleDao titleDao = new TitleDaoImpl();
        InstituteDao instituteDao = new InstituteDaoImpl();
        Title title;
        Institute institute;
        for (int i = 0; i < teacherList.size(); i++) {
            title = titleDao.findById(teacherList.get(i).getTitleId());
            institute = instituteDao.findById(teacherList.get(i).getInstituteId());
            data[i][0] = teacherList.get(i).getTeacherId();
            data[i][1] = title.getTitleName();
            data[i][2] = institute.getInstituteName();
            data[i][3] = teacherList.get(i).getTeacherName();
            data[i][4] = teacherList.get(i).getSex();
            data[i][5] = teacherList.get(i).getTel();
            data[i][6] = teacherList.get(i).getEmail();
            data[i][7] = teacherList.get(i).getMySubjectNum();
        }
        return data;
    }

}
