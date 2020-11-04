package ru.ssau.tk._KEPA_._practice_.operations;

import static org.testng.Assert.*;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.*;

public class RightSteppingDifferentialOperatorTest {
    @Test
    public void testDerive() {
        final double DELTA = 0.00001;
        double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.001, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 6.001, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 10.001, DELTA);

    }
}