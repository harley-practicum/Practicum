package test.service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void testAddTask() {
        Task task = new Task(1, "Test Task", "Description for Test Task", TaskStatus.NEW);
        taskManager.addTask(task);
        assertEquals(1, taskManager.getAllTasks().size());
    }

    @Test
    public void testAddEpic() {
        Epic epic = new Epic(1, "Test Epic", "Description for Test Epic", TaskStatus.NEW);
        taskManager.addEpic(epic);
        assertEquals(1, taskManager.getAllEpics().size());
    }

    @Test
    public void testAddSubtask() {
        // Создание эпика
        Epic epic = new Epic(1, "Test Epic", "Description for Test Epic", TaskStatus.NEW);
        taskManager.addEpic(epic); // Добавление эпика в менеджер

        // Создание подзадачи
        Subtask subtask = new Subtask(2, "Test Subtask", "Description for Test Subtask", TaskStatus.NEW, epic.getId());
        taskManager.addSubtask(subtask); // Добавление подзадачи в менеджер

        assertEquals(1, taskManager.getAllSubtasks().size()); // Проверка, что подзадача добавлена
        assertEquals(1, epic.getSubtasks().size()); // Проверка, что у эпика есть подзадачи
    }
}
