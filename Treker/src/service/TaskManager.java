package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.Map;

public interface TaskManager {
    // Метод для добавления задачи
    Task addTask(Task task);

    // Метод для добавления эпика
    Epic addEpic(Epic epic);

    // Метод для добавления подзадачи
    void addSubtask(Subtask subtask);

    // Метод для получения задачи по ID
    Task getTask(int id);

    // Метод для получения эпика по ID
    Epic getEpic(int id);

    // Метод для получения подзадачи по ID
    Subtask getSubtask(int id);

    // Метод для удаления задачи по ID
    void removeTask(int id);

    // Метод для удаления эпика по ID
    void removeEpic(int id);

    // Метод для удаления подзадачи по ID
    void removeSubtask(int id);

    // Метод для получения всех задач
    Map<Integer, Task> getAllTasks();

    // Метод для получения всех эпиков
    Map<Integer, Epic> getAllEpics();

    // Метод для получения всех подзадач
    Map<Integer, Subtask> getAllSubtasks();

    // Метод для получения менеджера истории
    HistoryManager getHistoryManager();
}
