package ru.ssau.tk.Ssau.Laba2.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final double[] xValuesList = new double[]{1, 2, 3, 4, 5};
    private final double[] yValuesList = new double[]{6, 7, 8, 9, 10};

    private final double[] xValuesArray = new double[]{11, 12, 13, 14, 15};
    private final double[] yValuesArray = new double[]{16, 17, 18, 19, 20};

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction hyperbolicTangent = new HyperbolicTan();
        MathFunction inverseCosine = new InverseCosFunction();
        MathFunction sqrFunction = new SqrFunction();
        CompositeFunction identitySqrFunction = new CompositeFunction(identityFunction, sqrFunction);
        CompositeFunction hypTanSqrFunction = new CompositeFunction(hyperbolicTangent, sqrFunction);
        CompositeFunction invHypTanFunction = new CompositeFunction(inverseCosine, hyperbolicTangent);
        CompositeFunction identitySqrInvHypTanFunction = new CompositeFunction(identitySqrFunction, invHypTanFunction);

        final double delta = 0.00001;
        assertEquals(identitySqrInvHypTanFunction.apply(10), 0.82093, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(0), 0.76159, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(-2), -0.91041, delta);
        assertEquals(identitySqrInvHypTanFunction.apply(1), 0.95182, delta);

        assertEquals(invHypTanFunction.apply(1), 0.95182, delta);
        assertEquals(invHypTanFunction.apply(3), -0.76581, delta);
        assertEquals(invHypTanFunction.apply(0), 0.76159, delta);
        assertEquals(invHypTanFunction.apply(-2), -0.98377, delta);

        assertEquals(hypTanSqrFunction.apply(1), 0.58003, delta);
        assertEquals(hypTanSqrFunction.apply(0), 0, delta);
        assertEquals(hypTanSqrFunction.apply(-3), 0.99013, delta);
        assertEquals(hypTanSqrFunction.apply(5), 0.99982, delta);

        assertEquals(identitySqrFunction.apply(1), 1, delta);
        assertEquals(identitySqrFunction.apply(2), 4, delta);
        assertEquals(identitySqrFunction.apply(-3), 9, delta);
        assertEquals(identitySqrFunction.apply(0), 0, delta);

        MathFunction listFunction = new LinkedListTabulatedFunction(xValuesList, yValuesList);
        MathFunction arrayFunction = new LinkedListTabulatedFunction(xValuesArray, yValuesArray);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrFunction);
        MathFunction listArrayHyperbolicTangentFunction = listFunction.andThen(arrayFunction).andThen(hyperbolicTangent);
        assertEquals(arrayListSqrFunction.apply(1.2), 125.44, delta);
        assertEquals(listArrayHyperbolicTangentFunction.apply(2.4), 1, delta);
        assertEquals(arrayListSqrFunction.apply(3.6), 184.96, delta);
        assertEquals(listArrayHyperbolicTangentFunction.apply(7.8), 1, delta);
        assertNotEquals(arrayListSqrFunction.apply(5.7), 174, delta);
        assertNotEquals(listArrayHyperbolicTangentFunction.apply(9.7), 76.8, delta);
    }
}
