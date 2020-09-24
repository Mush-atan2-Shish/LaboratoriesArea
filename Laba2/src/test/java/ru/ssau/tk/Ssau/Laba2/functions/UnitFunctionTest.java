package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    UnitFunction unitFun = new UnitFunction(10);

    @Test
    public void testUnitApply() {
        assertEquals(unitFun.apply(10), 1);
        assertEquals(unitFun.apply(-100), 1);
    }

    @Test
    public void testUnitGet() {
        assertEquals(unitFun.getConstant(), 1);
    }
}
