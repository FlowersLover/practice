package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction sqrFunction = new SqrFunction();
        assertEquals(sqrFunction.apply(0), 0, DELTA);
        assertEquals(sqrFunction.apply(3), 9, DELTA);
        assertEquals(sqrFunction.apply(-3), 9, DELTA);
    }
}