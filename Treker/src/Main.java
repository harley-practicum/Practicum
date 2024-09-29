import model.Epic;
import model.Subtask;
import model.Task;
import service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Задача 1", "Описание задачи 1");
        manager.createTask(task1);

        Epic epic = new Epic("Эпик 1", "Описание эпика 1");
        manager.createEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", epic.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", epic.getId());
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        subtask1.setStatus(model.TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        System.out.println(manager.getEpicById(epic.getId()));
    }
}
