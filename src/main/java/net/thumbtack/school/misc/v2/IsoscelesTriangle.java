package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Figure;
import net.thumbtack.school.figures.v2.Point;

import java.util.Objects;

/**
 * Равнобедренный треугольник, xy1 - первая точка, xy2 - вторая точка (по оси X), xy3 - третья точка (по оси Y).
 * Одна сторона параллельна оси X, другая сторона параллельна оси Y.
 */

public class IsoscelesTriangle extends Figure {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;

    public IsoscelesTriangle(int x1, int y1, int x2, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y1;
        this.x3 = x1;
        this.y3 = y3;
    }

    public IsoscelesTriangle(Point xy1, Point xy2, Point xy3) {
        this(xy1.getX(), xy1.getY(), xy2.getX(), xy3.getY());
    }

    /**
     * Треугольник с первой точкой в начале координат.
     *
     * @param cathetusX длина по оси X
     * @param cathetusY длина по оси Y
     */

    public IsoscelesTriangle(int cathetusX, int cathetusY) {
        this(0, 0, cathetusX, cathetusY);
    }

    /**
     * Треугольник с первой точкой в начале координат, длина по оси X равна 1, длина по оси Y равна 1.
     */

    public IsoscelesTriangle() {
        this(0, 0, 1, 1);
    }

    public int getCathetusX() {
        return x2 - x1;
    }

    public int getCathetusY() {
        return y3 - y1;
    }

    public Point getXY1() {
        return new Point(x1, y1);
    }

    public void setXY1(Point xy1) {
        this.x1 = xy1.getX();
        this.y1 = xy1.getY();
    }

    public Point getXY2() {
        return new Point(x2, y2);
    }

    public void setXY2(Point xy2) {
        this.x2 = xy2.getX();
        this.y2 = xy2.getY();
    }

    public Point getXY3() {
        return new Point(x3, y3);
    }

    public void setXY3(Point xy3) {
        this.x3 = xy3.getX();
        this.y3 = xy3.getY();
    }

    @Override
    public void moveTo(int x, int y) {
        x2 = getCathetusX() + x;
        y3 = getCathetusY() + y;
        x1 = x;
        y1 = y;
        y2 = y;
        x3 = x;
    }

    @Override
    public void moveRel(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        x3 += dx;
        y3 += dy;
    }

    /**
     * Уведичение катетов в ratio раз без изменения первой точки
     */
    @Override
    public void resize(double ratio) {
        x2 = x1 + (int) (x2 * ratio);
        y3 = y1 + (int) (y3 * ratio);
    }

    @Override
    public double getArea() {
        return getCathetusX() * getCathetusY() * 0.5;
    }

    @Override
    public double getPerimeter() {
        double gipotenuza = Math.sqrt(getCathetusX() * getCathetusX() + getCathetusY() * getCathetusY());
        return gipotenuza + getCathetusX() + getCathetusY();
    }

    @Override
    public boolean isInside(int x, int y) {
        int a = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
        int b = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
        int c = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);
        return a >= 0 && b >= 0 && c >= 0;
    }

    public boolean isIntersects(IsoscelesTriangle triangle) {
        return isInside(triangle.getXY1())
                || isInside(triangle.getXY2())
                || isInside(triangle.getXY3())
                || triangle.isInside(this);
    }

    public boolean isInside(IsoscelesTriangle triangle) {
        return isInside(triangle.getXY1())
                && isInside(triangle.getXY2())
                && isInside(triangle.getXY3());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IsoscelesTriangle)) {
            return false;
        }
        IsoscelesTriangle that = (IsoscelesTriangle) o;
        return x1 == that.x1 &&
                y1 == that.y1 &&
                x2 == that.x2 &&
                y2 == that.y2 &&
                x3 == that.x3 &&
                y3 == that.y3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
