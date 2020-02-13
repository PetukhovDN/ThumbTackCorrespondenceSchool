package net.thumbtack.school.figures.v3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEllipse {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testEllipse1() {
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
    }

    @Test
    public void testEllipse2() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
    }

    @Test
    public void testEllipse3() {
        Ellipse ellipse = new Ellipse(10, 20);
        assertAll(
                () -> assertEquals(0, ellipse.getCenter().getX()),
                () -> assertEquals(0, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
    }

    @Test
    public void testEllipse4() {
        Ellipse ellipse = new Ellipse();
        assertAll(
                () -> assertEquals(0, ellipse.getCenter().getX()),
                () -> assertEquals(0, ellipse.getCenter().getY()),
                () -> assertEquals(1, ellipse.getXAxis()),
                () -> assertEquals(1, ellipse.getYAxis())
        );
    }

    @Test
    public void testMoveEllipse() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        ellipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, ellipse.getCenter().getX()),
                () -> assertEquals(70, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
        ellipse.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, ellipse.getCenter().getX()),
                () -> assertEquals(200, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
        ellipse.moveTo(1000, 2000);
        assertAll(
                () -> assertEquals(1000, ellipse.getCenter().getX()),
                () -> assertEquals(2000, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
    }

    @Test
    public void testSetCenterAndAxesEllipse() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        ellipse.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, ellipse.getCenter().getX()),
                () -> assertEquals(70, ellipse.getCenter().getY()),
                () -> assertEquals(10, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis()))
        ;
        ellipse.setCenter(new Point(300, 400));
        ellipse.setXAxis(500);
        ellipse.setYAxis(600);
        assertAll(
                () -> assertEquals(300, ellipse.getCenter().getX()),
                () -> assertEquals(400, ellipse.getCenter().getY()),
                () -> assertEquals(500, ellipse.getXAxis()),
                () -> assertEquals(600, ellipse.getYAxis())
        );
    }

    @Test
    public void testResizeEllipse1() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        ellipse.resize(5);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(50, ellipse.getXAxis()),
                () -> assertEquals(100, ellipse.getYAxis())
        );
    }

    @Test
    public void testResizeEllipse2() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        ellipse.resize(0.3);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(3, ellipse.getXAxis()),
                () -> assertEquals(6, ellipse.getYAxis())
        );
    }

    @Test
    public void testStretchEllipse1() {
        Ellipse ellipse = new Ellipse(10, 20, 30, 40);
        ellipse.stretch(3, 5);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(90, ellipse.getXAxis()),
                () -> assertEquals(200, ellipse.getYAxis())
        );
    }

    @Test
    public void testStretchEllipse2() {
        Ellipse ellipse = new Ellipse(10, 20, 30, 40);
        ellipse.stretch(0.3, 0.5);
        assertAll(
                () -> assertEquals(10, ellipse.getCenter().getX()),
                () -> assertEquals(20, ellipse.getCenter().getY()),
                () -> assertEquals(9, ellipse.getXAxis()),
                () -> assertEquals(20, ellipse.getYAxis())
        );
    }


    @Test
    public void testAreaEllipse() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        assertEquals(Math.PI * 50, ellipse.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterEllipse() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        assertEquals(2 * Math.PI * Math.sqrt(62.5), ellipse.getPerimeter(), DOUBLE_EPS);
    }

    @Test
    public void testIsPointInsideEllipse1() {
        Ellipse ellipse = new Ellipse(10, 20, 10, 20);
        assertAll(
                () -> assertTrue(ellipse.isInside(10, 20)),
                () -> assertTrue(ellipse.isInside(10, 10)),
                () -> assertTrue(ellipse.isInside(10, 30)),
                () -> assertTrue(ellipse.isInside(5, 20)),
                () -> assertTrue(ellipse.isInside(15, 20)),
                () -> assertTrue(ellipse.isInside(12, 13)),
                () -> assertFalse(ellipse.isInside(15, 10)),
                () -> assertFalse(ellipse.isInside(15, 30)),
                () -> assertFalse(ellipse.isInside(5, 30))
        );
    }

    @Test
    public void testIsPointInsideEllipse2() {
        Ellipse ellipse = new Ellipse(new Point(10, 20), 10, 20);
        assertAll(
                () -> assertTrue(ellipse.isInside(new Point(10, 20))),
                () -> assertTrue(ellipse.isInside(new Point(10, 10))),
                () -> assertTrue(ellipse.isInside(new Point(10, 30))),
                () -> assertTrue(ellipse.isInside(new Point(5, 20))),
                () -> assertTrue(ellipse.isInside(new Point(15, 20))),
                () -> assertTrue(ellipse.isInside(new Point(12, 13))),
                () -> assertFalse(ellipse.isInside(new Point(15, 10))),
                () -> assertFalse(ellipse.isInside(new Point(15, 30))),
                () -> assertFalse(ellipse.isInside(new Point(5, 30)))
        );
    }

    @Test
    public void testEqualsEllipse() {
        Ellipse ellipse1 = new Ellipse(new Point(10, 20), 10, 20);
        Ellipse ellipse2 = new Ellipse(new Point(10, 20), 10, 20);
        Ellipse ellipse3 = new Ellipse(10, 20, 10, 20);
        Ellipse ellipse4 = new Ellipse(new Point(0, 0), 10, 20);
        Ellipse ellipse5 = new Ellipse(new Point(20, 10), 10, 20);
        assertEquals(ellipse1, ellipse2);
        assertEquals(ellipse1, ellipse3);
        assertNotEquals(ellipse1, ellipse4);
        assertNotEquals(ellipse1, ellipse5);
    }

}
