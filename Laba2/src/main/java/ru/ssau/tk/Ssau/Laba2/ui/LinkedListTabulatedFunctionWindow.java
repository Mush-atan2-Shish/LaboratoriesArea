package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.functions.*;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.function.Consumer;

public class LinkedListTabulatedFunctionWindow extends JDialog {
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JLabel fromLabel = new JLabel("От:");
    private final JLabel toLabel = new JLabel("До:");
    private final JLabel countLabel = new JLabel("Количество:");
    private final JTextField countField = new JTextField();
    private final JTextField fromField = new JTextField();
    private final JTextField toField = new JTextField();
    private final JButton okButton = new JButton();
    private TabulatedFunctionFactory factory;
    private final Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    TabulatedFunction function;

    public LinkedListTabulatedFunctionWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        this.factory = factory;
        this.setBounds(300, 200, 500, 150);
        designButton(okButton, 20, 20, "OK");
        fillMap();
        compose();
        addButtonListeners(callback);
    }

    public void designButton(JButton button, int width, int height, String name) {
        button.setText(name);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.pink);
        button.setForeground(Color.DARK_GRAY);
        button.setFocusPainted(false);
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        LinkedListTabulatedFunctionWindow app = new LinkedListTabulatedFunctionWindow(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFunctionMap.put("Гиперболический тангенс", new HyperbolicTan());
        nameFunctionMap.put("Единичная функция", new UnitFunction());
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Обратный косинус", new InverseCosFunction());
        nameFunctionMap.put("Тождественная функция", new IdentityFunction());
        String[] functions = new String[6];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = factory.create(selectedFunction, from, to, count);
                callback.accept(function);
                this.dispose();
            } catch (Exception e) {
                ErrorsWindow errorWindow = new ErrorsWindow(this, e);
                errorWindow.showErrorsWindow(this, e);
            }
        });
    }
}
