package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.Ssau.Laba2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.Point;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {

    private final double[] valuesX = new double[]{-3, -2, -1, 0, 1, 2, 3};
    private final double[] valuesY = new double[]{-6, -4, -2, 0, 2, 4, 6};
    private final double[] valuesYForList = new double[]{10, 20, 30, 40, 50, 60, 70};

    ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), 0.00001);
        }
        assertEquals(testArrayFunction.getCount(), i);

        LinkedListTabulatedFunction testListFunction = getTestList();
        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), 0.00001);
            assertEquals(myPoint.y, testListFunction.getY(i++), 0.00001);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(new TabulatedFunctionOperationService().getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService myObj = new TabulatedFunctionOperationService();
        myObj.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(myObj.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();

        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(testListFunction, errorTest));

        final double[] errorX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(testListFunction, errorTest1));

        TabulatedFunction testSumOfArrays = new TabulatedFunctionOperationService().sum(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction testSumOfLists = new TabulatedFunctionOperationService().sum(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = new TabulatedFunctionOperationService().sum(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        ArrayTabulatedFunction testArrayFunction = getTestArray();
        LinkedListTabulatedFunction testListFunction = getTestList();
        TabulatedFunction testSubtractOfArrays = new TabulatedFunctionOperationService().subtract(testArrayFunction, testArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }
        TabulatedFunction testSubtractOfLists = new TabulatedFunctionOperationService().subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }
        TabulatedFunction testSubtractOfArrayAndList = new TabulatedFunctionOperationService().subtract(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
    }

    @Test
    public void testMultiply() {
        double[] yValues1 = new double[]{2., 8., 32., 160.};
        double[] xValues1 = new double[]{2., 4., 6., 8.};
        double[] yValues2 = new double[]{20., 40., 60., 80.};
        double[] xValues2 = new double[]{2., 4., 6., 8.};

        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues1, yValues1);
        TabulatedFunction b = linkedListFactory.create(xValues2, yValues2);
        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
        TabulatedFunction multiplyThroughArray = operationService.multiply(a, a);
        TabulatedFunction multiplyThroughLinkedList = operationService.multiply(b, b);
        TabulatedFunction multiplyOfArrayAndList = operationService.multiply(a, b);
        int i = 0;
        for (Point point : multiplyThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues1[i++]);
        }
        i = 0;
        for (Point point : multiplyThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] * yValues2[i++]);
        }
        i = 0;
        for (Point point : multiplyOfArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues2[i++]);
        }
    }

    @Test
    public void testDivide() {
        double[] yValues1 = new double[]{2., 8., 32., 160.};
        double[] xValues1 = new double[]{2., 4., 6., 8.};
        double[] yValues2 = new double[]{20., 40., 60., 80.};
        double[] xValues2 = new double[]{2., 4., 6., 8.};

        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues1, yValues1);
        TabulatedFunction b = linkedListFactory.create(xValues2, yValues2);
        TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();
        TabulatedFunction divideThroughArray = operationService.divide(a, a);
        TabulatedFunction divideThroughLinkedList = operationService.divide(b, b);
        TabulatedFunction divideArrayAndList = operationService.divide(a, b);
        int i = 0;
        for (Point point : divideThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues1[i++]);
        }
        i = 0;
        for (Point point : divideThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] / yValues2[i++]);
        }
        i = 0;
        for (Point point : divideArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues2[i++]);
        }
    }
}
