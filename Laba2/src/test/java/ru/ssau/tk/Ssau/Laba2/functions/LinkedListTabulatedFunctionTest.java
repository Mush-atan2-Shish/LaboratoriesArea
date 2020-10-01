package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};

    @Test
    public void testFloorIndexOfX() {

        LinkedListTabulatedFunction testingFloorIndexOfX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 2, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 3, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.32), 4);

    }

    @Test
    public void testExtrapolateLeft() {

        LinkedListTabulatedFunction testingExtrapolateLeft = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.0), 2.1, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.2), 2.1, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.05), 2.5);

    }

    @Test
    public void testExtrapolateRight() {

        LinkedListTabulatedFunction testingExtrapolateRight = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.1, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(2.56), 2.1, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.5);

    }

    @Test
    public void testInterpolate() {

        LinkedListTabulatedFunction testingInterpolate = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.00001;
        assertEquals(testingInterpolate.interpolate(1.22, 1), 2.22, delta);
        assertEquals(testingInterpolate.interpolate(1.15, 1), 2.15, delta);
        assertNotEquals(testingInterpolate.interpolate(1.247, 1), 4.237, delta);

    }

    @Test
    public void testGetX() {

        LinkedListTabulatedFunction testingGetX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetX.getX(1), 1.2, delta);
        assertEquals(testingGetX.getX(2), 1.3, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);

    }

    @Test
    public void testGetY() {

        LinkedListTabulatedFunction testingGetY = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetY.getY(1), 2.2, delta);
        assertEquals(testingGetY.getY(2), 2.3, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);

    }

    @Test
    public void testSetY() {

        LinkedListTabulatedFunction testingSetY = new LinkedListTabulatedFunction(xValues, yValues);
        testingSetY.setY(1, 2.23);
        final double delta = 0.0001;
        assertEquals(testingSetY.getY(1), 2.23, delta);
        testingSetY.setY(2, 2.45);
        assertEquals(testingSetY.getY(2), 2.45, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);

    }

    @Test
    public void testIndexOfX() {

        LinkedListTabulatedFunction testingIndexOfX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfX.indexOfX(1.3), 2, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 3, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);

    }

    @Test
    public void testIndexOfY() {

        LinkedListTabulatedFunction testingIndexOfY = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfY.indexOfY(2.5), 4, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 3, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);

    }

    @Test
    public void testLeftBound() {

        LinkedListTabulatedFunction testingLeftBound = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(testingLeftBound.leftBound(), 1.1, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, 0.0001);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, 0.0001);

    }

    @Test
    public void testRightBound() {

        LinkedListTabulatedFunction testingRightBound = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(testingRightBound.rightBound(), 1.5, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.4, 0.0001);
        assertNotEquals(testingRightBound.rightBound(), 1.3, 0.0001);

    }
}
