package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class ColoredIsoscelesTriangle extends IsoscelesTriangle implements Colored {
    private int color;

    public ColoredIsoscelesTriangle(int x1, int y1, int x2, int y3, int color) {
        super(x1, y1, x2, y3);
        this.color = color;
    }

    public ColoredIsoscelesTriangle(Point xy1, Point xy2, Point xy3, int color) {
        this(xy1.getX(), xy1.getY(), xy2.getX(), xy3.getY(), color);
    }

    public ColoredIsoscelesTriangle(int cathetusX, int cathetusY, int color) {
        this(0, 0, cathetusX, cathetusY, color);
    }

    public ColoredIsoscelesTriangle(int color) {
        this(0, 0, 1, 1, color);
    }

    public ColoredIsoscelesTriangle() {
        this(0, 0, 1, 1, 1);

    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ColoredIsoscelesTriangle)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ColoredIsoscelesTriangle that = (ColoredIsoscelesTriangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
