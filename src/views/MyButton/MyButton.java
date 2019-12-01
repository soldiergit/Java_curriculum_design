package views.MyButton;

import javax.swing.*;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/9 9:19
 * @Desc: 自定义了两个属性，方便记录表格（JTable）中每一个按钮的行和列
 */


public class MyButton extends JButton {

    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public MyButton() {

    }

    public MyButton(String name) {
        super(name);
    }

}