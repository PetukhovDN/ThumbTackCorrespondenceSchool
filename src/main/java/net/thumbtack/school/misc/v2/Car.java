package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import net.thumbtack.school.iface.v2.Driveable;
import net.thumbtack.school.iface.v2.Movable;

import java.util.Objects;

public class Car implements Movable, Driveable {
    private int positionX;
    private int positionY;
    private double speed;
    private int weight;
    private Human driver = new Human("AutoPilot", 10, 10, 300);

    public Car(int positionX, int positionY, double speed, int weight) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.weight = weight;
    }

    public Car(Point position, double speed, int weight) {
        this(position.getX(), position.getY(), speed, weight);
    }

    public Car(double speed, int weight) {
        this(0, 0, speed, weight);
    }

    public Car(double speed) {
        this(0, 0, speed, 1000);
    }

    public Car(int weight) {
        this(0, 0, 100, weight);
    }

    public Car() {
        this(0, 0, 100, 1000);
    }

    public Point getPosition() {
        return new Point(positionX, positionY);
    }

    public void setPosition(Point startPosition) {
        this.positionX = startPosition.getX();
        this.positionY = startPosition.getY();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public Human getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Human driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        setDriver(new Human());
    }

    @Override
    public void moveTo(int x, int y) {
        positionX = (int) (x + weight / speed);
        positionY = (int) (y + weight / speed);
    }

    @Override
    public void moveRel(int dx, int dy) {
        positionX = (int) (positionX + dx + weight / speed);
        positionY = (int) (positionY + dy + weight / speed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return positionX == car.positionX &&
                positionY == car.positionY &&
                Double.compare(car.speed, speed) == 0 &&
                weight == car.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY, speed, weight);
    }
}
