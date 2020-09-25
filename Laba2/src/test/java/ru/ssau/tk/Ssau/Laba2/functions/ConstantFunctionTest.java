package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    private final ConstantFunction constFun = new ConstantFunction(5);

    @Test
    public void testGetConstant() {
        assertEquals(constFun.getConstant(), 5);
    }

    @Test
    public void testApply() {
        assertEquals(constFun.apply(100), 5);
        assertEquals(constFun.apply(-100), 5);
        assertEquals(constFun.apply(0), 5);
        assertEquals(constFun.apply(1), 5);
        assertNotEquals(constFun.apply(-2), 0);
        assertNotEquals(constFun.apply(10), 1);
    }
}
