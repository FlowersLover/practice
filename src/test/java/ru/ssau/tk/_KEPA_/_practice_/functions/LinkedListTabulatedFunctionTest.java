package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final double[] valuesX = new double[]{1, 2, 3, 4, 5, 6};
    private final double[] valuesY = new double[]{1, 4, 9, 16, 25, 36};
    private final MathFunction sqrFunction = new SqrFunction();
    private final double DELTA = 0.00001;


    private LinkedListTabulatedFunction getThroughArrays() {
        return new LinkedListTabulatedFunction(valuesX, valuesY);
    }

    private LinkedListTabulatedFunction getThroughMathFunction() {
        return new LinkedListTabulatedFunction(sqrFunction, 0, 10, 101);
    }

    @Test
    public void testApply() {
        assertEquals(getThroughArrays().apply(3), 9, DELTA);
        assertEquals(getThroughMathFunction().apply(5), 25, DELTA);

        assertEquals(getThroughArrays().apply(7), 47, DELTA);
        assertEquals(getThroughMathFunction().apply(11), 119.9, DELTA);

        assertEquals(getThroughArrays().apply(0.5), -0.5, DELTA);
        assertEquals(getThroughMathFunction().apply(7.5), 56.25, DELTA);
    }

    @Test
    public void testGetCount() {

        assertEquals(getThroughArrays().getCount(), 6, DELTA);
        assertEquals(getThroughMathFunction().getCount(), 101, DELTA);
        assertNotEquals(getThroughArrays().getCount(), 3, DELTA);
        assertNotEquals(getThroughMathFunction().getCount(), 10, DELTA);

    }

    @Test
    public void testGetX() {
        assertEquals(getThroughArrays().getX(0), 1, DELTA);
        assertEquals(getThroughArrays().getX(5), 6, DELTA);
        assertNotEquals(getThroughArrays().getX(1), 0, DELTA);
        assertEquals(getThroughMathFunction().getX(100), 10, DELTA);
        assertEquals(getThroughMathFunction().getX(0), 0, DELTA);
        assertNotEquals(getThroughMathFunction().getX(5), 1, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getThroughArrays().getY(0), 1, DELTA);
        assertEquals(getThroughArrays().getY(4), 25, DELTA);
        assertNotEquals(getThroughArrays().getY(2), 1, DELTA);
        assertEquals(getThroughMathFunction().getY(1), 0.01, DELTA);
        assertEquals(getThroughMathFunction().getY(20), 4, DELTA);
        assertNotEquals(getThroughMathFunction().getY(30), 0, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction testDefinedThroughArrays = getThroughArrays();
        LinkedListTabulatedFunction testDefinedThroughMathFunction = getThroughMathFunction();

        testDefinedThroughArrays.setY(2, 300.);
        testDefinedThroughArrays.setY(3, 20.);
        testDefinedThroughArrays.setY(4, 1);

        testDefinedThroughMathFunction.setY(2, 1001.);
        testDefinedThroughMathFunction.setY(30, 1002.);
        testDefinedThroughMathFunction.setY(50, 1003.);

        assertEquals(testDefinedThroughArrays.getY(2), 300, DELTA);
        assertEquals(testDefinedThroughArrays.getY(3), 20, DELTA);
        assertEquals(testDefinedThroughArrays.getY(4), 1, DELTA);
        assertNotEquals(testDefinedThroughArrays.getY(4), 25, DELTA);

        assertEquals(testDefinedThroughMathFunction.getY(2), 1001., DELTA);
        assertEquals(testDefinedThroughMathFunction.getY(30), 1002., DELTA);
        assertEquals(testDefinedThroughMathFunction.getY(50), 1003., DELTA);

    }

    @Test
    public void testLeftBound() {
        assertEquals(getThroughArrays().leftBound(), 1, DELTA);
        assertNotEquals(getThroughArrays().leftBound(), 0, DELTA);
        assertEquals(getThroughMathFunction().leftBound(), 0, DELTA);
        assertNotEquals(getThroughMathFunction().leftBound(), 0.1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getThroughArrays().rightBound(), 6, DELTA);
        assertNotEquals(getThroughArrays().rightBound(), 2, DELTA);
        assertEquals(getThroughMathFunction().rightBound(), 10, DELTA);
        assertNotEquals(getThroughMathFunction().rightBound(), 3, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getThroughArrays().interpolate(3.4, getThroughArrays().floorIndexOfX(3.4)), 11.8, DELTA);
        assertEquals(getThroughArrays().interpolate(1.2, getThroughArrays().floorIndexOfX(1.2)), 1.6, DELTA);
        assertNotEquals(getThroughArrays().interpolate(1.2, getThroughArrays().floorIndexOfX(1.2)), 1, DELTA);
        assertEquals(getThroughMathFunction().interpolate(2.125, getThroughMathFunction().floorIndexOfX(2.125)), 4.5175, DELTA);
        assertEquals(getThroughMathFunction().interpolate(5.252, getThroughMathFunction().floorIndexOfX(5.252)), 27.586, DELTA);
        assertNotEquals(getThroughMathFunction().interpolate(0.125, getThroughMathFunction().floorIndexOfX(0.125)), 0, DELTA);

    }

    @Test
    public void testIndexOfX() {
        assertEquals(getThroughArrays().indexOfX(2), 1, DELTA);
        assertEquals(getThroughArrays().indexOfX(3), 2, DELTA);
        assertNotEquals(getThroughArrays().indexOfX(3), 3, DELTA);
        assertEquals(getThroughMathFunction().indexOfX(0.2), 2, DELTA);
        assertEquals(getThroughMathFunction().indexOfX(120), -1, DELTA);
        assertNotEquals(getThroughMathFunction().indexOfX(3), 0, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getThroughArrays().indexOfY(4), 1, DELTA);
        assertEquals(getThroughArrays().indexOfY(25), 4, DELTA);
        assertNotEquals(getThroughArrays().indexOfY(25), 6, DELTA);
        assertEquals(getThroughMathFunction().indexOfY(0), 0, DELTA);
        assertEquals(getThroughMathFunction().indexOfY(110), -1, DELTA);
        assertNotEquals(getThroughMathFunction().indexOfY(36), 61, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getThroughArrays().floorIndexOfX(3.6), 2, DELTA);
        assertEquals(getThroughArrays().floorIndexOfX(-4), 0, DELTA);
        assertNotEquals(getThroughArrays().floorIndexOfX(4.5), 0, DELTA);
        assertEquals(getThroughMathFunction().floorIndexOfX(-1), 0, DELTA);
        assertEquals(getThroughMathFunction().floorIndexOfX(3.122), 31, DELTA);
        assertNotEquals(getThroughMathFunction().floorIndexOfX(3), 31, DELTA);

    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getThroughArrays().extrapolateLeft(3), 7, DELTA);
        assertEquals(getThroughArrays().extrapolateLeft(1.11), 1.33, DELTA);
        assertNotEquals(getThroughArrays().extrapolateLeft(1.11), 0, DELTA);
        assertEquals(getThroughMathFunction().extrapolateLeft(0.03), 0.003, DELTA);
        assertEquals(getThroughMathFunction().extrapolateLeft(0.07), 0.007, DELTA);
        assertNotEquals(getThroughMathFunction().extrapolateLeft(0.07), 0.7, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getThroughArrays().extrapolateRight(5.5), 30.5, DELTA);
        assertEquals(getThroughArrays().extrapolateRight(5.11), 26.21, DELTA);
        assertNotEquals(getThroughArrays().extrapolateRight(5.11), 0, DELTA);
        assertEquals(getThroughMathFunction().extrapolateRight(9.91), 98.209, DELTA);
        assertEquals(getThroughMathFunction().extrapolateRight(9.95), 99.005, DELTA);
        assertNotEquals(getThroughMathFunction().extrapolateRight(9.95), 100, DELTA);


    }
}