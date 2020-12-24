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
    private TabulatedFunction function;

    public FileWriter(TabulatedFunction func) {
        setModal(true);
        this.function = func;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Bin files", "bin"));
        chooser.setAcceptAllFileFilterUsed(false);
        int rVal = chooser.showSaveDialog(FileWriter.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                    FunctionsIO.writeTabulatedFunction(outputStream, function);
                } catch (Exception e) {
                    new ErrorsWindow(this, e);
                }
            }
        }
    }

    public static void main(TabulatedFunction func) {
        new FileWriter(func);
    }
}
