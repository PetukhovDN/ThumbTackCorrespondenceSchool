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

    public T getFirstContent() {
        return this.firstContent;
    }

    public V getSecondContent() {
        return secondContent;
    }

    public void setFirstContent(T firstContent) {
        this.firstContent = firstContent;
    }

    public void setSecondContent(V secondContent) {
        this.secondContent = secondContent;
    }

    @Override
    public double getArea() {
        return getFirstContent().getArea() + getSecondContent().getArea();
    }

    public boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox) {
        return getArea() - pairBox.getArea() < DOUBLE_EPS;
    }

    public static boolean isAreaEqual(PairBox<? extends Figure, ? extends Figure> pairBox1, PairBox<? extends Figure, ? extends Figure> pairBox2) {
        return pairBox1.getArea() - pairBox2.getArea() < DOUBLE_EPS;
    }
}
