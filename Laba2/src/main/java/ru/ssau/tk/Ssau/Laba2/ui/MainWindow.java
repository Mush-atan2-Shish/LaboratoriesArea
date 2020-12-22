package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends javax.swing.JFrame {
    private JButton inputButtonTable = new JButton("Создать табулированную функцию");
    private JButton inputButtonMath = new JButton("Создать простую функцию");
    private JButton inputButtonFactory = new JButton("Выбрать тип фабрики");
    private JButton openButton = new JButton("Открыть функцию");
    private JButton saveButton = new JButton("Сохранить функцию");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private TableModelMainWindow tableModel = new TableModelMainWindow();
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    private JButton jButtonArrayTabulated;
    private JButton jButtonLinkedListTabulated;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private PicturePanel picturePanel1;

    public MainWindow() {
        actionPerformed();
        initComponents();
    }

    public void wrapTable(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    public void actionPerformed() {
        inputButtonTable.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        ArrayTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorsWindow(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                LinkedListTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
        inputButtonFactory.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
        saveButton.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
    }

    private void initComponents() {
        setTitle("Главное окно");
        picturePanel1 = new PicturePanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonArrayTabulated = new javax.swing.JButton();
        jButtonLinkedListTabulated = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(896, 611);
        picturePanel1.setLayout(new java.awt.BorderLayout());
        picturePanel1.setImageFile(new java.io.File("C:\\Users\\Karina\\IdeaProjects\\LaboratoriesArea\\LaboratoriesArea\\Laba2\\src\\main\\java\\ru\\ssau\\tk\\Ssau\\Laba2\\ui\\photo\\2.jpg"));
        jPanel1.setLayout(new java.awt.GridLayout());
        jPanel1.setOpaque(false);
        jPanel1.add(jLabel1);
        designButton(jButtonArrayTabulated, 100, 100, "Массивы");
        jPanel1.add(jButtonArrayTabulated);
        jButtonArrayTabulated.addActionListener(event -> {
            try {
                ArrayTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
            } catch (Exception e) {
                new ErrorsWindow(this, e);
            }
        });
        picturePanel1.add(jPanel1, BorderLayout.SOUTH);
        getContentPane().add(picturePanel1, java.awt.BorderLayout.CENTER);

        designButton(jButtonLinkedListTabulated, 100, 100, "Связный список");
        jPanel1.add(jButtonLinkedListTabulated);
        jButtonLinkedListTabulated.addActionListener(event -> {
            try {
                LinkedListTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
            } catch (Exception e) {
                new ErrorsWindow(this, e);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonFactory)
                        .addComponent(tableScrollPane))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonFactory)
                        .addComponent(tableScrollPane))
        );
    }

    public void designButton(JButton button, int width, int height, String name) {
        button.setFont(new Font("TimesRoman", Font.BOLD, 14));
        button.setText(name);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.pink);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
}
