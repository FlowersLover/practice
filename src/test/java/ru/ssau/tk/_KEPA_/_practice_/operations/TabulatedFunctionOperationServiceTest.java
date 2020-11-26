package ru.ssau.tk._KEPA_._practice_.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;
import ru.ssau.tk._KEPA_._practice_.exceptions.*;

public class TabulatedFunctionOperationServiceTest {

    private final double[] valuesX = new double[]{1., 2., 3., 4., 5., 6.};
    private final double[] valuesY = new double[]{1., 4., 9., 16., 25., 36.};
    private final double[] valuesYForList = new double[]{10, 20, 30, 40, 50, 60};
    private final double DELTA = 0.00001;

    private final double[] yValues1 = new double[]{2., 8., 32., 160.};
    private final double[] xValues1 = new double[]{2., 4., 6., 8.};
    private final double[] yValues2 = new double[]{20., 40., 60., 80.};
    private final double[] xValues2 = new double[]{2., 4., 6., 8.};

    private final TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues1, yValues1);
    private TabulatedFunction b =  new LinkedListTabulatedFunctionFactory().create(xValues2, yValues2);

    public ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    public LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction array = getTestArray();
        Point[] points = TabulatedFunctionOperationService.asPoints(array);
        TabulatedFunction list = getTestList();
        int i = 0;
        for (Point point : points) {
            assertEquals(point.x, array.getX(i), DELTA);
            assertEquals(point.y, array.getY(i++), DELTA);
        }
        assertEquals(array.getCount(), i);

        points = TabulatedFunctionOperationService.asPoints(list);
        i = 0;
        for (Point point : points) {
            assertEquals(point.x, list.getX(i), DELTA);
            assertEquals(point.y, list.getY(i++), DELTA);
        }
        assertEquals(list.getCount(), i);

    }

    @Test
    public void testSum() {
        TabulatedFunction sumThroughArray = operationServiceThroughArray.sum(a, b);
        TabulatedFunction sumThroughLinkedList = operationServiceThroughLinkedList.sum(a, b);

        int i = 0;
        for (Point point : sumThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] + yValues2[i++]);
        }
        assertTrue(sumThroughArray instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : sumThroughLinkedList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] + yValues2[i++]);
        }
        assertTrue(sumThroughLinkedList instanceof LinkedListTabulatedFunction);

    }

    @Test
    public void testSubtract() {
        TabulatedFunction subtractThroughArray = operationServiceThroughArray.subtract(a, b);
        TabulatedFunction subtractThroughLinkedList = operationServiceThroughLinkedList.subtract(a, b);
        int i = 0;
        for (Point point : subtractThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] - yValues2[i++]);
        }
        assertTrue(subtractThroughArray instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : subtractThroughLinkedList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] - yValues2[i++]);
        }
        assertTrue(subtractThroughLinkedList instanceof LinkedListTabulatedFunction);

    }

    @Test
    public void testMultiply() {
        TabulatedFunction multiplyThroughArray = operationServiceThroughArray.multiply(a, b);
        TabulatedFunction multiplyThroughLinkedList = operationServiceThroughLinkedList.multiply(a, b);
        int i = 0;
        for (Point point : multiplyThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues2[i++]);
        }
        assertTrue(multiplyThroughArray instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : multiplyThroughLinkedList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues2[i++]);
        }
        assertTrue(multiplyThroughLinkedList instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testDivide() {
        TabulatedFunction divideThroughArray = operationServiceThroughArray.divide(a, b);
        TabulatedFunction divideThroughLinkedList = operationServiceThroughLinkedList.divide(a, b);
        int i = 0;
        for (Point point : divideThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues2[i++]);
        }
        assertTrue(divideThroughArray instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : divideThroughLinkedList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues2[i++]);
        }
        assertTrue(divideThroughLinkedList instanceof LinkedListTabulatedFunction);

    }

    @Test
    public void exceptionsTest() {
        assertThrows(InconsistentFunctionsException.class, () -> {
            TabulatedFunction c = new LinkedListTabulatedFunctionFactory().create(new double[]{2., 8., 16., 160}, new double[]{1., 2., 3., 4.});
            operationServiceThroughLinkedList.sum(a, c);
        });
        assertThrows(InconsistentFunctionsException.class, () -> {
            TabulatedFunction c = new LinkedListTabulatedFunctionFactory().create(new double[]{2., 8., 16.}, new double[]{1., 2., 3.});
            operationServiceThroughArray.multiply(a, c);
        });
    }


}