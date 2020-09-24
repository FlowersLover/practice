package ru.ssau.tk._KEPA_._practice_.functions;

public class ConstantFunction implements MathFunction {

    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    @Override
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }

}
