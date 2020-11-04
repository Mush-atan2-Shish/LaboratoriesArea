package ru.ssau.tk.Ssau.Laba2.functions;

import exceptions.InterpolationException;

import java.util.Iterator;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {

    protected int count;
    protected double[] xValues;
    protected double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Length less than 2 points");
        }
        if ((xFrom >= xTo) || (xFrom < 0) | (xTo < 0)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        xValues = new double[count];
        yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X is less than the left border");
        }
        for (int i = 0; i <= count - 1; i++) {
            if (x < xValues[i]) {
                return i - 1;
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || x > xValues[floorIndex + 1]) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public double getX(int index) {
        checkIndex(index);
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        checkIndex(index);
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        checkIndex(index);
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

    @Override
    public void insert(double x, double y) {
        for (int i = 0; i < count; i++) {
            if (x == xValues[i]) {
                setY(i, y);
                return;
            }
        }
        {
            double[] xNewValues = new double[count + 1];
            double[] yNewValues = new double[count + 1];
            if (floorIndexOfX(x) == 0) {
                xNewValues[0] = x;
                yNewValues[0] = y;
                System.arraycopy(xValues, 0, xNewValues, 1, count);
                System.arraycopy(yValues, 0, yNewValues, 1, count);
            } else {
                System.arraycopy(xValues, 0, xNewValues, 0, floorIndexOfX(x) + 1);
                System.arraycopy(yValues, 0, yNewValues, 0, floorIndexOfX(x) + 1);
                xNewValues[floorIndexOfX(x) + 1] = x;
                yNewValues[floorIndexOfX(x) + 1] = y;
                System.arraycopy(xValues, floorIndexOfX(x) + 1, xNewValues, floorIndexOfX(x) + 2, count - floorIndexOfX(x) - 1);
                System.arraycopy(yValues, floorIndexOfX(x) + 1, yNewValues, floorIndexOfX(x) + 2, count - floorIndexOfX(x) - 1);
            }
            this.xValues = xNewValues;
            this.yValues = yNewValues;
            count++;
        }
    }

    @Override
    public void remove(int index) {
        double[] xTempValues = new double[count + 1];
        double[] yTempValues = new double[count + 1];
        System.arraycopy(xValues, 0, xTempValues, 0, index);
        System.arraycopy(yValues, 0, yTempValues, 0, index);
        System.arraycopy(xValues, index + 1, xTempValues, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yTempValues, index, count - index - 1);
        this.xValues = xTempValues;
        this.yValues = yTempValues;
        count--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > count - 1) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds of array");
        }
    }

    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }
}
