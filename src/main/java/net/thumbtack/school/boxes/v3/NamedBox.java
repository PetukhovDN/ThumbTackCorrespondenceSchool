package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;

public class NamedBox<T extends Figure> extends Box<T> {
    private String name;

    public NamedBox(T content, String name) {
        super(content);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
