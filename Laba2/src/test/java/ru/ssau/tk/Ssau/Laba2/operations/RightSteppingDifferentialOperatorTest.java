package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.SqrFunction;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(0.1);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.1, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.0999, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4.1, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4.09879999, 0.01);
    }
}
