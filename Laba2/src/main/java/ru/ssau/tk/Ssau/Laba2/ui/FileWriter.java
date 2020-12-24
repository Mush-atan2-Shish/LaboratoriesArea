package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class FileWriter extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField directory = new JTextField();
    private JButton save = new JButton("Сохранить функцию");
    private TabulatedFunction function;

    public FileWriter(TabulatedFunction func) {
        setTitle("Сохранить функцию");
        setModal(true);
        this.function = func;
        JPanel panel = new JPanel();
        addListenerForSaveButton();
        save.setFocusPainted(false);
        save.setBackground(Color.pink);
        panel.add(save);
        Container cp = getContentPane();
        cp.add(panel, BorderLayout.SOUTH);
        directory.setEditable(false);
        filename.setEditable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(filename);
        panel.add(directory);
        cp.add(panel, BorderLayout.NORTH);
    }

    public void addListenerForSaveButton() {
        save.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Text files", "txt"));
            chooser.setAcceptAllFileFilterUsed(false);
            int rVal = chooser.showSaveDialog(FileWriter.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(chooser.getSelectedFile().getName());
                directory.setText(chooser.getCurrentDirectory().toString());
                File file = chooser.getSelectedFile();
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, function);
                    } catch (Exception e) {
                        new ErrorsWindow(this, e);
                    }
                }
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("Отмена");
                directory.setText("");
            }
        });
    }

    public static void main(TabulatedFunction func) {
        run(new FileWriter(func), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
