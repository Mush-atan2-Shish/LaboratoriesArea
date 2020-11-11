package ru.ssau.tk.Ssau.Laba2.operations;

import ru.ssau.tk.Ssau.Laba2.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction>  {
    T derive(T function);
}
