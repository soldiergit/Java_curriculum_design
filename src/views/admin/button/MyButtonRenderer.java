package views.admin.button;

import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * 按钮的编辑器，添加初始化显示效果
 */
public class MyButtonRenderer implements TableCellRenderer {

    private JPanel panel;
    private JRadioButton button;

    public MyButtonRenderer() {
        button = new JRadioButton("详情");
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}
