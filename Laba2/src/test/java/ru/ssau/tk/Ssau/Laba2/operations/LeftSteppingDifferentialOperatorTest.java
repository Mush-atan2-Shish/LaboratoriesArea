package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(0.1);
        SqrFunction check = new SqrFunction();
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.9, 0.01);  //g(x)=(f(x)-f(x-h))/h
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(0.9), 1.7, 0.01);
        double[] xValue = {0.9, 1};
        assertEquals((check.apply(xValue[1]) - check.apply(xValue[0])) / (xValue[1] - xValue[0]), 1.9, 0.01);  //y'=(y(k+1)-y(k))/(x(k+1)-x(k))
    }
}
