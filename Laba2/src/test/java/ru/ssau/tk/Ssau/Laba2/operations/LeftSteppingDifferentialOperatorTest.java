package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(0.0002);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, 0.001);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, 0.001);
    }
}
