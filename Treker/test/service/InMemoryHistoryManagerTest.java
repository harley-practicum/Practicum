package service;
import model.Status;
import model.Task;
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
    void addTask_ShouldAddTaskToHistory() {
        Task task = new Task(1, "Test Task", "Description", Status.NEW);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }

    @Test
    void addTask_ShouldRemovePreviousInstanceOfTask() {
        Task task1 = new Task(1, "Task 1", "Description 1", Status.NEW);
        Task task2 = new Task(1, "Task 1", "Description 1", Status.NEW); // Идентичная задача
        historyManager.add(task1);
        historyManager.add(task2); // Добавляем ту же задачу снова

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size()); // История должна содержать только одну задачу
        assertEquals(task2, history.get(0)); // Проверяем, что в истории осталась последняя добавленная задача
    }

    @Test
    void addTask_ShouldLimitHistorySize() {
        // Добавляем 12 задач
        for (int i = 0; i < 12; i++) {
            historyManager.add(new Task(i + 1, "Task " + (i + 1), "Description " + (i + 1), Status.NEW));
        }

        // Получаем историю
        List<Task> history = historyManager.getHistory();

        // Проверяем, что история содержит ровно 10 задач
        assertEquals(10, history.size(), "История должна содержать ровно 10 задач.");

        // Проверяем, что самые старые задачи были удалены
        assertEquals(3, history.get(0).getId(), "Самая старая задача должна быть Task 3");
        assertEquals(4, history.get(1).getId(), "Вторая задача в истории должна быть Task 4");
        assertEquals(12, history.get(9).getId(), "Последняя задача в истории должна быть Task 12");
    }


    @Test
    void addTask_ShouldNotAddNullTask() {
        historyManager.add(null); // Добавляем null задачу

        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty()); // История должна оставаться пустой
    }

    @Test
    void getHistory_ShouldReturnEmptyListWhenNoTasks() {
        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty()); // При отсутствии задач история должна быть пустой
    }
}
