package service;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private int idCounter = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    public int addNewTask(Task task) {
        task.setId(idCounter++);
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int addNewEpic(Epic epic) {
        epic.setId(idCounter++);
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    public int addNewSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            subtask.setId(idCounter++);
            subtasks.put(subtask.getId(), subtask);
            epic.addSubtask(subtask);
            return subtask.getId();
        }
        return -1;
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
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
                epic.updateStatus();
            }
        }
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    public Map<Integer, Subtask> getSubtasks() {
        return subtasks;
    }
}

