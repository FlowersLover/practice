package ru.ssau.tk._KEPA_._practice_.operations;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

import ru.ssau.tk._KEPA_._practice_.functions.*;

public class TabulatedFunctionOperationServiceTest {
    private final double[] valuesX = new double[]{1., 2., 3., 4., 5., 6.};
    private final double[] valuesY = new double[]{1., 4., 9., 16., 25., 36.};
    private final double[] valuesYForList = new double[]{10, 20, 30, 40, 50, 60};
    private final double DELTA = 0.00001;

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
        Points = TabulatedFunctionOperationService.asPoints(list);
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
}