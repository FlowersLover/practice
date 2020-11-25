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
    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 4, 9, 16, 25});
        TabulatedDifferentialOperator differentialOperatorList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionList = differentialOperatorList.deriveSynchronously(linkedListTabulatedFunction);
        assertTrue(differentialFunctionList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < differentialFunctionList.getCount(); i++) {
            assertEquals(differentialFunctionList.getX(i), (1+ i));
        }

        assertEquals(differentialFunctionList.getY(0), 3);
        assertEquals(differentialFunctionList.getY(1), 5);
        assertEquals(differentialFunctionList.getY(2), 7);
        assertEquals(differentialFunctionList.getY(3), 9);
        assertEquals(differentialFunctionList.getY(4), 9);

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{7, 8, 9, 10, 11, 12}, new double[]{49, 64, 82, 100, 121, 144});
        TabulatedDifferentialOperator differentialOperatorArray = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction differentialFunctionArray = differentialOperatorArray.deriveSynchronously(arrayTabulatedFunction);
        assertTrue(differentialFunctionArray instanceof ArrayTabulatedFunction);

        for (int i = 0; i < differentialFunctionArray.getCount(); i++) {
            assertEquals(differentialFunctionArray.getX(i), (7 + i));
        }

        assertEquals(differentialFunctionArray.getY(0), 15);
        assertEquals(differentialFunctionArray.getY(1), 18);
        assertEquals(differentialFunctionArray.getY(2), 18);
        assertEquals(differentialFunctionArray.getY(3), 21);
        assertEquals(differentialFunctionArray.getY(4), 23);
        assertEquals(differentialFunctionArray.getY(5), 23);
    }
}
