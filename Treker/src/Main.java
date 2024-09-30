import service.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Task 1", "Описание задачи 1");
        Task task2 = new Task("Task 2", "Описание задачи 2");
        int taskId1 = taskManager.addNewTask(task1);
        int taskId2 = taskManager.addNewTask(task2);

        // Создание эпика
        Epic epic = new Epic("Эпик 1", "Описание эпика");
        int epicId = taskManager.addNewEpic(epic);

        // Создание подзадач
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epicId);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epicId);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        // Обновление статуса подзадачи
        subtask1.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);

        // Вывод всех задач
        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println("Название: " + task.getTitle() + ", Описание: " + task.getDescription());
        }

        // Вывод всех эпиков
        System.out.println("Эпики:");
        for (Epic e : taskManager.getEpics()) {
            System.out.println("Название: " + e.getTitle() + ", Описание: " + e.getDescription());
        }

        // Вывод подзадач для конкретного эпика
        System.out.println("Подзадачи для эпика:");
        for (Subtask subtask : taskManager.getEpicSubtasks(epicId)) {
            System.out.println("Название: " + subtask.getTitle() + ", Описание: " + subtask.getDescription());
        }
    }
}


