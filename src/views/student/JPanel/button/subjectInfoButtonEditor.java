package views.student.JPanel.button;


import views.MyButton.MyButton;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 按钮的渲染器,添加按钮响应事件
 */
public class subjectInfoButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 1L;
    private MyButton button;
    private CardLayout layout;// = new CardLayout();
    private JPanel cardPanel;// = new JPanel();

    public subjectInfoButtonEditor(CardLayout layout, JPanel cardPanel) {
        button = new MyButton("填报");
        button.addActionListener(this);
        this.layout=layout ;
        this.cardPanel=cardPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,  boolean isSelected, int row, int column) {
        //将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }

    //按钮点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        int sure = JOptionPane.showConfirmDialog(null,"确认填报？","志愿填报确认框",JOptionPane.YES_NO_OPTION);
        if(sure == 0) {  //“是”:0,“否”:1,“取消”:2
            layout.show(cardPanel,"studentChooseSubject");
        }
    }

}
