package ru.ssau.tk._KEPA_._practice_.functions.factory;

import ru.ssau.tk._KEPA_._practice_.functions.MathFunction;
import ru.ssau.tk._KEPA_._practice_.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}
