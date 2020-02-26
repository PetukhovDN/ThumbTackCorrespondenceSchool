package net.thumbtack.school.colors.v3;

import net.thumbtack.school.colors.ColorException;

public enum Color {
    RED, GREEN, BLUE;

    public static Color colorFromString(String colorString) throws ColorException {
        // REVU Enum (родитель всех enum в Java) содержит некоторые полезные методы. Попробуйте использовать их здесь.
        if (colorString == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } else if (colorString.equals("RED")) {
            return RED;
        } else if (colorString.equals("GREEN")) {
            return GREEN;
        } else if (colorString.equals("BLUE")) {
            return BLUE;
        } else throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
    }

    // REVU Пробел после имени метода не нужен
    // REVU Непонятно назначение метода
    public static Color color (Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } else if (color == RED) {
            return RED;
        } else if (color == GREEN) {
            return GREEN;
        } else if (color == BLUE) {
            return BLUE;
        } else throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
    }
}
