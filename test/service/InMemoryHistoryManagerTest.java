package service;

import model.Task;
import model.Status; // Предположим, что Status — это перечисление статусов задач
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void addNewTask_ShouldAddTaskToHistory() {
        Task task1 = new Task(1, "Task 1", "", Status.NEW);
        historyManager.add(task1);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "History should contain one task");
        assertEquals(task1, history.get(0), "Task in history should be Task 1");
    }

    @Test
    void addDuplicateTask_ShouldReplaceOldTaskInHistory() {
        Task task1 = new Task(1, "Task 1", "", Status.NEW);
        Task task2 = new Task(2, "Task 2", "", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1); // Adding task1 again

        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size(), "History should contain two tasks");
        assertEquals(task2, history.get(0), "Task 2 should be first in history");
        assertEquals(task1, history.get(1), "Task 1 should be last in history");
    }

    @Test
    void removeExistingTask_ShouldRemoveTaskFromHistory() {
        Task task1 = new Task(1, "Task 1", "", Status.NEW);
        Task task2 = new Task(2, "Task 2", "", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(1); // Remove task1

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "History should contain one task");
        assertEquals(task2, history.get(0), "Remaining task should be Task 2");
    }

    @Test
    void removeNonExistingTask_ShouldNotAffectHistory() {
        Task task1 = new Task(1, "Task 1", "", Status.NEW);

        historyManager.add(task1);
        historyManager.remove(2); // Try to remove non-existing task (id=2)

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size(), "History should still contain one task");
        assertEquals(task1, history.get(0), "Remaining task should be Task 1");
    }

    @Test
    void getHistory_WhenHistoryIsEmpty_ShouldReturnEmptyList() {
        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty(), "History should be empty");
    }

    @Test
    void getHistory_ShouldReturnTasksInCorrectOrder() {
        Task task1 = new Task(1, "Task 1", "", Status.NEW);
        Task task2 = new Task(2, "Task 2", "", Status.NEW);
        Task task3 = new Task(3, "Task 3", "", Status.NEW);

        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);

        List<Task> history = historyManager.getHistory();
        assertEquals(3, history.size(), "History should contain three tasks");
        assertEquals(task1, history.get(0), "First task should be Task 1");
        assertEquals(task2, history.get(1), "Second task should be Task 2");
        assertEquals(task3, history.get(2), "Third task should be Task 3");
    }
}
