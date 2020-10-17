package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};
    private final MathFunction source = new SqrFunction();

    private LinkedListTabulatedFunction getListThroughListFunction() {
        return new LinkedListTabulatedFunction(source, 1, 16, 6);
    }

    private LinkedListTabulatedFunction getListThroughArrayFunction() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    @Test
    public void testInsert() {
        LinkedListTabulatedFunction testingInsert = new LinkedListTabulatedFunction(xValues, yValues);
        testingInsert.insert(1.25, 2.0);
        assertEquals(testingInsert.getCount(), 6);
        assertEquals(testingInsert.getX(2), 1.25);
        assertEquals(testingInsert.getY(2), 2.0);
        testingInsert.insert(1.07, 2.54);
        assertEquals(testingInsert.getX(0), 1.07);
        assertEquals(testingInsert.getY(0), 2.54);
        testingInsert.insert(1.67, 2.14);
        assertEquals(testingInsert.getX(7), 1.67);
        assertEquals(testingInsert.getY(7), 2.14);

        LinkedListTabulatedFunction testingInsertList = new LinkedListTabulatedFunction(source, 1, 16, 6);
        testingInsertList.insert(4.25, 2.0);
        assertEquals(testingInsertList.getX(2), 4.25);
        assertEquals(testingInsertList.getY(2), 2.0);
        testingInsertList.insert(1.07, 2.54);
        assertEquals(testingInsertList.getX(0), 1.07);
        assertEquals(testingInsertList.getY(0), 2.54);
        testingInsertList.insert(17.67, 20.14);
        assertEquals(testingInsertList.getX(8), 17.67);
        assertEquals(testingInsertList.getY(8), 20.14);

    }

    @Test
    public void testGetCount() {

        assertEquals(getListThroughListFunction().getCount(), 6);
        assertNotEquals(getListThroughListFunction().getCount(), 7);
        assertNotEquals(getListThroughListFunction().getCount(), 5);

    }

    @Test
    public void testRemove() {

        getListThroughListFunction().remove(3);
        assertEquals(getListThroughListFunction().getX(3), 10);
        getListThroughListFunction().remove(1);
        assertEquals(getListThroughListFunction().getX(1), 4);

    }

    @Test
    public void testAddNode() {

        getListThroughListFunction().addNode(7, 14);
        final double delta = 0.0001;
        assertEquals(getListThroughListFunction().leftBound(), 1, delta);
        assertEquals(getListThroughListFunction().rightBound(), 16, delta);
        assertNotEquals(getListThroughListFunction().getX(0), 8, delta);
        assertEquals(getListThroughListFunction().extrapolateRight(8), 24, delta);

    }

    @Test
    public void testApply() {

        final double delta = 0.0001;
        assertEquals(getListThroughListFunction().apply(-3.2), -20, delta);
        assertEquals(getListThroughListFunction().apply(21), 401, delta);
        assertEquals(getListThroughListFunction().apply(7.25), 36.75, delta);
        assertNotEquals(getListThroughListFunction().apply(7.25), 59.25, delta);
        assertEquals(getListThroughArrayFunction().apply(1.0), 2.0, delta);
        assertEquals(getListThroughArrayFunction().apply(1.56), 2.56, delta);
        assertEquals(getListThroughArrayFunction().apply(1.22), 2.22, delta);
        assertNotEquals(getListThroughArrayFunction().apply(1.22), 4.22, delta);

    }

    @Test
    public void testFloorNodeOfX() {

        assertEquals(getListThroughListFunction().floorNodeOfX(16).x, 16, 0.0001);
        assertEquals(getListThroughListFunction().floorNodeOfX(13).x, 13, 0.0001);
        assertNotEquals(getListThroughListFunction().floorNodeOfX(13).x, 10, 0.0001);

    }

    @Test
    public void testFloorIndexOfX() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().floorIndexOfX(1.35), 2, delta);
        assertEquals(getListThroughArrayFunction().floorIndexOfX(1.43), 3, delta);
        assertNotEquals(getListThroughArrayFunction().floorIndexOfX(1.32), 4);
        assertEquals(getListThroughListFunction().floorIndexOfX(7.39), 2);
        assertEquals(getListThroughListFunction().floorIndexOfX(13.78), 4);
        assertNotEquals(getListThroughListFunction().floorIndexOfX(13.78), 3);

    }

    @Test
    public void testExtrapolateLeft() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().extrapolateLeft(1.0), 2.0, delta);
        assertEquals(getListThroughArrayFunction().extrapolateLeft(-1.2), -0.2, delta);
        assertNotEquals(getListThroughArrayFunction().extrapolateLeft(1.05), 2.5);
        assertEquals(getListThroughListFunction().extrapolateLeft(-1), -9, delta);
        assertEquals(getListThroughListFunction().extrapolateLeft(-2), -14, delta);
        assertNotEquals(getListThroughListFunction().extrapolateLeft(-2), 7, delta);

    }

    @Test
    public void testExtrapolateRight() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().extrapolateRight(1.56), 2.56, delta);
        assertEquals(getListThroughArrayFunction().extrapolateRight(2.56), 3.56, delta);
        assertNotEquals(getListThroughArrayFunction().extrapolateRight(1.56), 2.5);
        assertEquals(getListThroughListFunction().extrapolateRight(20), 372, delta);
        assertEquals(getListThroughListFunction().extrapolateRight(25), 517, delta);
        assertNotEquals(getListThroughListFunction().extrapolateRight(25), 89, delta);

    }

    @Test
    public void testInterpolate() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().interpolate(1.22, 1), 2.22, delta);
        assertEquals(getListThroughArrayFunction().interpolate(1.247, 1), 2.247, delta);
        assertNotEquals(getListThroughArrayFunction().interpolate(1.247, 1), 4.237, delta);
        assertEquals(getListThroughListFunction().interpolate(11, 3), 123, delta);
        assertEquals(getListThroughListFunction().interpolate(12, 3), 146, delta);
        assertNotEquals(getListThroughListFunction().interpolate(11, 3), 121, delta);

    }

    @Test
    public void testGetX() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().getX(1), 1.2, delta);
        assertEquals(getListThroughArrayFunction().getX(2), 1.3, delta);
        assertNotEquals(getListThroughArrayFunction().getX(1), 2.2, delta);
        assertEquals(getListThroughListFunction().getX(1), 4, delta);
        assertEquals(getListThroughListFunction().getX(3), 10, delta);
        assertNotEquals(getListThroughListFunction().getX(3), 16, delta);

    }

    @Test
    public void testGetY() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().getY(1), 2.2, delta);
        assertEquals(getListThroughArrayFunction().getY(2), 2.3, delta);
        assertNotEquals(getListThroughArrayFunction().getY(1), 3.2, delta);
        assertEquals(getListThroughListFunction().getY(1), 16, delta);
        assertEquals(getListThroughListFunction().getY(3), 100, delta);
        assertNotEquals(getListThroughListFunction().getY(3), 121, delta);

    }

    @Test
    public void testSetY() {

        LinkedListTabulatedFunction testingSetY = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        testingSetY.setY(1, 2.23);
        assertEquals(testingSetY.getY(1), 2.23, delta);
        testingSetY.setY(2, 2.45);
        assertEquals(testingSetY.getY(2), 2.45, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);

        LinkedListTabulatedFunction testingSetYList = new LinkedListTabulatedFunction(source, 1, 16, 6);
        testingSetYList.setY(2, 78);
        assertEquals(testingSetYList.getY(2), 78, delta);
        assertNotEquals(testingSetYList.getY(2), 49, delta);
        testingSetYList.setY(4, 11);
        assertEquals(testingSetYList.getY(4), 11, delta);

    }

    @Test
    public void testIndexOfX() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().indexOfX(1.3), 2, delta);
        assertEquals(getListThroughArrayFunction().indexOfX(1.4), 3, delta);
        assertNotEquals(getListThroughArrayFunction().indexOfX(1.5), 1, delta);
        assertEquals(getListThroughListFunction().indexOfX(13), 4);
        assertEquals(getListThroughListFunction().indexOfX(16), 5);
        assertNotEquals(getListThroughListFunction().indexOfX(13), 1);

    }

    @Test
    public void testIndexOfY() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().indexOfY(2.5), 4, delta);
        assertEquals(getListThroughArrayFunction().indexOfY(2.4), 3, delta);
        assertNotEquals(getListThroughArrayFunction().indexOfY(2.1), 4, delta);
        assertEquals(getListThroughListFunction().indexOfY(49), 2, delta);
        assertEquals(getListThroughListFunction().indexOfY(169), 4, delta);
        assertNotEquals(getListThroughListFunction().indexOfY(49), 6, delta);

    }

    @Test
    public void testLeftBound() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().leftBound(), 1.1, delta);
        assertNotEquals(getListThroughArrayFunction().leftBound(), 1.2, delta);
        assertNotEquals(getListThroughArrayFunction().leftBound(), 1.5, delta);
        assertEquals(getListThroughListFunction().leftBound(), 1, delta);
        assertNotEquals(getListThroughListFunction().leftBound(), 2, delta);
        assertNotEquals(getListThroughListFunction().leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {

        final double delta = 0.0001;
        assertEquals(getListThroughArrayFunction().rightBound(), 1.5, delta);
        assertNotEquals(getListThroughArrayFunction().rightBound(), 1.4, delta);
        assertNotEquals(getListThroughArrayFunction().rightBound(), 1.3, delta);
        assertEquals(getListThroughListFunction().rightBound(), 16, delta);
        assertNotEquals(getListThroughListFunction().rightBound(), 19, delta);
        assertNotEquals(getListThroughListFunction().rightBound(), 27, delta);

    }
}
