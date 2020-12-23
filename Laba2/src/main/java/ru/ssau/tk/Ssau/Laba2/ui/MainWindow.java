package ru.ssau.tk.Ssau.Laba2.ui;

import com.sun.deploy.panel.JreTableModel;
import ru.ssau.tk.Ssau.Laba2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private JButton inputButtonTable = new JButton();
    private JButton inputButtonMath = new JButton();
    private JButton inputButtonFactory = new JButton();
    private JButton openButton = new JButton();
    private JButton saveButton = new JButton();
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private TableModelMainWindow tableModel = new TableModelMainWindow();
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public MainWindow() {
        setTitle("Главное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 720, 840);
        setContentPane(new BgPanel());
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
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

    void compose() {
        setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane();
        //pane.getViewport().setBackground(Color.yellow);
        //TableColumn col = table.getColumnModel().getColumn(2);
        //table.setBackground(Color.yellow);
        //col.setCellRenderer(new CustomRenderer());
        pane.setViewportView(table);
        JPanel northPanel = new JPanel();
        northPanel.setBackground(new Color(16, 16, 16));
        designButton(inputButtonTable, "Массив");
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
        northPanel.add(inputButtonTable);
        designButton(inputButtonMath, "Связный список");
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
        northPanel.add(inputButtonMath);
        designButton(inputButtonFactory, "Выбрать тип фабрики");
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
        northPanel.add(inputButtonFactory);
        designButton(openButton, "Открыть функцию");
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
        northPanel.add(openButton);
        designButton(saveButton, "Сохранить функцию");
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
        northPanel.add(saveButton);
        add(northPanel, BorderLayout.NORTH);
        add(pane, BorderLayout.SOUTH);
        setLocationByPlatform(true);
    }

    public void designButton(JButton button, String name) {
        button.setText(name);
        button.setBackground(Color.pink);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setBackground(new Color(16, 16, 16));
        window.setVisible(true);
    }

    class BgPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("photo/2.jpg"));
            } catch (IOException e) {
            }

            g.drawImage(im, 5, 0, null);
        }
    }
}
