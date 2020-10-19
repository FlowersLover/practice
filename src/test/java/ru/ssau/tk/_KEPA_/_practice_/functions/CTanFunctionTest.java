package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CTanFunctionTest {
    private final static double DELTA=0.00001;
    @Test
    public void testApply() {
        MathFunction ctanFunction = new CTanFunction();
        assertEquals(ctanFunction.apply(java.lang.Math.PI/6), java.lang.Math.pow(3, 0.5), DELTA) ;
        assertEquals(ctanFunction.apply(java.lang.Math.PI/4), 1, DELTA) ;
        assertEquals(ctanFunction.apply(java.lang.Math.PI/3), Math.pow(3, 0.5)/3, DELTA) ;
        assertEquals(ctanFunction.apply(java.lang.Math.PI/2), 0, DELTA) ;
    }
}
