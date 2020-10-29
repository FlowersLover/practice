package ru.ssau.tk._KEPA_._practice_.functions;

import org.testng.annotations.Test;
import ru.ssau.tk._KEPA_._practice_.exceptions.*;


import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {

    @Test
    public static void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{8, 4, 0};
            double[] valuesY = new double[]{7, -2};
            AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
        });
        double[] valuesX = new double[]{3, 5};
        double[] valuesY = new double[]{0, 8};
        AbstractTabulatedFunction.checkLengthIsTheSame(valuesX, valuesY);
    }

    @Test
    public static void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{2, -5, 7, 6, 3};
            AbstractTabulatedFunction.checkSorted(valuesX);
        });
        double[] valuesX = new double[]{1, 2, 3, 4, 5};
        AbstractTabulatedFunction.checkSorted(valuesX);
    }
}