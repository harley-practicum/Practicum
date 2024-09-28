import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        taskManager.createEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic.getId());
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epic.getId());
        taskManager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Переезд", "Перевезти вещи в новую квартиру", epic.getId());
        taskManager.createSubtask(subtask3);

        // Обновление статуса подзадачи
        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);

        // Вывод информации о задачах и подзадачах
        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(taskManager.getAllEpics());

        // Удаление всех подзадач
        taskManager.deleteSubtasks();
        System.out.println("Подзадачи после удаления:");
        System.out.println(taskManager.getAllSubtasks());
    }
}
