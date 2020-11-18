package ru.ssau.tk._KEPA_._practice_.io;

import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;
import ru.ssau.tk._KEPA_._practice_.operations.*;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File outList = new File("output/serialized linked list functions.bin");
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {1, 4, 9, 16, 25};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        LinkedListTabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction firstDerive = differentialOperator.derive(listFunction);
        TabulatedFunction secondDerive = differentialOperator.derive(firstDerive);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outList));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(outList))) {

            FunctionsIO.serialize(out, listFunction);
            FunctionsIO.serialize(out, firstDerive);
            FunctionsIO.serialize(out, secondDerive);

            TabulatedFunction resultList = FunctionsIO.deserialize(in);
            TabulatedFunction resultList1 = FunctionsIO.deserialize(in);
            TabulatedFunction resultList2 = FunctionsIO.deserialize(in);

            System.out.println(resultList.toString() + "\n");
            System.out.println(resultList1.toString() + "\n");
            System.out.println(resultList2.toString() + "\n");
        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }
    }
}
