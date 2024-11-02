package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EpicTest {

    private Epic epic;

    @BeforeEach
    void setUp() {
        // Инициализируем объект Epic перед каждым тестом
        epic = new Epic(1, "Epic Title", "Epic Description", Status.NEW);
    }

    @Test
    void testAddSubtask() {
        Subtask subtask = new Subtask(2, "Subtask Title", "Subtask Description", Status.NEW, 1);
        epic.addSubtask(subtask);

        List<Subtask> subtasks = epic.getSubtasks();
        assertEquals(1, subtasks.size(), "Subtask list should contain 1 subtask after addition.");
        assertEquals(subtask, subtasks.get(0), "The added subtask should be the same as the one retrieved.");
    }

    @Test
    void testRemoveSubtask() {
        Subtask subtask1 = new Subtask(2, "Subtask 1", "Description 1", Status.NEW, 1);
        Subtask subtask2 = new Subtask(3, "Subtask 2", "Description 2", Status.NEW, 1);
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);

        epic.removeSubtask(2); // Удаляем подзадачу с ID 2

        List<Subtask> subtasks = epic.getSubtasks();
        assertEquals(1, subtasks.size(), "Subtask list should contain 1 subtask after removal.");
        assertEquals(subtask2, subtasks.get(0), "The remaining subtask should be the one with ID 3.");
    }

    @Test
    void testRemoveNonExistentSubtask() {
        Subtask subtask = new Subtask(2, "Subtask Title", "Subtask Description", Status.NEW, 1);
        epic.addSubtask(subtask);

        epic.removeSubtask(3); // Пытаемся удалить несуществующую подзадачу с ID 3

        List<Subtask> subtasks = epic.getSubtasks();
        assertEquals(1, subtasks.size(), "Subtask list should remain unchanged when trying to remove non-existent subtask.");
    }

    @Test
    void testEqualsAndHashCode() {
        Epic epic2 = new Epic(1, "Epic Title", "Epic Description", Status.NEW);
        epic2.addSubtask(new Subtask(2, "Subtask Title", "Subtask Description", Status.NEW, 1));

        assertNotEquals(epic, epic2, "Epics should not be equal as epic2 has no subtasks.");

        epic.addSubtask(new Subtask(2, "Subtask Title", "Subtask Description", Status.NEW, 1));

        assertEquals(epic, epic2, "Epics should be equal as they have the same properties.");
        assertEquals(epic.hashCode(), epic2.hashCode(), "Epics should have the same hash code when they are equal.");
    }

    @Test
    void testToString() {
        String expected = "Epic{id=1, title='Epic Title', description='Epic Description', status=NEW, subtasks=[]}";
        assertEquals(expected, epic.toString(), "toString() should return the correct string representation.");
    }
}
