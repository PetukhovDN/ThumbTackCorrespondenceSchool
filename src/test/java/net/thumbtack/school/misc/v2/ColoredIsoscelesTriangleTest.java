package net.thumbtack.school.misc.v2;

import net.thumbtack.school.figures.v2.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ColoredIsoscelesTriangleTest {

    @Test
    public void testColoredIsoscelesTriangle1() {
        Point xy1 = new Point(10, 20);
        Point xy2 = new Point(30, 20);
        Point xy3 = new Point(10, 40);
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(xy1, xy2, xy3, 4);
        assertAll(
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY())
        );
    }


    @Test
    public void testIsoscelesTriangle2() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, 9);
        assertAll(
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY())
        );
    }

    @Test
    public void testIsoscelesTriangle3() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(40, 40, 1);
        assertAll(
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getCathetusY())
        );
    }

    @Test
    public void testIsoscelesTriangle4() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle();
        assertAll(
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getCathetusY())
        );
    }

    @Test
    public void testSetPoints() {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle(5, 10, 15, 45, 5);
        triangle.setXY1(new Point(-20, -20));
        triangle.setXY2(new Point(20, -20));
        triangle.setXY3(new Point(-20, 20));
        assertAll(
                () -> Assertions.assertEquals(-20, triangle.getXY1().getX()),
                () -> Assertions.assertEquals(-20, triangle.getXY1().getY()),
                () -> Assertions.assertEquals(20, triangle.getXY2().getX()),
                () -> Assertions.assertEquals(-20, triangle.getXY2().getY()),
                () -> Assertions.assertEquals(-20, triangle.getXY3().getX()),
                () -> Assertions.assertEquals(20, triangle.getXY3().getY()),
                () -> Assertions.assertEquals(40, triangle.getCathetusX())
        );
    }

    @Test
    public void testSetColor1() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(5);
        assertAll(
                () -> assertEquals(5, coloredIsoscelesTriangle.getColor())
        );
        coloredIsoscelesTriangle.setColor(7);
        assertEquals(7, coloredIsoscelesTriangle.getColor());
    }


    @Test
    public void testMoveTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(0, 0, 10, 20, 23);
        coloredIsoscelesTriangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(100, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(50, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(70, coloredIsoscelesTriangle.getXY3().getY())
        );
        coloredIsoscelesTriangle.moveTo(110, 190);
        assertAll(
                () -> assertEquals(120, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(210, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getCathetusX())
        );
        coloredIsoscelesTriangle.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1110, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(1200, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY())
        );
    }

    @Test
    public void testResizeTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(0, 0, 100, 100, 55);
        coloredIsoscelesTriangle.resize(0.3);
        assertAll(
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getCathetusX())
        );
    }

    @Test
    public void testAreaTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 10, 20, 30, 16);
        assertEquals(100, coloredIsoscelesTriangle.getArea(), 1);
    }

    @Test
    public void testPerimeterTriangle() {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle(20, 20, 23, 24, 16);
        assertEquals(12, triangle.getPerimeter(), 1);
    }

    @Test
    public void testIsPointInsideTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, 17);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isInside(20, 30)),
                () -> assertTrue(coloredIsoscelesTriangle.isInside(10, 30)),
                () -> assertTrue(coloredIsoscelesTriangle.isInside(10, 40)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(30, 30)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(0, 0)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(10, 100)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(-10, 20)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(10, -20))
        );
    }

    @Test
    public void testIsIntersectsTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, 99);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(15, 25, 25, 35, 4))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, 20, 30, 40, 4))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, 20, 50, 40, 4))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, -20, 30, 40, 4))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, 20, 30, 60, 4))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(20, 30, 40, 50, 6))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, -20, 50, 60, 6))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(0, 10, 20, 30, 6))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, 20, 50, 40, 6))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-40, 20, -30, 40, 6)))
        );
    }

    @Test
    public void testIsTriangleInsideTriangle() {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, 12);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(15, 25, 25, 35, 12))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(-40, 20, -30, 40, 15))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(110, 120, 130, 140, 56))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(10, 120, 30, 140, 57))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(10, -40, 30, -20, 58)))
        );
    }

    @Test
    public void testEqualsTriangle() {
        ColoredIsoscelesTriangle triangle1 = new ColoredIsoscelesTriangle(new Point(10, 10), new Point(20, 10), new Point(10, 30), 5);
        ColoredIsoscelesTriangle triangle2 = new ColoredIsoscelesTriangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), 7);
        ColoredIsoscelesTriangle triangle3 = new ColoredIsoscelesTriangle(7);
        ColoredIsoscelesTriangle triangle4 = new ColoredIsoscelesTriangle(20, 20, 11);
        ColoredIsoscelesTriangle triangle5 = new ColoredIsoscelesTriangle(0, 0, 20, 20, 11);
        assertEquals(triangle2, triangle3);
        assertEquals(triangle4, triangle5);
        assertNotEquals(triangle1, triangle2);
        assertNotEquals(triangle3, triangle5);
    }
}
