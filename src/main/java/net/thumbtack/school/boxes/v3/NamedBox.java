package net.thumbtack.school.boxes.v3;

import net.thumbtack.school.figures.v3.Figure;

// REVU Напишите вопрос в личну в Skype
public class NamedBox<T extends Figure> extends Box<T> { //Have a question of parameter T
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
