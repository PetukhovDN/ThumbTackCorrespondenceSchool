package net.thumbtack.school.figures.v1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCircle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testCircle1() {
        Point center = new Point(10, 20);
        Circle circle = new Circle(center, 10);
        assertAll(
                () -> assertEquals(10, circle.getCenter().getX()),
                () -> assertEquals(20, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
    }

    @Test
    public void testCircle2() {
        Circle circle = new Circle(10, 20, 10);
        assertAll(
                () -> assertEquals(10, circle.getCenter().getX()),
                () -> assertEquals(20, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
    }

    @Test
    public void testCircle3() {
        Circle circle = new Circle(10);
        assertAll(
                () -> assertEquals(0, circle.getCenter().getX()),
                () -> assertEquals(0, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
    }

    @Test
    public void testCircle4() {
        Circle circle = new Circle();
        assertAll(
                () -> assertEquals(0, circle.getCenter().getX()),
                () -> assertEquals(0, circle.getCenter().getY()),
                () -> assertEquals(1, circle.getRadius())
        );
    }

    @Test
    public void testSetCenterAndRadius() {
        Circle circle = new Circle(10, 20, 10);
        circle.setCenter(new Point(100, 50));
        circle.setRadius(200);
        assertAll(
                () -> assertEquals(100, circle.getCenter().getX()),
                () -> assertEquals(50, circle.getCenter().getY()),
                () -> assertEquals(200, circle.getRadius())
        );
    }

    @Test
    public void testMoveCircle() {
        Circle circle = new Circle(10, 20, 10);
        circle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, circle.getCenter().getX()),
                () -> assertEquals(70, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
        circle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, circle.getCenter().getX()),
                () -> assertEquals(200, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
        circle.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1100, circle.getCenter().getX()),
                () -> assertEquals(1200, circle.getCenter().getY()),
                () -> assertEquals(10, circle.getRadius())
        );
    }

    @Test
    public void testResizeCircle1() {
        Circle circle = new Circle(10, 20, 10);
        circle.resize(5);
        assertAll(
                () -> assertEquals(10, circle.getCenter().getX()),
                () -> assertEquals(20, circle.getCenter().getY()),
                () -> assertEquals(50, circle.getRadius())
        );
    }

    @Test
    public void testResizeCircle2() {
        Circle circle = new Circle(10, 20, 10);
        circle.resize(0.3);
        assertAll(
                () -> assertEquals(10, circle.getCenter().getX()),
                () -> assertEquals(20, circle.getCenter().getY()),
                () -> assertEquals(3, circle.getRadius())
        );
    }

    @Test
    public void testAreaCircle() {
        Circle circle = new Circle(10, 20, 10);
        assertEquals(Math.PI * 100, circle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterCircle() {
        Circle circle = new Circle(10, 20, 10);
        assertEquals(2 * Math.PI * 10, circle.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideCircle1() {
        Circle circle = new Circle(10, 10, 10);
        assertAll(
                () -> assertTrue(circle.isInside(10, 10)),
                () -> assertTrue(circle.isInside(20, 10)),
                () -> assertTrue(circle.isInside(10, 20)),
                () -> assertTrue(circle.isInside(15, 15)),
                () -> assertFalse(circle.isInside(18, 18))
        );
    }

    @Test
    public void testIsPointInsideCircle2() {
        Circle circle = new Circle(new Point(10, 10), 10);
        assertAll(
                () -> assertTrue(circle.isInside(new Point(10, 10))),
                () -> assertTrue(circle.isInside(new Point(20, 10))),
                () -> assertTrue(circle.isInside(new Point(10, 20))),
                () -> assertTrue(circle.isInside(new Point(15, 15))),
                () -> assertFalse(circle.isInside(new Point(18, 18)))
        );
    }

    @Test
    public void testEqualsCircle() {
        Circle circle1 = new Circle(new Point(10, 10), 10);
        Circle circle2 = new Circle(new Point(10, 10), 10);
        Circle circle3 = new Circle(new Point(10, 10), 20);
        Circle circle4 = new Circle(new Point(0, 0), 10);
        assertEquals(circle1, circle2);
        assertNotEquals(circle1, circle3);
        assertNotEquals(circle1, circle4);
    }

    @Test
    @SuppressWarnings("unused")
    public void testCircleFactory() {
        Circle circle1 = CircleFactory.createCircle(new Point(0, 0), 1);
        Circle circle2 = CircleFactory.createCircle(new Point(3, 4), 1);
        Circle circle3 = CircleFactory.createCircle(new Point(10, 20), 1);
        assertEquals(3, CircleFactory.getCircleCount());
    }

}
