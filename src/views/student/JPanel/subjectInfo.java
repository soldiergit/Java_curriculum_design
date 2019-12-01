package views.student.JPanel;

import com.soldier.dao.InstituteDao;
import com.soldier.dao.SubjectDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.InstituteDaoImpl;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Institute;
import com.soldier.entitys.Subject;
import com.soldier.entitys.Teacher;
import views.student.JPanel.button.subjectInfoButtonEditor;
import views.student.JPanel.button.subjectInfoButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class subjectInfo extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel = new JPanel(new GridLayout(6,1,5,2));
    private JTable table;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultTableModel model;

    private TeacherDao teacherDao;
    private SubjectDao subjectDao;
    private InstituteDao instituteDao;
    private List<Subject> subjectList;

    private Object[] name = {"选题编号","选题名称","教师姓名","教师工号","教师所属学院","人数上限","已填报数量","选题要求","   "};
    private Object[][] data;

    public subjectInfo(CardLayout layout, JPanel cardPanel) {

        add(borderPanel);
        borderPanel.add(panel,BorderLayout.CENTER);  //面板中心
        JLabel title = new JLabel("=================选题列表=================",JLabel.CENTER);
        title.setSize(10,20);
        title.setFont(new Font("Dialog",1,20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.BLUE);
        title.setForeground(Color.white);
        borderPanel.add(title,BorderLayout.NORTH);  //面板顶边

        teacherDao = new TeacherDaoImpl();
        subjectDao = new SubjectDaoImpl();
        instituteDao = new InstituteDaoImpl();
        subjectList = new ArrayList<Subject>();
        try {
            subjectList = subjectDao.findAll();
            data= new Object[subjectList.size()][9] ;
            data = (Object[][]) initData(teacherDao,subjectDao,instituteDao,subjectList,data);
        } catch (SQLException e) {
            System.out.println("subjectInfo:初始化数组失败！");
            e.printStackTrace();
        }

        model = new DefaultTableModel(data,name) {
            @Override
            public boolean isCellEditable(int row, int column) {  //设置下标为8的列可编辑
                if (column == 8) {
                    return true;
                }
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(900,400));
        scrollPane.setViewportView(table);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  //设置表格中的内容居中显示
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //设置表格自动调整长度模式

        /**  将第8列设置为按钮  设置宽度*/
        table.getColumnModel().getColumn(8).setCellEditor(new subjectInfoButtonEditor(layout,cardPanel));
        table.getColumnModel().getColumn(8).setCellRenderer(new subjectInfoButtonRenderer());
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(70);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);
        table.getColumnModel().getColumn(7).setPreferredWidth(250);
        table.getColumnModel().getColumn(8).setPreferredWidth(70);
        panel.add(scrollPane);  //将滚动窗口添加到面板中
    }

    public Object initData(TeacherDao teacherDao, SubjectDao subjectDao, InstituteDao instituteDao, List<Subject> subjectList,
                           Object[][] data) throws SQLException {
        Teacher teacher = new Teacher();
        Institute institute = new Institute();
        subjectList = subjectDao.findAll();
        for (int i = 0; i < subjectList.size(); i++) {
            teacher = teacherDao.findById(subjectList.get(i).getTeacherId());
            institute = instituteDao.findById(teacher.getInstituteId());
            data[i][0] = subjectList.get(i).getSubjectId();
            data[i][1] = subjectList.get(i).getSubjectName();
            data[i][2] = teacher.getTeacherName();
            data[i][3] = teacher.getTeacherId();
            data[i][4] = institute.getInstituteName();
            data[i][5] = subjectList.get(i).getSubjectNum();
            data[i][6] = subjectList.get(i).getAlreadyFillNum();
            data[i][7] = subjectList.get(i).getSubjectAsk();
        }
        return data;
    }

}
