package net.thumbtack.school.iface.v2;

import net.thumbtack.school.figures.v2.Point;

public interface Movable {
    void moveTo(int x, int y);

    void moveRel(int dx, int dy);

    default void moveTo(Point point) {
        moveTo(point.getX(), point.getY());
    }
}
