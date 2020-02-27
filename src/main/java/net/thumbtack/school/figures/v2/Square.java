package net.thumbtack.school.figures.v2;

import java.util.Objects;

public class Square extends Figure {
    private int xLeft;
    private int yTop;
    private int size;


    public Square(int xLeft, int yTop, int size) {
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.size = size;
    }

    public Square(Point leftTop, int size) {
        this(leftTop.getX(), leftTop.getY(), size);
    }

    public Square(int size) {
        this(0, -size, size);
    }

    public Square() {
        this(0, -1, 1);
    }

    public Point getTopLeft() {
        return new Point(xLeft, yTop);
    }

    public void setTopLeft(Point topLeft) {
        xLeft = topLeft.getX();
        yTop = topLeft.getY();
    }

    public Point getBottomRight() {
        return new Point(xLeft + size, yTop + size);
    }

    public int getLength() {
        return size;
    }

    public void moveTo(int x, int y) {
        xLeft = x;
        yTop = y;
    }

    public void moveRel(int dx, int dy) {
        xLeft += dx;
        yTop += dy;
    }

    @Override
    public void resize(double ratio) {
        size *= ratio;
    }

    @Override
    public double getArea() {
        return Math.pow(size, 2);
    }

    @Override
    public double getPerimeter() {
        return size * 4;
    }

    @Override
    public boolean isInside(int x, int y) {
        return x <= xLeft + size && x >= xLeft && y <= yTop + size && y >= yTop;
    }

    public boolean isIntersects(Square square) {

        return isInside(square.xLeft, square.yTop)
                || isInside(square.xLeft + size, square.yTop + size)
                || isInside(square.xLeft + size, square.yTop)
                || isInside(square.xLeft, square.yTop + size)
                || square.isInside(this);
    }

    public boolean isInside(Square square) {
        return isInside(square.getTopLeft())
                && isInside(square.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return xLeft == square.xLeft &&
                yTop == square.yTop &&
                size == square.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLeft, yTop, size);
    }
}
