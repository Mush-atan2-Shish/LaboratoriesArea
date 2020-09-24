package ru.ssau.tk.Ssau.Laba2.functions;

public class HyperbolicTan implements MathFunction {


    @Override
    public double apply(double x) {
        return Math.tanh(x);
    }
}
