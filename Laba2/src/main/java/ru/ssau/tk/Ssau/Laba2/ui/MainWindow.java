package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends javax.swing.JFrame {

    private JButton jButtonArrayTabulated;
    private JButton jButtonLinkedListTabulated;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private PicturePanel picturePanel1;

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Главное окно");
        picturePanel1 = new PicturePanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonArrayTabulated = new javax.swing.JButton();
        jButtonLinkedListTabulated = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(596, 611);
        picturePanel1.setLayout(new java.awt.BorderLayout());
        picturePanel1.setImageFile(new java.io.File("C:\\Users\\Пользователь\\IdeaProjects\\LaboratoriesAreaLaba2\\Laba2\\src\\main\\java\\ru\\ssau\\tk\\Ssau\\Laba2\\ui\\photo\\2.jpg"));
        jPanel1.setLayout(new java.awt.GridLayout());
        jPanel1.setOpaque(false);
        jPanel1.add(jLabel1);
        jButtonArrayTabulated.setFont(new Font("TimesRoman", Font.BOLD, 14));
        jButtonArrayTabulated.setText("Перейти к окну создания табулированной функции с помощью массивов");
        jButtonArrayTabulated.setPreferredSize(new Dimension(100, 100));
        jButtonArrayTabulated.setBackground(Color.pink);
        jButtonArrayTabulated.setForeground(Color.DARK_GRAY);
        jButtonArrayTabulated.setFocusPainted(false);
        jPanel1.add(jButtonArrayTabulated);
        jButtonArrayTabulated.addActionListener(event -> {
            try {
                ArrayTabulatedFunctionWindow.main();
            } catch (Exception e) {
                new ErrorsWindow(this, e);
            }
        });
        picturePanel1.add(jPanel1, BorderLayout.SOUTH);
        getContentPane().add(picturePanel1, java.awt.BorderLayout.CENTER);

        jButtonLinkedListTabulated.setFont(new Font("TimesRoman", Font.BOLD, 14));
        jButtonLinkedListTabulated.setText("Перейти к окну создания табулированной функции с помощью связного списка");
        jButtonLinkedListTabulated.setPreferredSize(new Dimension(100, 100));
        jButtonLinkedListTabulated.setBackground(Color.pink);
        jButtonLinkedListTabulated.setForeground(Color.DARK_GRAY);
        jButtonLinkedListTabulated.setFocusPainted(false);
        jPanel1.add(jButtonLinkedListTabulated);
        jButtonLinkedListTabulated.addActionListener(event -> {
            try {
                LinkedListTabulatedFunctionWindow.main();
            } catch (Exception e) {
                new ErrorsWindow(this, e);
            }
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
