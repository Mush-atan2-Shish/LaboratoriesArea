package ru.ssau.tk.Ssau.Laba2.functions;

public interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
