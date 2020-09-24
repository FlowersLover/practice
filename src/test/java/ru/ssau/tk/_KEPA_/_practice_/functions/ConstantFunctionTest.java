package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    private static final double DELTA = 1.0E-5D;
    private final ConstantFunction constantFunction = new ConstantFunction(4.55);
    private final ConstantFunction constantFunction1 = new ConstantFunction(1.66);

    @Test
    public void testApply() {
        assertEquals(constantFunction.apply(4.55), 4.55, DELTA);
        assertEquals(constantFunction1.apply(1.66), 1.66, DELTA);
    }
    @Test
    public void testGetConstant(){
            assertEquals(constantFunction.getConstant(),4.55,DELTA);
            assertEquals(constantFunction1.getConstant(),1.66,DELTA);
            assertNotEquals(constantFunction.getConstant(),4,DELTA);
            assertNotEquals(constantFunction1.getConstant(),1,DELTA);
        }
    }
