package net.thumbtack.school.database.model;

import java.util.Objects;

public class Subject {

    private int id;
    private String name;

    public Subject() {
    }

    public Subject(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Subject(String name) {
        this(0, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return getId() == subject.getId() &&
                Objects.equals(getName(), subject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
