package ru.ssau.tk.Ssau.Laba2.concurrent;

import ru.ssau.tk.Ssau.Laba2.functions.ConstantFunction;
import ru.ssau.tk.Ssau.Laba2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);

        AddingTask addingTask = new AddingTask(function);
        MultiplyingTask multiplyingTask = new MultiplyingTask(function);

        Thread thread1 = new Thread(multiplyingTask);
        thread1.start();
        Thread thread2 = new Thread(multiplyingTask);
        thread2.start();
        Thread thread3 = new Thread(addingTask);
        thread3.start();

        Thread.sleep(2000);

        System.out.println(function.toString());
    }
}
