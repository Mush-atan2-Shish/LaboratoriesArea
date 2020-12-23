package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ElementwiseOperationsWindow extends JFrame {
    private TableModelMainWindow tableModel = new TableModelMainWindow();
    private JTable table1 = new JTable(tableModel);
    private JTable table2 = new JTable(tableModel);
    private JTable table3 = new JTable(tableModel);
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private TabulatedFunctionFactory factory;
    JButton plus = new JButton("+");
    JButton subtraction = new JButton("-");
    JButton multiplication = new JButton("*");
    JButton division = new JButton("/");


    public ElementwiseOperationsWindow() {
        setTitle("Поэлементные операции");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1200, 700);
        getContentPane().add(plus);
        getContentPane().add(division);
        getContentPane().add(multiplication);
        getContentPane().add(subtraction);
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
        setLocationRelativeTo(null);
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table1);
        JScrollPane secondTableScrollPane = new JScrollPane(table2);
        JScrollPane resultTableScrollPane = new JScrollPane(table3);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollPane)
                        .addComponent(plus)
                        .addComponent(division)
                        .addComponent(multiplication)
                        .addComponent(subtraction)
                        .addComponent(secondTableScrollPane)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createSequentialGroup()
                                .addGap(60)
                ));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120))
                        .addComponent(plus)
                        .addComponent(division)
                        .addComponent(multiplication)
                        .addComponent(subtraction)
                        .addGap(110)

                        .addComponent(secondTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(130)
                        )
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                                .addGap(60)
                ));
    }

    public static void main(String[] args) {
        ElementwiseOperationsWindow window = new ElementwiseOperationsWindow();
        window.setVisible(true);
    }
}
