package ru.ssau.tk._KEPA_._practice_.io;

import ru.ssau.tk._KEPA_._practice_.functions.*;

import java.io.*;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        double[] x = {2., 4., 6., 8.};
        double[] y = {4., 16., 36., 64.};
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        try {
            BufferedWriter outArray = new BufferedWriter(new FileWriter("output/array function.txt"));
            BufferedWriter outList = new BufferedWriter(new FileWriter("output/linked list function.txt"));

            FunctionsIO.writeTabulatedFunction(outArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outList, listFunction);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
