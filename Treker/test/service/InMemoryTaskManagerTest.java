package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        taskManager = new InMemoryTaskManager(historyManager);
    }

    @Test
    public void testAddNewTask() {
        Task task = new Task(1, "Test Task", "This is a test task", Status.NEW);
        int id = taskManager.addNewTask(task);

        assertEquals(1, id); // Проверяем, что ID задачи равен 1
        assertEquals(task, taskManager.getTask(id)); // Проверяем, что добавленная задача соответствует ожидаемой
    }

    @Test
    public void testGetAllTasks() {
        Task task1 = new Task(1, "Test Task 1", "This is the first test task", Status.NEW);
        Task task2 = new Task(2, "Test Task 2", "This is the second test task", Status.IN_PROGRESS);

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        List<Task> tasks = taskManager.getAllTasks();

        assertEquals(2, tasks.size()); // Проверяем, что общее количество задач равно 2
        assertTrue(tasks.contains(task1)); // Проверяем, что первая задача в списке
        assertTrue(tasks.contains(task2)); // Проверяем, что вторая задача в списке
    }

    @Test
    public void testRemoveTask() {
        Task task = new Task(1, "Test Task", "This is a test task", Status.NEW);
        taskManager.addNewTask(task);

        taskManager.deleteTasks(1); // Удаляем задачу
        assertNull(taskManager.getTask(1)); // Проверяем, что задача была удалена
    }

    // Добавьте больше тестов для других методов (например, для эпиков, подзадач и т.д.)
}
