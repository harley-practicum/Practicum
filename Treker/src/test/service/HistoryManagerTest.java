package test.service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void testAddToHistory() {
        historyManager.add(1);
        historyManager.add(2);
        assertEquals(2, historyManager.getHistory().size());
    }

    @Test
    public void testRemoveFromHistory() {
        historyManager.add(1);
        historyManager.add(2);
        historyManager.remove(1);
        assertEquals(1, historyManager.getHistory().size());
    }

    @Test
    public void testClearHistory() {
        historyManager.add(1);
        historyManager.add(2);
        historyManager.clear();
        assertEquals(0, historyManager.getHistory().size());
    }

    @Test
    public void testTaskEquality() {
        Task task1 = new Task(1, "Task 1", "Description 1", TaskStatus.NEW);
        Task task2 = new Task(1, "Task 2", "Description 2", TaskStatus.NEW);
        assertEquals(task1, task2); // Проверка равенства по ID
    }

    // Добавьте тесты для проверки неизменности задачи после добавления в менеджер
    @Test
    public void testTaskImmutability() {
        Task task = new Task(1, "Task", "Description", TaskStatus.NEW);
        historyManager.add(task.getId());
        // Здесь должна быть логика проверки неизменности, например:
        // assertEquals(task, historyManager.get(task.getId()));
    }
}
