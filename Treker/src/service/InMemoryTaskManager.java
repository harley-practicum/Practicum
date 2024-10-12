package service;

import model.Task;
import model.Epic;
import model.Subtask;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks; // Задачи
    private final Map<Integer, Epic> epics; // Эпики
    private final Map<Integer, Subtask> subtasks; // Подзадачи
    private int nextId = 1; // Счетчик для ID
    protected HistoryManager historyManager; // Менеджер истории

    // Конструктор с передачей менеджера истории
    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
        this.tasks = new HashMap<>();   // Инициализация мапы для задач
        this.epics = new HashMap<>();    // Инициализация мапы для эпиков
        this.subtasks = new HashMap<>(); // Инициализация мапы для подзадач
    }


    public InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
    }

    @Override
    public Task addTask(Task task) {
        task.setId(nextId); // Устанавливаем уникальный ID
        tasks.put(nextId, task); // Сохраняем задачу с новым ID
        nextId++; // Увеличиваем счетчик ID
        return task;
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(nextId); // Устанавливаем уникальный ID
        epics.put(nextId, epic); // Сохраняем эпик с новым ID
        nextId++; // Увеличиваем счетчик ID
        return epic; // Возвращаем добавленный эпик
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(nextId); // Устанавливаем уникальный ID
        subtasks.put(nextId, subtask); // Сохраняем подзадачу с новым ID
        nextId++; // Увеличиваем счетчик ID
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask); // Добавляем подзадачу в эпик
        }
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(id); // Добавляем ID в историю
        return tasks.get(id); // Возвращаем задачу по ID
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(id); // Добавляем ID в историю
        return epics.get(id); // Возвращаем эпик по ID
    }

    @Override
    public Subtask getSubtask(int id) {
        historyManager.add(id); // Добавляем ID в историю
        return subtasks.get(id); // Возвращаем подзадачу по ID
    }

    @Override
    public void removeTask(int id) {
        tasks.remove(id); // Удаляем задачу по ID
    }

    @Override
    public void removeEpic(int id) {
        epics.remove(id); // Удаляем эпик по ID
    }

    @Override
    public void removeSubtask(int id) {
        subtasks.remove(id); // Удаляем подзадачу по ID
    }

    @Override
    public Map<Integer, Task> getAllTasks() {
        return tasks; // Возвращаем все задачи
    }

    @Override
    public Map<Integer, Epic> getAllEpics() {
        return epics; // Возвращаем все эпики
    }

    @Override
    public Map<Integer, Subtask> getAllSubtasks() {
        return subtasks; // Возвращаем все подзадачи
    }

    public HistoryManager getHistoryManager() {
        return historyManager; // Возвращаем менеджер истории
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager; // Устанавливаем менеджер истории
    }
    public int getNextId() {
        return nextId++;
    }
}
