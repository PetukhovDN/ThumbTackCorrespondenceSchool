package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredCircle extends Circle {

    private int color;

    public ColoredCircle(int xCenter, int yCenter, int radius, int color) {
        super(xCenter, yCenter, radius);
        this.color = color;
    }

    public ColoredCircle(Point center, int radius, int color) {
        this(center.getX(), center.getY(), radius, color);
    }


    public ColoredCircle(int radius, int color) {
        this(0, 0, radius, color);
    }

    public ColoredCircle(int color) {
        this(0, 0, 1, color);
    }

    public ColoredCircle() {
        this(0, 0, 1, 1);
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
        ColoredCircle that = (ColoredCircle) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}
