package service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManagersTest {

    @Test
    void testGetDefaultTaskManager() {
        // Тестируем, что метод getDefault возвращает экземпляр InMemoryTaskManager
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "TaskManager должен быть не null");
        assertTrue(taskManager instanceof InMemoryTaskManager, "Должен возвращаться экземпляр InMemoryTaskManager");
    }

    @Test
    void testGetDefaultHistoryManager() {
        // Тестируем, что метод getDefaultHistory возвращает экземпляр InMemoryHistoryManager
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "HistoryManager должен быть не null");
        assertTrue(historyManager instanceof InMemoryHistoryManager, "Должен возвращаться экземпляр InMemoryHistoryManager");
    }
}
