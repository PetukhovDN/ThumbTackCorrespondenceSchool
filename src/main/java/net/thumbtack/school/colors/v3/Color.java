package net.thumbtack.school.colors.v3;

import net.thumbtack.school.colors.ColorException;

public enum Color {
    RED,
    GREEN,
    BLUE;

    public static Color verifyColor(Color color) throws ColorException {
        if (color == null) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        return color;
    }

    public static Color colorFromString(String colorString) throws ColorException {
        try {
            return valueOf(colorString);
        } catch (IllegalArgumentException e) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        } catch (NullPointerException e) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
    }

}
