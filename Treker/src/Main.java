import model.*;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Создание и добавление задачи
        Task task1 = new Task("Закупки", "Купить продукты для ужина");
        taskManager.createTask(task1);
        System.out.println("Создана задача: " + task1);

        // Обновление задачи
        task1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1);
        System.out.println("Обновленная задача: " + task1);

        // Создание и добавление эпика
        Epic epic = new Epic("Переезд", "Организовать переезд в новую квартиру");
        taskManager.createEpic(epic);
        System.out.println("Создан эпик: " + epic);

        // Создание и добавление подзадач для эпика
        Subtask subtask1 = new Subtask("Упаковка вещей", "Упаковать все вещи в коробки", epic.getId());
        taskManager.createSubtask(subtask1);
        System.out.println("Создана подзадача: " + subtask1);

        Subtask subtask2 = new Subtask("Аренда грузовика", "Арендовать грузовик для переезда", epic.getId());
        taskManager.createSubtask(subtask2);
        System.out.println("Создана подзадача: " + subtask2);

        // Обновление статуса подзадачи
        subtask1.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);
        System.out.println("Обновленная подзадача: " + subtask1);

        // Проверка статуса эпика после обновления подзадач
        System.out.println("Эпик после обновления подзадач: " + epic);

        // Удаление подзадачи
        taskManager.deleteSubtaskById(subtask1.getId());
        System.out.println("Подзадача удалена. Эпик после удаления подзадачи: " + epic);

        // Удаление всех задач
        taskManager.deleteTasks();
        System.out.println("Все задачи удалены.");
    }
}
