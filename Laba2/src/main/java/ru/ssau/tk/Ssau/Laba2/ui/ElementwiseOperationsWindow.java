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
    JButton plus = new JButton();
    JButton subtraction = new JButton();
    JButton multiplication = new JButton();
    JButton division = new JButton();

    JButton createArrayOne = new JButton();
    JButton createListOne = new JButton();
    JButton saveOne = new JButton();
    JButton downloadOne = new JButton();

    JButton createArrayTwo = new JButton();
    JButton createListTwo = new JButton();
    JButton saveTwo = new JButton();
    JButton downloadTwo = new JButton();

    JButton saveThree = new JButton();

    public ElementwiseOperationsWindow() {
        setTitle("Поэлементные операции");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1200, 700);
        designButton(plus, "+");
        designButton(subtraction, "-");
        designButton(multiplication, "*");
        designButton(division, "/");
        getContentPane().add(plus);
        getContentPane().add(division);
        getContentPane().add(multiplication);
        getContentPane().add(subtraction);

        designButton(createArrayOne, "Создать через массивы");
        designButton(createListOne, "Создать через функцию");
        designButton(saveOne, "Сохранить");
        designButton(downloadOne, "Загрузить");
        getContentPane().add(createArrayOne);
        getContentPane().add(createListOne);
        getContentPane().add(saveOne);
        getContentPane().add(downloadOne);

        designButton(createArrayTwo, "Создать через массивы");
        designButton(createListTwo, "Создать через функцию");
        designButton(saveTwo, "Сохранить");
        designButton(downloadTwo, "Загрузить");
        getContentPane().add(createArrayTwo);
        getContentPane().add(createListTwo);
        getContentPane().add(saveTwo);
        getContentPane().add(downloadTwo);
        designButton(saveThree, "Сохранить");
        getContentPane().add(saveThree);
        compose();
        setLocationRelativeTo(null);
        this.factory = new ArrayTabulatedFunctionFactory();

        createArrayOne.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        ArrayTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                        int countNew = tableModel.getFunction().getCount();
                        // wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorsWindow(this, e);
                    }
                }
        );

        createArrayTwo.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        ArrayTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                        int countNew = tableModel.getFunction().getCount();
                        // wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorsWindow(this, e);
                    }
                }
        );

        createListOne.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                LinkedListTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                //wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        createListTwo.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                LinkedListTabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                //wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        saveOne.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });


        saveTwo.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        saveThree.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        downloadOne.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                //  wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });

        downloadTwo.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                // wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorsWindow(this, e);
            }
        });
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane firstTableScrollPane = new JScrollPane(table1);
        JScrollPane secondTableScrollPane = new JScrollPane(table2);
        JScrollPane resultTableScrollPane = new JScrollPane(table3);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(plus)
                                .addComponent(multiplication)
                                .addComponent(subtraction)
                                .addComponent(division))
                        .addComponent(secondTableScrollPane)
                        .addComponent(resultTableScrollPane))

                .addGroup(layout.createSequentialGroup()
                        .addComponent(createArrayOne)
                        .addComponent(createListOne)
                        .addGap(67)
                        .addComponent(createArrayTwo)
                        .addComponent(createListTwo))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(saveOne)
                        .addComponent(downloadOne)
                        .addGap(230)
                        .addComponent(saveTwo)
                        .addComponent(downloadTwo)
                        .addGap(175)
                        .addComponent(saveThree)));


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstTableScrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120)
                                .addComponent(plus)
                                .addComponent(multiplication)
                                .addComponent(subtraction)
                                .addComponent(division)
                                .addGap(110)
                        )
                        .addComponent(secondTableScrollPane)
                        .addComponent(resultTableScrollPane))
                .addGroup(layout.createParallelGroup()
                        .addComponent(createArrayOne)
                        .addComponent(createListOne)
                        .addGap(60)
                        .addComponent(createArrayTwo)
                        .addComponent(createListTwo))
                .addGroup(layout.createParallelGroup()
                        .addComponent(saveOne)
                        .addComponent(downloadOne)
                        .addComponent(saveTwo)
                        .addComponent(downloadTwo)
                        .addComponent(saveThree)));
    }

    public void designButton(JButton button, String name) {
        button.setText(name);
        button.setBackground(Color.pink);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        ElementwiseOperationsWindow window = new ElementwiseOperationsWindow();
        window.setVisible(true);
    }
}
