package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    ZeroFunction zeroFun = new ZeroFunction(10);

    @Test
    public void testUnitApply() {
        assertEquals(zeroFun.apply(10), 0);
        assertEquals(zeroFun.apply(-100), 0);
    }

    @Test
    public void testUnitGet() {
        assertEquals(zeroFun.getConstant(), 0);
    }
}