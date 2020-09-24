package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    private final static double DELTA = 0.00001;
    UnitFunction unitFunction = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(unitFunction.apply(5), 1, DELTA);
        assertEquals(unitFunction.apply(-5), 1, DELTA);
        assertNotEquals(unitFunction.apply(1), 0, DELTA);
    }

    @Test
    public void testGetConstant() {
        assertEquals(unitFunction.getConstant(), 1, DELTA);
    }

}