package service;


import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.util.*;


public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Task> tasks; // Задачи
    private final Map<Integer, Epic> epics; // Эпики
    private final Map<Integer, Subtask> subtasks; // Подзадачи
    private int nextId = 1; // Счетчик для ID
    protected HistoryManager historyManager; // Менеджер истории
    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
        this.tasks = new HashMap<>();   // Инициализация мапы для задач
        this.epics = new HashMap<>();    // Инициализация мапы для эпиков
        this.subtasks = new HashMap<>(); // Инициализация мапы для подзадач
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
        // Проверяем, что epicId задан верно и такой эпик есть
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            throw new NoSuchElementException("Epic with ID " + subtask.getEpicId() + " not found.");
        }
        // Устанавливаем id и добавляем подзадачу в хранилище только после проверки epicId
        int id = nextId++;
        subtask.setId(id);
        subtasks.put(id, subtask);

        // Добавляем ID подзадачи в эпик
        epic.addSubtask(subtask);
        // Обновляем статус эпика, так как была добавлена новая подзадача
        updateStatus(subtask.getEpicId(),subtask.getStatus());

        // Возвращаем ID добавленной подзадачи
        return id;
    }

    @Override
    public List<Task> getTasks() {
        // Проверяем, что карта задач не null
        if (tasks == null) {
            throw new IllegalStateException("Tasks storage is not initialized."); // Выбрасываем исключение
        }
        // Возвращаем список всех задач
        return new ArrayList<>(tasks.values());
    }
    @Override
    public List<Epic> getEpics() {
        if (epics == null) {
            throw new IllegalStateException("Epics storage is not initialized."); // Выбрасываем исключение
        }
        // Возвращаем список всех эпиков
        return new ArrayList<>(epics.values());
    }
    @Override
    public List<Subtask> getSubtasks() {
        // Проверяем, что карта подзадач не null
        if (subtasks == null) {
            throw new IllegalStateException("Subtasks storage is not initialized."); // Выбрасываем исключение
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
        // Возвращаем список подзадач, если они найдены
        return epicSubtasks;
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
        Task existingTask = tasks.get(task.getId());
        existingTask.setTitle(task.getTitle()); // Обновляем название
        existingTask.setDescription(task.getDescription()); // Обновляем описание
        existingTask.setStatus(task.getStatus()); // Обновляем статус
        // Нет необходимости сохранять в мапу, так как мы изменили объект
    }    @Override
    public void updateEpic(Epic epic) {
        // Проверяем, существует ли эпик с заданным ID
        if (epic == null || !epics.containsKey(epic.getId())) {
            throw new NoSuchElementException("Epic with id " + epic.getId() + " does not exist.");
        }
        Epic existingEpic = epics.get(epic.getId());
        // Обновляем параметры существующего эпика с использованием сеттеров
        existingEpic.setTitle(epic.getTitle());
        existingEpic.setDescription(epic.getDescription());
        existingEpic.setStatus(epic.getStatus());
        // Нет необходимости сохранять в мапу, так как мы изменили объект
    }
    public void updateSubtask(Subtask subtask) {
        if (subtask == null || !subtasks.containsKey(subtask.getId())) {
            throw new NoSuchElementException("Subtask with id " + subtask.getId() + " does not exist.");
        }
        // Получаем существующую подзадачу
        Subtask existingSubtask = subtasks.get(subtask.getId());

        // Обновляем параметры существующей подзадачи с использованием сеттеров
        existingSubtask.setTitle(subtask.getTitle()); // Обновляем название
        existingSubtask.setDescription(subtask.getDescription()); // Обновляем описание
        existingSubtask.setStatus(subtask.getStatus()); // Обновляем статус
        // ID остаётся прежним и не меняется
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
        // Проверяем, существует ли подзадача с данным ID
        if (!subtasks.containsKey(id)) {
            throw new NoSuchElementException("Subtask with id " + id + " does not exist.");
        }

        Subtask subtask = subtasks.get(id); // Получаем подзадачу
        int epicId = subtask.getEpicId(); // Получаем ID эпика, связанного с подзадачей

        // Проверяем, существует ли эпик
        Epic epic = epics.get(epicId);
        if (epic != null) {
            epic.removeSubtask(id); // Удаляем подзадачу из эпика
        }

        // Удаляем подзадачу из глобального списка подзадач
        subtasks.remove(id);
        updateStatus(epicId, epic.getStatus());

        // Пройдёмся по всем эпикам и удалим подзадачу, если она есть в их списках
        for (Epic currentEpic : epics.values()) {
            if (currentEpic.getSubtasks().contains(id)) {
                currentEpic.removeSubtask(id); // Удаляем подзадачу из текущего эпика
            }
        }
    }
    @Override
    public void deleteEpic(int id) {
        // Получаем эпик по ID
        Epic epic = epics.get(id);

        // Проверяем, существует ли эпик
        if (epic == null) {
            throw new NoSuchElementException("Epic with id " + id + " does not exist.");
        }
        // Удаляем все подзадачи, связанные с эпиком
        List<Integer> subtaskIdsToDelete = new ArrayList<>();

        // Проходим по всем подзадачам и собираем те, что принадлежат этому эпику
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == id) {
                subtaskIdsToDelete.add(subtask.getId());
            }
        }

        // Удаляем подзадачи по их ID
        for (Integer subtaskId : subtaskIdsToDelete) {
            subtasks.remove(subtaskId);
        }
        // Удаляем сам эпик
        epics.remove(id);
    }

    @Override
    public void updateStatus(int id, Status newStatus) {
        // Проверяем, существует ли задача в списке задач
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            if (task != null) {
                task.setStatus(newStatus); // Обновляем статус задачи
            }
        }
        // Проверяем, существует ли подзадача в списке подзадач
        else if (subtasks.containsKey(id)) {
            Subtask subtask = subtasks.get(id);
            if (subtask != null) {
                subtask.setStatus(newStatus); // Обновляем статус подзадачи

                // Получаем эпик, связанный с подзадачей
                Epic epic = epics.get(subtask.getEpicId());
                if (epic != null) {
                    // Логика обновления статуса эпика на основе его подзадач
                    boolean allDone = true;
                    boolean inProgress = false;

                    // Проходим по всем подзадачам эпика и анализируем их статусы
                    for (Subtask epicSubtask : subtasks.values()) {
                        if (epicSubtask.getEpicId() == epic.getId()) {
                            if (epicSubtask.getStatus() == Status.IN_PROGRESS) {
                                inProgress = true; // Найдена подзадача в статусе IN_PROGRESS
                            }
                            if (epicSubtask.getStatus() != Status.DONE) {
                                allDone = false; // Найдена подзадача, которая не завершена
                            }
                        }
                    }

                    // Устанавливаем статус эпика в зависимости от статусов подзадач
                    if (allDone) {
                        epic.setStatus(Status.DONE); // Все подзадачи завершены
                    } else if (inProgress) {
                        epic.setStatus(Status.IN_PROGRESS); // Есть подзадачи в процессе выполнения
                    } else {
                        epic.setStatus(Status.NEW); // Все подзадачи в статусе NEW
                    }
                }
            }
        }
        // Проверяем, существует ли эпик в списке эпиков
        else if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            if (epic != null) {
                epic.setStatus(newStatus); // Обновляем статус эпика

                // Логика обновления статуса эпика на основе его подзадач, аналогичная предыдущей
                boolean allDone = true;
                boolean inProgress = false;

                // Проходим по всем подзадачам эпика и анализируем их статусы
                for (Subtask subtask : subtasks.values()) {
                    if (subtask.getEpicId() == epic.getId()) {
                        if (subtask.getStatus() == Status.IN_PROGRESS) {
                            inProgress = true; // Найдена подзадача в статусе IN_PROGRESS
                        }
                        if (subtask.getStatus() != Status.DONE) {
                            allDone = false; // Найдена подзадача, которая не завершена
                        }
                    }
                }
                // Устанавливаем статус эпика в зависимости от статусов подзадач
                if (allDone) {
                    epic.setStatus(Status.DONE); // Все подзадачи завершены
                } else if (inProgress) {
                    epic.setStatus(Status.IN_PROGRESS); // Есть подзадачи в процессе выполнения
                } else {
                    epic.setStatus(Status.NEW); // Все подзадачи в статусе NEW
                }
            }
        } else {
            // Если ни задача, ни подзадача, ни эпик не найдены, выбрасываем исключение
            throw new NoSuchElementException("Task, Subtask, or Epic with id " + id + " does not exist.");
        }
    }
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory(); // Возвращаем историю задач из менеджера истории
    }

}

