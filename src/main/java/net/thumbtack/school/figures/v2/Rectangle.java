package net.thumbtack.school.figures.v2;

import net.thumbtack.school.iface.v2.Stretchable;

import java.util.Objects;

public class Rectangle extends Figure implements Stretchable {
    private int xLeft;
    private int yTop;
    private int xRight;
    private int yBottom;

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xRight;
        this.yBottom = yBottom;
    }

    public Rectangle(Point topLeft, Point bottomRight) {
        this(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }

    public Rectangle(int length, int width) {
        this(0, -width, length, 0);
    }

    public Rectangle() {
        this(0, -1, 1, 0);
    }

    public int getLength() {
        return xRight - xLeft;
    }

    public int getWidth() {
        return yBottom - yTop;
    }

    public Point getTopLeft() {
        return new Point(xLeft, yTop);
    }

    public void setTopLeft(Point topLeft) {
        this.xLeft = topLeft.getX();
        this.yTop = topLeft.getY();
    }

    public Point getBottomRight() {
        return new Point(xRight, yBottom);
    }

    public void setBottomRight(Point bottomRight) {
        this.xRight = bottomRight.getX();
        this.yBottom = bottomRight.getY();
    }

    @Override
    public void moveTo(int x, int y) {
        xRight = getLength() + x;
        yBottom = getWidth() + y;
        xLeft = x;
        yTop = y;
    }

    @Override
    public void moveRel(int dx, int dy) {
        xLeft += dx;
        xRight += dx;
        yTop += dy;
        yBottom += dy;
    }

    @Override
    public void resize(double ratio) {
        xRight = xLeft + (int) (getLength() * ratio);
        yBottom = yTop + (int) (getWidth() * ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        xRight = xLeft + (int) (getLength() * xRatio);
        yBottom = yTop + (int) (getWidth() * yRatio);
    }

    @Override
    public double getArea() {
        return getLength() * getWidth();
    }

    @Override
    public double getPerimeter() {
        return (getLength() + getWidth()) * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        return x <= xRight && x >= xLeft && y <= yBottom && y >= yTop;
    }

    public boolean isIntersects(Rectangle rectangle) {
        return isInside(rectangle.xRight, rectangle.yBottom)
                || isInside(rectangle.xRight, rectangle.yTop)
                || isInside(rectangle.xLeft, rectangle.yTop)
                || isInside(rectangle.xLeft, rectangle.yBottom)
                || rectangle.isInside(this);
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft())
                && isInside(rectangle.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return xLeft == rectangle.xLeft &&
                yTop == rectangle.yTop &&
                xRight == rectangle.xRight &&
                yBottom == rectangle.yBottom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLeft, yTop, xRight, yBottom);
    }
}
