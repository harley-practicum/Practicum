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

    public List<Integer> getSubtaskIds() {
        List<Integer> ids = new ArrayList<>();
        for (Subtask subtask : subtasks) {
            ids.add(subtask.getId()); // Добавляем ID каждой подзадачи в список
        }
        return ids;
    }
    public void updateEpicStatus() {
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW); // Если подзадач нет, статус - NEW
            return;
        }

        int newCount = 0;
        int inProgressCount = 0;
        int doneCount = 0;

        // Подсчитываем количество подзадач по статусам
        for (Subtask subtask : subtasks) {
            switch (subtask.getStatus()) {
                case NEW:
                    newCount++;
                    break;
                case IN_PROGRESS:
                    inProgressCount++;
                    break;
                case DONE:
                    doneCount++;
                    break;
            }
        }

        // Обновляем статус эпика
        if (doneCount == subtasks.size()) {
            setStatus(Status.DONE); // Все подзадачи выполнены
        } else if (inProgressCount > 0) {
            setStatus(Status.IN_PROGRESS); // Есть подзадачи в процессе
        } else {
            setStatus(Status.NEW); // Все подзадачи новые
        }
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
            updateEpicStatus(); // Обновляем статус эпика после удаления подзадачи
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

