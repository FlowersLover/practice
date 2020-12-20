package ru.ssau.tk._KEPA_._practice_.ui;


import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class XYTableModel extends AbstractTableModel {
    private static final int X_COLUMN = 0;
    private static final int Y_COLUMN = 1;
    private List<String> xValues;
    private List<String> yValues;
    private boolean flag = true;

    public XYTableModel(List<String> xValues, List<String> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN:
                return xValues.get(rowIndex);

            case Y_COLUMN:
                return yValues.get(rowIndex);

        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN -> xValues.set(rowIndex, String.valueOf(value));
            case Y_COLUMN -> yValues.set(rowIndex, String.valueOf(value));
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case X_COLUMN:
            case Y_COLUMN:
                return true;
        }
        return false;
    }

    @Override
    public void fireTableDataChanged() {
        fireTableChanged(new TableModelEvent(this));
        flag = false;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case X_COLUMN -> "X";
            case Y_COLUMN -> "Y";
            default -> super.getColumnName(column);
        };
    }

    public void removeAll() {
        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
    }

}

