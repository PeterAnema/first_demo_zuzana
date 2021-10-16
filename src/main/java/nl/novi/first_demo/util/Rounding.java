package nl.novi.first_demo.util;

public class Rounding {

    public static double roundTo(double value, int digitsAfterDecimalPoint) {
        double n = Math.pow(10, digitsAfterDecimalPoint);
        return Math.round(value * n) / n;
    }

}
