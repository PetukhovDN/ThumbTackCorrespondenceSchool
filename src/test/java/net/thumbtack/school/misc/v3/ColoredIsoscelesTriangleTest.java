package net.thumbtack.school.misc.v3;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ColoredIsoscelesTriangleTest {

    @Test
    public void testColoredIsoscelesTriangle1() throws ColorException {
        Point xy1 = new Point(10, 20);
        Point xy2 = new Point(30, 20);
        Point xy3 = new Point(10, 40);
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(xy1, xy2, xy3, Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
    }


    @Test
    public void testColoredIsoscelesTriangle2() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, Color.BLUE);
        assertAll(
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY()),
                () -> assertNotEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())

        );
    }

    @Test
    public void testColoredIsoscelesTriangle3() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(40, 40, Color.GREEN);
        assertAll(
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(40, coloredIsoscelesTriangle.getCathetusY()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
    }

    @Test
    public void testColoredIsoscelesTriangle4() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle();
        assertAll(
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(0, coloredIsoscelesTriangle.getXY3().getX()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(1, coloredIsoscelesTriangle.getCathetusY()),
                () -> assertEquals(Color.RED, coloredIsoscelesTriangle.getColor())
        );
    }

    @Test
    public void testColoredIsoscelesTriangle5() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredIsoscelesTriangle((Color) null));
    }

    @Test
    public void testColoredIsoscelesTriangle6() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredIsoscelesTriangle((String) null));
    }

    @Test
    public void testColoredIsoscelesTriangle7() throws ColorException {
        Point xy1 = new Point(10, 20);
        Point xy2 = new Point(30, 20);
        Point xy3 = new Point(10, 40);
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(xy1, xy2, xy3, "GREEN");
        assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor());
    }


    @Test
    public void testColoredIsoscelesTriangle8() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, "BLUE");
        assertNotEquals(Color.GREEN, coloredIsoscelesTriangle.getColor());
    }

    @Test
    public void testColoredIsoscelesTriangle9() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(40, 40, "GREEN");
        assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor());
    }

    @Test
    public void testColoredIsoscelesTriangle10() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle();
        assertEquals(Color.RED, coloredIsoscelesTriangle.getColor());
    }

    @Test
    public void testSetPoints() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle(5, 10, 15, 45, Color.GREEN);
        triangle.setXY1(new Point(-20, -20));
        triangle.setXY2(new Point(20, -20));
        triangle.setXY3(new Point(-20, 20));
        assertAll(
                () -> assertEquals(-20, triangle.getXY1().getX()),
                () -> assertEquals(-20, triangle.getXY1().getY()),
                () -> assertEquals(20, triangle.getXY2().getX()),
                () -> assertEquals(-20, triangle.getXY2().getY()),
                () -> assertEquals(-20, triangle.getXY3().getX()),
                () -> assertEquals(20, triangle.getXY3().getY()),
                () -> assertEquals(40, triangle.getCathetusX())
        );
    }

    @Test
    public void testSetColor1() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle(Color.GREEN);
        assertEquals(Color.GREEN, triangle.getColor());
        triangle.setColor(Color.BLUE);
        assertEquals(Color.BLUE, triangle.getColor());
    }

    @Test
    public void testSetColor2() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle("GREEN");
        assertEquals(Color.GREEN, triangle.getColor());
        triangle.setColor(Color.BLUE);
        assertEquals(Color.BLUE, triangle.getColor());
    }

    @Test
    public void testSetColor3() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle("GREEN");
        assertEquals(Color.GREEN, triangle.getColor());
        assertThrows(ColorException.class, () -> triangle.setColor((Color) null));
    }

    @Test
    public void testSetColor4() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle("GREEN");
        assertEquals(Color.GREEN, triangle.getColor());
        assertThrows(ColorException.class, () -> triangle.setColor((String) null));
    }

    @Test
    public void testMoveTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(0, 0, 10, 20, Color.GREEN);
        coloredIsoscelesTriangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(100, coloredIsoscelesTriangle.getXY1().getX()),
                () -> assertEquals(50, coloredIsoscelesTriangle.getXY2().getY()),
                () -> assertEquals(70, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
        coloredIsoscelesTriangle.moveTo(110, 190);
        assertAll(
                () -> assertEquals(120, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(210, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(10, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
        coloredIsoscelesTriangle.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1110, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(1200, coloredIsoscelesTriangle.getXY1().getY()),
                () -> assertEquals(20, coloredIsoscelesTriangle.getCathetusY()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
    }

    @Test
    public void testResizeTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(0, 0, 100, 100, Color.GREEN);
        coloredIsoscelesTriangle.resize(0.3);
        assertAll(
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY3().getY()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getXY2().getX()),
                () -> assertEquals(30, coloredIsoscelesTriangle.getCathetusX()),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
    }

    @Test
    public void testAreaTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 10, 20, 30, Color.GREEN);
        assertEquals(100, coloredIsoscelesTriangle.getArea(), 1);
    }

    @Test
    public void testPerimeterTriangle() throws ColorException {
        ColoredIsoscelesTriangle triangle = new ColoredIsoscelesTriangle(20, 20, 23, 24, Color.GREEN);
        assertEquals(12, triangle.getPerimeter(), 1);
    }

    @Test
    public void testIsPointInsideTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isInside(20, 30)),
                () -> assertTrue(coloredIsoscelesTriangle.isInside(10, 30)),
                () -> assertTrue(coloredIsoscelesTriangle.isInside(10, 40)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(30, 30)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(0, 0)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(10, 100)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(-10, 20)),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(10, -20)),
                () -> assertEquals(Color.GREEN, coloredIsoscelesTriangle.getColor())
        );
    }

    @Test
    public void testIsIntersectsTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(15, 25, 25, 35, Color.GREEN))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, 20, 30, 40, Color.GREEN))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, 20, 50, 40, Color.GREEN))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, -20, 30, 40, Color.GREEN))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(10, 20, 30, 60, Color.GREEN))),
                () -> assertTrue(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(20, 30, 40, 50, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, -20, 50, 60, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(0, 10, 20, 30, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-10, 20, 50, 40, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isIntersects(new ColoredIsoscelesTriangle(-40, 20, -30, 40, Color.GREEN)))
        );
    }

    @Test
    public void testIsTriangleInsideTriangle() throws ColorException {
        ColoredIsoscelesTriangle coloredIsoscelesTriangle = new ColoredIsoscelesTriangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(15, 25, 25, 35, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(-40, 20, -30, 40, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(110, 120, 130, 140, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(10, 120, 30, 140, Color.GREEN))),
                () -> assertFalse(coloredIsoscelesTriangle.isInside(new ColoredIsoscelesTriangle(10, -40, 30, -20, Color.GREEN)))
        );
    }

    @Test
    public void testEqualsTriangle() throws ColorException {
        ColoredIsoscelesTriangle triangle1 = new ColoredIsoscelesTriangle(new Point(10, 10), new Point(20, 10), new Point(10, 30), Color.GREEN);
        ColoredIsoscelesTriangle triangle2 = new ColoredIsoscelesTriangle(new Point(0, 0), new Point(1, 0), new Point(0, 1), Color.GREEN);
        ColoredIsoscelesTriangle triangle3 = new ColoredIsoscelesTriangle(Color.GREEN);
        ColoredIsoscelesTriangle triangle4 = new ColoredIsoscelesTriangle(20, 20, Color.GREEN);
        ColoredIsoscelesTriangle triangle5 = new ColoredIsoscelesTriangle(0, 0, 20, 20, Color.GREEN);
        assertEquals(triangle2, triangle3);
        assertEquals(triangle4, triangle5);
        assertNotEquals(triangle1, triangle2);
        assertNotEquals(triangle3, triangle5);
    }
}
