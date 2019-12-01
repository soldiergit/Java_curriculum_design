package views.admin.button;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 * 按钮的渲染器,添加按钮响应事件
 */
public class MyButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    private JRadioButton button;

    public MyButtonEditor() {
        button = new JRadioButton("详情");
        button.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) {
        return button;
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
//        JOptionPane.showMessageDialog(null,"详情","选项",JOptionPane.OK_OPTION);
//        this.button.getR
    }

}
