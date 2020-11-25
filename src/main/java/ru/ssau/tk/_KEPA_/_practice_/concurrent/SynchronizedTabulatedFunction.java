package ru.ssau.tk._KEPA_._practice_.concurrent;

import ru.ssau.tk._KEPA_._practice_.functions.TabulatedFunction;
import ru.ssau.tk._KEPA_._practice_.functions.*;

import java.util.Iterator;
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
            return function.iterator();
        }
    }

    @Override
    public double apply(double x) {
        synchronized (sync) {
            return function.apply(x);
        }
    }
}

