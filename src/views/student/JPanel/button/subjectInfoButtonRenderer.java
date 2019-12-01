package views.student.JPanel.button;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * 按钮的编辑器，添加初始化显示效果
 */
public class subjectInfoButtonRenderer implements TableCellRenderer {

    private JPanel panel;
    private JButton button;

    public subjectInfoButtonRenderer() {
        button = new JButton("填报");
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}
