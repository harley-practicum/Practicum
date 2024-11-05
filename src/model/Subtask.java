package model;

import java.util.Objects;

public class Subtask extends Task {

    private int epicId; // ID эпика, к которому относится подзадача

    public Subtask(int id, String title, String description, Status status, int epicId) {
        super(id, title, description, status);
        this.epicId = epicId; // Установка ID эпика
    }
    public int getEpicId() {
        return epicId; // Получение ID эпика
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subtask subtask)) return false;
        if (!super.equals(o)) return false;
        return epicId == subtask.epicId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }
    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", epicId=" + epicId +
                '}';
    }
}