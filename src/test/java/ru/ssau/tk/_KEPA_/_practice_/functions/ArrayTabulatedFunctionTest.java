package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{1.,2.,3., 4.,5.,6.};
    private final double[] valuesY = new double[]{1.,4.,9.,16.,25.,36.};
    private final MathFunction sqrFunction = new SqrFunction();
    private final double DELTA = 0.00001;


    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunction , 0, 10, 101);
    }
    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-1), -5, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-1), -0.1, DELTA);
        assertEquals(getDefinedThroughArrays().apply(40), 410, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(30), 498, DELTA);
        assertEquals(getDefinedThroughArrays().apply(1.5), 2.5, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(1.5), 2.25, DELTA);


    }
    @Test
    public void testGetCount() {

        assertEquals(getDefinedThroughArrays().getCount(), 6,DELTA);
        assertEquals(getDefinedThroughMathFunction().getCount(), 101,DELTA);
        assertNotEquals(getDefinedThroughArrays().getCount(), 3,DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getCount(), 10,DELTA);

    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), 1, DELTA);
        assertEquals(getDefinedThroughArrays().getX(1), 2, DELTA);
        assertNotEquals(getDefinedThroughArrays().getX(1), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(100), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getX(5), 0, DELTA);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().getX(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().getX(-1));
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), 1, DELTA);
        assertEquals(getDefinedThroughArrays().getY(4), 25, DELTA);
        assertNotEquals(getDefinedThroughArrays().getY(2), 1, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(1),0.01, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(20), 4, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getY(30),0, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().getY(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().getY(-1));
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();

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

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().setY(-1,0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().setY(-1,0));

    }

    @Test
    public void testLeftBound() {
        assertEquals(getDefinedThroughArrays().leftBound(), 1, DELTA);
        assertNotEquals(getDefinedThroughArrays().leftBound(), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().leftBound(), 0.1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getDefinedThroughArrays().rightBound(), 6, DELTA);
        assertNotEquals(getDefinedThroughArrays().rightBound(), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 10, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().rightBound(), 3, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(3.4, getDefinedThroughArrays().floorIndexOfX(3.4)), 11.8, DELTA);
        assertEquals(getDefinedThroughArrays().interpolate(1.2, getDefinedThroughArrays().floorIndexOfX(1.2)), 1.6, DELTA);
        assertNotEquals(getDefinedThroughArrays().interpolate(1.2, getDefinedThroughArrays().floorIndexOfX(1.2)), 1, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(2.125, getDefinedThroughMathFunction().floorIndexOfX(2.125)), 4.5175, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(5.252, getDefinedThroughMathFunction().floorIndexOfX(5.252)), 27.586, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().interpolate(0.125, getDefinedThroughMathFunction().floorIndexOfX(0.125)), 0, DELTA);

    }

    @Test
    public void testIndexOfX() {
        assertEquals(getDefinedThroughArrays().indexOfX(2), 1, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(3), 2, DELTA);
        assertNotEquals(getDefinedThroughArrays().indexOfX(3), 3, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(8), 80, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(3), 30, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfX(3), 0, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getDefinedThroughArrays().indexOfY(4), 1, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(25), 4, DELTA);
        assertNotEquals(getDefinedThroughArrays().indexOfY(25), 6, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(1), 10, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(36), 60, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfY(36), 61, DELTA);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getDefinedThroughArrays().floorIndexOfX(3.6), 2, DELTA);
        assertNotEquals(getDefinedThroughArrays().floorIndexOfX(4.5), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(3.122), 31, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().floorIndexOfX(3), 31, DELTA);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughArrays().floorIndexOfX(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> getDefinedThroughMathFunction().floorIndexOfX(-1));

    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(1.4), 2.2, DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateLeft(1.11), 1.33, DELTA);
        assertNotEquals(getDefinedThroughArrays().extrapolateLeft(1.11), 0 , DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(0.03), 0.003, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateLeft(0.07), 0.007, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateLeft(0.07), 0.7, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(5.5), 30.5, DELTA);
        assertEquals(getDefinedThroughArrays().extrapolateRight(5.11), 26.21, DELTA);
        assertNotEquals(getDefinedThroughArrays().extrapolateRight(5.11), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(9.91), 98.209, DELTA);
        assertEquals(getDefinedThroughMathFunction().extrapolateRight(9.95), 99.005, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateRight(9.95), 100, DELTA);


    }
}