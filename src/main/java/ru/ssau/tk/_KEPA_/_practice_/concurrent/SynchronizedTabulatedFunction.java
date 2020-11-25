package ru.ssau.tk._KEPA_._practice_.concurrent;

import ru.ssau.tk._KEPA_._practice_.functions.TabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction function;
    private final Object sync;

    public SynchronizedTabulatedFunction(TabulatedFunction function, Object sync) {
        this.function = function;
        this.sync = Objects.requireNonNull(sync);
    }

    @Override
    public int getCount() {
        synchronized (sync) {
            return function.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (sync) {
            return function.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (sync) {
            return function.getY(index);
        }
    }


    @Override
    public void setY(int index, double value) {
        synchronized (sync) {
            function.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (sync) {
            return function.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (sync) {
            return function.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (sync) {
            return function.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (sync) {
            return function.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (sync) {
            Point[] points = TabulatedFunctionOperationService.asPoints(function);
            return new Iterator<>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return i < points.length;
                }

                @Override
                public Point next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return points[i++];
                }
            };
        }
    }

    @Override
    public double apply(double x) {
        synchronized (sync) {
            return function.apply(x);
        }
    }
}

