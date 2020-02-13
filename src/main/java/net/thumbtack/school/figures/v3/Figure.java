package net.thumbtack.school.figures.v3;

import net.thumbtack.school.iface.v3.HasArea;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;

public abstract class Figure implements HasArea, Movable, Resizable {
    public abstract void moveTo(int x, int y);

    public abstract void moveRel(int dx, int dy);

    public abstract void resize(double ratio);

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract boolean isInside(int x, int y);

    public boolean isInside(Point point) {
        return isInside(point.getX(), point.getY());
    }

}
