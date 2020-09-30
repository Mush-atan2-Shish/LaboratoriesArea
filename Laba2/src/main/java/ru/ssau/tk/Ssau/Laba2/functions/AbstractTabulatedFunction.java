package ru.ssau.tk.Ssau.Laba2.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    private int count;

    @Override
    public int getCount() {
        return count;
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected abstract double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }
}
