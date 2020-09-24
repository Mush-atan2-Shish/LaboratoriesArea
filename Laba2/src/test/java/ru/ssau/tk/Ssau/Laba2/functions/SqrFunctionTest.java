package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction sqrX = new SqrFunction();
        assertEquals(sqrX.apply(-1), 1);
        assertEquals(sqrX.apply(0), 0);
        assertEquals(sqrX.apply(1.3), 1.69, 0.0001);
    }
}