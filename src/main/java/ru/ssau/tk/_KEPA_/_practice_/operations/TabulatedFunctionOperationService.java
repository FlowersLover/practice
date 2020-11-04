package ru.ssau.tk._KEPA_._practice_.operations;

import ru.ssau.tk._KEPA_._practice_.functions.Point;
import ru.ssau.tk._KEPA_._practice_.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point currentPoint : tabulatedFunction) {
            points[i++] = currentPoint;
        }
        return points;
    }
}
