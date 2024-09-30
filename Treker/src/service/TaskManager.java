package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.*;

public class TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private int idCounter = 1;

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getEpicSubtasks(int epicId) {
        return epics.get(epicId).getSubtasks();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public int addNewTask(Task task) {
        task.id = idCounter++;
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int addNewEpic(Epic epic) {
        epic.id = idCounter++;
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    public Integer addNewSubtask(Subtask subtask) {
        subtask.id = idCounter++;
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask);
        }
        return subtask.getId();
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            Task existingTask = tasks.get(task.getId());
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic savedEpic = epics.get(epic.getId());
            savedEpic.setTitle(epic.getTitle());
            savedEpic.setDescription(epic.getDescription());
            // Удалён вызов updateStatus()
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            Subtask existingSubtask = subtasks.get(subtask.getId());
            existingSubtask.setTitle(subtask.getTitle());
            existingSubtask.setDescription(subtask.getDescription());
            existingSubtask.setStatus(subtask.getStatus());
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.updateStatus(); // Обновление статуса эпика
            }
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            // Удаляем все подзадачи, связанные с эпиком
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            subtasks.remove(id);
            if (epic != null) {
                epic.getSubtasks().remove(subtask);
                updateEpicStatus(epic); // Обновление статуса эпика
            }
        }
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteSubtasks() {
        // Очищаем все подзадачи
        subtasks.clear();

        // Очищаем подзадачи у всех эпиков и устанавливаем статус в NEW
        for (Epic epic : epics.values()) {
            epic.clearSubtasks(); // Очищаем подзадачи у эпика
            epic.setStatus(TaskStatus.NEW); // Устанавливаем статус в NEW
        }
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }

    private void updateEpicStatus(Epic epic) {
        // Логика обновления статуса эпика
        epic.updateStatus();
    }
}
