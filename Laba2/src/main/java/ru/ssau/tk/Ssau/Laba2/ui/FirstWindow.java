package ru.ssau.tk.Ssau.Laba2.ui;

import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame {

    public FirstWindow() {
        super("First Window"); // название окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // при нажатии на X программа завершается
        setSize(600, 600); // размеры окна
        setLocationRelativeTo(null); // центрируем окно относительно экрана с учётом заданных выше размеров
        setLayout(new FlowLayout()); // самый простой менеджер компоновки
        JTextArea textArea = new JTextArea("   Введите количество точек функции"); // текст в текстовом поле
        JButton button = new JButton("ОК"); // текст кнопки
        button.addActionListener(event -> { // слушатель, заданный с помощью лямбда-выражения
            // создаём экземпляр второго окна, передавая туда callback-функцию
            // задача callback-функции заключается в том, чтобы в текстовое поле этого окна записалась дата,
            // созданная во втором окне; функция будет брать дату, которое ей передаёт второе окно
            SecondWindow secondWindow = new SecondWindow();
            secondWindow.setVisible(true); // показываем второе окно
        });
        getContentPane().add(textArea); // добавлеем текстовое поле
        getContentPane().add(button); // добавляем кнопку
    }

    public static void main(String[] args) {
        FirstWindow firstWindow = new FirstWindow(); // создаём первое окно
        firstWindow.setVisible(true); // показываем первое окно
    }

}