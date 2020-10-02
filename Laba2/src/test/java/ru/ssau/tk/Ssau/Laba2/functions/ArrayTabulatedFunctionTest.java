package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};
    private final MathFunction source = new SqrFunction();
    private final ArrayTabulatedFunction testingArrayFunction = new ArrayTabulatedFunction(source, 1, 16, 6);

    @Test
    public void testGetCount() {

        assertEquals(testingArrayFunction.getCount(), 6);
        assertNotEquals(testingArrayFunction.getCount(), 7);
        assertNotEquals(testingArrayFunction.getCount(), 5);

    }

    @Test
    public void testApply() {
        ArrayTabulatedFunction testingApply = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingApply.apply(1.0), 2.0, delta);
        assertEquals(testingApply.apply(1.56), 2.56, delta);
        assertEquals(testingApply.apply(1.22), 2.22, delta);
        assertNotEquals(testingApply.apply(1.22), 1.23, delta);
        assertEquals(testingArrayFunction.apply(-3.2), -20, delta);
        assertEquals(testingArrayFunction.apply(21), 401, delta);
        assertEquals(testingArrayFunction.apply(7.25), 53.25, delta);
        assertNotEquals(testingArrayFunction.apply(7.25), 59.25, delta);
    }

    @Test
    public void testFloorIndexOfX() {

        ArrayTabulatedFunction testingFloorIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 2, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 3, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.32), 4);
        assertEquals(testingArrayFunction.floorIndexOfX(7.39), 2);
        assertEquals(testingArrayFunction.floorIndexOfX(13.78), 4);
        assertNotEquals(testingArrayFunction.floorIndexOfX(13.78), 3);

    }

    @Test
    public void testExtrapolateLeft() {

        ArrayTabulatedFunction testingExtrapolateLeft = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.0), 2.0, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.2), -0.2, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.05), 2.5);
        assertEquals(testingArrayFunction.extrapolateLeft(-1), -9, delta);
        assertEquals(testingArrayFunction.extrapolateLeft(-2), -14, delta);
        assertNotEquals(testingArrayFunction.extrapolateLeft(-2), 7, delta);

    }

    @Test
    public void testExtrapolateRight() {

        ArrayTabulatedFunction testingExtrapolateRight = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.56, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(2.56), 3.56, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.1);
        assertEquals(testingArrayFunction.extrapolateRight(20), 372, delta);
        assertEquals(testingArrayFunction.extrapolateRight(25), 517, delta);
        assertNotEquals(testingArrayFunction.extrapolateRight(25), 89, delta);

    }

    @Test
    public void testInterpolate() {

        ArrayTabulatedFunction testingInterpolate = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.00001;
        assertEquals(testingInterpolate.interpolate(1.22, 1), 2.22, delta);
        assertEquals(testingInterpolate.interpolate(1.247, 1), 2.247, delta);
        assertNotEquals(testingInterpolate.interpolate(1.247, 1), 4.237, delta);
        assertEquals(testingArrayFunction.interpolate(11, 3), 123, delta);
        assertEquals(testingArrayFunction.interpolate(12, 3), 146, delta);
        assertNotEquals(testingArrayFunction.interpolate(11, 3), 121, delta);

    }

    @Test
    public void testGetX() {

        ArrayTabulatedFunction testingGetX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetX.getX(1), 1.2, delta);
        assertEquals(testingGetX.getX(2), 1.3, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);
        assertEquals(testingArrayFunction.getX(1), 4, delta);
        assertEquals(testingArrayFunction.getX(3), 10, delta);
        assertNotEquals(testingArrayFunction.getX(3), 16, delta);

    }

    @Test
    public void testGetY() {

        ArrayTabulatedFunction testingGetY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetY.getY(1), 2.2, delta);
        assertEquals(testingGetY.getY(2), 2.3, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);
        assertEquals(testingArrayFunction.getY(1), 16, delta);
        assertEquals(testingArrayFunction.getY(3), 100, delta);
        assertNotEquals(testingArrayFunction.getY(3), 121, delta);

    }

    @Test
    public void testSetY() {

        ArrayTabulatedFunction testingSetY = new ArrayTabulatedFunction(xValues, yValues);
        testingSetY.setY(1, 2.23);
        final double delta = 0.0001;
        assertEquals(testingSetY.getY(1), 2.23, delta);
        testingSetY.setY(2, 2.45);
        assertEquals(testingSetY.getY(2), 2.45, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);
        testingArrayFunction.setY(2, 78);
        assertEquals(testingArrayFunction.getY(2), 78, delta);
        assertNotEquals(testingArrayFunction.getY(2), 49, delta);
        testingArrayFunction.setY(4, 11);
        assertEquals(testingArrayFunction.getY(4), 11, delta);

    }

    @Test
    public void testIndexOfX() {

        ArrayTabulatedFunction testingIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfX.indexOfX(1.3), 2, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 3, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);
        assertEquals(testingArrayFunction.indexOfX(13), 4);
        assertEquals(testingArrayFunction.indexOfX(16), 5);
        assertNotEquals(testingArrayFunction.indexOfX(13), 1);

    }

    @Test
    public void testIndexOfY() {

        ArrayTabulatedFunction testingIndexOfY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfY.indexOfY(2.5), 4, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 3, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);
        assertEquals(testingArrayFunction.indexOfY(49), 2, delta);
        assertEquals(testingArrayFunction.indexOfY(169), 4, delta);
        assertNotEquals(testingArrayFunction.indexOfY(49), 6, delta);

    }

    @Test
    public void testLeftBound() {

        ArrayTabulatedFunction testingLeftBound = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingLeftBound.leftBound(), 1.1, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, delta);
        assertEquals(testingArrayFunction.leftBound(), 1, delta);
        assertNotEquals(testingArrayFunction.leftBound(), 2, delta);
        assertNotEquals(testingArrayFunction.leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {

        ArrayTabulatedFunction testingRightBound = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingRightBound.rightBound(), 1.5, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.4, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.3, delta);
        assertEquals(testingArrayFunction.rightBound(), 16, delta);
        assertNotEquals(testingArrayFunction.rightBound(), 19, delta);
        assertNotEquals(testingArrayFunction.rightBound(), 27, delta);

    }
}
