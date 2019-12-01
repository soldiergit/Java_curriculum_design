package views.teacher.JPanel.button;

import views.MyButton.MyButton;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * 按钮的编辑器，添加初始化显示效果
 */
public class TeacherSubjectButtonRenderer implements TableCellRenderer {

    private JPanel panel;
    private MyButton button;
    private String buttonName;

    public TeacherSubjectButtonRenderer(String buttonName) {
        this.buttonName = buttonName;
        button = new MyButton(buttonName);
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}
