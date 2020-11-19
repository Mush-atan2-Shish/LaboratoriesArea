package ru.ssau.tk.Ssau.Laba2.io;

import ru.ssau.tk.Ssau.Laba2.functions.Point;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;

import java.io.*;

public final class FunctionsIO {
    FunctionsIO() {
        throw new UnsupportedOperationException("Unavailable operation");
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        int i = 0;
        for (Point a : function) {
            printWriter.printf("%f %f\n", a.x, a.y);
        }
        printWriter.flush();
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException{
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point point: function){
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }
}
