package net.thumbtack.school.colors;

import net.thumbtack.school.colors.v3.ColorErrorCode;

public class ColorException extends Exception {
    ColorErrorCode colorErrorCode;
    public ColorException(ColorErrorCode colorErrorCode){
        this.colorErrorCode = colorErrorCode;
    }

    public ColorErrorCode getColorErrorCode() {
        return colorErrorCode;
    }
}
