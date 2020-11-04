package ru.ssau.tk._KEPA_._practice_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.SqrFunction;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.*;

public class MiddleSteppingDifferentialOperatorTest {
    @Test
    public void testDerive() {
        final double DELTA = 0.00001;
        double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2., DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 6., DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 10., DELTA);

    }

}