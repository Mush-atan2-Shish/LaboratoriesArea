package ru.ssau.tk.Ssau.Laba2.concurrent;

import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    public ReadWriteTask(TabulatedFunction function) {
        this.function = function;
    }

    private final TabulatedFunction function;

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < function.getCount(); i++) {
            x = function.getX(i);
            synchronized (function) {
                y = function.getY(i);
                System.out.printf("%s, before write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
                function.setY(i, y + 1);
                y = function.getY(i);
            }
            System.out.printf("%s, after write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
    }
}
