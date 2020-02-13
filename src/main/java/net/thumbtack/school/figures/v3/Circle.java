package net.thumbtack.school.figures.v3;


import java.util.Objects;

public class Circle extends Figure {

    private int xCenter;
    private int yCenter;
    private int radius;

    public Circle(int xCenter, int yCenter, int radius) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
    }

    public Circle(Point center, int radius) {
        this(center.getX(), center.getY(), radius);
    }

    public Circle(int radius) {
        this(0, 0, radius);
    }

    public Circle() {
        this(0, 0, 1);
    }

    public Point getCenter() {
        return new Point(xCenter, yCenter);
    }

    public void setCenter(Point center) {
        this.xCenter = center.getX();
        this.yCenter = center.getY();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void moveTo(int x, int y) {
        xCenter = x;
        yCenter = y;
    }

    @Override
    public void moveRel(int dx, int dy) {
        xCenter += dx;
        yCenter += dy;
    }

    @Override
    public void resize(double ratio) {
        radius *= ratio;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        return Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)) <= radius;
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

