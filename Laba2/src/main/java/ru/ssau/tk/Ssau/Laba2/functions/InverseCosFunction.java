package ru.ssau.tk.Ssau.Laba2.functions;

public class InverseCosFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return 1 / Math.cos(x);
    }
}
