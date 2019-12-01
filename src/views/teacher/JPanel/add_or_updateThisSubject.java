package views.teacher.JPanel;

import com.soldier.dao.SubjectDao;
import com.soldier.dao.TeacherDao;
import com.soldier.dao.impl.SubjectDaoImpl;
import com.soldier.dao.impl.TeacherDaoImpl;
import com.soldier.entitys.Subject;
import com.soldier.entitys.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/10 9:16
 * @Desc:
 */
public class add_or_updateThisSubject extends JPanel {

    private JPanel borderPanel = new JPanel(new BorderLayout());  //边界布局
    //网格布局：指定 行数 和 列数 的网格布局, 并指定 水平 和 竖直 网格间隙；默认构造时 每个组件占据一行一列
    private JPanel panel = new JPanel(new GridLayout(10, 1));
    private JLabel title;  //标题
    private JTextField thisTeacherId;
    private JTextField thisSubjectName;
    private JTextField thisSubjectNum;
    private JTextField thisAlreadyFillNum;
    private JTextArea thisSubjectAsk;
    private Teacher teacher;
    private Subject subject;
    private SubjectDao subjectDao = new SubjectDaoImpl();
    private TeacherDao teacherDao = new TeacherDaoImpl();


    public add_or_updateThisSubject(CardLayout layout, JPanel cardPanel, String teacherId, int theSubjectId) {
        try {
            teacher = teacherDao.findById(teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(borderPanel);
        borderPanel.add(panel, BorderLayout.CENTER);  //面板中心
        JPanel panelA = new JPanel();
        JLabel labelA = new JLabel("教师工号：");
        thisTeacherId = new JTextField(teacherId);
        thisTeacherId.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
        panelA.add(labelA);
        panelA.add(thisTeacherId);
        panel.add(panelA);
        thisTeacherId.setColumns(10);
        /**
         * 用数字4444来区别是进行添加还是修改选题操作
         */
        if (theSubjectId == 4444) {
            title = new JLabel("=================添加选题=================", JLabel.CENTER);

            JPanel panelB = new JPanel();
            JLabel labelB = new JLabel("选题名称：");
            thisSubjectName = new JTextField();
            thisSubjectName.setColumns(10);
            panelB.add(labelB);
            panelB.add(thisSubjectName);
            panel.add(panelB);

            JPanel panelC = new JPanel();
            JLabel labelC = new JLabel("发布数量：");
            thisSubjectNum = new JTextField();
            panelC.add(labelC);
            panelC.add(thisSubjectNum);
            panel.add(panelC);
            thisSubjectNum.setColumns(10);

            JPanel panelD = new JPanel();
            JLabel labelD = new JLabel("选题要求：");
            thisSubjectAsk = new JTextArea(2,10);
            panelD.add(labelD);
            panelD.add(thisSubjectAsk);
            panel.add(panelD);

            JButton addButton = new JButton("确认添加");
            borderPanel.add(addButton, BorderLayout.SOUTH);  //面板底部
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int sure = JOptionPane.showConfirmDialog(null,"确认添加嘛？","确认对话框",JOptionPane.YES_NO_OPTION);
                    if (sure == JOptionPane.YES_OPTION) {  //“是”:0,“否”:1,“取消”:2
                        String subjectName = thisSubjectName.getText();
                        int subjectNum = Integer.valueOf(thisSubjectNum.getText());
                        String subjectAsk = thisSubjectAsk.getText();
                        Subject subject = new Subject(teacherId,subjectName,subjectNum,subjectAsk);
                        try {
                            subjectDao.add(subject);
                            teacherDao.addMySubjectNum(teacher.getMySubjectNum(), teacherId);
                        } catch (SQLException e1) {
                            System.out.println("add_or_updateThisSubject：添加选题失败！");
                            e1.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "添加选题成功！");
                    }
                }
            });
            JButton returnButton = new JButton("返回");
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.show(cardPanel,"viewMySubject");
                    JOptionPane.showMessageDialog(null, "您的选题列表没有变！");
                    System.out.println("教师：==查看==已发布的选题（取消添加选题）");
                }
            });
            JMenuBar menuBar = new JMenuBar();  //创建菜单条
            menuBar.add(Box.createHorizontalStrut(100));  //设置菜单(JMenu)间距
            menuBar.add(addButton);
            menuBar.add(Box.createHorizontalStrut(150));  //设置菜单(JMenu)间距
            menuBar.add(returnButton);
            borderPanel.add(menuBar,BorderLayout.SOUTH);  //面板底部

        } else{
            title = new JLabel("=================修改个人发布的选题================", JLabel.CENTER);

            try {
               subject = subjectDao.findById(theSubjectId);
            } catch (SQLException e) {
                System.out.println("获取当前选题信息失败！");
                e.printStackTrace();
            }

            JPanel panelB = new JPanel();
            JLabel labelB = new JLabel("选题名称：");
            thisSubjectName = new JTextField(subject.getSubjectName());
            panelB.add(labelB);
            panelB.add(thisSubjectName);
            panel.add(panelB);
            thisSubjectName.setColumns(10);

            JPanel panelC = new JPanel();
            JLabel labelC = new JLabel("发布数量：");
            thisSubjectNum = new JTextField(String.valueOf(subject.getSubjectNum()));
            panelC.add(labelC);
            panelC.add(thisSubjectNum);
            panel.add(panelC);
            thisSubjectNum.setColumns(10);

            JPanel panelD = new JPanel();
            JLabel labelD = new JLabel("已被学生填报的数量：");
            thisAlreadyFillNum = new JTextField(String.valueOf(subject.getAlreadyFillNum()));
            thisAlreadyFillNum.setEnabled(false);//文本框以反白显示，不可选择文本也不可编辑
            panelD.add(labelD);
            panelD.add(thisAlreadyFillNum);
            panel.add(panelD);
            thisAlreadyFillNum.setColumns(10);

            JPanel panelE = new JPanel();
            JLabel labelE = new JLabel("选题要求：");
            thisSubjectAsk = new JTextArea(2,10);
            thisSubjectAsk.setText(subject.getSubjectAsk());
            panelE.add(labelE);
            panelE.add(thisSubjectAsk);
            panel.add(panelE);

            JButton updateButton = new JButton("确认修改");
            borderPanel.add(updateButton, BorderLayout.SOUTH);  //面板底部
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int sure = JOptionPane.showConfirmDialog(null,"确认修改嘛？","确认对话框",JOptionPane.YES_NO_OPTION);
                    if (sure == JOptionPane.YES_OPTION) {  //“是”:0,“否”:1,“取消”:2
                        String subjectName = thisSubjectName.getText();
                        int subjectNum = Integer.valueOf(thisSubjectNum.getText());
                        String subjectAsk = thisSubjectAsk.getText();
                        if (subjectNum >= subject.getSubjectNum()) {
                            Subject subject = new Subject(theSubjectId,teacherId,subjectName,subjectNum,subjectAsk);
                            try {
                                subjectDao.update(subject);
                            } catch (SQLException e1) {
                                System.out.println("教师：==想要==修改选题失败！选题编号："+subject.getSubjectId());
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "修改选题成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "发布数量必须大于等于您原来的发布数量："+subject.getSubjectNum());
                        }
                    }
                }
            });
            JButton NoButton = new JButton("取消修改");
            NoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    thisSubjectName.setText(subject.getSubjectName());
                    thisSubjectNum.setText(String.valueOf(subject.getSubjectNum()));
                    thisSubjectAsk.setText(subject.getSubjectAsk());
                }
            });
            JMenuBar menuBar = new JMenuBar();  //创建菜单条
            menuBar.add(Box.createHorizontalStrut(100));  //设置菜单(JMenu)间距
            menuBar.add(updateButton);
            menuBar.add(Box.createHorizontalStrut(150));  //设置菜单(JMenu)间距
            menuBar.add(NoButton);
            borderPanel.add(menuBar,BorderLayout.SOUTH);  //面板底部
        }

        title.setSize(10, 20);
        title.setFont(new Font("Dialog", 1, 20));
        title.setOpaque(true);  //此句是重点，设置背景颜色必须先将它设置为不透明的，因为默认是透明的
        title.setBackground(Color.BLUE);
        title.setForeground(Color.white);
        borderPanel.add(title, BorderLayout.NORTH);  //面板顶边
    }
}
