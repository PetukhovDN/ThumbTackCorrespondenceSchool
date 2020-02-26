package net.thumbtack.school.colors;

import net.thumbtack.school.colors.v3.ColorErrorCode;

public class ColorException extends Exception {
    // REVU Члены классов должны быть по возможности приватными
    ColorErrorCode colorErrorCode;
    public ColorException(ColorErrorCode colorErrorCode){
        this.colorErrorCode = colorErrorCode;
    }

    public ColorErrorCode getColorErrorCode() {
        return colorErrorCode;
    }
}
