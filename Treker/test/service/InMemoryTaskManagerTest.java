package service;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
    }

    @Test
    void addNewTask_ShouldAssignUniqueId() {
        // Создаем новую задачу с ID 1
        Task task = new Task(1, "Test Task", "Description", Status.NEW);
        int taskId = taskManager.addNewTask(task);

        // Проверяем, что ID задачи равен 1
        assertEquals(1, taskId);
        // Проверяем, что задача сохранена
        assertNotNull(taskManager.getTask(taskId));
    }

    @Test
    void addNewTask_ShouldAssignSequentialIds() {
        // Создаем и добавляем первую задачу с ID 1
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW);
        int taskId1 = taskManager.addNewTask(task1);

        // Создаем и добавляем вторую задачу с ID 2
        Task task2 = new Task(2, "Task 2", "Description 2", Status.NEW);
        int taskId2 = taskManager.addNewTask(task2);

        // Проверяем, что ID первой задачи равен 1
        assertEquals(1, taskId1);
        // Проверяем, что ID второй задачи равен 2
        assertEquals(2, taskId2);
        // Проверяем, что обе задачи сохранены
        assertNotNull(taskManager.getTask(taskId1));
        assertNotNull(taskManager.getTask(taskId2));
    }

    @Test
    void addNewTask_ShouldThrowExceptionForNullTask() {
        // Проверяем, что добавление null задачи вызывает исключение
        assertThrows(NullPointerException.class, () -> {
            taskManager.addNewTask(null);
        });
    }
}
