import service.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        int taskId1 = manager.addNewTask(task1);

        Task task2 = new Task("Задача 2", "Описание задачи 2");
        int taskId2 = manager.addNewTask(task2);

        // Создание эпика
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
        int epicId1 = manager.addNewEpic(epic1);

        // Создание подзадач
        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epicId1);
        int subtaskId1 = manager.addNewSubtask(subtask1);

        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epicId1);
        int subtaskId2 = manager.addNewSubtask(subtask2);

        // Обновление статуса задачи
        task1.setStatus(TaskStatus.DONE);
        manager.updateTask(task1);

        // Обновление статуса подзадачи
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        // Вывод списка задач
        System.out.println("Список задач:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }

        // Вывод списка эпиков
        System.out.println("Список эпиков:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
            System.out.println("Подзадачи эпика:");
            for (Subtask subtask : manager.getEpicSubtasks(epic.getId())) {
                System.out.println(subtask);
            }
        }
    }
}


