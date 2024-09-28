package service;

import model.Task;
import model.TaskStatus;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int idCounter = 0;

    // Метод для создания новой задачи
    public Task createTask(String title, String description, TaskStatus status) {
        Task task = new Task(title, description, 0, status);
        task.setId(generateNewId()); // Устанавливаем id через сеттер
        tasks.put(task.getId(), task);
        return task;
    }

    // Метод для генерации уникального id
    private int generateNewId() {
        return ++idCounter;
    }

    // Метод для получения задачи по id
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    // Метод для удаления задачи по id
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    // Метод для удаления всех задач
    public void deleteAllTasks() {
        tasks.clear();
    }

    // Метод для получения всех задач
    public Map<Integer, Task> getAllTasks() {
        return tasks;
    }
}



