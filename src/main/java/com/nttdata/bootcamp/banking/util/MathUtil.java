package com.nttdata.bootcamp.banking.util;

import java.math.BigDecimal;

public class MathUtil {

    public static double sum(double a, double b) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.add(bigDecimalB).doubleValue();
    }

    public static double rest(double a, double b) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.subtract(bigDecimalB).doubleValue();
    }

}
