package ru.ssau.tk._KEPA_._practice_.io;

import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;
import ru.ssau.tk._KEPA_._practice_.operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        File file = new File("input/binary function.bin");
        ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        LinkedListTabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            TabulatedFunction functionArray = FunctionsIO.readTabulatedFunction(in, arrayFactory);
            System.out.println(functionArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите размер и значения функции:");
            TabulatedFunction LinkedList = FunctionsIO.readTabulatedFunction(in, listFactory);
            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction diffFunctionList = differentialOperator.derive(LinkedList);
            System.out.println(diffFunctionList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

