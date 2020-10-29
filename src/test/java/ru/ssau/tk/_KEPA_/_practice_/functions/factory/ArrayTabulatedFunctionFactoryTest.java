package ru.ssau.tk._KEPA_._practice_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.MathFunction;
import ru.ssau.tk._KEPA_._practice_.functions.SqrFunction;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1., 2., 3., 4., 5., 6.};
    private final double[] valuesY = new double[]{1., 4., 9., 16., 25., 36.};
    private final MathFunction sqrFunction = new SqrFunction();


    @Test
    public void testCreate() {
        var testArrayFunction1 = new ArrayTabulatedFunctionFactory().create(valuesX, valuesY);
        var testArrayFunction2=new ArrayTabulatedFunctionFactory().create(sqrFunction,1,101,100);
        assertTrue(testArrayFunction1 instanceof ArrayTabulatedFunction);
        assertTrue(testArrayFunction2 instanceof ArrayTabulatedFunction);
    }

}