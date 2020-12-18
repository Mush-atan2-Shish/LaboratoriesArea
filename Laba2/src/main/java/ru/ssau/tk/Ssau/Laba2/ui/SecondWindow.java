package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.function.Consumer;

public class SecondWindow extends JFrame {
    private JTable table;
    private JButton btnAdd;
    public String xValues = "";
    public double[] xDoubleValues;
    public String yValues = "";
    public double[] yDoubleValues;
    private DefaultTableModel tableModel;
    private JTextField txtField1;
    private JTextField txtField2;

    SecondWindow() {
        super("TabulatedFunction");
        setBounds(200, 200, 600, 600);
        createGUI();
    }

    private void createGUI() {

        setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        table = new JTable();
        pane.setViewportView(table);
        JPanel eastPanel = new JPanel();
        btnAdd = new JButton("Добавить");
        eastPanel.add(btnAdd);
        JPanel northPanel = new JPanel();
        txtField1 = new JTextField();
        txtField2 = new JTextField();
        JLabel lblField1 = new JLabel("Значение Х   ");
        JLabel lblField2 = new JLabel("Значение Y   ");
        northPanel.add(lblField1);
        northPanel.add(txtField1);
        northPanel.add(lblField2);
        northPanel.add(txtField2);
        txtField1.setPreferredSize(lblField1.getPreferredSize());
        txtField2.setPreferredSize(lblField2.getPreferredSize());

        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(pane, BorderLayout.CENTER);
        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        table.setModel(tableModel);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = tableModel.getRowCount() + 1;
                tableModel.addRow(new Object[]{txtField1.getText(), txtField2.getText()});
                xValues += txtField1.getText() + " ";
                yValues += txtField2.getText() + " ";
//                String[] xNumbers = xValues.split(" ");
//                for (int i=0; i<xNumbers.length;i++) {
//                    xDoubleValues[i]=(double) Double.parseDouble(xNumbers[i]);
//                }
            }
        });
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
