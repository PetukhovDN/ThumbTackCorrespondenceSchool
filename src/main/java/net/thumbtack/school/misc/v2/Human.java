package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.iface.v2.Movable;

import java.util.Objects;

public class Human implements Movable {

    private String name;
    private int positionX;
    private int positionY;
    private int weight;

    public Human(String name, int positionX, int positionY, int weight) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.weight = weight;
    }

    public Human(String name, Point startPosition, int weight) {
        this(name, startPosition.getX(), startPosition.getY(), weight);
    }

    public Human(String name, int weight) {
        this(name, 0, 0, weight);
    }

    public Human(String name) {
        this(name, 0, 0, 60);
    }

    public Human() {
        this("StandardName", 0, 0, 60);
    }

    public Point getPosition() {
        return new Point(positionX, positionY);
    }

    public void setPosition(Point startPosition) {
        this.positionX = startPosition.getX();
        this.positionY = startPosition.getY();
    }

    public String getName() {
        return name;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void moveTo(int x, int y) {
        positionX = x;
        positionY = y;
    }

    @Override
    public void moveRel(int dx, int dy) {
        positionX += dx;
        positionY += dy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Human)) {
            return false;
        }
        Human human = (Human) o;
        return positionX == human.positionX && // REVU Отсустствует сравнение по имени. Так и задумано?
                positionY == human.positionY &&
                weight == human.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY, weight);
    }

}
