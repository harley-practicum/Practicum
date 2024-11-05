package service;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager(new InMemoryHistoryManager()); // Инициализируем менеджер задач с историей
        // Очистка всех задач
        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        taskManager.deleteAllSubtasks();
    }
    @Test
    void simpleTestForSubtaskId() {
        Epic epic = new Epic(0, "Simple Epic", "Simple Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask(0, "Simple Subtask", "Simple Description", Status.NEW, epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        System.out.println("Subtask ID: " + subtaskId); // Выводим ID подзадачи
        assertEquals(2, subtaskId); // Проверяем, что ID равен 2
    }

    @Test
    void addNewTask_ShouldAddTaskAndReturnId() {
        Task task = new Task(0, "Task Title", "Task Description", Status.NEW);
        int taskId = taskManager.addNewTask(task);
        assertEquals(1, taskId); // ID должен быть 1
        assertEquals(task, taskManager.getTask(taskId)); // Проверяем, что задача добавлена
    }

    @Test
    void deleteEpic_ShouldRemoveEpicAndItsSubtasks() {
        // Создаем эпик с необходимыми параметрами
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        // Создаем подзадачу, связанную с эпиком
        Subtask subtask = new Subtask(0, "Subtask Title", "Subtask Description", Status.NEW, epicId);
        taskManager.addNewSubtask(subtask);

        // Удаляем эпик
        taskManager.deleteEpic(epicId);

        // Проверяем, что выбрасывается исключение, когда мы пытаемся получить удаленный эпик
        Exception exception = assertThrows(NoSuchElementException.class, () -> taskManager.getEpic(epicId));
        assertEquals("Epic with id " + epicId + " does not exist.", exception.getMessage());

        // Проверяем, что подзадачи также были удалены
        assertEquals(0, taskManager.getSubtasks().size());
    }

    @Test
    void addNewSubtask_ShouldAddSubtaskAndUpdateEpic() {
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW); // Создание эпика с 4 аргументами
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask(0, "Subtask Title", "Subtask Description", Status.NEW, epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        assertEquals(2, subtaskId); // ID подзадачи должен быть 2
        assertEquals(subtask, taskManager.getSubtask(subtaskId)); // Проверяем, что подзадача добавлена
        assertEquals(1, taskManager.getEpicSubtasks(epicId).size()); // Эпик должен содержать одну подзадачу
    }




    @Test
    void deleteTask_ShouldRemoveTask() {
        Task task = new Task(0, "Task Title", "Task Description", Status.NEW);
        int taskId = taskManager.addNewTask(task);
        taskManager.deleteTask(taskId);

        Exception exception = assertThrows(NoSuchElementException.class, () -> taskManager.getTask(taskId));
        assertEquals("Task с таким id " + taskId + " не существует.", exception.getMessage());
    }

    @Test
    void updateTask_ShouldUpdateTask() {
        Task task = new Task(0, "Task Title", "Task Description", Status.NEW);
        int taskId = taskManager.addNewTask(task);

        task.setTitle("Updated Title");
        taskManager.updateTask(task);

        Task updatedTask = taskManager.getTask(taskId);
        assertEquals("Updated Title", updatedTask.getTitle()); // Проверяем, что название обновлено
    }

    @Test
    void updateEpic_ShouldUpdateEpic() {
        // Создаем эпик с 4 аргументами, включая статус
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        // Обновляем название эпика
        epic.setTitle("Updated Epic Title");
        taskManager.updateEpic(epic);

        // Проверяем, что эпик обновился
        Epic updatedEpic = taskManager.getEpic(epicId);
        assertEquals("Updated Epic Title", updatedEpic.getTitle()); // Проверяем, что название обновлено
    }

    @Test
    void updateSubtask_ShouldUpdateSubtask() {
        // Создаем эпик с 4 аргументами, включая статус
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        // Создаем подзадачу, связанную с эпиком
        Subtask subtask = new Subtask(0, "Subtask Title", "Subtask Description", Status.NEW, epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        // Обновляем название подзадачи
        subtask.setTitle("Updated Subtask Title");
        taskManager.updateSubtask(subtask);

        // Проверяем, что подзадача обновилась
        Subtask updatedSubtask = taskManager.getSubtask(subtaskId);
        assertEquals("Updated Subtask Title", updatedSubtask.getTitle()); // Проверяем, что название обновлено
    }


    @Test
    void getTasks_ShouldReturnAllTasks() {
        Task task1 = new Task(0, "Task 1", "Description 1", Status.NEW);
        Task task2 = new Task(0, "Task 2", "Description 2", Status.NEW);
        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size()); // Должны быть 2 задачи
    }

    @Test
    void getEpics_ShouldReturnAllEpics() {
        // Создаем эпики с необходимыми параметрами, включая статус
        Epic epic1 = new Epic(0, "Epic 1", "Description 1", Status.NEW);
        Epic epic2 = new Epic(0, "Epic 2", "Description 2", Status.NEW);
        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        // Получаем список эпиков
        List<Epic> epics = taskManager.getEpics();
        assertEquals(2, epics.size()); // Должны быть 2 эпика
    }


    @Test
    void getSubtasks_ShouldReturnAllSubtasks() {
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        // Создаем подзадачи, связанные с эпиком
        Subtask subtask1 = new Subtask(0, "Subtask 1", "Description 1", Status.NEW, epicId);
        Subtask subtask2 = new Subtask(0, "Subtask 2", "Description 2", Status.NEW, epicId);

        // Добавляем подзадачи в менеджер задач
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        // Получаем список всех подзадач
        List<Subtask> subtasks = taskManager.getSubtasks();

        // Проверяем, что в списке 2 подзадачи
        assertEquals(2, subtasks.size()); // Должны быть 2 подзадачи
    }


    @Test
    void deleteAllTasks_ShouldClearAllTasks() {
        Task task = new Task(0, "Task Title", "Task Description", Status.NEW);
        taskManager.addNewTask(task);
        taskManager.deleteAllTasks();

        assertEquals(0, taskManager.getTasks().size()); // Должны быть 0 задач
    }

    @Test
    void deleteAllEpics_ShouldClearAllEpics() {
        // Создаем эпик с необходимыми параметрами, включая статус
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        taskManager.addNewEpic(epic);
        taskManager.deleteAllEpics();

        assertEquals(0, taskManager.getEpics().size()); // Должны быть 0 эпиков
    }


    @Test
    void deleteAllSubtasks_ShouldClearAllSubtasks() {
        // Создаем эпик с необходимыми параметрами, включая статус
        Epic epic = new Epic(0, "Epic Title", "Epic Description", Status.NEW);
        int epicId = taskManager.addNewEpic(epic);

        // Создаем подзадачу, связанную с эпиком
        Subtask subtask = new Subtask(0, "Subtask Title", "Subtask Description", Status.NEW, epicId);
        taskManager.addNewSubtask(subtask);

        // Удаляем все подзадачи
        taskManager.deleteAllSubtasks();

        // Проверяем, что подзадач больше нет
        assertEquals(0, taskManager.getSubtasks().size()); // Должны быть 0 подзадач
    }

}
