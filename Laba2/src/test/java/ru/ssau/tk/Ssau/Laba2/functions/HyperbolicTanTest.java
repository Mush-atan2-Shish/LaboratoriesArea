package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HyperbolicTanTest {

    @Test
    public void testApply() {
        HyperbolicTan hypTan = new HyperbolicTan();
        assertEquals(hypTan.apply(1), 0.7616, 0.0001);
        assertEquals(hypTan.apply(0), 0);
        assertEquals(hypTan.apply(-10), -1, 0.0001);
    }
}