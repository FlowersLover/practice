package ru.ssau.tk._KEPA_._practice_.operations;

import ru.ssau.tk._KEPA_._practice_.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}