package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks = new ArrayList<>();

    // Конструктор
    public Epic(String title, String description) {
        super(title, description);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    public void updateStatus() {
        // Логика обновления статуса эпика на основе статусов подзадач
        // Например, если все подзадачи DONE, то статус эпика DONE
    }

    // Метод установки статуса
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}







