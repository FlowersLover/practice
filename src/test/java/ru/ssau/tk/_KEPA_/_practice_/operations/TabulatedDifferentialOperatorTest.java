package ru.ssau.tk._KEPA_._practice_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    final double DELTA = 0.00005;
    private double[] xValues = new double[]{4., 6., 8., 10.};
    private double[] yValues = new double[]{2., 10., 14., 36.};


    @Test
    public void testDerive() {
        TabulatedFunction deriveThroughArray = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        deriveThroughArray = differentialArrayOperator.derive(deriveThroughArray);
        assertTrue(deriveThroughArray instanceof ArrayTabulatedFunction);
        assertEquals(deriveThroughArray.getX(0), 4., DELTA);
        assertEquals(deriveThroughArray.getX(1), 6., DELTA);
        assertEquals(deriveThroughArray.getX(2), 8., DELTA);
        assertEquals(deriveThroughArray.getX(3), 10., DELTA);

        assertEquals(deriveThroughArray.getY(0), 4., DELTA);
        assertEquals(deriveThroughArray.getY(1), 2., DELTA);
        assertEquals(deriveThroughArray.getY(2), 11., DELTA);
        assertEquals(deriveThroughArray.getY(3), 11., DELTA);

        TabulatedFunction deriveThroughLinkedList = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        deriveThroughLinkedList = differentialListOperator.derive(deriveThroughLinkedList);
        assertTrue(deriveThroughLinkedList instanceof LinkedListTabulatedFunction);
        assertEquals(deriveThroughLinkedList.getX(0), 4., DELTA);
        assertEquals(deriveThroughLinkedList.getX(1), 6., DELTA);
        assertEquals(deriveThroughLinkedList.getX(2), 8., DELTA);
        assertEquals(deriveThroughLinkedList.getX(3), 10., DELTA);

        assertEquals(deriveThroughLinkedList.getY(0), 4., DELTA);
        assertEquals(deriveThroughLinkedList.getY(1), 2., DELTA);
        assertEquals(deriveThroughLinkedList.getY(2), 11., DELTA);
        assertEquals(deriveThroughLinkedList.getY(3), 11., DELTA);

    }
}
