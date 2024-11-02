package service;

import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;


public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>(); // Задачи
    private final Map<Integer, Epic> epics = new HashMap<>(); // Эпики
    private final Map<Integer, Subtask> subtasks = new HashMap<>(); // Подзадачи

    private int nextId = 1; // Счетчик для ID
    protected HistoryManager historyManager; // Менеджер истории
    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
            }
    public int getNextId() {
        return nextId;
    }
    @Override
    public int addNewTask(Task task) {
        int id = nextId++; // Получаем следующий уникальный ID
        task.setId(id); // Устанавливаем уникальный ID для задачи
        tasks.put(id, task); // Сохраняем задачу в коллекции
        return id; // Возвращаем ID добавленной задачи
    }
    @Override
    public int addNewEpic(Epic epic) {
        int id = nextId++; // Получаем следующий уникальный ID
        epic.setId(id); // Устанавливаем уникальный ID для эпика
        epics.put(id, epic); // Сохраняем эпик в коллекции
        return id; // Возвращаем ID добавленного эпика
    }
    @Override
    public Integer addNewSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());// Проверяем, что epicId задан верно и такой эпик есть
        if (epic == null) {
            throw new NoSuchElementException("Epic with ID " + subtask.getEpicId() + " not found.");
        }
        int id = nextId++;// Устанавливаем id и добавляем подзадачу в хранилище только после проверки epicId
        subtask.setId(id);
        subtasks.put(id, subtask);
        epic.addSubtask(subtask);// Добавляем ID подзадачи в эпик
        updateEpicStatus(epic.getId());// Обновляем статус эпика, так как была добавлена новая подзадача
        return id;// Возвращаем ID добавленной подзадачи
    }
    @Override
    public List<Task> getTasks() {
        // Проверяем, что карта задач не null
        if (tasks == null) {
            System.out.println("Задач нет");
        }
        // Возвращаем список всех задач
        return new ArrayList<>(tasks.values());
    }
    @Override
    public List<Epic> getEpics() {
        if (epics == null) {
            System.out.println("Эпиков нет.");
        }
        return new ArrayList<>(epics.values());
    }
    @Override
    public List<Subtask> getSubtasks() {
        // Проверяем, что карта подзадач не null
        if (subtasks == null) {
            System.out.println("Подзадач нет.");
        }
        // Возвращаем список всех подзадач
        return new ArrayList<>(subtasks.values());
    }
    @Override
    public List<Subtask> getEpicSubtasks(int epicId) {
        // Находим эпик по его ID
        Epic epic = epics.get(epicId);

        // Проверяем, существует ли эпик
        if (epic == null) {
            throw new NoSuchElementException("Epic with id " + epicId + " does not exist.");
        }
        // Получаем список подзадач этого эпика
        List<Subtask> epicSubtasks = new ArrayList<>();
        // Проходим по всем подзадачам, связанным с эпиком
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                epicSubtasks.add(subtask);
            }
        }
        return epicSubtasks;// Возвращаем список подзадач, если они найдены
    }
    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id); // Получаем задачу по ID
        if (task == null) {
            // Если задача не найдена, выбрасываем исключение
            throw new NoSuchElementException("Task with id " + id + " does not exist.");
        }
        historyManager.add(task); // Добавляем задачу в историю
        return task; // Возвращаем найденную задачу
    }
    @Override
    public Epic getEpic(int id) {
        // Проверяем, существует ли эпик с заданным ID
        Epic epic = epics.get(id); // Получаем эпик по ID
        if (epic == null) {
            // Если эпика нет, выбрасываем исключение
            throw new NoSuchElementException("Epic with id " + id + " does not exist.");
        }
        historyManager.add(epic); // Добавляем эпик в историю
        return epic; // Возвращаем найденный эпик
    }
    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtasks.get(id); // Получаем подзадачу по ID
        if (subtask == null) {
            throw new NoSuchElementException("Subtask with id " + id + " does not exist.");
        }
        historyManager.add(subtask); // Добавляем объект подзадачи в историю
        return subtask; // Возвращаем найденную подзадачу
    }
    @Override
    public void updateTask(Task task) {
        // Проверяем, существует ли задача с заданным ID
        if (task == null || !tasks.containsKey(task.getId())) {
            throw new NoSuchElementException("Task with id " + task.getId() + " does not exist.");
        }
        tasks.put(task.getId(), task);// Обновляем задачу целиком
    }

    @Override
    public void updateEpic(Epic epic) {
        // Проверяем, существует ли эпик с заданным ID
        if (epic == null || !epics.containsKey(epic.getId())) {
            throw new NoSuchElementException("Epic with id " + epic.getId() + " does not exist.");
        }
        Epic existingEpic = epics.get(epic.getId());
        // Обновляем параметры эпика, кроме статуса
        existingEpic.setTitle(epic.getTitle());  // Обновляем название
        existingEpic.setDescription(epic.getDescription());  // Обновляем описание
        updateEpicStatus(existingEpic.getId());// Обновляем статус эпика, основываясь на статусах подзадач
    }
    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtask == null || !subtasks.containsKey(subtask.getId())) {// Проверяем, существует ли подзадача с заданным ID
            throw new NoSuchElementException("Subtask with id " + subtask.getId() + " does not exist.");
        }
        Subtask existingSubtask = subtasks.get(subtask.getId());
        existingSubtask.setTitle(subtask.getTitle()); // Обновляем название
        existingSubtask.setDescription(subtask.getDescription()); // Обновляем описание
        existingSubtask.setStatus(subtask.getStatus()); // Обновляем статус
        int epicId = existingSubtask.getEpicId(); // Получаем ID эпика из подзадачи
        updateEpicStatus(epicId); // Пересчитываем статус эпика
    }
    @Override
    public void deleteTask(int id) {
        // Проверяем, существует ли задача с данным ID
        if (!tasks.containsKey(id)) {
            throw new NoSuchElementException("Task with id " + id + " does not exist.");
        }
        // Удаляем задачу по ID
        tasks.remove(id);
    }
    @Override
    public void deleteSubtask(int id) {
        if (!subtasks.containsKey(id)) {
            throw new NoSuchElementException("Subtask with id " + id + " does not exist.");
        }
        Subtask subtask = subtasks.get(id); // Получаем подзадачу
        int epicId = subtask.getEpicId(); // Получаем ID эпика, связанного с подзадачей
        Epic epic = epics.get(epicId);
        if (epic != null) {// Проверяем, существует ли эпик
            epic.removeSubtask(id); // Удаляем подзадачу из эпика
            updateEpicStatus(epicId); // Обновляем статус эпика после удаления подзадачи
        }
        subtasks.remove(id);// Удаляем подзадачу из глобального списка подзадач
    }
    @Override
    public void deleteEpic(int id) {
        // Получаем эпик по ID
        Epic epic = epics.get(id);
        if (epic == null) {// Проверяем, существует ли эпик
            throw new NoSuchElementException("Epic with id " + id + " does not exist.");
        }
        List<Subtask> subtasksToRemove = epic.getSubtasks();
        for (Subtask subtask : subtasksToRemove) {// Удаляем все подзадачи, связанные с эпиком
            subtasks.remove(subtask.getId());
        }
        epics.remove(id);// Удаляем сам эпик
    }
    @Override
    public void deleteAllTasks() {
        if (tasks.isEmpty()) {// Проверяем, есть ли задачи
            System.out.println("Нет задач для удаления.");
            return;
        }
        tasks.clear(); // Очищаем коллекцию задач
        System.out.println("Все задачи удалены.");
    }
    @Override
    public void deleteAllEpics() {
        if (epics.isEmpty()) {// Проверяем, есть ли вообще эпики в коллекции
            System.out.println("Нет эпиков для удаления.");
            return;
        }
        for (Epic epic : epics.values()) {
            List<Subtask> subtaskList = epic.getSubtasks();// Получаем список подзадач эпика
            if (!subtaskList.isEmpty()) {// Проверяем, есть ли у эпика подзадачи
                for (Subtask subtask : subtaskList) {// Удаляем каждую подзадачу, связанную с эпиком
                    subtasks.remove(subtask.getId()); // Удаляем подзадачу по её ID
                }
            }
        }
        epics.clear(); // Удаляем все эпики из коллекции
        System.out.println("Все эпики и связанные с ними подзадачи удалены.");
    }
    @Override
    public void deleteAllSubtasks() {
        if (subtasks.isEmpty()) {
            System.out.println("Нет подзадач для удаления."); // Сообщаем, если подзадач нет
            return;
        }
        subtasks.clear(); // Очищаем коллекцию подзадач
        System.out.println("Все подзадачи удалены."); // Подтверждаем успешное удаление
    }
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory(); // Возвращаем историю задач из менеджера истории
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId); // Получаем эпик по ID
        if (epic == null) {
            return; // Если эпик не найден, выходим
        }
        List<Subtask> subtasks = epic.getSubtasks(); // Получаем список подзадач
        if (subtasks.isEmpty()) {
            epic.setStatus(Status.NEW); // Если подзадач нет, устанавливаем статус NEW
            return;
        }
        int newCount = 0;
        int inProgressCount = 0;
        int doneCount = 0;
        // Перебираем все подзадачи и считаем их статусы
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

        // Обновляем статус эпика на основе подсчитанных значений
        if (doneCount == subtasks.size()) {
            epic.setStatus(Status.DONE); // Все подзадачи выполнены
        } else if (inProgressCount > 0) {
            epic.setStatus(Status.IN_PROGRESS); // Есть хотя бы одна подзадача в процессе
        } else {
            epic.setStatus(Status.NEW); // Все подзадачи новые
        }
    }
}