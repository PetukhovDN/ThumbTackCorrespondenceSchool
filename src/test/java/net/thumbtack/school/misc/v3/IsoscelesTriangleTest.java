package net.thumbtack.school.misc.v3;

import net.thumbtack.school.figures.v3.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsoscelesTriangleTest {

    @Test
    public void testIsoscelesTriangle1() {
        Point xy1 = new Point(10, 20);
        Point xy2 = new Point(30, 20);
        Point xy3 = new Point(10, 40);
        IsoscelesTriangle triangle = new IsoscelesTriangle(xy1, xy2, xy3);
        assertAll(
                () -> assertEquals(10, triangle.getXY1().getX()),
                () -> assertEquals(20, triangle.getXY1().getY()),
                () -> assertEquals(30, triangle.getXY2().getX()),
                () -> assertEquals(20, triangle.getXY2().getY()),
                () -> assertEquals(10, triangle.getXY3().getX()),
                () -> assertEquals(40, triangle.getXY3().getY()),
                () -> assertEquals(20, triangle.getKatetX()),
                () -> assertEquals(20, triangle.getKatetY())
        );
    }


    @Test
    public void testIsoscelesTriangle2() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(10, 20, 30, 40);
        assertAll(
                () -> assertEquals(10, triangle.getXY1().getX()),
                () -> assertEquals(20, triangle.getXY1().getY()),
                () -> assertEquals(30, triangle.getXY2().getX()),
                () -> assertEquals(20, triangle.getXY2().getY()),
                () -> assertEquals(10, triangle.getXY3().getX()),
                () -> assertEquals(40, triangle.getXY3().getY()),
                () -> assertEquals(20, triangle.getKatetX()),
                () -> assertEquals(20, triangle.getKatetY())
        );
    }

    @Test
    public void testIsoscelesTriangle3() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(40, 40);
        assertAll(
                () -> assertEquals(0, triangle.getXY1().getX()),
                () -> assertEquals(0, triangle.getXY1().getY()),
                () -> assertEquals(40, triangle.getXY2().getX()),
                () -> assertEquals(0, triangle.getXY2().getY()),
                () -> assertEquals(0, triangle.getXY3().getX()),
                () -> assertEquals(40, triangle.getXY3().getY()),
                () -> assertEquals(40, triangle.getKatetX()),
                () -> assertEquals(40, triangle.getKatetY())
        );
    }

    @Test
    public void testIsoscelesTriangle4() {
        IsoscelesTriangle triangle = new IsoscelesTriangle();
        assertAll(
                () -> assertEquals(0, triangle.getXY1().getX()),
                () -> assertEquals(0, triangle.getXY1().getY()),
                () -> assertEquals(1, triangle.getXY2().getX()),
                () -> assertEquals(0, triangle.getXY2().getY()),
                () -> assertEquals(0, triangle.getXY3().getX()),
                () -> assertEquals(1, triangle.getXY3().getY()),
                () -> assertEquals(1, triangle.getKatetX()),
                () -> assertEquals(1, triangle.getKatetY())
        );
    }

    @Test
    public void testSetPoints() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(5, 10, 15, 45);
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
                () -> assertEquals(40, triangle.getKatetX())
        );
    }

    @Test
    public void testMoveTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(0, 0, 10, 20);
        triangle.moveRel(100, 50);
        assertAll(
                () -> assertEquals(100, triangle.getXY1().getX()),
                () -> assertEquals(50, triangle.getXY2().getY()),
                () -> assertEquals(70, triangle.getXY3().getY())
        );
        triangle.moveTo(110, 190);
        assertAll(
                () -> assertEquals(120, triangle.getXY2().getX()),
                () -> assertEquals(210, triangle.getXY3().getY()),
                () -> assertEquals(10, triangle.getKatetX())
        );
        triangle.moveTo(new Point(1100, 1200));
        assertAll(
                () -> assertEquals(1110, triangle.getXY2().getX()),
                () -> assertEquals(1200, triangle.getXY1().getY()),
                () -> assertEquals(20, triangle.getKatetY())
        );
    }

    @Test
    public void testResizeTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(0, 0, 100, 100);
        triangle.resize(0.3);
        assertAll(
                () -> assertEquals(30, triangle.getXY3().getY()),
                () -> assertEquals(30, triangle.getXY2().getX()),
                () -> assertEquals(30, triangle.getKatetX())
        );
    }

    @Test
    public void testAreaTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(10, 10, 20, 30);
        assertEquals(100, triangle.getArea());
    }

    @Test
    public void testPerimeterTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(20, 20, 23, 24);
        assertEquals(12, triangle.getPerimeter());
    }

    @Test
    public void testIsPointInsideTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(triangle.isInside(20, 30)),
                () -> assertTrue(triangle.isInside(10, 30)),
                () -> assertTrue(triangle.isInside(10, 40)),
                () -> assertFalse(triangle.isInside(30, 30)),
                () -> assertFalse(triangle.isInside(0, 0)),
                () -> assertFalse(triangle.isInside(10, 100)),
                () -> assertFalse(triangle.isInside(-10, 20)),
                () -> assertFalse(triangle.isInside(10, -20))
        );
    }

    @Test
    public void testIsIntersectsTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(15, 25, 25, 35))),
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(-10, 20, 30, 40))),
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(10, 20, 50, 40))),
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(10, -20, 30, 40))),
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(10, 20, 30, 60))),
                () -> assertTrue(triangle.isIntersects(new IsoscelesTriangle(20, 30, 40, 50))),
                () -> assertFalse(triangle.isIntersects(new IsoscelesTriangle(-10, -20, 50, 60))),
                () -> assertFalse(triangle.isIntersects(new IsoscelesTriangle(0, 10, 20, 30))),
                () -> assertFalse(triangle.isIntersects(new IsoscelesTriangle(-10, 20, 50, 40))),
                () -> assertFalse(triangle.isIntersects(new IsoscelesTriangle(-40, 20, -30, 40)))
        );
    }

    @Test
    public void testIsTriangleInsideTriangle() {
        IsoscelesTriangle triangle = new IsoscelesTriangle(10, 20, 30, 40);
        assertAll(
                () -> assertTrue(triangle.isInside(new IsoscelesTriangle(15, 25, 25, 35))),
                () -> assertFalse(triangle.isInside(new IsoscelesTriangle(-40, 20, -30, 40))),
                () -> assertFalse(triangle.isInside(new IsoscelesTriangle(110, 120, 130, 140))),
                () -> assertFalse(triangle.isInside(new IsoscelesTriangle(10, 120, 30, 140))),
                () -> assertFalse(triangle.isInside(new IsoscelesTriangle(10, -40, 30, -20)))
        );
    }

    @Test
    public void testEqualsTriangle() {
        IsoscelesTriangle triangle1 = new IsoscelesTriangle(new Point(10, 10), new Point(20, 10), new Point(10, 30));
        IsoscelesTriangle triangle2 = new IsoscelesTriangle(new Point(0, 0), new Point(1, 0), new Point(0, 1));
        IsoscelesTriangle triangle3 = new IsoscelesTriangle();
        IsoscelesTriangle triangle4 = new IsoscelesTriangle(20, 20);
        IsoscelesTriangle triangle5 = new IsoscelesTriangle(0, 0, 20, 20);
        assertEquals(triangle2, triangle3);
        assertEquals(triangle4, triangle5);
        assertNotEquals(triangle1, triangle2);
        assertNotEquals(triangle3, triangle5);
    }
}