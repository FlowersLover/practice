package ru.ssau.tk._KEPA_._practice_.functions;

import ru.ssau.tk._KEPA_._practice_.exceptions.*;


public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Lengths of xValues and yValues are different");
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i + 1] < xValues[i]) {
                throw new ArrayIsNotSortedException("xValues is not sort");
            }
        }
    }

    public abstract int getCount();

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return (extrapolateLeft(x));
        }

        if (x > rightBound()) {
            return (extrapolateRight(x));
        }

        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");

        for (Point point : this) {
            stringBuilder.append("[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}

