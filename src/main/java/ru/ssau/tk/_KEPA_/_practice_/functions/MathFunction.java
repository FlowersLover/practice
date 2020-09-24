package ru.ssau.tk._KEPA_._practice_.functions;

public interface MathFunction {
    default double apply(double x) {
        return x;
    }

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
