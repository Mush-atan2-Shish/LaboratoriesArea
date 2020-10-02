package ru.ssau.tk.Ssau.Laba2.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {

    protected int count;
    protected double[] xValues;
    protected double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        xValues[0] = xFrom;
        xValues[count - 1] = xTo;
        for (int i = 1; i < count - 1; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
        }
        for (int i = 0; i < count; i++) {
            yValues[i] = source.apply(xValues[i]);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (x == xValues[i]) {
                return i;
            }
        }
        int i = 0;
        do {
            i++;
        }
        while (x > xValues[i]);
        return i - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return yValues[0] + (yValues[1] - yValues[0]) * (x - xValues[0]) / (xValues[1] - xValues[0]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return yValues[count - 2] + (yValues[count - 1] - yValues[count - 2]) * (x - xValues[count - 2]) / (xValues[count - 1] - xValues[count - 2]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return yValues[floorIndex] + (yValues[floorIndex + 1] - yValues[floorIndex]) * (x - xValues[floorIndex]) / (xValues[floorIndex + 1] - xValues[floorIndex]);
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (x == xValues[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (y == yValues[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }
}
