package test;

import model.Task;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void testHistoryManager() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Task 1");
        taskManager.addTask(task1);
        Task task2 = new Task("Task 2");
        taskManager.addTask(task2);

        taskManager.getTask(task1.getId());
        taskManager.getTask(task2.getId());

        HistoryManager historyManager = taskManager.getHistoryManager();
        assertEquals(2, historyManager.getHistory().size());
    }
}
