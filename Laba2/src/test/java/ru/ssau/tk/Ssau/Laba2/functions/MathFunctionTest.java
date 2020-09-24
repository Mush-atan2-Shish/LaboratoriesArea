package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    @Test
    public void testApply() {
        MathFunction hypTan = new HyperbolicTan();
        MathFunction inverseCos = new InverseCos();
        MathFunction sqrFun = new SqrFunction();

        MathFunction firstComposite = hypTan.andThen(inverseCos).andThen(sqrFun);
        MathFunction secondComposite = sqrFun.andThen(inverseCos).andThen(hypTan);

        final double delta = 0.0001;
        assertEquals(firstComposite.apply(1), 1.9091, delta);
        assertEquals(firstComposite.apply(0), 1, delta);
        assertEquals(firstComposite.apply(-3), 3.3734, delta);

        assertEquals(secondComposite.apply(-1), 0.9518, delta);
        assertEquals(secondComposite.apply(0), 0.7616, delta);
        assertEquals(secondComposite.apply(2), -0.9104, delta);
    }
}