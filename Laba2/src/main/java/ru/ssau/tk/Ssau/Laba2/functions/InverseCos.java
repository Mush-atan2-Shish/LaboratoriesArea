package ru.ssau.tk.Ssau.Laba2.functions;

public class InverseCos implements MathFunction{

    @Override
    public double apply(double x) {
        return 1 / Math.cos(x);
    }
}
