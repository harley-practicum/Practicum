package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        // Инициализация тестового объекта перед каждым тестом
        task = new Task(1, "Test Task", "This is a test description", Status.NEW);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a test description", task.getDescription());
        assertEquals(Status.NEW, task.getStatus());
    }

    @Test
    void testSetters() {
        task.setTitle("Updated Task");
        task.setDescription("Updated description");
        task.setStatus(Status.IN_PROGRESS);

        assertEquals("Updated Task", task.getTitle());
        assertEquals("Updated description", task.getDescription());
        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }

    @Test
    void testEquals() {
        Task task2 = new Task(1, "Another Task", "Different description", Status.NEW);
        Task task3 = new Task(2, "Another Task", "Different description", Status.NEW);

        assertEquals(task, task2); // Ожидаем, что объекты с одинаковым id равны
        assertNotEquals(task, task3); // Ожидаем, что объекты с разными id не равны
    }

    @Test
    void testHashCode() {
        // Теперь ожидаем, что hashCode будет равен, если id равны
        Task task2 = new Task(1, "Another Task", "Different description", Status.NEW);
        assertEquals(task.hashCode(), task2.hashCode()); // Ожидаем одинаковые хеш-коды для равных объектов
    }

    @Test
    void testToString() {
        String expectedString = "Task{id=1, title='Test Task', description='This is a test description', status=NEW}";
        assertEquals(expectedString, task.toString()); // Проверяем правильность вывода метода toString
    }
}

