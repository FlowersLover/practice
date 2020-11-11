package ru.ssau.tk._KEPA_._practice_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {
    final double DELTA = 0.00001;
    double step = 0.001;

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 9.999, DELTA);
    }
}