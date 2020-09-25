package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    private final ZeroFunction zeroFun = new ZeroFunction();

    @Test
    public void testUnitApply() {
        assertEquals(zeroFun.apply(10), 0);
        assertEquals(zeroFun.apply(-100), 0);
        assertNotEquals(zeroFun.apply(-1), 1);
        assertNotEquals(zeroFun.apply(1), 1);
    }

    @Test
    public void testUnitGet() {
        assertEquals(zeroFun.getConstant(), 0);
    }
}
