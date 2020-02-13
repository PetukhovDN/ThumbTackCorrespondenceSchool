package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.ColorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestColoredRectangle {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testColoredRectangle1() throws ColorException {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        ColoredRectangle coloredRectangle = new ColoredRectangle(topLeft, bottomRight, Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(30, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(40, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(20, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );

    }

    @Test
    public void testColoredRectangle2() throws ColorException {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        ColoredRectangle coloredRectangle = new ColoredRectangle(topLeft, bottomRight, "GREEN");
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(30, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(40, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(20, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );

    }

    @Test
    public void testColoredRectangle3() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(30, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(40, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(20, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle4() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, "GREEN");
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(30, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(40, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(20, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle5() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, Color.GREEN);
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(10, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(10, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle6() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, "GREEN");
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(10, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(10, coloredRectangle.getLength()),
                () -> assertEquals(20, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle7() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(Color.GREEN);
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle8() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
    }

    @Test
    public void testColoredRectangle9() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredRectangle((Color) null));
    }

    @Test
    public void testColoredRectangle10() throws ColorException {
        assertThrows(ColorException.class, () -> new ColoredRectangle((String) null));
    }

    @Test
    public void testColoredRectangle11() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle();
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.RED, coloredRectangle.getColor())
        );
    }

    @Test
    public void testSetColor1() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(Color.GREEN);
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
        coloredRectangle.setColor(Color.BLUE);
        assertEquals(Color.BLUE, coloredRectangle.getColor());
    }

    @Test
    public void testSetColor2() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
        coloredRectangle.setColor("BLUE");
        assertEquals(Color.BLUE, coloredRectangle.getColor());
    }

    @Test
    public void testSetColor3() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
        assertThrows(ColorException.class, () -> coloredRectangle.setColor((Color) null));
    }

    @Test
    public void testSetColor4() throws ColorException {

        ColoredRectangle coloredRectangle = new ColoredRectangle("GREEN");
        assertAll(
                () -> assertEquals(0, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(-1, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(0, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(1, coloredRectangle.getLength()),
                () -> assertEquals(1, coloredRectangle.getWidth()),
                () -> assertEquals(Color.GREEN, coloredRectangle.getColor())
        );
        assertThrows(ColorException.class, () -> coloredRectangle.setColor((String) null));
    }

    @Test
    public void testSetCoordinates() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.setTopLeft(new Point(50, 60));
        coloredRectangle.setBottomRight(new Point(100, 200));
        assertAll(
                () -> assertEquals(50, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(60, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(100, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(200, coloredRectangle.getBottomRight().getY()),
                () -> assertEquals(50, coloredRectangle.getLength()),
                () -> assertEquals(140, coloredRectangle.getWidth())
        );
    }

    @Test
    public void testMoveColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(110, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(70, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(130, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(90, coloredRectangle.getBottomRight().getY())
        );
        coloredRectangle.moveTo(100, 200);
        assertAll(
                () -> assertEquals(100, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(200, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(120, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(220, coloredRectangle.getBottomRight().getY())
        );
        coloredRectangle.moveTo(new Point(1000, 2000));
        assertAll(
                () -> assertEquals(1000, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(2000, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(1020, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(2020, coloredRectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testResizeColoredRectangle1() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.resize(3);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(70, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(80, coloredRectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testResizeColoredRectangle2() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.resize(0.3);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(16, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(26, coloredRectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testStretchColoredRectangle1() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.stretch(3, 5);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(70, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(120, coloredRectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testStretchColoredRectangle2() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        coloredRectangle.stretch(0.3, 0.5);
        assertAll(
                () -> assertEquals(10, coloredRectangle.getTopLeft().getX()),
                () -> assertEquals(20, coloredRectangle.getTopLeft().getY()),
                () -> assertEquals(16, coloredRectangle.getBottomRight().getX()),
                () -> assertEquals(30, coloredRectangle.getBottomRight().getY())
        );
    }

    @Test
    public void testIsPointInsideColoredRectangle1() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredRectangle.isInside(20, 30)),
                () -> assertTrue(coloredRectangle.isInside(10, 30)),
                () -> assertTrue(coloredRectangle.isInside(30, 30)),
                () -> assertTrue(coloredRectangle.isInside(10, 40)),
                () -> assertFalse(coloredRectangle.isInside(0, 0)),
                () -> assertFalse(coloredRectangle.isInside(10, 100)),
                () -> assertFalse(coloredRectangle.isInside(-10, 20)),
                () -> assertFalse(coloredRectangle.isInside(10, -20)))
        ;
    }

    @Test
    public void testIsPointInsideColoredRectangle2() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredRectangle.isInside(new Point(20, 30))),
                () -> assertTrue(coloredRectangle.isInside(new Point(10, 30))),
                () -> assertTrue(coloredRectangle.isInside(new Point(30, 30))),
                () -> assertTrue(coloredRectangle.isInside(new Point(10, 40))),
                () -> assertFalse(coloredRectangle.isInside(new Point(0, 0))),
                () -> assertFalse(coloredRectangle.isInside(new Point(10, 100))),
                () -> assertFalse(coloredRectangle.isInside(new Point(-10, 20))),
                () -> assertFalse(coloredRectangle.isInside(new Point(10, -20)))
        );
    }

    @Test
    public void testIsIntersectsColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(15, 25, 25, 35, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(-10, 20, 30, 40, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(10, 20, 50, 40, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(-10, 20, 50, 40, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(10, -20, 30, 40, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(10, 20, 30, 60, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(-10, -20, 50, 60, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(0, 10, 20, 30, Color.GREEN))),
                () -> assertTrue(coloredRectangle.isIntersects(new ColoredRectangle(20, 30, 40, 50, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isIntersects(new ColoredRectangle(-40, 20, -30, 40, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isIntersects(new ColoredRectangle(110, 120, 130, 140, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isIntersects(new ColoredRectangle(10, 120, 30, 140, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isIntersects(new ColoredRectangle(10, -40, 30, -20, Color.GREEN)))
        );
    }

    @Test
    public void testIsColoredRectangleInsideColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        assertAll(
                () -> assertTrue(coloredRectangle.isInside(new ColoredRectangle(15, 25, 25, 35, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isInside(new ColoredRectangle(-40, 20, -30, 40, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isInside(new ColoredRectangle(110, 120, 130, 140, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isInside(new ColoredRectangle(10, 120, 30, 140, Color.GREEN))),
                () -> assertFalse(coloredRectangle.isInside(new ColoredRectangle(10, -40, 30, -20, Color.GREEN)))
        );
    }

    @Test
    public void testEqualsColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle1 = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        ColoredRectangle coloredRectangle2 = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        ColoredRectangle coloredRectangle3 = new ColoredRectangle(20, 30, 40, 50, Color.GREEN);
        ColoredRectangle coloredRectangle4 = new ColoredRectangle(20, 30, 40, 50, Color.RED);
        assertEquals(coloredRectangle1, coloredRectangle2);
        assertNotEquals(coloredRectangle1, coloredRectangle3);
        assertNotEquals(coloredRectangle1, coloredRectangle4);
    }

    @Test
    public void testAreaColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle1 = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        ColoredRectangle coloredRectangle2 = new ColoredRectangle(20, 30, 40, 80, Color.GREEN);
        assertEquals(400, coloredRectangle1.getArea(), DOUBLE_EPS);
        assertEquals(1000, coloredRectangle2.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testPerimeterColoredRectangle() throws ColorException {
        ColoredRectangle coloredRectangle1 = new ColoredRectangle(10, 20, 30, 40, Color.GREEN);
        ColoredRectangle coloredRectangle2 = new ColoredRectangle(20, 30, 40, 80, Color.GREEN);
        assertEquals(80, coloredRectangle1.getPerimeter(), DOUBLE_EPS);
        assertEquals(140, coloredRectangle2.getPerimeter(), DOUBLE_EPS);
    }

}
