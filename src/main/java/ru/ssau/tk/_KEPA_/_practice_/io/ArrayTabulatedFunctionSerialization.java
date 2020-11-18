package ru.ssau.tk._KEPA_._practice_.io;

import ru.ssau.tk._KEPA_._practice_.functions.*;
import ru.ssau.tk._KEPA_._practice_.functions.factory.*;
import ru.ssau.tk._KEPA_._practice_.operations.*;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File fileArray = new File("output/serialized array functions.bin");
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {1, 4, 9, 16, 25};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        TabulatedFunction firstDerive = differentialOperator.derive(arrayFunction);
        TabulatedFunction secondDerive = differentialOperator.derive(firstDerive);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileArray));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileArray))) {

            FunctionsIO.serialize(out, arrayFunction);
            FunctionsIO.serialize(out, firstDerive);
            FunctionsIO.serialize(out, secondDerive);

            TabulatedFunction deserializedArrayFunction = FunctionsIO.deserialize(in);
            TabulatedFunction deserializedFirstDerive = FunctionsIO.deserialize(in);
            TabulatedFunction deserializedSecondDerive = FunctionsIO.deserialize(in);

            System.out.println(deserializedArrayFunction.toString());
            System.out.println(deserializedFirstDerive.toString());
            System.out.println(deserializedSecondDerive.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
