package ru.ssau.tk._KEPA_._practice_.io;

import java.io.*;

import ru.ssau.tk._KEPA_._practice_.functions.*;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream output = new DataOutputStream(outputStream);
        output.writeInt(function.getCount());
        for (Point newPoint : function) {
            output.writeDouble(newPoint.x);
            output.writeDouble(newPoint.y);
        }
        output.flush();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        writer.flush();
    }
}
