package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.figures.v3.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoxes {
    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testRectangleBox() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        Box<Rectangle> rectBox = new Box<>(rect);
        assertAll(
                () -> assertEquals(10, rectBox.getContent().getTopLeft().getX()),
                () -> assertEquals(20, rectBox.getContent().getTopLeft().getY()),
                () -> assertEquals(30, rectBox.getContent().getBottomRight().getX()),
                () -> assertEquals(40, rectBox.getContent().getBottomRight().getY()),
                () -> assertEquals(rectBox.getContent().getLength(), 20),
                () -> assertEquals(rectBox.getContent().getWidth(), 20),
                () -> assertEquals(400, rectBox.getArea(), DOUBLE_EPS)
        );
    }

    @Test
    public void testEllipseBox() {
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        Box<Ellipse> ellipseBox = new Box<>(ellipse);
        assertAll(
                () -> assertEquals(10, ellipseBox.getContent().getCenter().getX()),
                () -> assertEquals(20, ellipseBox.getContent().getCenter().getY()),
                () -> assertEquals(10, ellipseBox.getContent().getXAxis()),
                () -> assertEquals(20, ellipseBox.getContent().getYAxis()),
                () -> assertEquals(Math.PI * 10 * 20 / 4, ellipseBox.getArea(), DOUBLE_EPS)
        );
    }

    @Test
    public void testCircleBox() {
        Point center = new Point(10, 20);
        Circle circle = new Circle(center, 10);
        Box<Circle> circleBox = new Box<>(circle);
        assertAll(
                () -> assertEquals(10, circleBox.getContent().getCenter().getX()),
                () -> assertEquals(20, circleBox.getContent().getCenter().getY()),
                () -> assertEquals(10, circleBox.getContent().getRadius()),
                () -> assertEquals(Math.PI * 10 * 10, circleBox.getArea(), DOUBLE_EPS)
        );
    }

    @Test
    public void testSquareBox() {
        Point topLeft = new Point(10, 20);
        Square square = new Square(topLeft, 10);
        Box<Square> squareBox = new Box<>(square);
        assertAll(
                () -> assertEquals(10, squareBox.getContent().getTopLeft().getX()),
                () -> assertEquals(20, squareBox.getContent().getTopLeft().getY()),
                () -> assertEquals(20, squareBox.getContent().getBottomRight().getX()),
                () -> assertEquals(30, squareBox.getContent().getBottomRight().getY()),
                () -> assertEquals(squareBox.getContent().getLength(), 10)
        );
    }

    @Test
    public void testColoredRectangleBox() throws ColorException {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        ColoredRectangle rect = new ColoredRectangle(topLeft, bottomRight, Color.BLUE);
        Box<ColoredRectangle> rectBox = new Box<>(rect);
        assertAll(
                () -> assertEquals(10, rectBox.getContent().getTopLeft().getX()),
                () -> assertEquals(20, rectBox.getContent().getTopLeft().getY()),
                () -> assertEquals(30, rectBox.getContent().getBottomRight().getX()),
                () -> assertEquals(40, rectBox.getContent().getBottomRight().getY()),
                () -> assertEquals(rectBox.getContent().getLength(), 20),
                () -> assertEquals(rectBox.getContent().getWidth(), 20),
                () -> assertEquals(Color.BLUE, rectBox.getContent().getColor()),
                () -> assertEquals(400, rectBox.getArea(), DOUBLE_EPS)
        );
    }

    @Test
    public void testColoredCircleBox() throws ColorException {
        Point center = new Point(10, 20);
        ColoredCircle coloredCircle = new ColoredCircle(center, 10, Color.GREEN);
        Box<ColoredCircle> coloredCircleBox = new Box<>(coloredCircle);
        assertAll(
                () -> assertEquals(10, coloredCircleBox.getContent().getCenter().getX()),
                () -> assertEquals(20, coloredCircleBox.getContent().getCenter().getY()),
                () -> assertEquals(10, coloredCircleBox.getContent().getRadius()),
                () -> assertEquals(Color.GREEN, coloredCircle.getColor())
        );
    }


    @Test
    public void testAreaBox() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        Box<Rectangle> rectBox = new Box<>(rect);
        assertEquals(400, rectBox.getArea(), DOUBLE_EPS);
    }

    @Test
    public void testAreaEqualBox1() throws ColorException {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 60);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        Box<Rectangle> rectBox = new Box<>(rect);
        ColoredRectangle coloredRectangle = new ColoredRectangle(topLeft, bottomRight, Color.BLUE);
        Box<ColoredRectangle> coloredRectangleBox = new Box<>(coloredRectangle);
        assertTrue(coloredRectangleBox.isAreaEqual(rectBox));
    }

    @Test
    public void testAreaEqualBox2() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 100);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        Box<Rectangle> rectBox = new Box<>(rect);
        Square square = new Square(topLeft, 40);
        Box<Square> squareBox = new Box<>(square);
        assertTrue(Box.isAreaEqual(rectBox, squareBox));
    }

    @Test
    public void testRectangleArrayBox() {
        Point topLeft1 = new Point(10, 20);
        Point bottomRight1 = new Point(30, 60);
        Rectangle rect1 = new Rectangle(topLeft1, bottomRight1);
        Point topLeft2 = new Point(20, 10);
        Point bottomRight2 = new Point(60, 30);
        Rectangle rect2 = new Rectangle(topLeft2, bottomRight2);
        Rectangle[] rectangles1 = new Rectangle[]{rect1, rect2};
        ArrayBox<Rectangle> rectArrayBox1 = new ArrayBox<>(rectangles1);
        Rectangle[] rectangles2 = new Rectangle[]{rect2, rect1};
        ArrayBox<Rectangle> rectArrayBox2 = new ArrayBox<>(rectangles2);
        assertTrue(rectArrayBox1.isSameSize(rectArrayBox2));
    }

    @Test
    public void testRectangleEllipseArrayBoxes() {
        Point topLeft1 = new Point(10, 20);
        Point bottomRight1 = new Point(30, 60);
        Rectangle rect1 = new Rectangle(topLeft1, bottomRight1);
        Point topLeft2 = new Point(20, 10);
        Point bottomRight2 = new Point(60, 30);
        Rectangle rect2 = new Rectangle(topLeft2, bottomRight2);
        Rectangle[] rectangles = new Rectangle[]{rect1, rect2};
        ArrayBox<Rectangle> rectArrayBox = new ArrayBox<>(rectangles);
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        Ellipse[] ellipses = new Ellipse[]{ellipse};
        ArrayBox<Ellipse> ellipseArrayBox = new ArrayBox<>(ellipses);
        assertFalse(rectArrayBox.isSameSize(ellipseArrayBox));
    }

    @Test
    public void testMixedFiguresArrayBoxes() {
        Point topLeft1 = new Point(10, 20);
        Point bottomRight1 = new Point(30, 60);
        Rectangle rect1 = new Rectangle(topLeft1, bottomRight1);
        Point center = new Point(10, 20);
        Ellipse ellipse = new Ellipse(center, 10, 20);
        Figure[] figures1 = new Figure[]{rect1, ellipse};
        ArrayBox<Figure> figureArrayBox1 = new ArrayBox<>(figures1);
        Point topLeft2 = new Point(20, 10);
        Point bottomRight2 = new Point(60, 30);
        Rectangle rect2 = new Rectangle(topLeft2, bottomRight2);
        Figure[] figures2 = new Figure[]{rect2};
        ArrayBox<Figure> figureArrayBox2 = new ArrayBox<>(figures2);
        assertFalse(figureArrayBox1.isSameSize(figureArrayBox2));
    }

    @Test
    public void testPairBox1() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 60);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        Point center = new Point(10, 20);
        Circle circle = new Circle(center, 10);
        PairBox<Rectangle, Circle> pairBox1 = new PairBox<>(rect, circle);
        PairBox<Circle, Rectangle> pairBox2 = new PairBox<>(circle, rect);
        assertTrue(pairBox1.isAreaEqual(pairBox2));
        assertTrue(PairBox.isAreaEqual(pairBox1, pairBox2));
    }

    @Test
    public void testPairBox2() throws ColorException {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 60);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        ColoredRectangle coloredRectangle = new ColoredRectangle(topLeft, bottomRight, Color.BLUE);
        PairBox<Rectangle, ColoredRectangle> pairBox1 = new PairBox<>(rect, coloredRectangle);
        PairBox<ColoredRectangle, Rectangle> pairBox2 = new PairBox<>(coloredRectangle, rect);
        assertTrue(pairBox1.isAreaEqual(pairBox2));
        assertTrue(PairBox.isAreaEqual(pairBox1, pairBox2));
    }

    @Test
    public void testRectangleNamedBox() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        NamedBox<Rectangle> rectBox = new NamedBox<>(rect, "Very good rect");
        assertAll(
                () -> assertEquals(10, rectBox.getContent().getTopLeft().getX()),
                () -> assertEquals(20, rectBox.getContent().getTopLeft().getY()),
                () -> assertEquals(30, rectBox.getContent().getBottomRight().getX()),
                () -> assertEquals(40, rectBox.getContent().getBottomRight().getY()),
                () -> assertEquals(rectBox.getContent().getLength(), 20),
                () -> assertEquals(rectBox.getContent().getWidth(), 20),
                () -> assertEquals(400, rectBox.getArea(), DOUBLE_EPS),
                () -> assertEquals("Very good rect", rectBox.getName())
        );
    }

    @Test
    public void testAreaNamedBox() {
        Point topLeft = new Point(10, 20);
        Point bottomRight = new Point(30, 40);
        Rectangle rect = new Rectangle(topLeft, bottomRight);
        NamedBox<Rectangle> rectBox = new NamedBox<>(rect, "Very good rect");
        assertEquals(400, rectBox.getArea(), DOUBLE_EPS);
        assertEquals("Very good rect", rectBox.getName());
    }

    /*
	@Test
	public void testMustNotBeCompiled() {
		Box<String> stringBox = new Box<>("My String");
	}
    */


}
