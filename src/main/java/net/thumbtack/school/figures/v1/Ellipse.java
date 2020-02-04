package net.thumbtack.school.figures.v1;

import java.util.Objects;

public class Ellipse {
    private int xCenter;
    private int yCenter;
    private int xAxis;
    private int yAxis;

    public Ellipse(int xCenter, int yCenter, int xAxis, int yAxis) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Ellipse(Point center, int xAxis, int yAxis) {
        this(center.getX(), center.getY(), xAxis, yAxis);
    }

    public Ellipse(int xAxis, int yAxis) {
        this(0, 0, xAxis, yAxis);
    }

    public Ellipse() {
        this(0, 0, 1, 1);
    }

    public Point getCenter() {
        return new Point(xCenter, yCenter);
    }

    public int getXAxis() {
        return xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public void setCenter(Point center) {
        this.xCenter = center.getX();
        this.yCenter = center.getY();
    }

    public void moveTo(int x, int y) {
        this.xCenter = x;
        this.yCenter = y;
    }

    public void moveTo(Point point) {
        this.xCenter = point.getX();
        this.yCenter = point.getY();
    }

    public void moveRel(int dx, int dy) {
        this.xCenter += dx;
        this.yCenter += dy;

    }

    public void resize(double ratio) {
        xAxis *= ratio;
        yAxis *= ratio;
    }

    public void stretch(double xRatio, double yRatio) {
        xAxis *= xRatio;
        yAxis *= yRatio;
    }

    public double getArea() {
        return (double) (xAxis * yAxis) / 4 * Math.PI;
    }

    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((Math.pow(xAxis, 2) + Math.pow(yAxis, 2)) / 8);
    }

    public boolean isInside(int x, int y) {
        // REVU Здесь вы получаете предупреждение о целочисленном делении в контексте операций с плавающей запятой.
        // Будет ли метод работать корректно для нечётных значений xAxis и yAxis?
        return Math.pow(x - xCenter, 2) / Math.pow(xAxis / 2.0, 2)
                + Math.pow(y - yCenter, 2) / Math.pow(yAxis / 2.0, 2) <= 1;
    }

    public boolean isInside(Point point) { // REVU Переиспользуйте уже реализованные методы
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return xCenter == ellipse.xCenter &&
                yCenter == ellipse.yCenter &&
                xAxis == ellipse.xAxis &&
                yAxis == ellipse.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCenter, yCenter, xAxis, yAxis);
    }
}
