package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};

    @Test
    public void testApply() {
        ArrayTabulatedFunction testingApply = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingApply.apply(1.0), 2.0, delta);
        assertEquals(testingApply.apply(1.56), 2.56, delta);
        assertEquals(testingApply.apply(1.22), 2.22, delta);
        assertNotEquals(testingApply.apply(1.22), 1.23, delta);
    }

    @Test
    public void testFloorIndexOfX() {

        ArrayTabulatedFunction testingFloorIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 2, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 3, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.32), 4);

    }

    @Test
    public void testExtrapolateLeft() {

        ArrayTabulatedFunction testingExtrapolateLeft = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.0), 2.0, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.2), -0.2, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.05), 2.5);

    }

    @Test
    public void testExtrapolateRight() {

        ArrayTabulatedFunction testingExtrapolateRight = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.56, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(2.56), 3.56, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.1);

    }

    @Test
    public void testInterpolate() {

        ArrayTabulatedFunction testingInterpolate = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.00001;
        assertEquals(testingInterpolate.interpolate(1.22, 1), 2.22, delta);
        assertEquals(testingInterpolate.interpolate(1.247, 1), 2.247, delta);
        assertNotEquals(testingInterpolate.interpolate(1.247, 1), 4.237, delta);

    }

    @Test
    public void testGetX() {

        ArrayTabulatedFunction testingGetX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetX.getX(1), 1.2, delta);
        assertEquals(testingGetX.getX(2), 1.3, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);

    }

    @Test
    public void testGetY() {

        ArrayTabulatedFunction testingGetY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetY.getY(1), 2.2, delta);
        assertEquals(testingGetY.getY(2), 2.3, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);

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

    }

    @Test
    public void testIndexOfX() {

        ArrayTabulatedFunction testingIndexOfX = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfX.indexOfX(1.3), 2, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 3, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);

    }

    @Test
    public void testIndexOfY() {

        ArrayTabulatedFunction testingIndexOfY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfY.indexOfY(2.5), 4, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 3, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);

    }

    @Test
    public void testLeftBound() {

        ArrayTabulatedFunction testingLeftBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingLeftBound.leftBound(), 1.1, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, 0.0001);

    }

    @Test
    public void testRightBound() {

        ArrayTabulatedFunction testingRightBound = new ArrayTabulatedFunction(xValues, yValues);
        assertEquals(testingRightBound.rightBound(), 1.5, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.4, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.3, 0.0001);

    }
}
