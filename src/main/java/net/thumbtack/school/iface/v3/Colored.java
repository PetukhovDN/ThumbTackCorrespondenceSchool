package net.thumbtack.school.iface.v3;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;

public interface Colored {
    Color getColor();

    void setColor(Color color) throws ColorException;

    // REVU Реализации этого метода во всех классах одинаковые. Подумайте о том, чтобы сделать этот метод default.
    void setColor(String colorString) throws ColorException;
}
