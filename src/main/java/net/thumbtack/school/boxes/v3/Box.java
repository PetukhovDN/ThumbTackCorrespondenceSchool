package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class Box<T extends Figure> implements HasArea {
    private static final double DOUBLE_EPS = 1E-6;
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public static boolean isAreaEqual(Box<? extends Figure> box1, Box<? extends Figure> box2) {
        return Math.abs(box1.getArea() - box2.getArea()) < DOUBLE_EPS;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public double getArea() {
        return content.getArea();
    }

    public boolean isAreaEqual(Box<? extends Figure> box) {
        return Math.abs(this.getArea() - box.getArea()) < DOUBLE_EPS;
    }
}
