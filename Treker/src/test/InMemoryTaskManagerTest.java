package test;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTaskManagerTest {
    @Test
    public void testAddTask() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Test Task");
        taskManager.addTask(task);
        assertEquals(1, taskManager.getAllTasks().size());
    }

    @Test
    public void testAddEpic() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("Test Epic");
        taskManager.addEpic(epic);
        assertEquals(1, taskManager.getAllEpics().size());
    }

    @Test
    public void testAddSubtask() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("Test Epic");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Test Subtask", epic.getId());
        taskManager.addSubtask(subtask);
        assertEquals(1, taskManager.getAllSubtasks().size());
        assertEquals(1, epic.getSubtasks().size());
    }
}
