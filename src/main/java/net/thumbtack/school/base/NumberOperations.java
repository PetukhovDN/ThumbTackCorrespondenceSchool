package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NumberOperations {

    public static Integer find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return null;
    }

    public static Integer find(double[] array, double value, double eps) {
        for (int i = 0; i < array.length; i++) {
            if (value - Math.abs(array[i]) <= eps) {
                return i;
            }
        }
        return null;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max) {
        // REVU Выражение сложное и содержит повторяющиеся части. Сделайте рефакторинг.
        return weight / volume < max && weight / volume >= min ? weight / volume : null;
    }

    public static Integer find(BigInteger[] array, BigInteger value) {
        for (int i = 0; i < array.length; i++) {
            // REVU Вместо сравнения можно проверять на равенство
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return null;
    }

    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {
        BigDecimal res = weight.divide(volume, 1, RoundingMode.HALF_UP);
        return res.compareTo(max) < 0 && res.compareTo(min) >= 0 ? res : null;
    }
}
