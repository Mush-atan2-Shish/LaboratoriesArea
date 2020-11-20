package ru.ssau.tk.Ssau.Laba2.io;

import ru.ssau.tk.Ssau.Laba2.functions.Point;
import ru.ssau.tk.Ssau.Laba2.functions.TabulatedFunction;
import ru.ssau.tk.Ssau.Laba2.functions.factory.TabulatedFunctionFactory;

import javax.swing.text.NumberFormatter;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    private FunctionsIO() {
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

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = Integer.parseInt(reader.readLine());
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            String[] splitLine = line.split(" ");
            try {
                xValues[i] = numberFormat.parse(splitLine[0]).doubleValue();
                yValues[i] = numberFormat.parse(splitLine[1]).doubleValue();
            } catch (ParseException eParse) {
                throw new IOException(eParse);
            }
        }
        return factory.create(xValues, yValues);
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point point : function) {
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream in = new DataInputStream(inputStream);
        int count = in.readInt();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = in.readDouble();
            yValues[i] = in.readDouble();
        }
        return factory.create(xValues, yValues);
    }
}
