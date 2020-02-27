package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;
import net.thumbtack.school.iface.v3.HasArea;

public class PairBox<T extends Figure, V extends Figure> implements HasArea {
    private static final double DOUBLE_EPS = 1E-6;

    private T firstContent;
    private V secondContent;

    public PairBox(T firstContent, V secondContent) {
        this.firstContent = firstContent;
        this.secondContent = secondContent;
    }

    public static boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox1, PairBox<? extends Figure, ? extends Figure> pairBox2) {
        return Math.abs(pairBox1.getArea() - pairBox2.getArea()) < DOUBLE_EPS;
    }

    public T getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(T firstContent) {
        this.firstContent = firstContent;
    }

    public V getSecondContent() {
        return secondContent;
    }

    public void setSecondContent(V secondContent) {
        this.secondContent = secondContent;
    }

    @Override
    public double getArea() {
        return firstContent.getArea() + secondContent.getArea();
    }

    public boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox) {
        return Math.abs(this.getArea() - pairBox.getArea()) < DOUBLE_EPS;
    }
}
