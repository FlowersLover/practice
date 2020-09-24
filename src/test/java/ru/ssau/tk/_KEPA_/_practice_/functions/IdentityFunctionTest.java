package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest  {
    private final static double DELTA=0.00001;
    @Test
    public void testApply() {
        MathFunction test = new IdentityFunction();
        assertEquals(test.apply(0), 0, DELTA) ;
        assertEquals(test.apply(1),1, DELTA) ;
        assertEquals(test.apply(-1),-1, DELTA) ;
    }
}