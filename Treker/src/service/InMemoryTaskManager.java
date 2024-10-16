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
    public int addNewTask(Task task) {
        int id = nextId++; // Получаем следующий уникальный ID
        task.setId(id); // Устанавливаем уникальный ID для задачи
        tasks.put(id, task); // Сохраняем задачу в коллекции
        return id; // Возвращаем ID добавленной задачи
    }
    @Override
    public Integer addNewSubtask(Subtask subtask) {
        // Получаем новый уникальный ID для подзадачи
        int id = nextId++;
        subtask.setId(id); // Устанавливаем ID подзадачи

        // Добавляем подзадачу в карту подзадач
        subtasks.put(id, subtask);

        // Получаем эпик, к которому относится подзадача
        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            // Добавляем ID подзадачи в эпик
            epic.addSubtask(subtask);
            // Обновляем статус эпика, так как была добавлена новая подзадача
            updateEpicStatus(epic.getId());
        } else {
            throw new NoSuchElementException("Epic with ID " + subtask.getEpicId() + " not found.");
        }

        // Возвращаем ID добавленной подзадачи
        return id;
    }
    @Override
    public int addNewEpic(Epic epic) {
        int id = nextId++; // Получаем следующий уникальный ID
        epic.setId(id); // Устанавливаем уникальный ID для эпика
        epics.put(id, epic); // Сохраняем эпик в коллекции
        return id; // Возвращаем ID добавленного эпика
    }
    @Override
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>(tasks.values()); // Получаем список всех задач
        // Добавляем каждую задачу в историю
        for (Task task : taskList) {
            historyManager.add(task);
        }
        return taskList; // Возвращаем список задач
    }
    @Override
    public List<Subtask> getAllSubtasks() {
        List<Subtask> subtaskList = new ArrayList<>(subtasks.values()); // Получаем список всех подзадач
        // Добавляем каждую подзадачу в историю
        for (Subtask subtask : subtaskList) {
            historyManager.add(subtask);
        }

        return subtaskList; // Возвращаем список подзадач
    }
    @Override
    public List<Subtask> getEpicSubtasks(int epicId) {
        Epic epic = epics.get(epicId); // Находим эпик по его ID
        if (epic == null) {
            throw new NoSuchElementException("Epic with id " + epicId + " does not exist.");
        }
        // Получаем список подзадач по их ID, используя метод getSubtaskIds из эпика
        List<Subtask> epicSubtasks = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId); // Находим подзадачу по её ID
            if (subtask != null) {
                epicSubtasks.add(subtask); // Добавляем подзадачу в список
                historyManager.add(subtask); // Добавляем подзадачу в историю
            }
        }

        return epicSubtasks; // Возвращаем список всех подзадач эпика
    }


    @Override
    public List<Epic> getAllEpics() {
        List<Epic> epicList = new ArrayList<>(epics.values()); // Получаем список всех эпиков

        // Добавляем каждый эпик в историю
        for (Epic epic : epicList) {
            historyManager.add(epic);
        }

        return epicList; // Возвращаем список эпиков
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id); // Получаем задачу по ID
        if (task != null) {
            historyManager.add(task); // Добавляем задачу в историю, если она существует
        }
        return task; // Возвращаем задачу (или null, если её нет)
    }
    @Override
    public List<Task> getAllTask() {
        // Создаем список для всех задач
        List<Task> allTasks = new ArrayList<>();

        // Добавляем все обычные задачи
        allTasks.addAll(tasks.values());

        // Добавляем все эпики
        allTasks.addAll(epics.values());

        // Добавляем все подзадачи
        allTasks.addAll(subtasks.values());

        // Добавляем в историю все обычные задачи
        for (Task task : tasks.values()) {
            historyManager.add(task);
        }

        // Добавляем в историю все эпики
        for (Epic epic : epics.values()) {
            historyManager.add(epic);
        }

        // Добавляем в историю все подзадачи
        for (Subtask subtask : subtasks.values()) {
            historyManager.add(subtask);
        }

        // Возвращаем полный список задач
        return allTasks;
    }


    @Override
    public Epic getEpic(int id) {
        // Проверяем, существует ли эпик с заданным ID
        Epic epic = epics.get(id);

        if (epic == null) {
            // Если эпика нет, можно выбросить исключение или вернуть null
            throw new NoSuchElementException("Epic with id " + id + " does not exist.");
        }

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
    public void removeEpic(int id) {
        // Получаем эпик по ID и удаляем его из коллекции эпиков
        Epic epic = epics.remove(id);

        if (epic != null) {
            // Если эпик найден, удаляем все связанные с ним подзадачи
            List<Integer> subtaskIds = epic.getSubtaskIds(); // Получаем список ID подзадач эпика

            // Удаляем каждую подзадачу, связанную с этим эпиком
            for (Integer subtaskId : subtaskIds) {
                subtasks.remove(subtaskId); // Удаляем подзадачу по ID из коллекции подзадач
                historyManager.remove(subtaskId); // Удаляем подзадачу из истории, если используется менеджер истории
            }

            // Удаляем эпик из истории
            historyManager.remove(id);
        }
    }
    @Override
    public void deleteTasks(int id) {
        // Удаляем задачу по ID
        Task task = tasks.remove(id);

        // Если задача найдена
        if (task != null) {
            // Удаляем задачу из истории, если используется
            historyManager.remove(id);
        }
    }

    @Override
    public void deleteSubtask(int id) {
        // Удаляем подзадачу по ID
        Subtask subtask = subtasks.remove(id);

        // Если подзадача найдена
        if (subtask != null) {
            // Получаем эпик, связанный с этой подзадачей
            Epic epic = epics.get(subtask.getEpicId());

            // Удаляем подзадачу из эпика, если эпик найден
            if (epic != null) {
                epic.removeSubtask(id);
                updateEpicStatus(epic.getId()); // Обновляем статус эпика
            }

            // Удаляем подзадачу из истории, если используется
            historyManager.remove(id);
        }
    }
    public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId); // Получаем эпик по ID

        if (epic != null) {
            List<Subtask> epicSubtasks = new ArrayList<>();

            // Собираем все подзадачи, принадлежащие этому эпику
            for (Integer subtaskId : epic.getSubtaskIds()) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    epicSubtasks.add(subtask);
                }
            }

            // Если у эпика нет подзадач, статус устанавливается как NEW
            if (epicSubtasks.isEmpty()) {
                epic.setStatus(Status.NEW);
                return;
            }

            // Подсчитываем количество подзадач по их статусам
            int newCount = 0;
            int inProgressCount = 0;
            int doneCount = 0;

            for (Subtask subtask : epicSubtasks) {
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

            // Устанавливаем статус эпика в зависимости от статусов подзадач
            if (doneCount == epicSubtasks.size()) {
                epic.setStatus(Status.DONE); // Все подзадачи завершены
            } else if (newCount == epicSubtasks.size()) {
                epic.setStatus(Status.NEW); // Все подзадачи новые
            } else {
                epic.setStatus(Status.IN_PROGRESS); // Есть подзадачи в процессе выполнения
            }
        }
    }
    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory(); // Возвращаем историю задач из менеджера истории
    }

}

