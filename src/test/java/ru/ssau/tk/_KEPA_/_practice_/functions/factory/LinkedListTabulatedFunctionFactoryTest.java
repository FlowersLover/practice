package ru.ssau.tk._KEPA_._practice_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.MathFunction;
import ru.ssau.tk._KEPA_._practice_.functions.SqrFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1., 2., 3., 4., 5., 6.};
    private final double[] valuesY = new double[]{1., 4., 9., 16., 25., 36.};
    private final MathFunction sqrFunction = new SqrFunction();

    @Test
    public void testCreate() {
        var testListFunction1 = new LinkedListTabulatedFunctionFactory().create(valuesX, valuesY);
        var testListFunction2 = new LinkedListTabulatedFunctionFactory().create(sqrFunction,1,101,100);
        assertTrue(testListFunction1 instanceof LinkedListTabulatedFunction);
        assertTrue(testListFunction2 instanceof LinkedListTabulatedFunction);
    }

}