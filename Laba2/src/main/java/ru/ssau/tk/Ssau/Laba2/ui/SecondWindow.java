package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondWindow extends JFrame {
    private JTable table;
    private JButton btnCreateTabulatedFunction;
    private DefaultTableModel tableModel;

    SecondWindow(Integer countSecond) {
        super("TabulatedFunction");
        setBounds(200, 200, 600, 600);

        setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new JTable();
        pane.setViewportView(table);
        JPanel eastPanel = new JPanel();
        btnCreateTabulatedFunction = new JButton("Создать табулированную функцию");
        eastPanel.add(btnCreateTabulatedFunction);
        add(eastPanel, BorderLayout.EAST);
        add(pane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, countSecond);
        table.setModel(tableModel);

        btnCreateTabulatedFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
}
