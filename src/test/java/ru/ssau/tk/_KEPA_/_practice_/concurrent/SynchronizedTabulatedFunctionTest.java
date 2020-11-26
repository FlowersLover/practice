package ru.ssau.tk._KEPA_._practice_.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.Point;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6};
    private final double[] yValues = new double[]{1, 4, 9, 16, 25, 36};
    private final Object sync = new Object();
    private final double DELTA = 0.00001;

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), sync);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), sync);
    }

    @Test
    public void testDoSynchronously() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals((int) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount), 6);
        assertEquals((double) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::leftBound), 1.0);
        assertEquals((double) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::rightBound), 6.0);
    }

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getCount(), 6, DELTA);
        assertEquals(synchronizedArr.getCount(), 6, DELTA);
    }

    @Test
    public void testGetX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getX(0), 1, DELTA);
        assertEquals(synchronizedTabulatedFunction.getX(2), 3, DELTA);
        assertEquals(synchronizedTabulatedFunction.getX(5), 6, DELTA);

        assertEquals(synchronizedArr.getX(1), 2, DELTA);
        assertEquals(synchronizedArr.getX(3), 4, DELTA);
        assertEquals(synchronizedArr.getX(5), 6, DELTA);
    }

    @Test
    public void testGetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getY(0), 1, DELTA);
        assertEquals(synchronizedTabulatedFunction.getY(2), 9, DELTA);
        assertEquals(synchronizedTabulatedFunction.getY(5), 36, DELTA);

        assertEquals(synchronizedArr.getY(1), 4, DELTA);
        assertEquals(synchronizedArr.getY(3), 16, DELTA);
        assertEquals(synchronizedArr.getY(5), 36, DELTA);
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        synchronizedTabulatedFunction.setY(0, 10);
        assertEquals(synchronizedTabulatedFunction.getY(0), 10, DELTA);
        synchronizedTabulatedFunction.setY(3, 40);
        assertEquals(synchronizedTabulatedFunction.getY(3), 40, DELTA);
        synchronizedTabulatedFunction.setY(5, 60);
        assertEquals(synchronizedTabulatedFunction.getY(5), 60, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        synchronizedArr.setY(1, 20);
        assertEquals(synchronizedArr.getY(1), 20, DELTA);
        synchronizedArr.setY(2, 30);
        assertEquals(synchronizedArr.getY(2), 30, DELTA);
        synchronizedArr.setY(4, 50);
        assertEquals(synchronizedArr.getY(4), 50, DELTA);
    }

    @Test
    public void testIteratorWhile() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        Iterator<Point> testIterator = synchronizedTabulatedFunction.iterator();
        int i = 0;
        while (testIterator.hasNext()) {
            Point myPoint = testIterator.next();
            assertEquals(synchronizedTabulatedFunction.getX(i), myPoint.x);
            assertEquals(synchronizedTabulatedFunction.getY(i++), myPoint.y);
        }
        assertEquals(synchronizedTabulatedFunction.getCount(), i);

        assertThrows(NoSuchElementException.class, testIterator::next);
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        i = 0;
        for (Point point : synchronizedArr) {
            assertEquals(point.x, synchronizedArr.getX(i), DELTA);
            assertEquals(point.y, synchronizedArr.getY(i++), DELTA);
        }
        assertEquals(synchronizedArr.getCount(), i);
    }

    @Test
    public void testIndexOfX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfX(1), 0, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfX(3), 2, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfX(6), 5, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfX(2), 1, DELTA);
        assertEquals(synchronizedArr.indexOfX(4), 3, DELTA);
        assertEquals(synchronizedArr.indexOfX(6), 5, DELTA);
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfY(1), 0, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfY(9), 2, DELTA);
        assertEquals(synchronizedTabulatedFunction.indexOfY(36), 5, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfY(1), 0, DELTA);
        assertEquals(synchronizedArr.indexOfY(16), 3, DELTA);
        assertEquals(synchronizedArr.indexOfY(36), 5, DELTA);
    }

    @Test
    public void testLeftBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.leftBound(), 1, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.rightBound(), 6, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.rightBound(), 6, DELTA);
    }

    @Test
    public void testApply() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.apply(4), 16, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(-7), -23, DELTA);
        assertEquals(synchronizedTabulatedFunction.apply(0), -2, DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.apply(0), -2, DELTA);
        assertEquals(synchronizedArr.apply(8), 58, DELTA);
        assertEquals(synchronizedArr.apply(-4), -14, DELTA);
    }

}

