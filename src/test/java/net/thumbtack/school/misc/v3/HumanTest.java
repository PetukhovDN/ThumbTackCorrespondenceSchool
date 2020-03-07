package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    public void testHuman1() {
        Human human = new Human("Bob", 6, 7, 120);
        assertAll(
                () -> assertEquals("Bob", human.getName()),
                () -> assertEquals(6, human.getPosition().getX()),
                () -> assertEquals(7, human.getPosition().getY()),
                () -> assertEquals(120, human.getWeight()),
                () -> assertNotEquals(0, human.getWeight())
        );
    }

    @Test
    public void testHuman2() {
        Point point = new Point(3, 3);
        Human human = new Human("Tom", point, 150);
        assertAll(
                () -> assertEquals("Tom", human.getName()),
                () -> assertEquals(3, human.getPosition().getX()),
                () -> assertEquals(3, human.getPosition().getY()),
                () -> assertEquals(150, human.getWeight()),
                () -> assertNotEquals(0, human.getWeight())
        );
    }

    @Test
    public void testHuman3() {
        Human human = new Human("Pol", 55, 66, 90);
        assertAll(
                () -> assertEquals("Pol", human.getName()),
                () -> assertEquals(55, human.getPosition().getX()),
                () -> assertEquals(66, human.getPosition().getY()),
                () -> assertEquals(90, human.getWeight()),
                () -> assertNotEquals(1300, human.getWeight())
        );
    }

    @Test
    public void testHuman4() {
        Human human = new Human("Rob", 79);
        assertAll(
                () -> assertEquals("Rob", human.getName()),
                () -> assertEquals(0, human.getPosition().getX()),
                () -> assertEquals(0, human.getPosition().getY()),
                () -> assertEquals(79, human.getWeight()),
                () -> assertNotEquals(0, human.getWeight())
        );
    }

    @Test
    public void testHuman5() {
        Human human = new Human("Sam", 95);
        assertAll(
                () -> assertEquals("Sam", human.getName()),
                () -> assertEquals(0, human.getPosition().getX()),
                () -> assertEquals(0, human.getPosition().getY()),
                () -> assertEquals(95, human.getWeight()),
                () -> assertNotEquals(0, human.getWeight())
        );
    }

    @Test
    public void testHuman6() {
        Human human = new Human();
        assertAll(
                () -> assertEquals("StandardName", human.getName()),
                () -> assertEquals(0, human.getPosition().getX()),
                () -> assertEquals(0, human.getPosition().getY()),
                () -> assertEquals(60, human.getWeight()),
                () -> assertNotEquals(0, human.getWeight())
        );
    }

    @Test
    void setPositionHuman1() {
        Human human = new Human();
        human.setPositionX(20);
        human.setPositionY(20);
        assertAll(
                () -> assertEquals(20, human.getPositionX()),
                () -> assertEquals(20, human.getPositionY())
        );
    }

    @Test
    void setPositionHuman2() {
        Human human = new Human();
        human.setPosition(new Point(20, 20));
        assertAll(
                () -> assertEquals(20, human.getPosition().getX()),
                () -> assertEquals(20, human.getPosition().getY())
        );
    }

    @Test
    void setWeightHuman() {
        Human human = new Human();
        human.setWeight(177);
        assertEquals(177, human.getWeight());
    }

    @Test
    public void testMoveHuman() {
        Human human = new Human("Jhon", 20, 30, 40);
        human.moveRel(100, 50);
        assertAll(
                () -> assertEquals(120, human.getPosition().getX()),
                () -> assertEquals(80, human.getPosition().getY())
        );
        human.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, human.getPosition().getX()),
                () -> assertEquals(200, human.getPosition().getY())
        );
        human.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, human.getPosition().getX()),
                () -> assertEquals(2000, human.getPosition().getY())
        );
    }

    @Test
    public void testEqualsHuman() {
        Human human1 = new Human();
        Human human2 = new Human("Bob", 12, 45, 150);
        Human human3 = new Human("Tom", 12, 45, 160);
        Human human4 = new Human("Tom", 12, 45, 160);
        Human human5 = new Human("StandardName", 0, 0, 60);
        Human human6 = new Human("Tim", 0, 100, 100);
        Human human7 = new Human("Sam", 100);
        assertEquals(human1, human5);
        assertEquals(human3, human4);
        assertNotEquals(human1, human6);
        assertNotEquals(human5, human7);
        assertNotEquals(human2, human3);
    }

}