package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.iface.v3.Colored;

import java.util.Objects;

import static net.thumbtack.school.colors.v3.Color.colorFromString;

public class ColoredRectangle extends Rectangle implements Colored {

    private Color color;

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom);
        this.color = Color.verifyColor(color);
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, String color) throws ColorException {
        this(xLeft, yTop, xRight, yBottom, colorFromString(color));
    }

    public ColoredRectangle(Point topLeft, Point bottomRight, Color color) throws ColorException {
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), color);
    }

    public ColoredRectangle(Point topLeft, Point bottomRight, String color) throws ColorException {
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY(), colorFromString(color));
    }

    public ColoredRectangle(int length, int width, Color color) throws ColorException {
        this(0, -width, length, 0, color);
    }

    public ColoredRectangle(int length, int width, String color) throws ColorException {
        this(0, -width, length, 0, colorFromString(color));
    }

    public ColoredRectangle(Color color) throws ColorException {
        this(0, -1, 1, 0, color);
    }

    public ColoredRectangle(String color) throws ColorException {
        super();
        this.color = colorFromString(color);
    }

    public ColoredRectangle() throws ColorException {
        this(0, -1, 1, 0, Color.RED);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) throws ColorException {
        this.color = Color.verifyColor(color);
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        setColor(colorFromString(colorString));
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
