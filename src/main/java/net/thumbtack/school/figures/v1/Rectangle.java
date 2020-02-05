package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Rectangle {
    private int xLeft;
    private int yTop;
    private int xRight;
    private int yBottom;
    //private int length; // REVU Это значение можно вычислить, когда оно необходимо. Не нужно хранить его в поле класса.
    //private int width; // REVU Это значение можно вычислить, когда оно необходимо. Не нужно хранить его в поле класса.

    //REVU На мой взгляд, класс будет выглядеть аккуратнее,
    //если вы поместите конструкторы перед всеми остальными методами

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xRight;
        this.yBottom = yBottom;
        //this.length = xRight - xLeft;
        //this.width = yBottom - yTop;
    }

    //REVU Используйте только один конструктор, который присваивает значения полям.
    //Другие конструкторы должны вызывать его с помощью this(параметры);

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

    public void setTopLeft(Point topLeft) {
        this.xLeft = topLeft.getX();
        this.yTop = topLeft.getY();
    }

    public void setBottomRight(Point bottomRight) {
        this.xRight = bottomRight.getX();
        this.yBottom = bottomRight.getY();
    }

    public Point getTopLeft() {
        return new Point(xLeft, yTop);
    }

    public Point getBottomRight() {
        return new Point(xRight, yBottom);
    }

    public void moveTo(int x, int y) {
        xRight = getLength() + x;
        yBottom = getWidth() + y;
        xLeft = x;
        yTop = y;
    }

    public void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }

    public void moveRel(int dx, int dy) {
        xLeft += dx;
        xRight += dx;
        yTop += dy;
        yBottom += dy;
    }

    public void resize(double ratio) {
        xRight = xLeft + (int) (getLength() * ratio);
        yBottom = yTop + (int) (getWidth() * ratio);
    }

    public void stretch(double xRatio, double yRatio) {
        xRight = xLeft + (int) (getLength() * xRatio);
        yBottom = yTop + (int) (getWidth() * yRatio);
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return (getLength() + getWidth()) * 2;
    }

    public boolean isInside(int x, int y) {
        return x <= xRight && x >= xLeft && y <= yBottom && y >= yTop;
    }

    public boolean isInside(Point point) { // REVU Переиспользуйте уже реализованные методы
        return isInside(point.getX(), point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {
        //REVU Подумайте, как можно реализовать проверку без циклов, используя только
        //значения координат.

        //Выглядит не очень... но работает.
        return isInside(rectangle.xRight, rectangle.yBottom) ||
                isInside(rectangle.xRight, rectangle.yTop) ||
                isInside(rectangle.xLeft, rectangle.yTop) ||
                isInside(rectangle.xLeft, rectangle.yBottom) ||
                rectangle.isInside(this);
    }

//        for (int i = rectangle.xLeft; i < rectangle.xRight; i++) {
//            for (int j = rectangle.yTop; j < rectangle.yBottom; j++) {
//                if (isInside(i, j)) {
//                    return true;
//                } // REVU Всегда используйте скобки {} в оформлении условий
//            }
//        }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.getTopLeft())
                && isInside(rectangle.getBottomRight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
