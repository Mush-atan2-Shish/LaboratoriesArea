package ru.ssau.tk.Ssau.Laba2.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.SqrFunction;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(0.1);
        SqrFunction check = new SqrFunction();
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.1, 0.01);   //h(x)=(f(x+h)-f(x))/h
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1.1), 2.3, 0.01);
        double[] xValue = {1, 1.1};
        assertEquals((check.apply(xValue[1]) - check.apply(xValue[0])) / (xValue[1] - xValue[0]), 2.1, 0.01);  //y'=(y(k+1)-y(k))/(x(k+1)-x(k))
    }
}
