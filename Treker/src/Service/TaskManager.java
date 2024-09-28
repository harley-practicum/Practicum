package service;

import model.Task;
import model.Subtask;
import model.Epic;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private List<Epic> epics;
    private List<Subtask> subtasks;

    public TaskManager() {
        tasks = new ArrayList<>();
        epics = new ArrayList<>();
        subtasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addEpic(Epic epic) {
        epics.add(epic);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Epic> getEpics() {
        return epics;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}


