package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

    private JButton jButton1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private PicturePanel picturePanel1;

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Main window");
        picturePanel1 = new PicturePanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(596, 611);
        picturePanel1.setLayout(new java.awt.BorderLayout());
        picturePanel1.setImageFile(new java.io.File("C:\\Users\\Karina\\IdeaProjects\\LaboratoriesArea\\LaboratoriesArea\\Laba2\\src\\main\\java\\ru\\ssau\\tk\\Ssau\\Laba2\\ui\\photo\\2.jpg"));
        jPanel1.setLayout(new java.awt.GridLayout());
        jPanel1.setOpaque(false);
        jPanel1.add(jLabel1);
        jButton1.setFont(new Font("TimesRoman", Font.BOLD, 14));
        jButton1.setText("Создать табулированную функцию");
        jButton1.setPreferredSize(new Dimension(100, 100));
        jButton1.setBackground(Color.pink);
        jButton1.setForeground(Color.DARK_GRAY);
        jButton1.setFocusPainted(false);
        jPanel1.add(jButton1);
        jButton1.addActionListener(event -> {
            try {
                ArrayTabulatedFunctionWindow.main();
            } catch (Exception e) {
                new ErrorsWindow(this, e);
            }
        });
        picturePanel1.add(jPanel1, BorderLayout.SOUTH);
        getContentPane().add(picturePanel1, java.awt.BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
