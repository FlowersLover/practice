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

    @Test
    public void testTestToString() {
        double[] x = {2.,4. ,6.,8. };
        double[] y = {4., 16.,36.,64.};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction size = 4\n[2.0; 4.0]\n[4.0; 16.0]\n[6.0; 36.0]\n[8.0; 64.0]");

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction size = 4\n[2.0; 4.0]\n[4.0; 16.0]\n[6.0; 36.0]\n[8.0; 64.0]");
    }
}