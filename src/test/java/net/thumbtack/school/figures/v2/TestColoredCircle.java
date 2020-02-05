package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredCircle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredCircle1() {
        Point center = new Point(10, 20);
        ColoredCircle coloredCircle = new ColoredCircle(center, 10, 1);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle2() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle3() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 1);
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle4() {
        ColoredCircle coloredCircle = new ColoredCircle(1);
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
    }

    @Test
    public void testColoredCircle5() {
        ColoredCircle coloredCircle = new ColoredCircle();
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
    }

    @Test
    public void testSetCenterAndRadius() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        coloredCircle.setCenter(new Point(100, 50));
        coloredCircle.setRadius(200);
        assertAll(
                () -> assertEquals(100, coloredCircle.getCenter().getX()),
                () -> assertEquals(50, coloredCircle.getCenter().getY()),
                () -> assertEquals(200, coloredCircle.getRadius())
        );
    }

    @Test
    public void testSetColor() {
        ColoredCircle coloredCircle = new ColoredCircle();
        assertAll(
                () -> assertEquals(0, coloredCircle.getCenter().getX()),
                () -> assertEquals(0, coloredCircle.getCenter().getY()),
                () -> assertEquals(1, coloredCircle.getRadius()),
                () -> assertEquals(1, coloredCircle.getColor())
        );
        coloredCircle.setColor(2);
        assertEquals(2, coloredCircle.getColor());
    }

    @Test
    public void testMoveColoredCircle() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        coloredCircle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, coloredCircle.getCenter().getX()),
                () -> assertEquals(70, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
        coloredCircle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, coloredCircle.getCenter().getX()),
                () -> assertEquals(200, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
        coloredCircle.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, coloredCircle.getCenter().getX()),
                () -> assertEquals(2000, coloredCircle.getCenter().getY()),
                () -> assertEquals(10, coloredCircle.getRadius())
        );
    }

    @Test
    public void testResizeColoredCircle1() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        coloredCircle.resize(5);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(50, coloredCircle.getRadius())
        );
    }

    @Test
    public void testResizeColoredCircle2() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        coloredCircle.resize(0.3);
        assertAll(
                () -> assertEquals(10, coloredCircle.getCenter().getX()),
                () -> assertEquals(20, coloredCircle.getCenter().getY()),
                () -> assertEquals(3, coloredCircle.getRadius())
        );
    }

    @Test
    public void testAreaColoredCircle() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        assertEquals(Math.PI * 100, coloredCircle.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterColoredCircle() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 20, 10, 1);
        assertEquals(2 * Math.PI * 10, coloredCircle.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideColoredCircle1() {
        ColoredCircle coloredCircle = new ColoredCircle(10, 10, 10, 1);
        assertAll(
                () -> assertTrue(coloredCircle.isInside(10, 10)),
                () -> assertTrue(coloredCircle.isInside(20, 10)),
                () -> assertTrue(coloredCircle.isInside(10, 20)),
                () -> assertTrue(coloredCircle.isInside(15, 15)),
                () -> assertFalse(coloredCircle.isInside(18, 18))
        );
    }

    @Test
    public void testIsPointInsideColoredCircle2() {
        ColoredCircle coloredCircle = new ColoredCircle(new Point(10, 10), 10, 1);
        assertAll(
                () -> assertTrue(coloredCircle.isInside(new Point(10, 10))),
                () -> assertTrue(coloredCircle.isInside(new Point(20, 10))),
                () -> assertTrue(coloredCircle.isInside(new Point(10, 20))),
                () -> assertTrue(coloredCircle.isInside(new Point(15, 15))),
                () -> assertFalse(coloredCircle.isInside(new Point(18, 18)))
        );
    }

    @Test
    public void testEqualsColoredCircle() {
        ColoredCircle coloredCircle1 = new ColoredCircle(new Point(10, 10), 10, 1);
        ColoredCircle coloredCircle2 = new ColoredCircle(new Point(10, 10), 10, 1);
        ColoredCircle coloredCircle3 = new ColoredCircle(new Point(10, 10), 20, 1);
        ColoredCircle coloredCircle4 = new ColoredCircle(new Point(0, 0), 10, 1);
        ColoredCircle coloredCircle5 = new ColoredCircle(new Point(10, 10), 10, 2);
        assertEquals(coloredCircle1, coloredCircle2);
        assertNotEquals(coloredCircle1, coloredCircle3);
        assertNotEquals(coloredCircle1, coloredCircle4);
        assertNotEquals(coloredCircle1, coloredCircle5);
    }

}
