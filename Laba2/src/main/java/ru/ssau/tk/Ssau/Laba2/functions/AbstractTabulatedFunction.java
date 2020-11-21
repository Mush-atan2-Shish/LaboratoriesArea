package ru.ssau.tk.Ssau.Laba2.functions;

import ru.ssau.tk.Ssau.Laba2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Ssau.Laba2.exceptions.DifferentLengthOfArraysException;

import java.io.Serializable;

public abstract class AbstractTabulatedFunction implements TabulatedFunction, Serializable {

    private static final long serialVersionUID = 6774919160548622675L;
    protected int count;

    @Override
    public int getCount() {
        return count;
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");

        for (Point point : this) {
            str.append("[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        }
        return interpolate(x, floorIndexOfX(x));
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("xValues and yValues have different lengths");
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if ((xValues[i + 1] <= xValues[i])) {
                throw new ArrayIsNotSortedException("xValues is not sort");
            }
        }
    }
}
