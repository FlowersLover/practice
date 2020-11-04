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

    double[] yValues1 = new double[]{2., 8., 32., 160.};
    double[] xValues1 = new double[]{2., 4., 6., 8.};
    double[] yValues2 = new double[]{20., 40., 60., 80.};
    double[] xValues2 = new double[]{2., 4., 6., 8.};

    TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
    TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(xValues1, yValues1);
    TabulatedFunction b = linkedListFactory.create(xValues2, yValues2);
    TabulatedFunctionOperationService operationService = new TabulatedFunctionOperationService();


    ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction array = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(array);
        TabulatedFunction list = getTestList();
        Points = TabulatedFunctionOperationService.asPoints(array);
        int i = 0;
        for (Point point : Points) {
            assertEquals(point.x, array.getX(i), DELTA);
            assertEquals(point.y, array.getY(i++), DELTA);
        }
        assertEquals(array.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(list);
        i = 0;
        for (Point point : Points) {
            assertEquals(point.x, list.getX(i), DELTA);
            assertEquals(point.y, list.getY(i++), DELTA);
        }
        assertEquals(list.getCount(), i);

    }

    @Test
    public void testGetFactory() {
        assertTrue(operationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        operationService.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(operationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {

        assertThrows(InconsistentFunctionsException.class, () -> {
            TabulatedFunction c = linkedListFactory.create(new double[]{1., 2., 3.}, new double[]{1., 2., 3.});
            operationService.sum(a, c);
        });
        assertThrows(InconsistentFunctionsException.class, () -> {
            TabulatedFunction c = linkedListFactory.create(new double[]{1., 2., 3.}, new double[]{1., 2., 3.});
            operationService.sum(b, c);
        });

        TabulatedFunction sumThroughArray = operationService.sum(a, a);
        TabulatedFunction sumThroughLinkedList = operationService.sum(b, b);
        TabulatedFunction sumOfArrayAndList = operationService.sum(a, b);

        int i = 0;
        for (Point point : sumThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] + yValues1[i++]);
        }
        i = 0;
        for (Point point : sumThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] + yValues2[i++]);
        }
        i = 0;
        for (Point point : sumOfArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] + yValues2[i++]);
        }

    }

    @Test
    public void testSubtract() {
        TabulatedFunction subtractThroughArray = operationService.subtract(a, a);
        TabulatedFunction subtractThroughLinkedList = operationService.subtract(b, b);
        TabulatedFunction subtractOfArrayAndList = operationService.subtract(a, b);
        int i = 0;
        for (Point point : subtractThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] - yValues1[i++]);
        }
        i = 0;
        for (Point point : subtractThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] - yValues2[i++]);
        }
        i = 0;
        for (Point point : subtractOfArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] - yValues2[i++]);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunction multiplyThroughArray = operationService.multiply(a, a);
        TabulatedFunction multiplyThroughLinkedList = operationService.multiply(b, b);
        TabulatedFunction multiplyOfArrayAndList = operationService.multiply(a, b);
        int i = 0;
        for (Point point : multiplyThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues1[i++]);
        }
        i = 0;
        for (Point point : multiplyThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] * yValues2[i++]);
        }
        i = 0;
        for (Point point : multiplyOfArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] * yValues2[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunction divideThroughArray = operationService.divide(a, a);
        TabulatedFunction divideThroughLinkedList = operationService.divide(b, b);
        TabulatedFunction divideArrayAndList = operationService.divide(a, b);
        int i = 0;
        for (Point point : divideThroughArray) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues1[i++]);
        }
        i = 0;
        for (Point point : divideThroughLinkedList) {
            assertEquals(point.x, xValues2[i]);
            assertEquals(point.y, yValues2[i] / yValues2[i++]);
        }
        i = 0;
        for (Point point : divideArrayAndList) {
            assertEquals(point.x, xValues1[i]);
            assertEquals(point.y, yValues1[i] / yValues2[i++]);
        }
    }


}