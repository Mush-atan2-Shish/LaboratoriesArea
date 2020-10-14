package ru.ssau.tk.Ssau.Laba2.functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {

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
        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0])
            return 0;
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
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

    @Override
    public void insert(double x, double y) {
        int indexOfX = indexOfX(x);
        if (indexOfX != -1) {
            setY(indexOfX, y);
        }
        indexOfX = floorIndexOfX(x);
        double[] newXValues = new double[count + 1];
        double[] newYValues = new double[count + 1];
        if (indexOfX == 0) {
            newXValues[0] = x;
            newYValues[0] = y;
            System.arraycopy(xValues, 0, newXValues, 1, count);
            System.arraycopy(yValues, 0, newYValues, 1, count);
        }
        if (indexOfX == count) {
            System.arraycopy(xValues, 0, newXValues, 0, count);
            System.arraycopy(yValues, 0, newYValues, 0, count);
            newXValues[count] = x;
            newYValues[count] = y;
        }
        if ((indexOfX != 0) && (indexOfX != count)) {
            System.arraycopy(xValues, 0, newXValues, 0, indexOfX + 1);
            System.arraycopy(yValues, 0, newYValues, 0, indexOfX + 1);
            newXValues[indexOfX + 1] = x;
            newYValues[indexOfX + 1] = y;
            System.arraycopy(xValues, indexOfX, newXValues, indexOfX + 1, count - indexOfX);
            System.arraycopy(yValues, indexOfX, newYValues, indexOfX + 1, count - indexOfX);
        }
        this.xValues = newXValues;
        this.yValues = newYValues;
        count++;
    }

    @Override
    public void remove(int index) {
        double[] xTempValues = new double[count];
        double[] yTempValues = new double[count];

        if (index == 0) {
            System.arraycopy(xValues, 1, xTempValues, 0, count - 1);
            System.arraycopy(yValues, 1, yTempValues, 0, count - 1);
        }

        if (index == count) {
            System.arraycopy(xValues, 0, xTempValues, 0, count - 1);
            System.arraycopy(yValues, 0, yTempValues, 0, count - 1);

        } else {
            System.arraycopy(xValues, 0, xTempValues, 0, index);
            System.arraycopy(yValues, 0, yTempValues, 0, index);
            System.arraycopy(xValues, index + 1, xTempValues, index, count - index - 1);
            System.arraycopy(yValues, index + 1, yTempValues, index, count - index - 1);
        }

        this.xValues = xTempValues;
        this.yValues = yTempValues;
        count--;
    }
}
