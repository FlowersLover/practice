package ru.ssau.tk._KEPA_._practice_.functions;


import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction  {

    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        for (int i =0; i < count; i++) {
            xValues[i] = xFrom + i * (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
    }


    @Override
    public int getCount() {
        return (count);
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
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
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            return 0;
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i] > x) {
                return i-1;
            }
        }
        return count;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }
    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }


    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }
}