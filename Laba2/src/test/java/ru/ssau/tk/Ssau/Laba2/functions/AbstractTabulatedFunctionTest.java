package ru.ssau.tk.Ssau.Laba2.functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction mockedInterpolate = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedInterpolate.interpolate(1.5, 1.1, 2.2, 3.3, 4.4), 3.7, delta);
        assertEquals(mockedInterpolate.interpolate(1.6, 1.1, 2.2, 3.3, 4.4), 3.8, delta);
        assertEquals(mockedInterpolate.interpolate(1.7, 1.1, 2.2, 3.3, 4.4), 3.9, delta);
        assertNotEquals(mockedInterpolate.interpolate(1.5, 1.1, 2.2, 3.3, 4.4), 4.2);
    }

    @Test
    public void testApply() {
        AbstractTabulatedFunction mockedApply = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedApply.apply(1.0), 3.3, delta);
        assertEquals(mockedApply.apply(3.0), 4.4, delta);
        assertNotEquals(mockedApply.apply(2.4), 5.2);
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{1, 3};
            double[] valuesY = new double[]{2};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
            double[] valuesNewX = new double[]{1, 3};
            double[] valuesNewY = new double[]{2, 4};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesNewX, valuesNewY);
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{5, 3, 7.2, 8.3};
            AbstractTabulatedFunction.checkSorted(valuesX);
            double[] valuesNewX = new double[]{1, 3, 7, 11};
            AbstractTabulatedFunction.checkSorted(valuesNewX);
        });
    }
}
