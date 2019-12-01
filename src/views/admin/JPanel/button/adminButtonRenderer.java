package views.admin.JPanel.button;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * 按钮的编辑器，添加初始化显示效果
 */
public class adminButtonRenderer implements TableCellRenderer {

    private JPanel panel;
    private JButton button;

    public adminButtonRenderer() {
        button = new JButton("修改");
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}
