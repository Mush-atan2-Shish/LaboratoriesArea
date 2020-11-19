package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(0.1);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.0001998, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4.0001998, 0.01);
    }
}
