package ru.ssau.tk._KEPA_._practice_.functions;

public class CTanFunction implements MathFunction {
    public double apply(double x) {
        return Math.pow(Math.tan(x), -1);
    }
}
