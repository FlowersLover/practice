package ru.ssau.tk._KEPA_._practice_.functions;

public interface TabulatedFunction extends MathFunction {
    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    double indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();
}
