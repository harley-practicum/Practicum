import model.Task;
import model.Subtask;
import model.Epic;
import model.TaskStatus;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task = new Task("Task 1", "Description 1", 1, TaskStatus.NEW);
        taskManager.addTask(task);

        Epic epic = new Epic("Epic 1", "Epic Description 1", 1, TaskStatus.NEW);
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask 1", "Subtask Description 1", 1, TaskStatus.NEW, 1);
        taskManager.addSubtask(subtask);

        System.out.println(taskManager.getTasks());
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());
    }
}


