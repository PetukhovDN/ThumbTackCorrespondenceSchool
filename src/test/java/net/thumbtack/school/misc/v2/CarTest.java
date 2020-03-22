package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void testCar1() {
        Car car = new Car(5, 6, 95.7, 1500);
        assertAll(
                () -> assertEquals(5, car.getPosition().getX()),
                () -> assertEquals(6, car.getPosition().getY()),
                () -> assertEquals(95.7, car.getSpeed()),
                () -> assertEquals(1500, car.getWeight()),
                () -> assertNotEquals(1300, car.getWeight())
        );
    }

    @Test
    public void testCar2() {
        Point point = new Point(5, 6);
        Car car = new Car(point, 95.7, 1500);
        assertAll(
                () -> assertEquals(5, car.getPosition().getX()),
                () -> assertEquals(6, car.getPosition().getY()),
                () -> assertEquals(95.7, car.getSpeed()),
                () -> assertEquals(1500, car.getWeight()),
                () -> assertNotEquals(1300, car.getWeight())
        );
    }

    @Test
    public void testCar3() {
        Car car = new Car(95.7, 1500);
        assertAll(
                () -> assertEquals(0, car.getPosition().getX()),
                () -> assertEquals(0, car.getPosition().getY()),
                () -> assertEquals(95.7, car.getSpeed()),
                () -> assertEquals(1500, car.getWeight()),
                () -> assertNotEquals(1300, car.getWeight())
        );
    }

    @Test
    public void testCar4() {
        Car car = new Car(1500);
        assertAll(
                () -> assertEquals(0, car.getPosition().getX()),
                () -> assertEquals(0, car.getPosition().getY()),
                () -> assertEquals(100, car.getSpeed()),
                () -> assertEquals(1500, car.getWeight()),
                () -> assertNotEquals(0, car.getWeight())
        );
    }

    @Test
    public void testCar5() {
        Car car = new Car(95.7);
        assertAll(
                () -> assertEquals(0, car.getPosition().getX()),
                () -> assertEquals(0, car.getPosition().getY()),
                () -> assertEquals(95.7, car.getSpeed()),
                () -> assertEquals(1000, car.getWeight()),
                () -> assertNotEquals(0, car.getWeight())
        );
    }

    @Test
    public void testCar6() {
        Car car = new Car();
        assertAll(
                () -> assertEquals(0, car.getPosition().getX()),
                () -> assertEquals(0, car.getPosition().getY()),
                () -> assertEquals(100, car.getSpeed()),
                () -> assertEquals(1000, car.getWeight()),
                () -> assertNotEquals(0, car.getWeight())
        );
    }

    @Test
    void setStartPositionCar() {
        Car car = new Car();
        car.setPosition(new Point(20, 20));
        assertAll(
                () -> assertEquals(20, car.getPosition().getX()),
                () -> assertEquals(20, car.getPosition().getY())
        );
    }

    @Test
    void setSpeedCar() {
        Car car = new Car();
        car.setSpeed(145);
        assertEquals(145, car.getSpeed());
    }

    @Test
    void setWeightCar() {
        Car car = new Car();
        car.setWeight(2333);
        assertEquals(2333, car.getWeight());
    }

    @Test
    void driveCar() {
        Car car = new Car();
        Human driver1 = new Human("Tod");
        car.setDriver(driver1);
        car.drive();
        assertEquals(driver1, car.getDriver()); // REVU Метод drive явно заменяет водителя. Почему водители должны остаться равны?
    }

    @Test
    public void testMoveCar() {
        Car car = new Car(10, 20, 30, 40);
        car.moveRel(100, 50);
        assertAll(
                () -> assertEquals(111, car.getPosition().getX()),
                () -> assertEquals(71, car.getPosition().getY())
        );
        car.moveTo(100, 200);
        assertAll(
                () -> assertEquals(101, car.getPosition().getX()),
                () -> assertEquals(201, car.getPosition().getY())
        );
        car.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1001, car.getPosition().getX()),
                () -> assertEquals(2001, car.getPosition().getY())
        );
    }

    @Test
    public void testDriver1() {
        Car car = new Car();
        Human human = new Human("Ivan");
        car.setDriver(human);
        assertAll(
                () -> assertEquals(human, car.getDriver()),
                () -> assertEquals("Ivan", car.getDriver().getName())
        );
    }

    @Test
    public void testDriver2() {
        Car car = new Car();
        Human human = new Human();
        car.setDriver(human);
        assertAll(
                () -> assertEquals(human, car.getDriver()),
                () -> assertEquals("StandardName", car.getDriver().getName())
        );
    }

    @Test
    public void testDriver3() {
        Car car = new Car();
        assertAll(
                () -> assertEquals("AutoPilot", car.getDriver().getName()),
                () -> assertEquals(300, car.getDriver().getWeight())
        );
    }

    @Test
    public void testEqualsCar() {
        Car car1 = new Car();
        Car car2 = new Car(12, 45, 160, 3000);
        Car car3 = new Car(12, 45, 160, 3000);
        Car car4 = new Car(0, 0, 100, 1000);
        Car car5 = new Car(100, 1000);
        assertEquals(car1, car4);
        assertEquals(car2, car3);
        assertEquals(car4, car5);
        assertEquals(car1, car5);
        assertNotEquals(car1, car2);
        assertNotEquals(car1, car3);
    }


}
