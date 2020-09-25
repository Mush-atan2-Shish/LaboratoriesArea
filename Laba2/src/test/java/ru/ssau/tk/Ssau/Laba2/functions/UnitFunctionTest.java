package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    private final UnitFunction unitFun = new UnitFunction();

    @Test
    public void testUnitApply() {
        assertEquals(unitFun.apply(10), 1);
        assertEquals(unitFun.apply(-100), 1);
        assertNotEquals(unitFun.apply(-5), 0);
        assertNotEquals(unitFun.apply(1), 0);
    }

    @Test
    public void testUnitGet() {
        assertEquals(unitFun.getConstant(), 1);
    }
}
