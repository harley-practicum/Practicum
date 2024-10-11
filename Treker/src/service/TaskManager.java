package service;

import model.Task;
import model.Epic;
import model.Subtask;

import java.util.Map;

public interface TaskManager {
    void addTask(Task task); // Добавить задачу
    void addEpic(Epic epic); // Добавить эпик
    void addSubtask(Subtask subtask); // Добавить подзадачу
    Task getTask(int id); // Получить задачу по ID
    Epic getEpic(int id); // Получить эпик по ID
    Subtask getSubtask(int id); // Получить подзадачу по ID
    void removeTask(int id); // Удалить задачу
    void removeEpic(int id); // Удалить эпик
    void removeSubtask(int id); // Удалить подзадачу
    Map<Integer, Task> getAllTasks(); // Получить все задачи
    Map<Integer, Epic> getAllEpics(); // Получить все эпики
    Map<Integer, Subtask> getAllSubtasks(); // Получить все подзадачи
}
