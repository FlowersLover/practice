package ru.ssau.tk._KEPA_._practice_.io;


import java.io.*;

import ru.ssau.tk._KEPA_._practice_.functions.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File arrayFile = new File("output/array function.bin");
        File listFile = new File("output/linked list function.bin");
        double[] x = {1, 2, 3, 4, 5, 6};
        double[] y = {2, 4, 6, 8, 10, 12};
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        try (BufferedOutputStream outArray = new BufferedOutputStream(new FileOutputStream(arrayFile));
             BufferedOutputStream outList = new BufferedOutputStream(new FileOutputStream(listFile))) {
            FunctionsIO.writeTabulatedFunction(outArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outList, listFunction);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

}
