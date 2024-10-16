package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    private Epic epic;

    @BeforeEach
    void setUp() {
        // Инициализация эпика перед каждым тестом
        epic = new Epic(1, "Epic Task", "Description of epic", Status.NEW);
    }

    @Test
    void testAddSubtask() {
        // Создаём экземпляр эпика
        Epic epic = new Epic(1, "Epic 1", "Description of epic 1", Status.NEW);

        // Создаём подзадачу с параметрами, включая epicId
        Subtask subtask = new Subtask(1, "Subtask 1", "Description of subtask 1", Status.NEW, epic.getId());

        // Добавляем подзадачу в эпик
        epic.addSubtask(subtask);

        // Проверяем, что подзадача была добавлена
        List<Integer> subtaskIds = epic.getSubtaskIds();
        assertEquals(1, subtaskIds.size());
        assertEquals(1, subtaskIds.get(0));
    }

    @Test
    void testRemoveSubtask() {
        // Создаём эпик
        Epic epic = new Epic(1, "Epic 1", "Description of epic 1", Status.NEW);

        // Создаём подзадачи с передачей epicId
        Subtask subtask1 = new Subtask(1, "Subtask 1", "Description of subtask 1", Status.NEW, epic.getId());
        Subtask subtask2 = new Subtask(2, "Subtask 2", "Description of subtask 2", Status.NEW, epic.getId());

        // Добавляем подзадачи в эпик
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);

        // Удаляем подзадачу и проверяем, что она удалена
        epic.removeSubtask(1);
        List<Integer> subtaskIds = epic.getSubtaskIds();

        // Проверяем, что осталась только подзадача 2
        assertEquals(1, subtaskIds.size());  // Проверяем количество оставшихся подзадач
        assertEquals(2, subtaskIds.get(0));  // Проверяем, что осталась только подзадача 2
    }


    @Test
    void testUpdateEpicStatus() {
        // Создаём эпик
        Epic epic = new Epic(1, "Epic 1", "Description of epic 1", Status.NEW);

        // Создаём подзадачи с передачей epicId
        Subtask subtask1 = new Subtask(1, "Subtask 1", "Description of subtask 1", Status.NEW, epic.getId());
        Subtask subtask2 = new Subtask(2, "Subtask 2", "Description of subtask 2", Status.IN_PROGRESS, epic.getId()); // Статус IN_PROGRESS

        // Добавляем подзадачи в эпик
        epic.addSubtask(subtask1);
        epic.addSubtask(subtask2);

        // Обновляем статус эпика
        epic.updateEpicStatus();

        // Проверяем, что статус эпика обновился до IN_PROGRESS
        assertEquals(Status.IN_PROGRESS, epic.getStatus()); // Ожидаем, что статус будет IN_PROGRESS
    }


    @Test
    void testEquals() {
        Epic epic2 = new Epic(1, "Epic Task", "Description of epic", Status.NEW);
        assertEquals(epic, epic2); // Ожидаем, что два эпика с одинаковыми id равны
    }

    @Test
    void testHashCode() {
        Epic epic2 = new Epic(1, "Epic Task", "Description of epic", Status.NEW);
        assertEquals(epic.hashCode(), epic2.hashCode()); // Ожидаем одинаковые хеш-коды для равных объектов
    }

    @Test
    void testToString() {
        String expectedString = "Epic{id=1, title='Epic Task', description='Description of epic', status=NEW, subtasks=[]}";
        assertEquals(expectedString, epic.toString()); // Проверяем правильность вывода метода toString
    }
}
