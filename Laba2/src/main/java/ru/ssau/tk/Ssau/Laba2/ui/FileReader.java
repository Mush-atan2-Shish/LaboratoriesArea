package ru.ssau.tk.Ssau.Laba2.ui;

import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

public class FileReader extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField directory = new JTextField();
    private JButton download = new JButton("Выбрать файл");
    private TabulatedFunction function;
    private TabulatedFunctionFactory factory;

    public FileReader(Consumer<? super TabulatedFunction> callback) {
        setTitle("Открыть функцию");
        setModal(true);
        JPanel panel = new JPanel();
        addListenerForOpenButton(callback);
        download.setBackground(Color.pink);
        download.setFocusPainted(false);
        panel.add(download);
        Container pane = getContentPane();
        pane.add(panel, BorderLayout.SOUTH);
        directory.setEditable(false);
        filename.setEditable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(filename);
        panel.add(directory);
        pane.add(panel, BorderLayout.NORTH);
    }

    public void addListenerForOpenButton(Consumer<? super TabulatedFunction> callback) {
        download.addActionListener(event -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Bin files", "bin"));
            chooser.setAcceptAllFileFilterUsed(false);
            int rVal = chooser.showOpenDialog(FileReader.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(chooser.getSelectedFile().getName());
                directory.setText(chooser.getCurrentDirectory().toString());
                File file = chooser.getSelectedFile();
                factory = new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        function = FunctionsIO.readTabulatedFunction(inputStream, factory);
                        callback.accept(function);
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

    public static void main(Consumer<? super TabulatedFunction> callback) {
        run(new FileReader(callback), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
