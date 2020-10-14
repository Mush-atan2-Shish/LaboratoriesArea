package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    double[] xValues = new double[]{1.1, 1.2, 1.3, 1.4, 1.5};
    double[] yValues = new double[]{2.1, 2.2, 2.3, 2.4, 2.5};
    private final MathFunction source = new SqrFunction();

    private ArrayTabulatedFunction getArrayThroughArrayFunction() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private ArrayTabulatedFunction getArrayThroughLinkedFunction() {
        return new ArrayTabulatedFunction(source, 1, 16, 6);
    }

    @Test
    public void testRemove() {

        getArrayThroughArrayFunction().remove(2);
        getArrayThroughArrayFunction().remove(1);
        getArrayThroughArrayFunction().remove(0);
        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().getX(0), 1.1, delta);
        assertEquals(getArrayThroughArrayFunction().getX(1), 1.2, delta);
        assertEquals(getArrayThroughArrayFunction().getX(2), 1.3, delta);
        assertNotEquals(getArrayThroughArrayFunction().getX(1), 8, delta);

    }

    @Test
    public void testInsert() {

        getArrayThroughArrayFunction().insert(1.14, 2.0);
        assertEquals(getArrayThroughArrayFunction().getX(0), 1.1);
        assertEquals(getArrayThroughArrayFunction().getX(1), 1.2);
        assertEquals(getArrayThroughArrayFunction().getX(2), 1.3);
        getArrayThroughLinkedFunction().insert(1.6, 2.7);
        assertEquals(getArrayThroughLinkedFunction().getX(2), 7.0);
        assertEquals(getArrayThroughLinkedFunction().getX(0), 1.0);
        assertEquals(getArrayThroughLinkedFunction().getX(1), 4.0);

    }

    @Test
    public void testGetCount() {

        assertEquals(getArrayThroughLinkedFunction().getCount(), 6);
        assertNotEquals(getArrayThroughLinkedFunction().getCount(), 7);
        assertNotEquals(getArrayThroughLinkedFunction().getCount(), 5);

    }

    @Test
    public void testApply() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().apply(1.0), 2.0, delta);
        assertEquals(getArrayThroughArrayFunction().apply(1.56), 2.56, delta);
        assertEquals(getArrayThroughArrayFunction().apply(1.22), 2.22, delta);
        assertNotEquals(getArrayThroughArrayFunction().apply(1.22), 1.23, delta);
        assertEquals(getArrayThroughLinkedFunction().apply(-3.2), -20, delta);
        assertEquals(getArrayThroughLinkedFunction().apply(21), 401, delta);
        assertEquals(getArrayThroughLinkedFunction().apply(7.25), 53.25, delta);
        assertNotEquals(getArrayThroughLinkedFunction().apply(7.25), 59.25, delta);
    }

    @Test
    public void testFloorIndexOfX() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().floorIndexOfX(1.35), 2, delta);
        assertEquals(getArrayThroughArrayFunction().floorIndexOfX(1.43), 3, delta);
        assertNotEquals(getArrayThroughArrayFunction().floorIndexOfX(1.32), 4);
        assertEquals(getArrayThroughLinkedFunction().floorIndexOfX(7.39), 2);
        assertEquals(getArrayThroughLinkedFunction().floorIndexOfX(13.78), 4);
        assertNotEquals(getArrayThroughLinkedFunction().floorIndexOfX(13.78), 3);

    }

    @Test
    public void testExtrapolateLeft() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().extrapolateLeft(1.0), 2.0, delta);
        assertEquals(getArrayThroughArrayFunction().extrapolateLeft(-1.2), -0.2, delta);
        assertNotEquals(getArrayThroughArrayFunction().extrapolateLeft(1.05), 2.5);
        assertEquals(getArrayThroughLinkedFunction().extrapolateLeft(-1), -9, delta);
        assertEquals(getArrayThroughLinkedFunction().extrapolateLeft(-2), -14, delta);
        assertNotEquals(getArrayThroughLinkedFunction().extrapolateLeft(-2), 7, delta);

    }

    @Test
    public void testExtrapolateRight() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().extrapolateRight(1.56), 2.56, delta);
        assertEquals(getArrayThroughArrayFunction().extrapolateRight(2.56), 3.56, delta);
        assertNotEquals(getArrayThroughArrayFunction().extrapolateRight(1.56), 2.1);
        assertEquals(getArrayThroughLinkedFunction().extrapolateRight(20), 372, delta);
        assertEquals(getArrayThroughLinkedFunction().extrapolateRight(25), 517, delta);
        assertNotEquals(getArrayThroughLinkedFunction().extrapolateRight(25), 89, delta);

    }

    @Test
    public void testInterpolate() {

        final double delta = 0.00001;
        assertEquals(getArrayThroughArrayFunction().interpolate(1.22, 1), 2.22, delta);
        assertEquals(getArrayThroughArrayFunction().interpolate(1.247, 1), 2.247, delta);
        assertNotEquals(getArrayThroughArrayFunction().interpolate(1.247, 1), 4.237, delta);
        assertEquals(getArrayThroughLinkedFunction().interpolate(11, 3), 123, delta);
        assertEquals(getArrayThroughLinkedFunction().interpolate(12, 3), 146, delta);
        assertNotEquals(getArrayThroughLinkedFunction().interpolate(11, 3), 121, delta);

    }

    @Test
    public void testGetX() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().getX(1), 1.2, delta);
        assertEquals(getArrayThroughArrayFunction().getX(2), 1.3, delta);
        assertNotEquals(getArrayThroughArrayFunction().getX(1), 2.2, delta);
        assertEquals(getArrayThroughLinkedFunction().getX(1), 4, delta);
        assertEquals(getArrayThroughLinkedFunction().getX(3), 10, delta);
        assertNotEquals(getArrayThroughLinkedFunction().getX(3), 16, delta);

    }

    @Test
    public void testGetY() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().getY(1), 2.2, delta);
        assertEquals(getArrayThroughArrayFunction().getY(2), 2.3, delta);
        assertNotEquals(getArrayThroughArrayFunction().getY(1), 3.2, delta);
        assertEquals(getArrayThroughLinkedFunction().getY(1), 16, delta);
        assertEquals(getArrayThroughLinkedFunction().getY(3), 100, delta);
        assertNotEquals(getArrayThroughLinkedFunction().getY(3), 121, delta);

    }

    @Test
    public void testSetY() {

        getArrayThroughArrayFunction().setY(1, 2.23);
        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().getY(1), 2.2, delta);
        getArrayThroughArrayFunction().setY(2, 2.45);
        assertEquals(getArrayThroughArrayFunction().getY(2), 2.3, delta);
        assertNotEquals(getArrayThroughArrayFunction().getY(2), 1.45, delta);
        getArrayThroughLinkedFunction().setY(2, 78);
        assertEquals(getArrayThroughLinkedFunction().getY(2), 49, delta);
        assertNotEquals(getArrayThroughLinkedFunction().getY(2), 78, delta);
        getArrayThroughLinkedFunction().setY(4, 11);
        assertEquals(getArrayThroughLinkedFunction().getY(4), 169, delta);

    }

    @Test
    public void testIndexOfX() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().indexOfX(1.3), 2, delta);
        assertEquals(getArrayThroughArrayFunction().indexOfX(1.4), 3, delta);
        assertNotEquals(getArrayThroughArrayFunction().indexOfX(1.5), 1, delta);
        assertEquals(getArrayThroughLinkedFunction().indexOfX(13), 4);
        assertEquals(getArrayThroughLinkedFunction().indexOfX(16), 5);
        assertNotEquals(getArrayThroughLinkedFunction().indexOfX(13), 1);

    }

    @Test
    public void testIndexOfY() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().indexOfY(2.5), 4, delta);
        assertEquals(getArrayThroughArrayFunction().indexOfY(2.4), 3, delta);
        assertNotEquals(getArrayThroughArrayFunction().indexOfY(2.1), 4, delta);
        assertEquals(getArrayThroughLinkedFunction().indexOfY(49), 2, delta);
        assertEquals(getArrayThroughLinkedFunction().indexOfY(169), 4, delta);
        assertNotEquals(getArrayThroughLinkedFunction().indexOfY(49), 6, delta);

    }

    @Test
    public void testLeftBound() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().leftBound(), 1.1, delta);
        assertNotEquals(getArrayThroughArrayFunction().leftBound(), 1.2, delta);
        assertNotEquals(getArrayThroughArrayFunction().leftBound(), 1.5, delta);
        assertEquals(getArrayThroughLinkedFunction().leftBound(), 1, delta);
        assertNotEquals(getArrayThroughLinkedFunction().leftBound(), 2, delta);
        assertNotEquals(getArrayThroughLinkedFunction().leftBound(), 5, delta);

    }

    @Test
    public void testRightBound() {

        final double delta = 0.0001;
        assertEquals(getArrayThroughArrayFunction().rightBound(), 1.5, delta);
        assertNotEquals(getArrayThroughArrayFunction().rightBound(), 1.4, delta);
        assertNotEquals(getArrayThroughArrayFunction().rightBound(), 1.3, delta);
        assertEquals(getArrayThroughLinkedFunction().rightBound(), 16, delta);
        assertNotEquals(getArrayThroughLinkedFunction().rightBound(), 19, delta);
        assertNotEquals(getArrayThroughLinkedFunction().rightBound(), 27, delta);

    }
}
