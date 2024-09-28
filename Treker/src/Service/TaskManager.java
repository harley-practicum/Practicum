package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private int idCounter = 1;

    public void createEpic(Epic epic) {
        epic.setId(idCounter++);
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(idCounter++);
        subtasks.put(subtask.getId(), subtask);
        int epicId = subtask.getEpicId();
        if (epics.containsKey(epicId)) {
            epics.get(epicId).addSubtask(subtask.getId());
        }
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
    }

    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
        }
        subtasks.clear();
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteEpics() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
        }
        epics.clear();
    }

    public Map<Integer, Task> getAllTasks() {
        return tasks;
    }

    public Map<Integer, Subtask> getAllSubtasks() {
        return subtasks;
    }

    public Map<Integer, Epic> getAllEpics() {
        return epics;
    }
}


