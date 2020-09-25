package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        MathFunction function = new IdentityFunction();
        assertEquals(function.apply(1), 1);
        assertEquals(function.apply(0), 0);
        assertEquals(function.apply(1.55), 1.55, 0.0001);
        assertEquals(function.apply(-2.15), -2.15, 0.001);
    }
}
