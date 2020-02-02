package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Circle {

    private int xCenter;
    private int yCenter;
    private int radius;

    public Circle(Point center, int radius) {
        this.xCenter = center.getX();
        this.yCenter = center.getY();
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }

    public Circle(int radius) {
        this.xCenter = 0;
        this.yCenter = 0;
        this.radius = radius;
    }

    public Circle() {
        this.xCenter = 0;
        this.yCenter = 0;
        this.radius = 1;
    }

    public Point getCenter() {
        return new Point(xCenter, yCenter);
    }

    public int getRadius() {
        return radius;
    }

    public void setCenter(Point center) {
        this.xCenter = center.getX();
        this.yCenter = center.getY();
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void moveTo(int x, int y) {
        xCenter = x;
        yCenter = y;
    }

    public void moveTo(Point point) {
        xCenter = point.getX();
        yCenter = point.getY();
    }

    public void moveRel(int dx, int dy) {
        xCenter += dx;
        yCenter += dy;
    }

    public void resize(double ratio) {
        radius *= ratio;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    public boolean isInside(int x, int y) {
        return Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)) <= radius;
    }

    public boolean isInside(Point point) { // REVU Переиспользуйте уже реализованные методы
        return Math.sqrt(Math.pow(point.getX() - xCenter, 2) + Math.pow(point.getY() - yCenter, 2)) <= radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return xCenter == circle.xCenter &&
                yCenter == circle.yCenter &&
                radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCenter, yCenter, radius);
    }
}

