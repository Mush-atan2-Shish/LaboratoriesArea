package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};
    private final MathFunction source = new SqrFunction();
    private final LinkedListTabulatedFunction testingListFunction = new LinkedListTabulatedFunction(source, 1, 16, 6);


    @Test
    public void testGetCount() {

        assertEquals(testingListFunction.getCount(), 6);
        assertNotEquals(testingListFunction.getCount(), 7);
        assertNotEquals(testingListFunction.getCount(), 5);

    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction testingApply = new LinkedListTabulatedFunction(xValues, yValues);

        final double delta = 0.0001;
        assertEquals(testingListFunction.apply(-3.2), -20, delta);
        assertEquals(testingListFunction.apply(21), 401, delta);
        assertEquals(testingListFunction.apply(7.25), 53.25, delta);
        assertNotEquals(testingListFunction.apply(7.25), 59.25, delta);
        assertEquals(testingApply.apply(1.0), 2.0, delta);
        assertEquals(testingApply.apply(1.56), 2.56, delta);
        assertEquals(testingApply.apply(1.22), 2.22, delta);
        assertNotEquals(testingApply.apply(1.22), 4.22, delta);

    }

    @Test
    public void testFloorNodeOfX() {

        LinkedListTabulatedFunction testingFloorNodeOfX = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(testingListFunction.floorNodeOfX(16).x, 16, 0.0001);
        assertEquals(testingListFunction.floorNodeOfX(13).x, 13, 0.0001);
        assertNotEquals(testingListFunction.floorNodeOfX(13).x, 10, 0.0001);

    }

    @Test
    public void testFloorIndexOfX() {

        LinkedListTabulatedFunction testingFloorIndexOfX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.35), 2, delta);
        assertEquals(testingFloorIndexOfX.floorIndexOfX(1.43), 3, delta);
        assertNotEquals(testingFloorIndexOfX.floorIndexOfX(1.32), 4);
        assertEquals(testingListFunction.floorIndexOfX(7.39), 2);
        assertEquals(testingListFunction.floorIndexOfX(13.78), 4);
        assertNotEquals(testingListFunction.floorIndexOfX(13.78), 3);

    }

    @Test
    public void testExtrapolateLeft() {

        LinkedListTabulatedFunction testingExtrapolateLeft = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateLeft.extrapolateLeft(1.0), 2.0, delta);
        assertEquals(testingExtrapolateLeft.extrapolateLeft(-1.2), -0.2, delta);
        assertNotEquals(testingExtrapolateLeft.extrapolateLeft(1.05), 2.5);
        assertEquals(testingListFunction.extrapolateLeft(-1), -9, delta);
        assertEquals(testingListFunction.extrapolateLeft(-2), -14, delta);
        assertNotEquals(testingListFunction.extrapolateLeft(-2), 7, delta);

    }

    @Test
    public void testExtrapolateRight() {

        LinkedListTabulatedFunction testingExtrapolateRight = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.56, delta);
        assertEquals(testingExtrapolateRight.extrapolateRight(2.56), 3.56, delta);
        assertNotEquals(testingExtrapolateRight.extrapolateRight(1.56), 2.5);
        assertEquals(testingListFunction.extrapolateRight(20), 372, delta);
        assertEquals(testingListFunction.extrapolateRight(25), 517, delta);
        assertNotEquals(testingListFunction.extrapolateRight(25), 89, delta);

    }

    @Test
    public void testInterpolate() {

        LinkedListTabulatedFunction testingInterpolate = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingInterpolate.interpolate(1.22, 1), 2.22, delta);
        assertEquals(testingInterpolate.interpolate(1.247, 1), 2.247, delta);
        assertNotEquals(testingInterpolate.interpolate(1.247, 1), 4.237, delta);
        assertEquals(testingListFunction.interpolate(11, 3), 123, delta);
        assertEquals(testingListFunction.interpolate(12, 3), 146, delta);
        assertNotEquals(testingListFunction.interpolate(11, 3), 121, delta);

    }

    @Test
    public void testGetX() {

        LinkedListTabulatedFunction testingGetX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetX.getX(1), 1.2, delta);
        assertEquals(testingGetX.getX(2), 1.3, delta);
        assertNotEquals(testingGetX.getX(1), 2.2, delta);
        assertEquals(testingListFunction.getX(1), 4, delta);
        assertEquals(testingListFunction.getX(3), 10, delta);
        assertNotEquals(testingListFunction.getX(3), 16, delta);

    }

    @Test
    public void testGetY() {

        LinkedListTabulatedFunction testingGetY = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingGetY.getY(1), 2.2, delta);
        assertEquals(testingGetY.getY(2), 2.3, delta);
        assertNotEquals(testingGetY.getY(1), 3.2, delta);
        assertEquals(testingListFunction.getY(1), 16, delta);
        assertEquals(testingListFunction.getY(3), 100, delta);
        assertNotEquals(testingListFunction.getY(3), 121, delta);

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
        testingListFunction.setY(2, 78);
        assertEquals(testingListFunction.getY(2), 78, delta);
        assertNotEquals(testingListFunction.getY(2), 49, delta);
        testingListFunction.setY(4, 11);
        assertEquals(testingListFunction.getY(4), 11, delta);

    }

    @Test
    public void testIndexOfX() {

        LinkedListTabulatedFunction testingIndexOfX = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfX.indexOfX(1.3), 2, delta);
        assertEquals(testingIndexOfX.indexOfX(1.4), 3, delta);
        assertNotEquals(testingIndexOfX.indexOfX(1.5), 1, delta);
        assertEquals(testingListFunction.indexOfX(13), 4);
        assertEquals(testingListFunction.indexOfX(16), 5);
        assertNotEquals(testingListFunction.indexOfX(13), 1);

    }

    @Test
    public void testIndexOfY() {

        LinkedListTabulatedFunction testingIndexOfY = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingIndexOfY.indexOfY(2.5), 4, delta);
        assertEquals(testingIndexOfY.indexOfY(2.4), 3, delta);
        assertNotEquals(testingIndexOfY.indexOfY(2.1), 4, delta);
        assertEquals(testingListFunction.indexOfY(49), 2, delta);
        assertEquals(testingListFunction.indexOfY(169), 4, delta);
        assertNotEquals(testingListFunction.indexOfY(49), 6, delta);

    }

    @Test
    public void testLeftBound() {

        LinkedListTabulatedFunction testingLeftBound = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingLeftBound.leftBound(), 1.1, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.2, delta);
        assertNotEquals(testingLeftBound.leftBound(), 1.5, delta);
        assertEquals(testingListFunction.leftBound(), 1, delta);
        assertNotEquals(testingListFunction.leftBound(), 2, delta);
        assertNotEquals(testingListFunction.leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {

        LinkedListTabulatedFunction testingRightBound = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingRightBound.rightBound(), 1.5, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.4, delta);
        assertNotEquals(testingRightBound.rightBound(), 1.3, delta);
        assertEquals(testingListFunction.rightBound(), 16, delta);
        assertNotEquals(testingListFunction.rightBound(), 19, delta);
        assertNotEquals(testingListFunction.rightBound(), 27, delta);

    }
}
