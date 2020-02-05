package net.thumbtack.school.figures.v2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFigure {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testIsInside1() {
        Figure[] figures = new Figure[3];
        figures[0] = new Circle(0, 0, 10);
        figures[1] = new Rectangle(-10, -20, 10, 20);
        figures[2] = new ColoredCircle(1, 1, 100, 1);
        assertAll(
                () -> figures[0].isInside(0, 0),
                () -> figures[1].isInside(0, 0),
                () -> figures[2].isInside(0, 0))
        ;
    }

    @Test
    public void testIsInside2() {
        Figure[] figures = new Figure[3];
        figures[0] = new Circle(0, 0, 10);
        figures[1] = new Rectangle(-10, -20, 10, 20);
        figures[2] = new ColoredCircle(1, 1, 100, 1);
        assertAll(
                () -> figures[0].isInside(0, 0),
                () -> figures[1].isInside(0, 0),
                () -> figures[2].isInside(0, 0))
        ;
    }

    @Test
    public void testMove() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        Figure figure = rect;
        figure.moveRel(10, 10);
        assertAll(
                () -> assertEquals(10, rect.getTopLeft().getX()),
                () -> assertEquals(10, rect.getTopLeft().getY()),
                () -> assertEquals(20, rect.getBottomRight().getX()),
                () -> assertEquals(20, rect.getBottomRight().getY()));
        figure.moveTo(100, 100);
        assertAll(
                () -> assertEquals(100, rect.getTopLeft().getX()),
                () -> assertEquals(100, rect.getTopLeft().getY()),
                () -> assertEquals(110, rect.getBottomRight().getX()),
                () -> assertEquals(110, rect.getBottomRight().getY())
        );
        figure.moveTo(new Point(1000, 1000));
        assertAll(
                () -> assertEquals(1000, rect.getTopLeft().getX()),
                () -> assertEquals(1000, rect.getTopLeft().getY()),
                () -> assertEquals(1010, rect.getBottomRight().getX()),
                () -> assertEquals(1010, rect.getBottomRight().getY())
        );

    }

    @Test
    public void testAreaAndPerimeter() {
        Figure figure = new Rectangle(0, 0, 10, 10);
        assertEquals(100, figure.getArea(), DOUBLE_EPS);
        assertEquals(40, figure.getPerimeter(), DOUBLE_EPS);
        figure = new ColoredCircle(1, 1, 100, 1);
        assertEquals(Math.PI * 100 * 100, figure.getArea(), DOUBLE_EPS);
        assertEquals(2 * Math.PI * 100, figure.getPerimeter(), DOUBLE_EPS);
    }

}
