package service;

import model.Epic;
import model.Subtask;
import model.Task;

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
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic savedEpic = epics.get(epic.getId());
            savedEpic.title = epic.getTitle();
            savedEpic.description = epic.getDescription();
            savedEpic.updateStatus();
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.updateStatus();
            }
        }
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        epics.remove(id);
    }

    public void deleteSubtask(int id) {
        subtasks.remove(id);
    }

    public void deleteTasks() {
        tasks.clear();
    }

    public void deleteSubtasks() {
        subtasks.clear();
    }

    public void deleteEpics() {
        epics.clear();
        subtasks.clear();
    }
}
