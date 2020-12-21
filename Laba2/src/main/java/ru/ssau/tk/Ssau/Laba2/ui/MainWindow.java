package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

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
        setSize(582, 580);
        picturePanel1.setLayout(new java.awt.BorderLayout());
        picturePanel1.setImageFile(new java.io.File("C:\\Users\\Пользователь\\IdeaProjects\\LaboratoriesAreaLaba2\\Laba2\\src\\main\\java\\ru\\ssau\\tk\\Ssau\\Laba2\\ui\\photo\\5.jpg"));
        jPanel1.setLayout(new java.awt.GridLayout());
        jPanel1.setOpaque(false);
        jPanel1.add(jLabel1);
        jButton1.setFont(new Font("TimesRoman", Font.BOLD, 14));
        jButton1.setText("Создать табулированную функцию");
        jButton1.setPreferredSize(new Dimension(100, 100));
        jButton1.setBackground(Color.WHITE);
        jPanel1.add(jButton1);
        jButton1.addActionListener(event -> ArrayTabulatedFunctionWindow.main());
        picturePanel1.add(jPanel1, java.awt.BorderLayout.NORTH);
        getContentPane().add(picturePanel1, java.awt.BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private PicturePanel picturePanel1;
}
