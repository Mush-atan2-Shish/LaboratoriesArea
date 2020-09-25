package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InverseCosFunctionTest {

    @Test
    public void testApply() {
        MathFunction value = new InverseCosFunction();
        assertEquals(value.apply(0), 1);
        assertNotEquals(value.apply(1), 1);
        assertEquals(value.apply(Math.PI * 2), 1);
        assertEquals(value.apply(Math.PI / 3), 2, 0.0001);
    }
}
