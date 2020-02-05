package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Square {
    private int xLeft;
    private int yTop;
    private int size;

    /*
     REVU Используйте только один конструктор, который присваивает значения полям.
     Другие конструкторы должны вызывать его с помощью this(параметры);
    */

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

    public Point getBottomRight() {
        return new Point(xLeft + size, yTop + size);
    }

    public void setTopLeft(Point topLeft) {
        xLeft = topLeft.getX();
        yTop = topLeft.getY();
    }

    public int getLength() {
        return size;
    }

    public void moveTo(int x, int y) {
        xLeft = x;
        yTop = y;
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        xLeft += dx;
        yTop += dy;
    }

    public void resize(double ratio) {
        size *= ratio;
    }

    public double getArea() {
        return Math.pow(size, 2);
    }

    public double getPerimeter() {
        return size * 4;
    }

    public boolean isInside(int x, int y) {
        return x <= xLeft + size && x >= xLeft && y <= yTop + size && y >= yTop;
    }

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Square square) {
        /*
         REVU Подумайте, как можно реализовать проверку без циклов, используя только
         значения координат.
        */

        return isInside(square.xLeft, square.yTop) ||
                isInside(square.xLeft + size, square.yTop + size) ||
                isInside(square.xLeft + size, square.yTop) ||
                isInside(square.xLeft, square.yTop + size) ||
                square.isInside(this);
    }
//        for (int i = square.xLeft; i <= square.xLeft + size; i++) {
//            for (int j = square.yTop; j <= square.yTop + size; j++) {
//                if (isInside(i, j)) return true; // REVU Всегда используйте скобки {} в оформлении условий
//            }
//        }
//        return false;

    public boolean isInside(Square square) {
        return isInside(square.getTopLeft())
                && isInside(square.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
