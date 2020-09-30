package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    @Test
    public void testInterpolate() {
        AbstractTabulatedFunction mockedInterpolate = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedInterpolate.interpolate(1.5,1.1,2.2,3.3,4.4),3.7, delta);
        assertEquals(mockedInterpolate.interpolate(1.6,1.1,2.2,3.3,4.4),3.8,delta);
        assertEquals(mockedInterpolate.interpolate(1.7,1.1,2.2,3.3,4.4),3.9,delta);
        assertNotEquals(mockedInterpolate.interpolate(1.5,1.1,2.2,3.3,4.4),4.2);
    }

    @Test
    public void testApply() {
        AbstractTabulatedFunction mockedApply = new MockTabulatedFunction();

        final double delta = 0.0001;
        assertEquals(mockedApply.apply(1.0),3.3, delta);
        assertEquals(mockedApply.apply(3.0),4.4, delta);
        assertNotEquals(mockedApply.apply(2.4),5.2);
    }
}