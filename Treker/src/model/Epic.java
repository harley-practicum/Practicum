package model;

import java.util.Objects;

public class Epic extends Task {
    public Epic(String title, String description, int id, TaskStatus status) {
        super(title, description, id, status);
    }

    @Override
    public String toString() {
        return "Epic{" + super.toString() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epic)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}


