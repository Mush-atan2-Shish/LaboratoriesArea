package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame {

    public FirstWindow() {
        super("First Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JPanel northPanel = new JPanel();
        JTextField txtField1 = new JTextField();
        JLabel lblField1 = new JLabel("Введите количество точек функции:  ");
        northPanel.add(lblField1);
        northPanel.add(txtField1);
        txtField1.setPreferredSize(lblField1.getPreferredSize());

        JPanel panel = new JPanel(new FlowLayout());

        JButton button = new JButton("ОК");
        button.addActionListener(event -> {
            int count = 0;
            try {
                count = Integer.parseInt(txtField1.getText());
                SecondWindow secondWindow = new SecondWindow();
                secondWindow.setVisible(true);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(panel, "Некорректный ввод!");
            }
        });
        add(northPanel, BorderLayout.NORTH);
        getContentPane().add(button);
    }

    public static void main(String[] args) {
        FirstWindow firstWindow = new FirstWindow();
        firstWindow.setVisible(true);
    }

}