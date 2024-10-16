package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryHistoryManagerTest {

    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void testAddTaskToHistory() {
        Task task = new Task(1, "Task 1", "Description 1", Status.NEW);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size()); // Проверяем, что задача добавлена
        assertEquals(task, history.get(0)); // Проверяем, что это именно наша задача
    }

    @Test
    void testAddTaskExceedingMaxSize() {
        // Добавляем 11 задач
        for (int i = 1; i <= 11; i++) {
            historyManager.add(new Task(i, "Task " + i, "Description " + i, Status.NEW));
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size()); // Проверяем, что размер истории не превышает 10

        // Проверяем, что первая добавленная задача была удалена
        assertEquals(2, history.get(0).getId());
    }

    @Test
    void testAddDuplicateTask() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW);
        Task task2 = new Task(2, "Task 2", "Description 2", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1); // Добавляем task1 снова

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size()); // Размер истории должен остаться 2
        assertEquals(task1, history.get(1)); // Task 1 должен быть перемещён в конец
    }

    @Test
    void testRemoveTaskFromHistory() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW);
        Task task2 = new Task(2, "Task 2", "Description 2", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);

        historyManager.remove(1); // Удаляем задачу с ID 1

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size()); // Должна остаться одна задача
        assertEquals(task2, history.get(0)); // Должна остаться задача 2
    }

    @Test
    void testGetEmptyHistory() {
        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty()); // Проверяем, что история пуста
    }
}
