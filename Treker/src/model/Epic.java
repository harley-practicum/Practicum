package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private final List<Subtask> subtasks; // Список подзадач
    public Epic(int id, String title, String description,Status status) {
        super(id, title, description, status);
        this.subtasks = new ArrayList<>(); // Инициализация списка подзадач
    }
    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask); // Добавление подзадачи в список
    }
    public List<Subtask> getSubtasks() {
        return subtasks;
    }
    public void removeSubtask(int id) {
        Subtask subtaskToRemove = null;
        for (Subtask subtask : subtasks) {
            if (subtask.getId() == id) {
                subtaskToRemove = subtask;
                break;
            }
        }
        if (subtaskToRemove != null) {
            subtasks.remove(subtaskToRemove); // Удаляем подзадачу из списка
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epic epic)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(subtasks, epic.subtasks);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasks);
    }
    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtasks=" + subtasks +
                '}';
    }
}

