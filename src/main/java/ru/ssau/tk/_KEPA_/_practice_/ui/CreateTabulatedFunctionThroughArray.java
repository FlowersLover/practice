package ru.ssau.tk._KEPA_._practice_.ui;

import ru.ssau.tk._KEPA_._practice_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._KEPA_._practice_.functions.TabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class CreateTabulatedFunctionThroughArray extends JDialog {
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new XYTableModel(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Введите количество точек:");
    private final JTextField countField = new JTextField("2");
    private final JButton inputButton = new JButton("Ввести");
    private final JButton createButton = new JButton("Создать");
    private TabulatedFunctionFactory factory;
    private TabulatedFunction function;

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        CreateTabulatedFunctionThroughArray app = new CreateTabulatedFunctionThroughArray(factory, callback);
        app.setVisible(true);
    }

    public CreateTabulatedFunctionThroughArray(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new FlowLayout());
        this.setBounds(300, 300, 500, 500);
        this.factory = factory;
        addButtonListeners(callback);
        compose();
        setLocationRelativeTo(null);
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        inputButton.addActionListener(event -> {
            try {
                createButton.setEnabled(true);
                int count = Integer.parseInt(countField.getText());
                clearTable(tableModel.getRowCount());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
                if (tableModel.getRowCount() > 1) {
                    createButton.setEnabled(true);
                }
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
        createButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                x[0] = xValues.get(0);
                y[0] = yValues.get(0);
                for (int i = 1; i < xValues.size(); i++) {
                    if (xValues.get(i - 1) > xValues.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                function = new ArrayTabulatedFunctionFactory().create(x, y);
                callback.accept(function);
                this.dispose();
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(createButton)
        );
    }

    public void clearTable(int n) {
        for (int i = 0; i < n; i++) {
            xValues.remove(n - i - 1);
            yValues.remove(n - i - 1);
            tableModel.fireTableDataChanged();
        }
    }


    public static void main(String[] args) {
    }
}



