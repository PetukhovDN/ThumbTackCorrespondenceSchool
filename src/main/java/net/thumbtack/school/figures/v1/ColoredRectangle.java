package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class ColoredRectangle extends Rectangle {

    private int xLeft;
    private int yTop;
    private int xRight;
    private int yBottom;
    private int color;

    public ColoredRectangle(Point topLeft, Point bottomRight, int color) {
        super(topLeft, bottomRight);
        this.color = color;
    }

    public ColoredRectangle(int xLeft, int yTop, int xRight, int yBottom, int color) {
        super(xLeft, yTop, xRight, yBottom);
        this.color = color;
    }

    public ColoredRectangle(int length, int width, int color) {
        super(length, width);
        this.color = color;
    }

    public ColoredRectangle(int color) {
        super();
        this.color = color;
    }

    public ColoredRectangle() {
        super();
        this.color = 1;
    }

    @Override
    public Point getTopLeft() {
        return super.getTopLeft();
    }

    @Override
    public Point getBottomRight() {
        return super.getBottomRight();
    }

    public int getColor() {
        return color;
    }

    @Override
    public void setTopLeft(Point topLeft) {
        super.setTopLeft(topLeft);
    }

    @Override
    public void setBottomRight(Point bottomRight) {
        super.setBottomRight(bottomRight);
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getLength() {
        return super.getLength();
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public void moveRel(int dx, int dy) {
        super.moveRel(dx, dy);
    }

    @Override
    public void resize(double ratio) {
        super.resize(ratio);
    }

    @Override
    public void stretch(double xRatio, double yRatio) {
        super.stretch(xRatio, yRatio);
    }

    @Override
    public double getArea() {
        return super.getArea();
    }

    @Override
    public double getPerimeter() {
        return super.getPerimeter();
    }

    @Override
    public boolean isInside(int x, int y) {
        return super.isInside(x, y);
    }

    @Override
    public boolean isInside(Point point) {
        return super.isInside(point);
    }

    @Override
    public boolean isIntersects(Rectangle rectangle) {
        return super.isIntersects(rectangle);
    }

    @Override
    public boolean isInside(Rectangle rectangle) {
        return super.isInside(rectangle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColoredRectangle that = (ColoredRectangle) o;
        return xLeft == that.xLeft &&
                yTop == that.yTop &&
                xRight == that.xRight &&
                yBottom == that.yBottom &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), xLeft, yTop, xRight, yBottom, color);
    }


}
