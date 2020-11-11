package ru.ssau.tk.Ssau.Laba2.functions.factory;

import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
