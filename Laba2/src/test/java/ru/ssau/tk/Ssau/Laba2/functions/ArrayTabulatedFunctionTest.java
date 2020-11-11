package ru.ssau.tk.Ssau.Laba2.functions;

import ru.ssau.tk.Ssau.Laba2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.Ssau.Laba2.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.Ssau.Laba2.exceptions.InterpolationException;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
    public void testConstructorExceptions() {
        final double[] brokenValues = {1, -1, 0};
        final double[] brokenValuesToo = {1, 1, 2};
        final double[] singleElementArray = {1};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(singleElementArray, yValues));
        assertThrows(DifferentLengthOfArraysException.class, () -> new ArrayTabulatedFunction(brokenValues, yValues));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(brokenValues, brokenValues));
        assertThrows(ArrayIsNotSortedException.class, () -> new ArrayTabulatedFunction(brokenValuesToo, brokenValuesToo));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 21, 16, 10));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(source, 1, 6, 1));
    }

    @Test
    public void testRemove() {

        ArrayTabulatedFunction testingRemoveArray = new ArrayTabulatedFunction(xValues, yValues);
        testingRemoveArray.remove(0);       //удаление из первого
        assertEquals(testingRemoveArray.getCount(), 4);
        assertEquals(testingRemoveArray.getX(0), 1.2);
        assertEquals(testingRemoveArray.getY(0), 2.2);
        assertEquals(testingRemoveArray.getX(1), 1.3);
        assertEquals(testingRemoveArray.getY(1), 2.3);
        assertEquals(testingRemoveArray.getX(2), 1.4);
        assertEquals(testingRemoveArray.getY(2), 2.4);
        assertEquals(testingRemoveArray.getX(3), 1.5);
        assertEquals(testingRemoveArray.getY(3), 2.5);

        testingRemoveArray.remove(2);      //удаление из середины
        assertEquals(testingRemoveArray.getCount(), 3);
        assertEquals(testingRemoveArray.getX(0), 1.2);
        assertEquals(testingRemoveArray.getY(0), 2.2);
        assertEquals(testingRemoveArray.getX(1), 1.3);
        assertEquals(testingRemoveArray.getY(1), 2.3);
        assertEquals(testingRemoveArray.getX(2), 1.5);
        assertEquals(testingRemoveArray.getY(2), 2.5);

        testingRemoveArray.remove(2);           //удаление последнего
        assertEquals(testingRemoveArray.getCount(), 2);
        assertEquals(testingRemoveArray.getX(0), 1.2);
        assertEquals(testingRemoveArray.getY(0), 2.2);
        assertEquals(testingRemoveArray.getX(1), 1.3);
        assertEquals(testingRemoveArray.getY(1), 2.3);

        ArrayTabulatedFunction testingRemoveList = new ArrayTabulatedFunction(source, 1, 16, 6);
        testingRemoveList.remove(0);       //удаление из первого
        assertEquals(testingRemoveList.getCount(), 5);
        assertEquals(testingRemoveList.getX(0), 4);
        assertEquals(testingRemoveList.getY(0), 16);
        assertEquals(testingRemoveList.getX(1), 7);
        assertEquals(testingRemoveList.getY(1), 49);
        assertEquals(testingRemoveList.getX(2), 10);
        assertEquals(testingRemoveList.getY(2), 100);
        assertEquals(testingRemoveList.getX(3), 13);
        assertEquals(testingRemoveList.getY(3), 169);
        assertEquals(testingRemoveList.getX(4), 16);
        assertEquals(testingRemoveList.getY(4), 256);

        testingRemoveList.remove(2);      //удаление из середины
        assertEquals(testingRemoveList.getCount(), 4);
        assertEquals(testingRemoveList.getX(0), 4);
        assertEquals(testingRemoveList.getY(0), 16);
        assertEquals(testingRemoveList.getX(1), 7);
        assertEquals(testingRemoveList.getY(1), 49);
        assertEquals(testingRemoveList.getX(2), 13);
        assertEquals(testingRemoveList.getY(2), 169);
        assertEquals(testingRemoveList.getX(3), 16);
        assertEquals(testingRemoveList.getY(3), 256);

        testingRemoveList.remove(3);           //удаление последнего
        assertEquals(testingRemoveList.getCount(), 3);
        assertEquals(testingRemoveList.getX(0), 4);
        assertEquals(testingRemoveList.getY(0), 16);
        assertEquals(testingRemoveList.getX(1), 7);
        assertEquals(testingRemoveList.getY(1), 49);
        assertEquals(testingRemoveList.getX(2), 13);
        assertEquals(testingRemoveList.getY(2), 169);

        final double[] brokenValuesToo = {1, 3};
        final double[] twoElementArray = {1, 5};
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayTabulatedFunction checkFunction = new ArrayTabulatedFunction(twoElementArray, brokenValuesToo);
            checkFunction.remove(0);
        });
    }

    @Test
    public void testInsert() {

        ArrayTabulatedFunction testingInsert = new ArrayTabulatedFunction(xValues, yValues);
        testingInsert.insert(1.25, 2.0);                   //х внутри исходного массива/листа
        assertEquals(testingInsert.getCount(), 6);
        assertEquals(testingInsert.getX(0), 1.1);
        assertEquals(testingInsert.getY(0), 2.1);
        assertEquals(testingInsert.getX(1), 1.2);
        assertEquals(testingInsert.getY(1), 2.2);
        assertEquals(testingInsert.getX(2), 1.25);
        assertEquals(testingInsert.getY(2), 2.0);
        assertEquals(testingInsert.getX(3), 1.3);
        assertEquals(testingInsert.getY(3), 2.3);
        assertEquals(testingInsert.getX(4), 1.4);
        assertEquals(testingInsert.getY(4), 2.4);
        assertEquals(testingInsert.getX(5), 1.5);
        assertEquals(testingInsert.getY(5), 2.5);

        testingInsert.insert(1.07, 2.54);                   //х меньше всех исходных
        assertEquals(testingInsert.getX(0), 1.07);
        assertEquals(testingInsert.getY(0), 2.54);
        assertEquals(testingInsert.getX(1), 1.1);
        assertEquals(testingInsert.getY(1), 2.1);
        assertEquals(testingInsert.getX(2), 1.2);
        assertEquals(testingInsert.getY(2), 2.2);
        assertEquals(testingInsert.getX(3), 1.25);
        assertEquals(testingInsert.getY(3), 2.0);
        assertEquals(testingInsert.getX(4), 1.3);
        assertEquals(testingInsert.getY(4), 2.3);
        assertEquals(testingInsert.getX(5), 1.4);
        assertEquals(testingInsert.getY(5), 2.4);
        assertEquals(testingInsert.getX(6), 1.5);
        assertEquals(testingInsert.getY(6), 2.5);
        assertEquals(testingInsert.getCount(), 7);

        testingInsert.insert(1.67, 2.14);                   //х больше всех исходных
        assertEquals(testingInsert.getX(0), 1.07);
        assertEquals(testingInsert.getY(0), 2.54);
        assertEquals(testingInsert.getX(1), 1.1);
        assertEquals(testingInsert.getY(1), 2.1);
        assertEquals(testingInsert.getX(2), 1.2);
        assertEquals(testingInsert.getY(2), 2.2);
        assertEquals(testingInsert.getX(3), 1.25);
        assertEquals(testingInsert.getY(3), 2.0);
        assertEquals(testingInsert.getX(4), 1.3);
        assertEquals(testingInsert.getY(4), 2.3);
        assertEquals(testingInsert.getX(5), 1.4);
        assertEquals(testingInsert.getY(5), 2.4);
        assertEquals(testingInsert.getX(6), 1.5);
        assertEquals(testingInsert.getY(6), 2.5);
        assertEquals(testingInsert.getX(7), 1.67);
        assertEquals(testingInsert.getY(7), 2.14);
        assertEquals(testingInsert.getCount(), 8);

        testingInsert.insert(1.1, 2.7);                      //проверка совпадающего х
        assertEquals(testingInsert.getX(0), 1.07);
        assertEquals(testingInsert.getY(0), 2.54);
        assertEquals(testingInsert.getX(1), 1.1);
        assertEquals(testingInsert.getY(1), 2.7);
        assertEquals(testingInsert.getX(2), 1.2);
        assertEquals(testingInsert.getY(2), 2.2);
        assertEquals(testingInsert.getX(3), 1.25);
        assertEquals(testingInsert.getY(3), 2.0);
        assertEquals(testingInsert.getX(4), 1.3);
        assertEquals(testingInsert.getY(4), 2.3);
        assertEquals(testingInsert.getX(5), 1.4);
        assertEquals(testingInsert.getY(5), 2.4);
        assertEquals(testingInsert.getX(6), 1.5);
        assertEquals(testingInsert.getY(6), 2.5);
        assertEquals(testingInsert.getX(7), 1.67);
        assertEquals(testingInsert.getY(7), 2.14);
        assertEquals(testingInsert.getCount(), 8);

        ArrayTabulatedFunction testingInsertList = new ArrayTabulatedFunction(source, 1, 16, 6);
        testingInsertList.insert(7, 85);                     //проверка совпадающего х
        assertEquals(testingInsertList.getCount(), 6);
        assertEquals(testingInsertList.getX(0), 1);
        assertEquals(testingInsertList.getY(0), 1);
        assertEquals(testingInsertList.getX(1), 4);
        assertEquals(testingInsertList.getY(1), 16);
        assertEquals(testingInsertList.getX(2), 7);
        assertEquals(testingInsertList.getY(2), 85);
        assertEquals(testingInsertList.getX(3), 10);
        assertEquals(testingInsertList.getY(3), 100);
        assertEquals(testingInsertList.getX(4), 13);
        assertEquals(testingInsertList.getY(4), 169);
        assertEquals(testingInsertList.getX(5), 16);
        assertEquals(testingInsertList.getY(5), 256);

        testingInsertList.insert(4.25, 2.0);                       //х внутри исходного массива/листа
        assertEquals(testingInsertList.getCount(), 7);
        assertEquals(testingInsertList.getX(0), 1);
        assertEquals(testingInsertList.getY(0), 1);
        assertEquals(testingInsertList.getX(1), 4);
        assertEquals(testingInsertList.getY(1), 16);
        assertEquals(testingInsertList.getX(2), 4.25);
        assertEquals(testingInsertList.getY(2), 2.0);
        assertEquals(testingInsertList.getX(3), 7);
        assertEquals(testingInsertList.getY(3), 85);
        assertEquals(testingInsertList.getX(4), 10);
        assertEquals(testingInsertList.getY(4), 100);
        assertEquals(testingInsertList.getX(5), 13);
        assertEquals(testingInsertList.getY(5), 169);
        assertEquals(testingInsertList.getX(6), 16);
        assertEquals(testingInsertList.getY(6), 256);

        testingInsertList.insert(0.07, 2.54);                         //х меньше всех исходных
        assertEquals(testingInsertList.getCount(), 8);
        assertEquals(testingInsertList.getX(0), 0.07);
        assertEquals(testingInsertList.getY(0), 2.54);
        assertEquals(testingInsertList.getX(1), 1);
        assertEquals(testingInsertList.getY(1), 1);
        assertEquals(testingInsertList.getX(2), 4);
        assertEquals(testingInsertList.getY(2), 16);
        assertEquals(testingInsertList.getX(3), 4.25);
        assertEquals(testingInsertList.getY(3), 2.0);
        assertEquals(testingInsertList.getX(4), 7);
        assertEquals(testingInsertList.getY(4), 85);
        assertEquals(testingInsertList.getX(5), 10);
        assertEquals(testingInsertList.getY(5), 100);
        assertEquals(testingInsertList.getX(6), 13);
        assertEquals(testingInsertList.getY(6), 169);
        assertEquals(testingInsertList.getX(7), 16);
        assertEquals(testingInsertList.getY(7), 256);

        testingInsertList.insert(17.67, 20.14);                       //х больше всех исходных
        assertEquals(testingInsertList.getCount(), 9);
        assertEquals(testingInsertList.getX(0), 0.07);
        assertEquals(testingInsertList.getY(0), 2.54);
        assertEquals(testingInsertList.getX(1), 1);
        assertEquals(testingInsertList.getY(1), 1);
        assertEquals(testingInsertList.getX(2), 4);
        assertEquals(testingInsertList.getY(2), 16);
        assertEquals(testingInsertList.getX(3), 4.25);
        assertEquals(testingInsertList.getY(3), 2.0);
        assertEquals(testingInsertList.getX(4), 7);
        assertEquals(testingInsertList.getY(4), 85);
        assertEquals(testingInsertList.getX(5), 10);
        assertEquals(testingInsertList.getY(5), 100);
        assertEquals(testingInsertList.getX(6), 13);
        assertEquals(testingInsertList.getY(6), 169);
        assertEquals(testingInsertList.getX(7), 16);
        assertEquals(testingInsertList.getY(7), 256);
        assertEquals(testingInsertList.getX(8), 17.67);
        assertEquals(testingInsertList.getY(8), 20.14);

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
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughArrayFunction().floorIndexOfX(-1));
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughArrayFunction().floorIndexOfX(1));
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughArrayFunction().floorIndexOfX(-3));
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughLinkedFunction().floorIndexOfX(-1));
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughLinkedFunction().floorIndexOfX(-10));
        assertThrows(IllegalArgumentException.class, () -> getArrayThroughLinkedFunction().floorIndexOfX(-4));
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
        assertThrows(InterpolationException.class, () -> getArrayThroughArrayFunction().interpolate(1.8, 3));
        assertThrows(InterpolationException.class, () -> getArrayThroughArrayFunction().interpolate(1, 0));
        assertThrows(InterpolationException.class, () -> getArrayThroughLinkedFunction().interpolate(17, 4));
        assertThrows(InterpolationException.class, () -> getArrayThroughLinkedFunction().interpolate(-1, 0));
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
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getX(-7));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getX(1000));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getX(18));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getX(-7));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getX(1000));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getX(18));

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
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(-7));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(1000));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(18));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(-7));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(1000));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(18));

    }

    @Test
    public void testSetY() {

        ArrayTabulatedFunction testingSetY = new ArrayTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        testingSetY.setY(1, 2.23);
        assertEquals(testingSetY.getY(1), 2.23, delta);
        testingSetY.setY(2, 2.45);
        assertEquals(testingSetY.getY(2), 2.45, delta);
        assertNotEquals(testingSetY.getY(2), 1.45, delta);

        ArrayTabulatedFunction testingSetYList = new ArrayTabulatedFunction(source, 1, 16, 6);
        testingSetYList.setY(2, 78);
        assertEquals(testingSetYList.getY(2), 78, delta);
        assertNotEquals(testingSetYList.getY(2), 49, delta);
        testingSetYList.setY(4, 11);
        assertEquals(testingSetYList.getY(4), 11, delta);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(-12));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(1004));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughArrayFunction().getY(185));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(-76));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(1070));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getArrayThroughLinkedFunction().getY(189));


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

    @Test
    public void testIteratorWhile() {
        ArrayTabulatedFunction testDefinedThroughArrays = getArrayThroughArrayFunction();
        Iterator<Point> myIterator = testDefinedThroughArrays.iterator();
        int i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testDefinedThroughArrays.getX(i), myPoint.x, 0.0001);
            assertEquals(testDefinedThroughArrays.getY(i++), myPoint.y, 0.0001);
        }
        assertEquals(testDefinedThroughArrays.getCount(), i);
        Iterator<Point> finalMyFirstIterator = myIterator;
        assertThrows(NoSuchElementException.class, () -> {
            finalMyFirstIterator.next();
        });

        ArrayTabulatedFunction testDefinedThroughMathFunction = getArrayThroughLinkedFunction();
        myIterator = testDefinedThroughMathFunction.iterator();
        i = 0;
        while (myIterator.hasNext()) {
            Point myPoint = myIterator.next();
            assertEquals(testDefinedThroughMathFunction.getX(i), myPoint.x, 0.0001);
            assertEquals(testDefinedThroughMathFunction.getY(i++), myPoint.y, 0.0001);
        }
        Iterator<Point> finalMySecondIterator = myIterator;
        assertThrows(NoSuchElementException.class, () -> {
            finalMySecondIterator.next();
        });
        assertEquals(testDefinedThroughMathFunction.getCount(), i);

    }

    @Test
    public void testIteratorForEach() {
        ArrayTabulatedFunction testDefinedThroughArrays = getArrayThroughArrayFunction();
        int i = 0;
        for (Point myPoint : testDefinedThroughArrays) {
            assertEquals(myPoint.x, testDefinedThroughArrays.getX(i), 0.0001);
            assertEquals(myPoint.y, testDefinedThroughArrays.getY(i++), 0.0001);
        }
        assertEquals(testDefinedThroughArrays.getCount(), i);

        ArrayTabulatedFunction testDefinedThroughMathFunction = getArrayThroughLinkedFunction();
        i = 0;
        for (Point myPoint : testDefinedThroughMathFunction) {
            assertEquals(myPoint.x, testDefinedThroughMathFunction.getX(i), 0.0001);
            assertEquals(myPoint.y, testDefinedThroughMathFunction.getY(i++), 0.0001);
        }
        assertEquals(testDefinedThroughMathFunction.getCount(), i);
    }
}
