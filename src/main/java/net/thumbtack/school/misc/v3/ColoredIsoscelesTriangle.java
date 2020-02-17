package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.figures.v3.Point;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

public class ColoredIsoscelesTriangle extends IsoscelesTriangle implements Colored {
    private Color color;

    public ColoredIsoscelesTriangle(int x1, int y1, int x2, int y3, Color color) throws ColorException {
        super(x1, y1, x2, y3);
        this.color = Color.color(color);
    }

    public ColoredIsoscelesTriangle(int x1, int y1, int x2, int y3, String color) throws ColorException {
        super(x1, y1, x2, y3);
        this.color = Color.colorFromString(color);
    }

    public ColoredIsoscelesTriangle(Point xy1, Point xy2, Point xy3, Color color) throws ColorException {
        this(xy1.getX(), xy1.getY(), xy2.getX(), xy3.getY(), Color.color(color));
    }

    public ColoredIsoscelesTriangle(Point xy1, Point xy2, Point xy3, String color) throws ColorException {
        this(xy1.getX(), xy1.getY(), xy2.getX(), xy3.getY(), Color.colorFromString(color));
    }

    public ColoredIsoscelesTriangle(int katetX, int katetY, Color color) throws ColorException {
        this(0, 0, katetX, katetY, Color.color(color));
    }

    public ColoredIsoscelesTriangle(int katetX, int katetY, String color) throws ColorException {
        this(0, 0, katetX, katetY, Color.colorFromString(color));
    }

    public ColoredIsoscelesTriangle(Color color) throws ColorException {
        this(0, 0, 1, 1, Color.color(color));
    }

    public ColoredIsoscelesTriangle(String color) throws ColorException {
        this(0, 0, 1, 1, Color.colorFromString(color));
    }

    public ColoredIsoscelesTriangle() throws ColorException {
        this(0, 0, 1, 1, Color.RED);

    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        this.color = Color.color(color);
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColoredIsoscelesTriangle)) return false;
        if (!super.equals(o)) return false;
        ColoredIsoscelesTriangle that = (ColoredIsoscelesTriangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
