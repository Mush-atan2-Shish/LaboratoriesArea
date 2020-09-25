package ru.ssau.tk.Ssau.Laba2.functions;

public class SqrFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
