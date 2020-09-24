package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    private final static double DELTA=0.00001;
    ZeroFunction zeroFunction= new ZeroFunction();
    @Test
    public void testApply(){
        assertEquals(zeroFunction.apply(5),0,DELTA);
        assertEquals(zeroFunction.apply(-5),0,DELTA);
        assertNotEquals(zeroFunction.apply(1),1,DELTA);
}
    @Test
    public void testGetConstant(){
        assertEquals(zeroFunction.getConstant(), 0, DELTA);
    }
}