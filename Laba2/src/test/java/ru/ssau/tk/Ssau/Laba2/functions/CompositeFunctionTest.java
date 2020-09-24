package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction hyperbolicTangent = new HyperbolicTan();
        MathFunction inverseCosine = new InverseCos();
        MathFunction sqrFunction = new SqrFunction();
        CompositeFunction identitySqrFunction = new CompositeFunction(identityFunction, sqrFunction);
        CompositeFunction hypTanSqrFunction = new CompositeFunction(hyperbolicTangent, sqrFunction);
        CompositeFunction invHypTanFunction = new CompositeFunction(inverseCosine, hyperbolicTangent);
        CompositeFunction identitySqrInvHypTanFunction = new CompositeFunction(identitySqrFunction, invHypTanFunction);

        final double delta = 0.00001;
        assertEquals(identitySqrInvHypTanFunction.apply(10),  0.82093, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(0),   0.76159, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(-2),  -0.91041, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(1),   0.95182, delta);

        assertEquals(invHypTanFunction.apply(1), 0.95182, delta);
        assertEquals(invHypTanFunction.apply(3), -0.76581, delta);
        assertEquals(invHypTanFunction.apply(0),  0.76159, delta);
        assertEquals(invHypTanFunction.apply(-2), -0.98377, delta);

        assertEquals(hypTanSqrFunction.apply(1), 0.58003, delta);
        assertEquals(hypTanSqrFunction.apply(0), 0, delta);
        assertEquals(hypTanSqrFunction.apply(-3), 0.99013, delta);
        assertEquals(hypTanSqrFunction.apply(5), 0.99982, delta);

        assertEquals(identitySqrFunction.apply(1),1, delta);
        assertEquals(identitySqrFunction.apply(2),4, delta);
        assertEquals(identitySqrFunction.apply(-3),9, delta);
        assertEquals(identitySqrFunction.apply(0),0, delta);



    }
}