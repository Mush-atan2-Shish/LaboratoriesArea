package ru.ssau.tk.Ssau.Laba2.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.Ssau.Laba2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {

    @Test
    public void testCreate() {
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {10, 20, 30, 40, 50};
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction newListFactory = listFactory.create(x, y);
        assertTrue(newListFactory instanceof LinkedListTabulatedFunction);
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction newArrayFactory = arrayFactory.create(x, y);
        assertTrue(newArrayFactory instanceof ArrayTabulatedFunction);
    }
}
