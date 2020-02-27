package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Colored;

import java.util.Objects;

public class ColoredRectangle extends Rectangle implements Colored {

    private int color;

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        super(xLeft, yTop, xRight, yBottom);
        this.color = color;
    }

    public ColoredRectangle(Point topLeft, Point bottomRight, int color) {
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), color);
    }

    public ColoredRectangle(int length, int width, int color) {
        this(0, -width, length, 0, color);
    }

    public ColoredRectangle(int color) {
        this(0, -1, 1, 0, color);
    }

    public ColoredRectangle() {
        this(0, -1, 1, 0, 1);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ColoredRectangle that = (ColoredRectangle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }


}
