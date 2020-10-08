package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.00001;
    @Test
    public void testApply() {
        final double[] xValues1 = new double[]{1, 2, 3, 4, 5, 6};
        final double[] yValues1 = new double[]{11, 12, 13, 14, 15, 16};

        final double[] xValues2 = new double[]{ 7, 8, 9, 10,11,12};
        final double[] yValues2 = new double[]{16, 17, 18, 19, 20,21};

        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();
        MathFunction ctanFunction = new CTanFunction();
        MathFunction identitySqrFunction = new CompositeFunction(identityFunction, sqrFunction);
        MathFunction sqrCtanFunction = new CompositeFunction(ctanFunction, sqrFunction);
        MathFunction composite = sqrFunction.andThen(ctanFunction).andThen(identityFunction);
        assertEquals(composite.apply(Math.pow(Math.PI / 6, 0.5)), Math.pow(3, 0.5), DELTA);
        assertEquals(identitySqrFunction.apply(3), 9, DELTA);
        assertEquals(sqrCtanFunction.apply(Math.PI / 6), 3, DELTA);
        assertNotEquals(sqrCtanFunction.apply(Math.PI / 6), 0, DELTA);

        MathFunction listFunction = new LinkedListTabulatedFunction(xValues1, yValues1);
        MathFunction arrayFunction = new ArrayTabulatedFunction(xValues2, yValues2);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrFunction);
        assertEquals(arrayListSqrFunction.apply(2), 441, DELTA);
        assertEquals(arrayListSqrFunction.apply(10.25), 855.5625, DELTA);
        assertEquals(arrayListSqrFunction.apply(25),1936 , DELTA);

    }
}
