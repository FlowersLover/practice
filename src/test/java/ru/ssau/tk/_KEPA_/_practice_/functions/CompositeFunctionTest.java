package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA=0.00001;
    @Test
    public void testApply() {
    MathFunction identityFunction = new IdentityFunction();
    MathFunction sqrFunction = new SqrFunction();
    MathFunction ctanFunction = new CTanFunction();
    MathFunction identitySqrFunction = new CompositeFunction(identityFunction,sqrFunction);
    MathFunction sqrCtanFunction = new CompositeFunction(ctanFunction,sqrFunction);
    MathFunction composite = sqrFunction.andThen(ctanFunction).andThen(identityFunction);
    assertEquals(composite.apply(Math.pow(Math.PI/6,0.5)), Math.pow(3,0.5), DELTA);
    assertEquals(identitySqrFunction.apply(3), 9, DELTA);
    assertEquals(sqrCtanFunction.apply(Math.PI/6), 3, DELTA);
    assertNotEquals(sqrCtanFunction.apply(Math.PI/6), 0,DELTA);
    }
}
