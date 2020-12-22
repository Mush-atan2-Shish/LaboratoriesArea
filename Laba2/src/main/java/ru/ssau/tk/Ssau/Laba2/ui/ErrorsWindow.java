package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Ssau.Laba2.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;

public class ErrorsWindow {
    ErrorsWindow(Component parent, Exception e) {
        showErrorsWindow(parent, e);
    }

    public void showErrorsWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Длина массивов неодинакова";
        }
        if (e instanceof IllegalArgumentException) {
            return "Задана некорректная функция";
        }
        {
            e.printStackTrace();
            return "Неизвестная ошибка";
        }
    }
}
