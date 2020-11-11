package ru.ssau.tk.Ssau.Laba2.operations;

import ru.ssau.tk.Ssau.Laba2.functions.Point;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point myPoint : tabulatedFunction) {
            points[i++] = myPoint;
        }
        return points;
    }
}
