package ru.ssau.tk._KEPA_._practice_.operations;

import ru.ssau.tk._KEPA_._practice_.functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override

    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x) - function.apply(x - step)) / step;
    }

}

