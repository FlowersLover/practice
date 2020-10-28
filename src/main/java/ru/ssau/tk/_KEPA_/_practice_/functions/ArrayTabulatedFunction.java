package ru.ssau.tk._KEPA_._practice_.functions;
import ru.ssau.tk._KEPA_._practice_.exceptions.*;


import java.util.Arrays;
import java.util.Iterator;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {

    private final double[] xValues;
    private final double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array less than minimum length");
        }
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count less than minimum length");
        }
        if (xFrom >= xTo) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
    }


    @Override
    public int getCount() {
        return (count);
    }

    @Override
    public double getX(int index)throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return xValues[index];
    }

    @Override
    public double getY(int index) throws ArrayIndexOutOfBoundsException  {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    protected int floorIndexOfX(double x) throws ArrayIndexOutOfBoundsException {
        if (x < xValues[0]) {
            throw new ArrayIndexOutOfBoundsException("Argument x less than minimal x in tabulated function");
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || xValues[floorIndex + 1] < x) {
            throw new InterpolationException();
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }
    @Override
    public Iterator<Point> iterator() {
        throw new  UnsupportedOperationException();
    }
}

