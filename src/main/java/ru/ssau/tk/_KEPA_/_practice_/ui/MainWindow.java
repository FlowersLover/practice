package ru.ssau.tk._KEPA_._practice_.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JButton buttonCreateArray = new JButton("Массив");
    private final JButton buttonCreateFunction = new JButton("Функция");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton buttonOperations = new JButton("Поэлементные операции");
    private final JButton buttonDifferentiation = new JButton("Дифференцирование");

    public MainWindow() {
        super("Главное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250, 250);
        getContentPane().add(buttonCreateArray);
        getContentPane().add(buttonCreateFunction);
        getContentPane().add(buttonOperations);
        getContentPane().add(buttonDifferentiation);
        getContentPane().add(buttonSettings);

        compose();
        addButtonListeners();

        setVisible(true);
    }

    private void addButtonListeners() {
        buttonCreateFunction.addActionListener(e -> {
            new CreateTabulatedFunctionThroughFunction();
        });

        buttonCreateArray.addActionListener(e -> {
            new CreateTabulatedFunctionThroughArray();
        });

        buttonSettings.addActionListener(e -> {
            new SettingsWindow();
        });

        buttonOperations.addActionListener(e -> {
            new OperationsWindow();
        });

        buttonDifferentiation.addActionListener(e -> {
            new DifferentiationWindow();

        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(buttonCreateArray)
                .addComponent(buttonCreateFunction)
                .addComponent(buttonOperations)
                .addComponent(buttonSettings)
                .addComponent(buttonDifferentiation));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(buttonCreateArray)
                .addComponent(buttonCreateFunction)
                .addComponent(buttonOperations)
                .addComponent(buttonSettings)
                .addComponent(buttonDifferentiation));
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
