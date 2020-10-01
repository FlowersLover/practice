package ru.ssau.tk._KEPA_._practice_.functions;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    private int count;

    @Override
    public int getCount() {
        return count;
    }

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
        }
        else {
            return interpolate(x, floorIndexOfX(x));
        }
    }
}
